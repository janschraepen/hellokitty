package be.janschraepen.hellokitty.web.controller;

import be.janschraepen.hellokitty.domain.cat.CatDTO;
import be.janschraepen.hellokitty.domain.cat.Gender;
import be.janschraepen.hellokitty.services.CatService;
import be.janschraepen.hellokitty.web.RequestParameter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CatControllerTest {

    @Mock
    private MessageSource messageSource;

    @Mock
    private CatService catService;

    @InjectMocks
    private CatController underTest = new CatController();


    @Test
    public void testList() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();

        List<CatDTO> list = Arrays.asList(new CatDTO[]{});

        when(catService.findAllCats()).thenReturn(list);

        ModelAndView mv = underTest.list(request);
        assertNotNull(mv);
        assertEquals("cat/list", mv.getViewName());
        assertEquals("cat.list.title", mv.getModel().get("title"));
        assertSame(list, mv.getModel().get("listItems"));
    }

    @Test
    public void testDoSearch() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter(RequestParameter.SEARCH, "searchFor");

        List<CatDTO> list = Arrays.asList(new CatDTO[]{});
        when(catService.findCats("searchFor")).thenReturn(list);

        ModelAndView mv = underTest.doSearch(request);
        assertNotNull(mv);
        assertEquals("cat/list", mv.getViewName());
        assertEquals("cat.list.title", mv.getModel().get("title"));
        assertEquals(list, mv.getModel().get("listItems"));
    }

    @Test
    public void testDoOpenEdit_new() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter(RequestParameter.UUID, "");

        when(messageSource.getMessage("cat.detail.new", null, new Locale("nl", "BE"))).thenReturn("NIEUWE KAT");

        ModelAndView mv = underTest.doOpenEdit(request);
        assertNotNull(mv);
        assertEquals("cat/edit", mv.getViewName());
        assertEquals("NIEUWE KAT", mv.getModel().get("title"));
        assertNotNull(mv.getModel().get("entity"));
    }

    @Test
    public void testDoOpenEdit_existing() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter(RequestParameter.UUID, "uuid");

        CatDTO entity = new CatDTO();
        entity.setName("name");
        when(catService.findCat("uuid")).thenReturn(entity);

        ModelAndView mv = underTest.doOpenEdit(request);
        assertNotNull(mv);
        assertEquals("cat/edit", mv.getViewName());
        assertEquals("name", mv.getModel().get("title"));
        assertNotNull(mv.getModel().get("entity"));
    }

    @Test
    public void testDoSave() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter(RequestParameter.UUID, "uuid");
        request.addParameter(RequestParameter.NAME, "name");
        request.addParameter(RequestParameter.BREED, "breed");
        request.addParameter(RequestParameter.AGE, "age");
        request.addParameter(RequestParameter.GENDER, "V");
        request.addParameter(RequestParameter.NEUTERED, "TRUE");
        request.addParameter(RequestParameter.CHIPPED, "FALSE");
        request.addParameter(RequestParameter.ATTENTION, "attention");
        request.addParameter(RequestParameter.BEHAVIORAL, "behavioral");
        request.addParameter(RequestParameter.NUTRITION, "nutrition");

        ModelAndView mv = underTest.doSave(request);
        assertNotNull(mv);
        assertEquals("cat/edit", mv.getViewName());
        assertEquals("name", mv.getModel().get("title"));
        assertNotNull(mv.getModel().get("entity"));

        ArgumentCaptor<CatDTO> c = ArgumentCaptor.forClass(CatDTO.class);
        verify(catService).saveCat(c.capture());

        CatDTO arg = c.getValue();
        assertNotNull(arg);
        assertEquals("uuid", arg.getId());
        assertEquals("name", arg.getName());
        assertEquals("breed", arg.getBreed());
        assertEquals("age", arg.getAge());
        assertEquals(Gender.V, arg.getGender());
        assertTrue(arg.isNeutered());
        assertFalse(arg.isChipped());
        assertEquals("attention", arg.getAttention());
        assertEquals("behavioral", arg.getBehavioral());
        assertEquals("nutrition", arg.getNutrition());
    }

    @Test
    public void testDoDelete() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter(RequestParameter.UUID, "uuid");

        List<CatDTO> list = Arrays.asList(new CatDTO[]{});
        when(catService.findAllCats()).thenReturn(list);

        ModelAndView mv = underTest.doDelete(request);
        assertNotNull(mv);
        assertEquals("cat/list", mv.getViewName());
        assertEquals("cat.list.title", mv.getModel().get("title"));
        assertEquals(list, mv.getModel().get("listItems"));

        ArgumentCaptor<String[]> s = ArgumentCaptor.forClass(String[].class);
        verify(catService).deleteCats(s.capture());

        String[] arg = s.getValue();
        assertNotNull(arg);
        assertEquals("uuid", arg[0]);
    }

}