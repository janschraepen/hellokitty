package be.janschraepen.hellokitty.web.controller;

import be.janschraepen.hellokitty.domain.cat.CatPersonDTO;
import org.apache.commons.lang3.NotImplementedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class WelcomeControllerTest {

    private WelcomeController underTest = new WelcomeController();

    @Test
    public void testShow() throws Exception {
        HttpServletRequest request = new MockHttpServletRequest();

        ModelAndView mv = underTest.show(request);
        assertNotNull(mv);
        assertEquals("welcome/index", mv.getViewName());
    }

    @Test(expected = NotImplementedException.class)
    public void testDoSearch() throws Exception {
        HttpServletRequest request = new MockHttpServletRequest();
        underTest.doSearch(request);
    }

    @Test(expected = NotImplementedException.class)
    public void testList() throws Exception {
        HttpServletRequest request = new MockHttpServletRequest();
        underTest.list(request);
    }

    @Test(expected = NotImplementedException.class)
    public void testDoOpenEdit() throws Exception {
        HttpServletRequest request = new MockHttpServletRequest();
        underTest.doOpenEdit(request);
    }

    @Test(expected = NotImplementedException.class)
    public void testDoSave() throws Exception {
        HttpServletRequest request = new MockHttpServletRequest();
        underTest.doSave(request);
    }

    @Test(expected = NotImplementedException.class)
    public void testDoDelete() throws Exception {
        HttpServletRequest request = new MockHttpServletRequest();
        underTest.doDelete(request);
    }

}