package be.janschraepen.hellokitty.web.controller;

import be.janschraepen.hellokitty.domain.person.ObjectFactory;
import be.janschraepen.hellokitty.domain.person.PersonDTO;
import be.janschraepen.hellokitty.domain.persontype.PersonTypeDTO;
import be.janschraepen.hellokitty.services.PersonService;
import be.janschraepen.hellokitty.services.PersonTypeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    static final String PARAM_PERSONTYPES = "personTypes";

    static final String PARAM_SEARCH = "search";
    static final String PARAM_UUID = "uuid";
    static final String PARAM_PERSON_TYPE_ID = "personTypeId";
    static final String PARAM_FIRSTNAME = "firstName";
    static final String PARAM_LASTNAME = "lastName";
    static final String PARAM_ADDRESSLINE1 = "addressLine1";
    static final String PARAM_ADDRESSLINE2 = "addressLine2";
    static final String PARAM_TELEPHONE = "telephone";
    static final String PARAM_GSM = "gsm";
    static final String PARAM_EMAIL = "email";

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonTypeService personTypeService;

    @Override
    @RequestMapping("/person/list")
    public ModelAndView list(HttpServletRequest request) {
        return list(request, VIEW_LIST, TITLE, DESCRIPTION, personService.findAllPersons());
    }

    @Override
    @RequestMapping("/person/edit")
    public ModelAndView doEvent(@RequestParam String _event, HttpServletRequest request) {
        return super.doEvent(_event, request);
    }

    @Override
    public ModelAndView doSearch(HttpServletRequest request) {
        String searchFor = request.getParameter(PARAM_SEARCH);
        return list(request, VIEW_LIST, TITLE, DESCRIPTION, personService.findPersons(searchFor));
    }

    @Override
    public ModelAndView doOpenEdit(HttpServletRequest request) {
        String uuid = request.getParameter(PARAM_UUID);

        PersonDTO person;
        String title;
        if (StringUtils.isBlank(uuid)) {
            person = new PersonDTO();
            title = messageSource.getMessage(TITLE_NEW, null, nl_BE);
        } else {
            person = personService.findPerson(uuid);
            title = person.getFirstName() + " - " + person.getLastName();
        }

        return detail(request, VIEW_EDIT, title, DESCRIPTION, person);
    }

    @Override
    public ModelAndView doSave(HttpServletRequest request) {
        String uuid = request.getParameter(PARAM_UUID);
        String personTypeId = request.getParameter(PARAM_PERSON_TYPE_ID);
        String firstName = request.getParameter(PARAM_FIRSTNAME);
        String lastName = request.getParameter(PARAM_LASTNAME);
        String addressLine1 = request.getParameter(PARAM_ADDRESSLINE1);
        String addressLine2 = request.getParameter(PARAM_ADDRESSLINE2);
        String telephone = request.getParameter(PARAM_TELEPHONE);
        String gsm = request.getParameter(PARAM_GSM);
        String email = request.getParameter(PARAM_EMAIL);

        PersonDTO person = ObjectFactory.getInstance().createDTO(uuid, personTypeId, firstName, lastName, addressLine1, addressLine2, telephone, gsm, email);
        personService.savePerson(person);

        String title = person.getFirstName() + " - " + person.getLastName();
        return detail(request, VIEW_EDIT, title, DESCRIPTION, person);
    }

    @Override
    public ModelAndView doDelete(HttpServletRequest request) {
        String uuid = request.getParameter(PARAM_UUID);

        personService.deletePerson(uuid);
        return list(request, VIEW_LIST, TITLE, DESCRIPTION, personService.findAllPersons());
    }

    @Override
    void addDetailModelParameters(ModelAndView mv) {
        mv.getModel().put(PARAM_PERSONTYPES, personTypeService.findAllPersonTypes());
    }

}
