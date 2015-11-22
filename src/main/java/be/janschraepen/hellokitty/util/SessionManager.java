package be.janschraepen.hellokitty.util;

import javax.servlet.http.HttpServletRequest;

/**
 * SessionManager class. This class is responsible for managing and
 * maintaining the session.
 */
public class SessionManager {

    /**
     * Get object with key from session.
     * @param request the HttpServletRequest
     * @param key the key to get
     * @return Object the object or null if not present
     */
    public static Object get(HttpServletRequest request, String key) {
        return request.getSession().getAttribute(key);
    }

    /**
     * Set an object on session with key.
     * @param request the HttpServletRequest
     * @param key the key to set
     * @param value the object to set
     */
    public static void set(HttpServletRequest request, String key, Object value) {
        request.getSession().setAttribute(key, value);
    }

}
