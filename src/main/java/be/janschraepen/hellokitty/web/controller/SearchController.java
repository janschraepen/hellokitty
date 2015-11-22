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
import java.util.ArrayList;

/**
 * CatController class. Used for mapping request
 * to Cat list/detail/etc..
 */
@Controller
public class SearchController extends AbstractController<CatPersonDTO> {

    static final String TITLE = "search.list.title";
    static final String DESCRIPTION = "search.list.description";

    static final String VIEW_LIST = "search/list";

    @Autowired
    private CatService catService;

    @Override
    @RequestMapping("/search/list")
    public ModelAndView list(HttpServletRequest request) {
        return list(request, "/search/list", VIEW_LIST, TITLE, DESCRIPTION, new ArrayList<>());
    }

    @Override
    @RequestMapping("/search/edit")
    public ModelAndView doEvent(@RequestParam String _event, @RequestParam String _referer, HttpServletRequest request) {
        return super.doEvent(_event, _referer, request);
    }

    @Override
    public ModelAndView doSearch(HttpServletRequest request) {
        String searchFor = request.getParameter(RequestParameter.SEARCH);
        String path = "/search/edit?_event=search&search=" + searchFor;
        return list(request, path, VIEW_LIST, TITLE, DESCRIPTION, catService.findCatPersons(searchFor));
    }

    @Override
    public ModelAndView doOpenEdit(HttpServletRequest request) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ModelAndView doSave(HttpServletRequest request) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ModelAndView doDelete(HttpServletRequest request) {
        throw new UnsupportedOperationException();
    }

}
