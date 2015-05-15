package be.janschraepen.hellokitty.web.controller;

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
            public ModelAndView list() {
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

        };
    }

    @Test
    public void testDoEvent_new() throws Exception {
        HttpServletRequest request = new MockHttpServletRequest();
        ModelAndView mv = underTest.doEvent("new", request);
        assertNotNull(mv);
        assertEquals("open_edit", mv.getViewName());
    }

    @Test
    public void testDoEvent_edit() throws Exception {
        HttpServletRequest request = new MockHttpServletRequest();
        ModelAndView mv = underTest.doEvent("edit", request);
        assertNotNull(mv);
        assertEquals("open_edit", mv.getViewName());
    }

    @Test
    public void testDoEvent_save() throws Exception {
        HttpServletRequest request = new MockHttpServletRequest();
        ModelAndView mv = underTest.doEvent("save", request);
        assertNotNull(mv);
        assertEquals("save", mv.getViewName());
    }

    @Test
    public void testDoEvent_delete() throws Exception {
        HttpServletRequest request = new MockHttpServletRequest();
        ModelAndView mv = underTest.doEvent("delete", request);
        assertNotNull(mv);
        assertEquals("delete", mv.getViewName());
    }

    @Test
    public void testDoEvent_search() throws Exception {
        HttpServletRequest request = new MockHttpServletRequest();
        ModelAndView mv = underTest.doEvent("search", request);
        assertNotNull(mv);
        assertEquals("search", mv.getViewName());
    }

    @Test
    public void testDoEvent_back() throws Exception {
        HttpServletRequest request = new MockHttpServletRequest();
        ModelAndView mv = underTest.doEvent("back", request);
        assertNotNull(mv);
        assertEquals("list", mv.getViewName());
    }

    @Test
    public void testList() throws Exception {
        List<String> list = Arrays.asList(new String[]{"arg"});

        ModelAndView mv = underTest.list("viewName", "title", "description", list);
        assertNotNull(mv);
        assertEquals("viewName", mv.getViewName());
        assertEquals("title", mv.getModel().get("title"));
        assertEquals("description", mv.getModel().get("description"));
        assertSame(list, mv.getModel().get("listItems"));
    }

    @Test
    public void testDetail() throws Exception {
        ModelAndView mv = underTest.detail("viewName", "title", "description", "arg");
        assertNotNull(mv);
        assertEquals("viewName", mv.getViewName());
        assertEquals("title", mv.getModel().get("title"));
        assertEquals("description", mv.getModel().get("description"));
        assertEquals("arg", mv.getModel().get("entity"));
    }

}