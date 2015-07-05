package be.janschraepen.hellokitty.web.controller;

import be.janschraepen.hellokitty.domain.cat.CatDTO;
import be.janschraepen.hellokitty.domain.cat.CatPersonDTO;
import be.janschraepen.hellokitty.services.CatService;
import be.janschraepen.hellokitty.web.RequestParameter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SearchControllerTest {

    @Mock
    private MessageSource messageSource;

    @Mock
    private CatService catService;

    @InjectMocks
    private SearchController underTest = new SearchController();

    @Test
    public void testList() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();

        ModelAndView mv = underTest.list(request);
        assertNotNull(mv);
        assertEquals("search/list", mv.getViewName());
        assertEquals("search.list.title", mv.getModel().get("title"));
        assertEquals(0, ((List<CatPersonDTO>) mv.getModel().get("listItems")).size(), 0);
    }

    @Test
    public void testDoSearch() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter(RequestParameter.SEARCH, "searchFor");

        List<CatPersonDTO> list = Arrays.asList(new CatPersonDTO[]{});
        when(catService.findCatPersons("searchFor")).thenReturn(list);

        ModelAndView mv = underTest.doSearch(request);
        assertNotNull(mv);
        assertEquals("search/list", mv.getViewName());
        assertEquals("search.list.title", mv.getModel().get("title"));
        assertEquals(list, mv.getModel().get("listItems"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testDoOpenEdit() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        underTest.doOpenEdit(request);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testDoSave() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        underTest.doSave(request);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testDoDelete() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        underTest.doDelete(request);
    }
}