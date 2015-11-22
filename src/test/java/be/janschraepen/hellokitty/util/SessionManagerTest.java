package be.janschraepen.hellokitty.util;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.Assert.*;

public class SessionManagerTest {

    @Test
    public void testGet() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.getSession().setAttribute("key", "value");

        Object result = SessionManager.get(request, "key");
        assertNotNull(result);
        assertTrue(result instanceof String);
        assertEquals("value", result);
    }

    @Test
    public void testSet() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();

        SessionManager.set(request, "key", "value");
        assertNotNull(request.getSession().getAttribute("key"));
        assertEquals("value", request.getSession().getAttribute("key"));
    }

}