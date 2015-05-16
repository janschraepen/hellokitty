package be.janschraepen.hellokitty.web.controller;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

/**
 * AbstractController class. Used as base class for all existing
 * Controllers in the project.
 */
public abstract class AbstractController<T> implements MessageSourceAware {

    static final Locale nl_BE = new Locale("nl", "BE");

    static final String PARAM_ACTION_URL = "actionUrl";
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

    protected MessageSource messageSource;

    /**
     * list entities of type <T>.
     *
     * @param request the servlet request
     * @return ModelAndView model and view
     */
    public abstract ModelAndView list(HttpServletRequest request);

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
                mv = list(request);
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
     * get the actionUrl for form submission.
     *
     * @param request the servlet request
     * @return String the event url
     */
    public String getActionUrl(HttpServletRequest request) {
        String eventUrl = request.getScheme();
        eventUrl += "://";
        eventUrl += request.getServerName();
        eventUrl += ":";
        eventUrl += request.getServerPort();
        eventUrl += request.getContextPath();
        return eventUrl;
    }

    /**
     * list entities of type <T>.
     *
     * @param request     the servlet request
     * @param viewName    the viewName
     * @param title       the title
     * @param description the description
     * @param entities    the entities
     * @return ModelAndView model and view
     */
    ModelAndView list(HttpServletRequest request, String viewName, String title, String description, List<T> entities) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(viewName);
        mv.getModel().put(PARAM_ACTION_URL, getActionUrl(request));
        mv.getModel().put(PARAM_TITLE, title);
        mv.getModel().put(PARAM_DESCRIPTION, description);
        mv.getModel().put(PARAM_LIST_ITEMS, entities);
        return mv;
    }

    /**
     * detail of an entity T.
     *
     * @param request     the servlet request
     * @param viewName    the viewName
     * @param title       the title
     * @param description the description
     * @param entity      the entity
     * @return ModelAndView model and view
     */
    ModelAndView detail(HttpServletRequest request, String viewName, String title, String description, T entity) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(viewName);
        mv.getModel().put(PARAM_ACTION_URL, getActionUrl(request));
        mv.getModel().put(PARAM_TITLE, title);
        mv.getModel().put(PARAM_DESCRIPTION, description);
        mv.getModel().put(PARAM_ENTITY, entity);
        return mv;
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

}
