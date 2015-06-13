package be.janschraepen.hellokitty.web.controller;

import be.janschraepen.hellokitty.domain.person.Person;
import be.janschraepen.hellokitty.domain.persontype.CannotModifyPersonTypeException;
import be.janschraepen.hellokitty.domain.persontype.PersonType;
import be.janschraepen.hellokitty.domain.persontype.PersonTypeDTO;
import be.janschraepen.hellokitty.services.PersonTypeService;
import be.janschraepen.hellokitty.web.RequestAttribute;
import be.janschraepen.hellokitty.web.RequestParameter;
import be.janschraepen.hellokitty.web.controller.stub.StubConstraintViolation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import javax.validation.metadata.ConstraintDescriptor;
import java.util.*;

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
        MockHttpServletRequest request = new MockHttpServletRequest();

        List<PersonTypeDTO> list = Arrays.asList(new PersonTypeDTO[]{ });

        when(personTypeService.findAllPersonTypes()).thenReturn(list);

        ModelAndView mv = underTest.list(request);
        assertNotNull(mv);
        assertEquals("persontype/list", mv.getViewName());
        assertEquals("persontype.list.title", mv.getModel().get("title"));
        assertSame(list, mv.getModel().get("listItems"));
    }

    @Test
    public void testDoSearch() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter(RequestParameter.SEARCH, "searchFor");

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
        request.addParameter(RequestParameter.UUID, "");

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
        request.addParameter(RequestParameter.UUID, "uuid");

        PersonTypeDTO entity = new PersonTypeDTO("uuid", "shortCode", "name");
        when(personTypeService.findPersonType("uuid")).thenReturn(entity);

        ModelAndView mv = underTest.doOpenEdit(request);
        assertNotNull(mv);
        assertEquals("persontype/edit", mv.getViewName());
        assertEquals("name", mv.getModel().get("title"));
        assertNotNull(mv.getModel().get("entity"));
    }

    @Test
    public void testDoSave() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter(RequestParameter.UUID, "uuid");
        request.addParameter(RequestParameter.SHORT_CODE, "shortCode");
        request.addParameter(RequestParameter.NAME, "name");

        PersonTypeDTO personType = new PersonTypeDTO();
        personType.setId("uuid");
        personType.setShortCode("shortCode");
        personType.setName("name");

        ArgumentCaptor<PersonTypeDTO> p = ArgumentCaptor.forClass(PersonTypeDTO.class);
        when(personTypeService.savePersonType(p.capture())).thenReturn(personType);

        ModelAndView mv = underTest.doSave(request);
        assertNotNull(mv);
        assertEquals("persontype/edit", mv.getViewName());
        assertEquals("name", mv.getModel().get("title"));
        assertNotNull(mv.getModel().get("entity"));

        PersonTypeDTO arg = p.getValue();
        assertNotNull(arg);
        assertEquals("uuid", arg.getId());
        assertEquals("shortCode", arg.getShortCode());
        assertEquals("name", arg.getName());
    }

    @Test
    public void testDoSave_onCannotModifyPersonTypeExceptionShowsErrorMessage() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter(RequestParameter.UUID, "uuid");
        request.addParameter(RequestParameter.SHORT_CODE, "shortCode");
        request.addParameter(RequestParameter.NAME, "name");

        when(personTypeService.savePersonType(anyObject())).thenThrow(new CannotModifyPersonTypeException());
        when(messageSource.getMessage("error.personType.systemValues.save", null, new Locale("nl", "BE"))).thenReturn("message");

        PersonTypeDTO entity = new PersonTypeDTO("uuid", "shortCode", "name");
        when(personTypeService.findPersonType("uuid")).thenReturn(entity);

        ModelAndView mv = underTest.doSave(request);
        assertNotNull(mv);
        assertEquals("persontype/edit", mv.getViewName());
        assertEquals("name", mv.getModel().get("title"));
        assertNotNull(mv.getModel().get("entity"));

        String attr = (String) request.getAttribute(RequestAttribute.ERROR_MSG);
        assertNotNull(attr);
        assertEquals("message", attr);
    }

    @Test
    public void testDoSave_onConstraintViolationExceptionShowsErrorMessage() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter(RequestParameter.UUID, "uuid");
        request.addParameter(RequestParameter.SHORT_CODE, "shortCode");
        request.addParameter(RequestParameter.NAME, "name");

        StubConstraintViolation<String> violation_1 = new StubConstraintViolation<>("{messageTemplate-1}");
        StubConstraintViolation<String> violation_2 = new StubConstraintViolation<>("{messageTemplate-1}");

        Set<ConstraintViolation<String>> violations = new HashSet<>();
        violations.add(violation_1);
        violations.add(violation_2);

        ConstraintViolationException exception = new ConstraintViolationException(violations);

        when(personTypeService.savePersonType(anyObject())).thenThrow(exception);
        when(messageSource.getMessage(anyObject(), anyObject(), anyObject())).thenReturn("Violation message");

        PersonTypeDTO entity = new PersonTypeDTO("uuid", "shortCode", "name");
        when(personTypeService.findPersonType("uuid")).thenReturn(entity);

        ModelAndView mv = underTest.doSave(request);
        assertNotNull(mv);
        assertEquals("persontype/edit", mv.getViewName());
        assertEquals("name", mv.getModel().get("title"));
        assertNotNull(mv.getModel().get("entity"));

        String attr = (String) request.getAttribute(RequestAttribute.ERROR_MSG);
        assertNotNull(attr);
        assertEquals("<ul><li>Violation message</li><li>Violation message</li></ul>", attr);
    }

    @Test
    public void testDoDelete() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter(RequestParameter.UUID, "uuid");

        List<PersonTypeDTO> list = Arrays.asList(new PersonTypeDTO[]{});
        when(personTypeService.findAllPersonTypes()).thenReturn(list);

        ModelAndView mv = underTest.doDelete(request);
        assertNotNull(mv);
        assertEquals("persontype/list", mv.getViewName());
        assertEquals("persontype.list.title", mv.getModel().get("title"));
        assertEquals(list, mv.getModel().get("listItems"));

        ArgumentCaptor<String[]> s = ArgumentCaptor.forClass(String[].class);
        verify(personTypeService).deletePersonTypes(s.capture());

        String[] arg = s.getValue();
        assertNotNull(arg);
        assertEquals("uuid", arg[0]);
    }

    @Test
    public void testDoDelete_onExceptionShowsErrorMessage() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter(RequestParameter.UUID, "uuid");

        doThrow(new CannotModifyPersonTypeException()).when(personTypeService).deletePersonTypes(new String[] {"uuid"});
        when(messageSource.getMessage("error.personType.systemValues.delete", null, new Locale("nl", "BE"))).thenReturn("message");

        List<PersonTypeDTO> list = Arrays.asList(new PersonTypeDTO[]{});
        when(personTypeService.findAllPersonTypes()).thenReturn(list);

        ModelAndView mv = underTest.doDelete(request);
        assertNotNull(mv);
        assertEquals("persontype/list", mv.getViewName());
        assertEquals("persontype.list.title", mv.getModel().get("title"));
        assertEquals(list, mv.getModel().get("listItems"));

        String attr = (String) request.getAttribute(RequestAttribute.ERROR_MSG);
        assertNotNull(attr);
        assertEquals("message", attr);
    }

}