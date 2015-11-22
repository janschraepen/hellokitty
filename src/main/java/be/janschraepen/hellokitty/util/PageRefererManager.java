package be.janschraepen.hellokitty.util;

import org.apache.commons.lang3.StringEscapeUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * PageRefererManager class. This class is responsible for managing the
 * page referals.
 */
public class PageRefererManager {

    private static final String PREVIOUS_PAGE = "_previous";
    private static final String CURRENT_PAGE = "_current";

    /**
     * Get previous page referer.
     * @param request the HttpServletRequest
     * @return String the previous page referer
     */
    public static String getPrevious(HttpServletRequest request) {
        String previous = (String) SessionManager.get(request, PREVIOUS_PAGE);
        previous += "&_referer=" + StringEscapeUtils.escapeHtml4(getCurrent(request));
        return previous;
    }

    /**
     * Get current page referer.
     * @param request the HttpServletRequest
     * @return String the current page referer
     */
    public static String getCurrent(HttpServletRequest request) {
        return (String) SessionManager.get(request, CURRENT_PAGE);
    }

    /**
     * Update page referers.
     * @param request the HttpServletRequest
     * @param referer the current page referer
     */
    public static void update(HttpServletRequest request, String referer) {
        setPrevious(request);
        setCurrent(request, referer);
    }

    /**
     * Set previous page referer.
     * @param request the HttpServletRequest
     */
    protected static void setPrevious(HttpServletRequest request) {
        String current = getCurrent(request);
        SessionManager.set(request, PREVIOUS_PAGE, current);
    }

    /**
     * Set current page referer.
     * @param request the HttpServletRequest
     * @param referer the referer url
     */
    protected static void setCurrent(HttpServletRequest request, String referer) {
        SessionManager.set(request, CURRENT_PAGE, referer);
    }

}
