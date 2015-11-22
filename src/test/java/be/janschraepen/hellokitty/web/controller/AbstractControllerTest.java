package be.janschraepen.hellokitty.web.controller;

import be.janschraepen.hellokitty.web.Event;
import be.janschraepen.hellokitty.web.controller.stub.StubConstraintViolation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AbstractControllerTest {

    @Mock
    private MessageSource messageSource;

    @InjectMocks
    private AbstractController<String> underTest = new AbstractController<String>() {

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

    @Test
    public void testDoEvent_new() throws Exception {
        HttpServletRequest request = new MockHttpServletRequest();
        ModelAndView mv = underTest.doEvent(Event.NEW, "_referer", request);
        assertNotNull(mv);
        assertEquals("open_edit", mv.getViewName());
    }

    @Test
    public void testDoEvent_edit() throws Exception {
        HttpServletRequest request = new MockHttpServletRequest();
        ModelAndView mv = underTest.doEvent(Event.EDIT, "_referer", request);
        assertNotNull(mv);
        assertEquals("open_edit", mv.getViewName());
    }

    @Test
    public void testDoEvent_save() throws Exception {
        HttpServletRequest request = new MockHttpServletRequest();
        ModelAndView mv = underTest.doEvent(Event.SAVE, "_referer", request);
        assertNotNull(mv);
        assertEquals("save", mv.getViewName());
    }

    @Test
    public void testDoEvent_delete() throws Exception {
        HttpServletRequest request = new MockHttpServletRequest();
        ModelAndView mv = underTest.doEvent(Event.DELETE, "_referer", request);
        assertNotNull(mv);
        assertEquals("delete", mv.getViewName());
    }

    @Test
    public void testDoEvent_search() throws Exception {
        HttpServletRequest request = new MockHttpServletRequest();
        ModelAndView mv = underTest.doEvent(Event.SEARCH, "_referer", request);
        assertNotNull(mv);
        assertEquals("search", mv.getViewName());
    }

    @Test
    public void testDoEvent_back() throws Exception {
        HttpServletRequest request = new MockHttpServletRequest();
        request.getSession().setAttribute("_previous", "http://previousUrl");
        request.getSession().setAttribute("_current", "http://currentUrl");

        ModelAndView mv = underTest.doEvent(Event.BACK, "_referer", request);
        assertNotNull(mv);
        assertEquals("redirect:http://previousUrl&_referer=http://currentUrl", mv.getViewName());
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

        ModelAndView mv = underTest.list(request, "refererPath", "viewName", "title", "description", list);
        assertNotNull(mv);
        assertEquals("http://localhost:80/context", mv.getModel().get("actionUrl"));
        assertEquals("refererPath", mv.getModel().get("referer"));
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

        ModelAndView mv = underTest.detail(request, "refererPath", "viewName", "title", "description", "arg");
        assertNotNull(mv);
        assertEquals("http://localhost:80/context", mv.getModel().get("actionUrl"));
        assertEquals("refererPath", mv.getModel().get("referer"));
        assertEquals("viewName", mv.getViewName());
        assertEquals("title", mv.getModel().get("title"));
        assertEquals("description", mv.getModel().get("description"));
        assertEquals("arg", mv.getModel().get("entity"));
        assertEquals("test", mv.getModel().get("test"));
    }

    @Test
    public void testHandleConstraintViolation() throws Exception {
        when(messageSource.getMessage(anyObject(), anyObject(), anyObject())).thenReturn("Violation message");

        StubConstraintViolation<String> violation_1 = new StubConstraintViolation<>("{messageTemplate-1}");
        StubConstraintViolation<String> violation_2 = new StubConstraintViolation<>("{messageTemplate-1}");

        Set<ConstraintViolation<String>> violations = new HashSet<>();
        violations.add(violation_1);
        violations.add(violation_2);

        ConstraintViolationException exception = new ConstraintViolationException(violations);

        String message = underTest.handleConstraintViolations(exception);
        assertNotNull(message);
        assertEquals("<ul><li>Violation message</li><li>Violation message</li></ul>", message);
    }

}