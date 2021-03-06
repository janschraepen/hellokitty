package be.janschraepen.hellokitty.web.controller;

import be.janschraepen.hellokitty.domain.cat.*;
import be.janschraepen.hellokitty.services.CatService;
import be.janschraepen.hellokitty.services.PersonService;
import be.janschraepen.hellokitty.services.PersonTypeService;
import be.janschraepen.hellokitty.web.Event;
import be.janschraepen.hellokitty.web.RequestAttribute;
import be.janschraepen.hellokitty.web.RequestParameter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.io.IOException;

/**
 * CatController class. Used for mapping request
 * to Cat list/detail/etc..
 */
@Controller
public class CatController extends AbstractController<CatDTO> {

    static final String TITLE = "cat.list.title";
    static final String DESCRIPTION = "cat.list.description";
    static final String TITLE_NEW = "cat.detail.new";

    static final String VIEW_LIST = "cat/list";
    static final String VIEW_EDIT = "cat/edit";

    @Autowired
    private CatService catService;

    @Autowired
    private PersonTypeService personTypeService;

    @Autowired
    private PersonService personService;

    @Override
    @RequestMapping("/cat/list")
    public ModelAndView list(HttpServletRequest request) {
        return list(request, "/cat/list", VIEW_LIST, TITLE, DESCRIPTION, catService.findAllCats());
    }

    @Override
    @RequestMapping("/cat/edit")
    public ModelAndView doEvent(@RequestParam String _event, @RequestParam String _referer, HttpServletRequest request) {
        ModelAndView mv = super.doEvent(_event, _referer, request);
        switch (_event) {
            case Event.ADD_PERSON:
                mv = doSavePerson(request);
                break;
            case Event.DELETE_PERSON:
                mv = doDeletePerson(request);
                break;
        }
        return mv;
    }

    @Override
    public ModelAndView doSearch(HttpServletRequest request) {
        String searchFor = request.getParameter(RequestParameter.SEARCH);
        String path = "/cat/edit?_event=search&search=" + searchFor;
        return list(request, path, VIEW_LIST, TITLE, DESCRIPTION, catService.findCats(searchFor));
    }

    @Override
    public ModelAndView doOpenEdit(HttpServletRequest request) {
        String uuid = request.getParameter(RequestParameter.UUID);

        CatDTO cat;
        String title;
        if (StringUtils.isBlank(uuid)) {
            cat = new CatDTO();
            title = messageSource.getMessage(TITLE_NEW, null, nl_BE);
        } else {
            cat = catService.findCat(uuid);
            title = cat.getName();
        }

        String path = "/cat/edit?_event=edit&uuid=" + uuid;
        return detail(request, path, VIEW_EDIT, title, DESCRIPTION, cat);
    }

    @Override
    public ModelAndView doSave(HttpServletRequest request) {
        String uuid = request.getParameter(RequestParameter.UUID);
        String name = request.getParameter(RequestParameter.NAME);
        String breed = request.getParameter(RequestParameter.BREED);
        String age = request.getParameter(RequestParameter.AGE);
        String gender = request.getParameter(RequestParameter.GENDER);
        boolean neutered = Boolean.valueOf(request.getParameter(RequestParameter.NEUTERED));
        boolean chipped = Boolean.valueOf(request.getParameter(RequestParameter.CHIPPED));
        String attention = request.getParameter(RequestParameter.ATTENTION);
        String behavioral = request.getParameter(RequestParameter.BEHAVIORAL);
        String nutrition = request.getParameter(RequestParameter.NUTRITION);
        String extraInfo = request.getParameter(RequestParameter.EXTRA_INFO);

        try {
            CatDTO cat = ObjectFactory.getInstance().createCatDTO(uuid, name, breed, age, Gender.valueOf(gender), neutered, chipped, attention, behavioral, nutrition, extraInfo);
            cat = catService.saveCat(cat);

            String title = cat.getName();
            String path = "/cat/edit?_event=edit&uuid=" + uuid;
            return detail(request, path, VIEW_EDIT, title, DESCRIPTION, cat);
        } catch (NullPointerException e) {
            request.setAttribute(RequestAttribute.ERROR_MSG, messageSource.getMessage("error.cat.invalid.gender", null, nl_BE));
            return doOpenEdit(request);
        } catch (ConstraintViolationException e) {
            request.setAttribute(RequestAttribute.ERROR_MSG, handleConstraintViolations(e));
            return doOpenEdit(request);
        }
    }

    @Override
    public ModelAndView doDelete(HttpServletRequest request) {
        String[] uuids = request.getParameterValues(RequestParameter.UUID);

        if (uuids != null) {
            catService.deleteCats(uuids);
        }

        return list(request, "/cat/list", VIEW_LIST, TITLE, DESCRIPTION, catService.findAllCats());
    }

    /**
     * delete entity CatPerson.
     *
     * @param request the servlet request
     * @return ModelAndView model and view
     */
    public ModelAndView doDeletePerson(HttpServletRequest request) {
        String[] uuids = request.getParameterValues(RequestParameter.PERSON_UUID);

        if (uuids != null) {
            catService.deleteCatPersons(uuids);
        }

        ModelAndView mv = doOpenEdit(request);
        mv.getModel().put(RequestParameter.ACTIVE_TAB, 1);
        return mv;
    }

    /**
     * save entity CatPerson
     *
     * @param request the servlet request
     * @return ModelAndView model and view
     */
    public ModelAndView doSavePerson(HttpServletRequest request) {
        String uuid = request.getParameter(RequestParameter.UUID);
        String personType = request.getParameter(RequestParameter.PERSON_TYPE);
        String person = request.getParameter(RequestParameter.PERSON);

        try {
            CatPersonDTO catPerson = ObjectFactory.getInstance().createCatPersonDTO(uuid, personType, person);
            catService.saveCatPerson(catPerson);
        } catch (DataIntegrityViolationException e) {
            request.setAttribute(RequestAttribute.ERROR_MSG, messageSource.getMessage("error.catPerson.invalid.personTypeOrPerson", null, nl_BE));
        }

        ModelAndView mv = doOpenEdit(request);
        mv.getModel().put(RequestParameter.ACTIVE_TAB, 1);
        return mv;
    }

    /**
     * upload picture for entity.
     *
     * @param request the servlet request
     * @param file    the MultiPart file
     * @return ModelAndView model and view
     */
    @RequestMapping("/cat/upload")
    public ModelAndView doSavePicture(HttpServletRequest request, @RequestParam("picture") MultipartFile file) throws IOException {
        String uuid = request.getParameter(RequestParameter.UUID);

        CatPictureDTO dto = ObjectFactory.getInstance().createCatPictureDTO(uuid, file.getBytes(), file.getSize(), file.getContentType());
        catService.updateCatPicture(dto);

        ModelAndView mv = doOpenEdit(request);
        mv.getModel().put(RequestParameter.ACTIVE_TAB, 2);
        return mv;
    }

    @Override
    void addDetailModelParameters(ModelAndView mv) {
        mv.getModel().put(RequestParameter.PERSONTYPES, personTypeService.findAllPersonTypes());
        mv.getModel().put(RequestParameter.PERSONS, personService.findAllPersons());
    }

}
