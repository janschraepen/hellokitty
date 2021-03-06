package be.janschraepen.hellokitty.web.controller;

import be.janschraepen.hellokitty.domain.person.ContactType;
import be.janschraepen.hellokitty.domain.person.ObjectFactory;
import be.janschraepen.hellokitty.domain.person.PersonContactDTO;
import be.janschraepen.hellokitty.domain.person.PersonDTO;
import be.janschraepen.hellokitty.services.PersonService;
import be.janschraepen.hellokitty.web.Event;
import be.janschraepen.hellokitty.web.RequestAttribute;
import be.janschraepen.hellokitty.web.RequestParameter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.Arrays;

/**
 * PersonController class. Used for mapping request
 * to Person list/detail/etc..
 */
@Controller
public class PersonController extends AbstractController<PersonDTO> {

    static final String TITLE = "person.list.title";
    static final String DESCRIPTION = "person.list.description";
    static final String TITLE_NEW = "person.detail.new";

    static final String VIEW_LIST = "person/list";
    static final String VIEW_EDIT = "person/edit";

    @Autowired
    private PersonService personService;

    @Override
    @RequestMapping("/person/list")
    public ModelAndView list(HttpServletRequest request) {
        return list(request, "/person/list", VIEW_LIST, TITLE, DESCRIPTION, personService.findAllPersons());
    }

    @Override
    @RequestMapping("/person/edit")
    public ModelAndView doEvent(@RequestParam String _event, @RequestParam String _referer, HttpServletRequest request) {
        ModelAndView mv = super.doEvent(_event, _referer, request);
        switch (_event) {
            case Event.DELETE_CONTACT:
                mv = doDeleteContact(request);
                break;
            case Event.ADD_CONTACT:
                mv = doSaveContact(request);
                break;
            case Event.EXPORT:
                mv = doExport(request);
                break;
        }
        return mv;
    }

    @Override
    public ModelAndView doSearch(HttpServletRequest request) {
        String searchFor = request.getParameter(RequestParameter.SEARCH);
        String path = "/person/edit?_event=search&search=" + searchFor;
        return list(request, path, VIEW_LIST, TITLE, DESCRIPTION, personService.findPersons(searchFor));
    }

    @Override
    public ModelAndView doOpenEdit(HttpServletRequest request) {
        String uuid = request.getParameter(RequestParameter.UUID);

        PersonDTO person;
        String title;
        if (StringUtils.isBlank(uuid)) {
            person = new PersonDTO();
            title = messageSource.getMessage(TITLE_NEW, null, nl_BE);
        } else {
            person = personService.findPerson(uuid);
            title = person.getFirstName() + " " + person.getLastName();
        }

        String path = "/person/edit?_event=edit&uuid=" + uuid;
        return detail(request, path, VIEW_EDIT, title, DESCRIPTION, person);
    }

    @Override
    public ModelAndView doSave(HttpServletRequest request) {
        String uuid = request.getParameter(RequestParameter.UUID);
        String firstName = request.getParameter(RequestParameter.FIRSTNAME);
        String lastName = request.getParameter(RequestParameter.LASTNAME);
        String addressLine1 = request.getParameter(RequestParameter.ADDRESSLINE1);
        String addressLine2 = request.getParameter(RequestParameter.ADDRESSLINE2);
        String extraInfo = request.getParameter(RequestParameter.EXTRA_INFO);

        try {
            PersonDTO person = ObjectFactory.getInstance().createPersonDTO(uuid, firstName, lastName, addressLine1, addressLine2, extraInfo);
            person = personService.savePerson(person);

            String title = person.getFirstName() + " " + person.getLastName();
            String path = "/person/edit?_event=edit&uuid=" + uuid;
            return detail(request, path, VIEW_EDIT, title, DESCRIPTION, person);
        } catch (ConstraintViolationException e) {
            request.setAttribute(RequestAttribute.ERROR_MSG, handleConstraintViolations(e));
            return doOpenEdit(request);
        }

    }

    @Override
    public ModelAndView doDelete(HttpServletRequest request) {
        String[] uuids = request.getParameterValues(RequestParameter.UUID);

        if (uuids != null) {
            personService.deletePersons(uuids);
        }

        return list(request, "/person/list", VIEW_LIST, TITLE, DESCRIPTION, personService.findAllPersons());
    }

    /**
     * delete entity PersonContact.
     *
     * @param request the servlet request
     * @return ModelAndView model and view
     */
    public ModelAndView doDeleteContact(HttpServletRequest request) {
        String[] uuids = request.getParameterValues(RequestParameter.CONTACT_UUID);

        if (uuids != null) {
            personService.deletePersonContacts(uuids);
        }

        ModelAndView mv = doOpenEdit(request);
        mv.getModel().put(RequestParameter.ACTIVE_TAB, 1);
        return mv;
    }

    /**
     * save entity PersonContact
     *
     * @param request the servlet request
     * @return ModelAndView model and view
     */
    public ModelAndView doSaveContact(HttpServletRequest request) {
        String uuid = request.getParameter(RequestParameter.UUID);
        String contactType = request.getParameter(RequestParameter.CONTACT_TYPE);
        String contactValue = request.getParameter(RequestParameter.CONTACT_VALUE);

        try {
            PersonContactDTO personContact = ObjectFactory.getInstance().createPersonContactDTO(uuid, ContactType.valueOf(contactType), contactValue);
            personService.savePersonContact(personContact);
        } catch (IllegalArgumentException e) {
            request.setAttribute(RequestAttribute.ERROR_MSG, messageSource.getMessage("error.personContact.invalid.contactType", null, nl_BE));
        } catch (ConstraintViolationException e) {
            request.setAttribute(RequestAttribute.ERROR_MSG, handleConstraintViolations(e));
        }
        ModelAndView mv = doOpenEdit(request);
        mv.getModel().put(RequestParameter.ACTIVE_TAB, 1);
        return mv;
    }

    /**
     * export email contacts.
     *
     * @param request the servlet request
     * @return ModelAndView model and view
     */
    public ModelAndView doExport(HttpServletRequest request) {
        String export = personService.findAllEmailContacts();

        ModelAndView mv = new ModelAndView();
        mv.setViewName("person/export");
        mv.getModel().put(RequestParameter.APP_VERSION, appVersion);
        mv.getModel().put(RequestParameter.EXPORT, export);
        return mv;
    }

    @Override
    void addDetailModelParameters(ModelAndView mv) {
        mv.getModel().put(RequestParameter.CONTACTTYPES, Arrays.asList(ContactType.values()));
    }

}
