package be.janschraepen.hellokitty.web.controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * AbstractController class. Used as base class for all existing
 * Controllers in the project.
 */
public abstract class AbstractController<T> {

    static final String PARAM_TITLE = "title";
    static final String PARAM_DESCRIPTION = "description";
    static final String PARAM_LIST_ITEMS = "listItems";
    static final String PARAM_ENTITY = "entity";

    static final String EVENT_NEW = "new";
    static final String EVENT_EDIT = "edit";
    static final String EVENT_SAVE = "save";
    static final String EVENT_DELETE = "delete";
    static final String EVENT_SEARCH = "search";
    static final String EVENT_BACK = "back";

    /**
     * list entities of type <T>.
     *
     * @return ModelAndView model and view
     */
    public abstract ModelAndView list();

    /**
     * do event on entity.
     *
     * @return ModelAndView model and view
     */
    public ModelAndView doEvent(@RequestParam String _event, HttpServletRequest request) {
        ModelAndView mv = null;
        switch (_event) {
            case EVENT_NEW:
                mv = doOpenEdit(request);
                break;
            case EVENT_EDIT:
                mv = doOpenEdit(request);
                break;
            case EVENT_SAVE:
                mv = doSave(request);
                break;
            case EVENT_DELETE:
                mv = doDelete(request);
                break;
            case EVENT_SEARCH:
                mv = doSearch(request);
                break;
            case EVENT_BACK:
                mv = list();
                break;
        }
        return mv;
    }

    /**
     * open edit entity <T>.
     *
     * @param request the servlet request
     * @return ModelAndView model and view
     */
    public abstract ModelAndView doOpenEdit(HttpServletRequest request);

    /**
     * save/update entity <T>.
     *
     * @param request the servlet request
     * @return ModelAndView model and view
     */
    public abstract ModelAndView doSave(HttpServletRequest request);

    /**
     * delete entity <T>.
     *
     * @param request the servlet request
     * @return ModelAndView model and view
     */
    public abstract ModelAndView doDelete(HttpServletRequest request);

    /**
     * open edit personType.
     *
     * @param request the servlet request
     * @return ModelAndView model and view
     */
    public abstract ModelAndView doSearch(HttpServletRequest request);

    /**
     * list entities of type <T>.
     *
     * @param viewName    the viewName
     * @param title       the title
     * @param description the description
     * @param entities    the entities
     * @return ModelAndView model and view
     */
    ModelAndView list(String viewName, String title, String description, List<T> entities) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(viewName);
        mv.getModel().put(PARAM_TITLE, title);
        mv.getModel().put(PARAM_DESCRIPTION, description);
        mv.getModel().put(PARAM_LIST_ITEMS, entities);
        return mv;
    }

    /**
     * detail of an entity T.
     *
     * @param viewName    the viewName
     * @param title       the title
     * @param description the description
     * @param entity      the entity
     * @return ModelAndView model and view
     */
    ModelAndView detail(String viewName, String title, String description, T entity) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(viewName);
        mv.getModel().put(PARAM_TITLE, title);
        mv.getModel().put(PARAM_DESCRIPTION, description);
        mv.getModel().put(PARAM_ENTITY, entity);
        return mv;
    }

}
