package be.janschraepen.hellokitty.web.controller;

import be.janschraepen.hellokitty.domain.cat.CatDTO;
import be.janschraepen.hellokitty.domain.cat.CatPersonDTO;
import be.janschraepen.hellokitty.domain.cat.Gender;
import be.janschraepen.hellokitty.domain.cat.ObjectFactory;
import be.janschraepen.hellokitty.services.CatService;
import be.janschraepen.hellokitty.services.PersonService;
import be.janschraepen.hellokitty.services.PersonTypeService;
import be.janschraepen.hellokitty.web.Event;
import be.janschraepen.hellokitty.web.RequestParameter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

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
        return list(request, VIEW_LIST, TITLE, DESCRIPTION, catService.findAllCats());
    }

    @Override
    @RequestMapping("/cat/edit")
    public ModelAndView doEvent(@RequestParam String _event, HttpServletRequest request) {
        ModelAndView mv = super.doEvent(_event, request);
        switch (_event) {
            case Event.ADD_PERSON:
                mv = doSavePerson(request);
                break;
        }
        return mv;
    }

    @Override
    public ModelAndView doSearch(HttpServletRequest request) {
        String searchFor = request.getParameter(RequestParameter.SEARCH);
        return list(request, VIEW_LIST, TITLE, DESCRIPTION, catService.findCats(searchFor));
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

        return detail(request, VIEW_EDIT, title, DESCRIPTION, cat);
    }

    @Override
    public ModelAndView doSave(HttpServletRequest request) {
        String uuid = request.getParameter(RequestParameter.UUID);
        String name = request.getParameter(RequestParameter.NAME);
        String breed = request.getParameter(RequestParameter.BREED);
        String age = request.getParameter(RequestParameter.AGE);
        Gender gender = Gender.valueOf(request.getParameter(RequestParameter.GENDER));
        boolean neutered = Boolean.valueOf(request.getParameter(RequestParameter.NEUTERED));
        boolean chipped = Boolean.valueOf(request.getParameter(RequestParameter.CHIPPED));
        String attention = request.getParameter(RequestParameter.ATTENTION);
        String behavioral = request.getParameter(RequestParameter.BEHAVIORAL);
        String nutrition = request.getParameter(RequestParameter.NUTRITION);

        CatDTO cat = ObjectFactory.getInstance().createCatDTO(uuid, name, breed, age, gender, neutered, chipped, attention, behavioral, nutrition);
        catService.saveCat(cat);

        String title = cat.getName();
        return detail(request, VIEW_EDIT, title, DESCRIPTION, cat);
    }

    @Override
    public ModelAndView doDelete(HttpServletRequest request) {
        String[] uuids = request.getParameterValues(RequestParameter.UUID);

        catService.deleteCats(uuids);
        return list(request, VIEW_LIST, TITLE, DESCRIPTION, catService.findAllCats());
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

        CatPersonDTO catPerson = ObjectFactory.getInstance().createCatPersonDTO(uuid, personType, person);
        catService.saveCatPerson(catPerson);

        ModelAndView mv = doOpenEdit(request);
        mv.getModel().put(RequestParameter.ACTIVE_TAB, 1);
        return mv;
    }

    @Override
    void addDetailModelParameters(ModelAndView mv) {
        mv.getModel().put(RequestParameter.PERSONTYPES, personTypeService.findAllPersonTypes());
        mv.getModel().put(RequestParameter.PERSONS, personService.findAllPersons());
    }

}
