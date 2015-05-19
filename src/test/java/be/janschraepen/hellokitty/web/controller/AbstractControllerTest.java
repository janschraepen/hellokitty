package be.janschraepen.hellokitty.web.controller;

import be.janschraepen.hellokitty.web.Event;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class AbstractControllerTest {

    private AbstractController<String> underTest;

    @Before
    public void setUp() throws Exception {
        underTest = new AbstractController<String>() {

            @Override
            public ModelAndView list(HttpServletRequest request) {
                ModelAndView mv = new ModelAndView();
                mv.setViewName("list");
                return mv;
            }

            @Override
            public ModelAndView doOpenEdit(HttpServletRequest request) {
                ModelAndView mv = new ModelAndView();
                mv.setViewName("open_edit");
                return mv;
            }

            @Override
            public ModelAndView doSave(HttpServletRequest request) {
                ModelAndView mv = new ModelAndView();
                mv.setViewName("save");
                return mv;
            }

            @Override
            public ModelAndView doDelete(HttpServletRequest request) {
                ModelAndView mv = new ModelAndView();
                mv.setViewName("delete");
                return mv;
            }

            @Override
            public ModelAndView doSearch(HttpServletRequest request) {
                ModelAndView mv = new ModelAndView();
                mv.setViewName("search");
                return mv;
            }

            @Override
            void addDetailModelParameters(ModelAndView mv) {
                mv.getModel().put("test", "test");
            }
        };
    }

    @Test
    public void testDoEvent_new() throws Exception {
        HttpServletRequest request = new MockHttpServletRequest();
        ModelAndView mv = underTest.doEvent(Event.NEW, request);
        assertNotNull(mv);
        assertEquals("open_edit", mv.getViewName());
    }

    @Test
    public void testDoEvent_edit() throws Exception {
        HttpServletRequest request = new MockHttpServletRequest();
        ModelAndView mv = underTest.doEvent(Event.EDIT, request);
        assertNotNull(mv);
        assertEquals("open_edit", mv.getViewName());
    }

    @Test
    public void testDoEvent_save() throws Exception {
        HttpServletRequest request = new MockHttpServletRequest();
        ModelAndView mv = underTest.doEvent(Event.SAVE, request);
        assertNotNull(mv);
        assertEquals("save", mv.getViewName());
    }

    @Test
    public void testDoEvent_delete() throws Exception {
        HttpServletRequest request = new MockHttpServletRequest();
        ModelAndView mv = underTest.doEvent(Event.DELETE, request);
        assertNotNull(mv);
        assertEquals("delete", mv.getViewName());
    }

    @Test
    public void testDoEvent_search() throws Exception {
        HttpServletRequest request = new MockHttpServletRequest();
        ModelAndView mv = underTest.doEvent(Event.SEARCH, request);
        assertNotNull(mv);
        assertEquals("search", mv.getViewName());
    }

    @Test
    public void testDoEvent_back() throws Exception {
        HttpServletRequest request = new MockHttpServletRequest();
        ModelAndView mv = underTest.doEvent(Event.BACK, request);
        assertNotNull(mv);
        assertEquals("list", mv.getViewName());
    }

    @Test
    public void testGetActionUrl() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setScheme("http");
        request.setServerName("localhost");
        request.setServerPort(80);
        request.setContextPath("/context");

        String actionUrl = underTest.getActionUrl(request);
        assertNotNull(actionUrl);
        assertEquals("http://localhost:80/context", actionUrl);
    }

    @Test
    public void testList() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setScheme("http");
        request.setServerName("localhost");
        request.setServerPort(80);
        request.setContextPath("/context");

        List<String> list = Arrays.asList(new String[]{"arg"});

        ModelAndView mv = underTest.list(request, "viewName", "title", "description", list);
        assertNotNull(mv);
        assertEquals("http://localhost:80/context", mv.getModel().get("actionUrl"));
        assertEquals("viewName", mv.getViewName());
        assertEquals("title", mv.getModel().get("title"));
        assertEquals("description", mv.getModel().get("description"));
        assertSame(list, mv.getModel().get("listItems"));
    }

    @Test
    public void testDetail() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setScheme("http");
        request.setServerName("localhost");
        request.setServerPort(80);
        request.setContextPath("/context");

        ModelAndView mv = underTest.detail(request, "viewName", "title", "description", "arg");
        assertNotNull(mv);
        assertEquals("http://localhost:80/context", mv.getModel().get("actionUrl"));
        assertEquals("viewName", mv.getViewName());
        assertEquals("title", mv.getModel().get("title"));
        assertEquals("description", mv.getModel().get("description"));
        assertEquals("arg", mv.getModel().get("entity"));
        assertEquals("test", mv.getModel().get("test"));
    }

}