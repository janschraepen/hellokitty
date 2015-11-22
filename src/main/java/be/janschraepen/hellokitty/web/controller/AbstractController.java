package be.janschraepen.hellokitty.web.controller;

import be.janschraepen.hellokitty.util.PageRefererManager;
import be.janschraepen.hellokitty.web.Event;
import be.janschraepen.hellokitty.web.RequestParameter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * AbstractController class. Used as base class for all existing
 * Controllers in the project.
 */
public abstract class AbstractController<T> implements MessageSourceAware {

    static final Locale nl_BE = new Locale("nl", "BE");

    @Value("#{version.number}")
    protected String appVersion;

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
     * @param _event the event
     * @param _referer the referer
     * @param request the servlet request
     * @return ModelAndView model and view
     */
    public ModelAndView doEvent(@RequestParam String _event, @RequestParam String _referer, HttpServletRequest request) {
        ModelAndView mv = null;
        switch (_event) {
            case Event.NEW:
                mv = doOpenEdit(request);
                break;
            case Event.EDIT:
                mv = doOpenEdit(request);
                break;
            case Event.SAVE:
                mv = doSave(request);
                break;
            case Event.DELETE:
                mv = doDelete(request);
                break;
            case Event.SEARCH:
                mv = doSearch(request);
                break;
            case Event.BACK:
                return new ModelAndView("redirect:" + PageRefererManager.getPrevious(request));
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
     * get the refererUrl for back navigation.
     *
     * @param request     the servlet request
     * @param refererPath the referer path
     * @return String the referer url
     */
    String getRefererUrl(HttpServletRequest request, String refererPath) {
        String refererUrl = getActionUrl(request);
        refererUrl += refererPath;
        return refererUrl;
    }

    /**
     * get the actionUrl for form submission.
     *
     * @param request the servlet request
     * @return String the event url
     */
    String getActionUrl(HttpServletRequest request) {
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
     * @param path        the referer path
     * @param viewName    the viewName
     * @param title       the title
     * @param description the description
     * @param entities    the entities
     * @return ModelAndView model and view
     */
    ModelAndView list(HttpServletRequest request, String path, String viewName, String title, String description, List<T> entities) {
        PageRefererManager.update(request, getRefererUrl(request, path));

        ModelAndView mv = new ModelAndView();
        mv.setViewName(viewName);
        mv.getModel().put(RequestParameter.APP_VERSION, appVersion);
        mv.getModel().put(RequestParameter.ACTION_URL, getActionUrl(request));
        mv.getModel().put(RequestParameter.REFERER_PATH, path);
        mv.getModel().put(RequestParameter.TITLE, title);
        mv.getModel().put(RequestParameter.DESCRIPTION, description);
        mv.getModel().put(RequestParameter.LIST_ITEMS, entities);
        return mv;
    }

    /**
     * detail of an entity T.
     *
     * @param request     the servlet request
     * @param path        the referer path
     * @param viewName    the viewName
     * @param title       the title
     * @param description the description
     * @param entity      the entity
     * @return ModelAndView model and view
     */
    ModelAndView detail(HttpServletRequest request, String path, String viewName, String title, String description, T entity) {
        PageRefererManager.update(request, getRefererUrl(request, path));

        ModelAndView mv = new ModelAndView();
        mv.setViewName(viewName);
        mv.getModel().put(RequestParameter.APP_VERSION, appVersion);
        mv.getModel().put(RequestParameter.ACTION_URL, getActionUrl(request));
        mv.getModel().put(RequestParameter.REFERER_PATH, path);
        mv.getModel().put(RequestParameter.TITLE, title);
        mv.getModel().put(RequestParameter.DESCRIPTION, description);
        mv.getModel().put(RequestParameter.ENTITY, entity);
        addDetailModelParameters(mv);
        return mv;
    }

    /**
     * Add additional Model params to the ModelAndView for detail page.
     *
     * @param mv model and view
     */
    void addDetailModelParameters(ModelAndView mv) {
        // override when needed to pass along additional model paramters
    }

    /**
     * Handle ConstraintViolationExceptions by building a list of violations
     *
     * @param e the ConstraintViolationException
     * @return String a html formatted list of violations
     */
    String handleConstraintViolations(ConstraintViolationException e) {
        ConstraintViolationException exception = (ConstraintViolationException) e;

        StringBuilder builder = new StringBuilder("<ul>");
        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        for (ConstraintViolation violation : violations) {
            builder.append("<li>")
                    .append(messageSource.getMessage(
                            violation.getMessageTemplate(),
                            new String[]{FieldPropertyMapping.fieldProperty.get(violation.getPropertyPath().toString())},
                            nl_BE))
                    .append("</li>");
        }
        builder.append("</ul>");
        return builder.toString();
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

}
