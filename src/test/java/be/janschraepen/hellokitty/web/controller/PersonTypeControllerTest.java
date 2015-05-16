package be.janschraepen.hellokitty.web.controller;

import be.janschraepen.hellokitty.domain.persontype.PersonTypeDTO;
import be.janschraepen.hellokitty.services.PersonTypeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PersonTypeControllerTest {

    @Mock
    private MessageSource messageSource;

    @Mock
    private PersonTypeService personTypeService;

    @InjectMocks
    private PersonTypeController underTest = new PersonTypeController();

    @Test
    public void testList() throws Exception {
        List<PersonTypeDTO> list = Arrays.asList(new PersonTypeDTO[]{ });

        when(personTypeService.findAllPersonTypes()).thenReturn(list);

        ModelAndView mv = underTest.list();
        assertNotNull(mv);
        assertEquals("persontype/list", mv.getViewName());
        assertEquals("persontype.list.title", mv.getModel().get("title"));
        assertSame(list, mv.getModel().get("listItems"));
    }

    @Test
    public void testDoSearch() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("search", "searchFor");

        List<PersonTypeDTO> list = Arrays.asList(new PersonTypeDTO[]{ });
        when(personTypeService.findPersonTypes("searchFor")).thenReturn(list);

        ModelAndView mv = underTest.doSearch(request);
        assertNotNull(mv);
        assertEquals("persontype/list", mv.getViewName());
        assertEquals("persontype.list.title", mv.getModel().get("title"));
        assertEquals(list, mv.getModel().get("listItems"));
    }

    @Test
    public void testDoOpenEdit_new() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("uuid", "");

        when(messageSource.getMessage("persontype.detail.new", null, new Locale("nl", "BE"))).thenReturn("NIEUW PERSOONTYPE");

        ModelAndView mv = underTest.doOpenEdit(request);
        assertNotNull(mv);
        assertEquals("persontype/edit", mv.getViewName());
        assertEquals("NIEUW PERSOONTYPE", mv.getModel().get("title"));
        assertNotNull(mv.getModel().get("entity"));
    }

    @Test
    public void testDoOpenEdit_existing() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("uuid", "uuid");

        PersonTypeDTO entity = new PersonTypeDTO("uuid", "shortCode", "name");
        when(personTypeService.findPersonType("uuid")).thenReturn(entity);

        ModelAndView mv = underTest.doOpenEdit(request);
        assertNotNull(mv);
        assertEquals("persontype/edit", mv.getViewName());
        assertEquals("shortCode - name", mv.getModel().get("title"));
        assertNotNull(mv.getModel().get("entity"));
    }

    @Test
    public void testDoSave() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("uuid", "uuid");
        request.addParameter("shortCode", "shortCode");
        request.addParameter("name", "name");

        ModelAndView mv = underTest.doSave(request);
        assertNotNull(mv);
        assertEquals("persontype/edit", mv.getViewName());
        assertEquals("shortCode - name", mv.getModel().get("title"));
        assertNotNull(mv.getModel().get("entity"));

        ArgumentCaptor<PersonTypeDTO> p = ArgumentCaptor.forClass(PersonTypeDTO.class);
        verify(personTypeService).savePersonType(p.capture());

        PersonTypeDTO arg = p.getValue();
        assertNotNull(arg);
        assertEquals("uuid", arg.getId());
        assertEquals("shortCode", arg.getShortCode());
        assertEquals("name", arg.getName());
    }

    @Test
    public void testDoDelete() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("uuid", "uuid");

        List<PersonTypeDTO> list = Arrays.asList(new PersonTypeDTO[]{ });
        when(personTypeService.findPersonTypes("searchFor")).thenReturn(list);

        ModelAndView mv = underTest.doDelete(request);
        assertNotNull(mv);
        assertEquals("persontype/list", mv.getViewName());
        assertEquals("persontype.list.title", mv.getModel().get("title"));
        assertEquals(list, mv.getModel().get("listItems"));

        ArgumentCaptor<String> s = ArgumentCaptor.forClass(String.class);
        verify(personTypeService).deletePersonType(s.capture());

        String arg = s.getValue();
        assertNotNull(arg);
        assertEquals("uuid", arg);
    }

}