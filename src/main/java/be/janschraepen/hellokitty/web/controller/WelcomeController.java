package be.janschraepen.hellokitty.web.controller;

import be.janschraepen.hellokitty.domain.persontype.PersonTypeDTO;
import be.janschraepen.hellokitty.web.RequestParameter;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * WelcomeController class. Used for mapping request
 * to the Welcome page.
 */
@Controller
public class WelcomeController extends AbstractController<PersonTypeDTO> {

    @RequestMapping("/welcome")
    public ModelAndView show(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("welcome/index");
        mv.getModel().put(RequestParameter.APP_VERSION, appVersion);
        return mv;
    }

    @Override
    public ModelAndView doSearch(HttpServletRequest request) {
        throw new NotImplementedException("nothing to search");
    }

    @Override
    public ModelAndView list(HttpServletRequest request) {
        throw new NotImplementedException("nothing to list");
    }

    @Override
    public ModelAndView doOpenEdit(HttpServletRequest request) {
        throw new NotImplementedException("nothing to edit");
    }

    @Override
    public ModelAndView doSave(HttpServletRequest request) {
        throw new NotImplementedException("nothing to save");
    }

    @Override
    public ModelAndView doDelete(HttpServletRequest request) {
        throw new NotImplementedException("nothing to delete");
    }

}
