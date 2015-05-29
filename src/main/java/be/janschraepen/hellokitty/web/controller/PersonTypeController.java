package be.janschraepen.hellokitty.web.controller;

import be.janschraepen.hellokitty.domain.persontype.CannotModifyPersonTypeException;
import be.janschraepen.hellokitty.domain.persontype.ObjectFactory;
import be.janschraepen.hellokitty.domain.persontype.PersonTypeDTO;
import be.janschraepen.hellokitty.services.PersonTypeService;
import be.janschraepen.hellokitty.web.RequestAttribute;
import be.janschraepen.hellokitty.web.RequestParameter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * PersonTypeController class. Used for mapping request
 * to PersonType list/detail/etc..
 */
@Controller
public class PersonTypeController extends AbstractController<PersonTypeDTO> {

    static final String TITLE = "persontype.list.title";
    static final String DESCRIPTION = "persontype.list.description";
    static final String TITLE_NEW = "persontype.detail.new";

    static final String VIEW_LIST = "persontype/list";
    static final String VIEW_EDIT = "persontype/edit";

    @Autowired
    private PersonTypeService personTypeService;

    @Override
    @RequestMapping("/person/type/list")
    public ModelAndView list(HttpServletRequest request) {
        return list(request, VIEW_LIST, TITLE, DESCRIPTION, personTypeService.findAllPersonTypes());
    }

    @Override
    @RequestMapping("/person/type/edit")
    public ModelAndView doEvent(@RequestParam String _event, HttpServletRequest request) {
        return super.doEvent(_event, request);
    }

    @Override
    public ModelAndView doSearch(HttpServletRequest request) {
        String searchFor = request.getParameter(RequestParameter.SEARCH);
        return list(request, VIEW_LIST, TITLE, DESCRIPTION, personTypeService.findPersonTypes(searchFor));
    }

    @Override
    public ModelAndView doOpenEdit(HttpServletRequest request) {
        String uuid = request.getParameter(RequestParameter.UUID);

        PersonTypeDTO personType;
        String title;
        if (StringUtils.isBlank(uuid)) {
            personType = new PersonTypeDTO();
            title = messageSource.getMessage(TITLE_NEW, null, nl_BE);
        } else {
            personType = personTypeService.findPersonType(uuid);
            title = personType.getShortCode() + " - " + personType.getName();
        }

        return detail(request, VIEW_EDIT, title, DESCRIPTION, personType);
    }

    @Override
    public ModelAndView doSave(HttpServletRequest request) {
        String uuid = request.getParameter(RequestParameter.UUID);
        String shortCode = request.getParameter(RequestParameter.SHORT_CODE);
        String name = request.getParameter(RequestParameter.NAME);

        PersonTypeDTO personType = ObjectFactory.getInstance().createDTO(uuid, shortCode, name);;
        String title;
        try {
            personTypeService.savePersonType(personType);

            title = personType.getShortCode() + " - " + personType.getName();
            return detail(request, VIEW_EDIT, title, DESCRIPTION, personType);
        } catch (CannotModifyPersonTypeException e) {
            request.setAttribute(RequestAttribute.ERROR_MSG, "Kan PersoonType Eigenaar, Contactpersoon en/of Dierenarts niet wijzigen!");
            return doOpenEdit(request);
        }
    }

    @Override
    public ModelAndView doDelete(HttpServletRequest request) {
        String[] uuids = request.getParameterValues(RequestParameter.UUID);

        try {
            personTypeService.deletePersonTypes(uuids);
        } catch (CannotModifyPersonTypeException e) {
            request.setAttribute(RequestAttribute.ERROR_MSG, "Kan PersoonType Eigenaar, Contactpersoon en/of Dierenarts niet wijzigen!");
        }
        return list(request, VIEW_LIST, TITLE, DESCRIPTION, personTypeService.findAllPersonTypes());
    }

}
