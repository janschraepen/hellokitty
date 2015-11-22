package be.janschraepen.hellokitty.util;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;

public class PageRefererManagerTest {

    private PageRefererManager underTest;

    @Before
    public void setUp() throws Exception {
        underTest = new PageRefererManager();
    }

    @Test
    public void testGetPrevious() throws Exception {
        HttpServletRequest request = new MockHttpServletRequest();
        request.getSession().setAttribute("_previous", "http://previousUrl");
        request.getSession().setAttribute("_current", "http://currentUrl");

        String result = underTest.getPrevious(request);
        assertNotNull(result);
        assertEquals("http://previousUrl&_referer=http://currentUrl", result);
    }

    @Test
    public void testGetCurrent() throws Exception {
        HttpServletRequest request = new MockHttpServletRequest();
        request.getSession().setAttribute("_current", "http://currentUrl");

        String result = underTest.getCurrent(request);
        assertNotNull(result);
        assertEquals("http://currentUrl", result);
    }

    @Test
    public void testUpdate() throws Exception {
        HttpServletRequest request = new MockHttpServletRequest();
        request.getSession().setAttribute("_current", "http://previousUrl");

        underTest.update(request, "http://currentUrl");
        assertEquals("http://previousUrl", request.getSession().getAttribute("_previous"));
        assertEquals("http://currentUrl", request.getSession().getAttribute("_current"));
    }

    @Test
    public void testSetPrevious() throws Exception {
        HttpServletRequest request = new MockHttpServletRequest();
        request.getSession().setAttribute("_current", "http://currentUrl");

        underTest.setPrevious(request);
        assertEquals("http://currentUrl", request.getSession().getAttribute("_previous"));
    }

    @Test
    public void testSetCurrent() throws Exception {
        HttpServletRequest request = new MockHttpServletRequest();

        underTest.setCurrent(request, "http://currentUrl");
        assertEquals("http://currentUrl", request.getSession().getAttribute("_current"));
    }

}