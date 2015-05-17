package be.janschraepen.hellokitty.web.controller;

import be.janschraepen.hellokitty.domain.person.PersonDTO;
import be.janschraepen.hellokitty.domain.persontype.PersonTypeDTO;
import be.janschraepen.hellokitty.services.PersonService;
import be.janschraepen.hellokitty.services.PersonTypeService;
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
public class PersonControllerTest {

    @Mock
    private MessageSource messageSource;

    @Mock
    private PersonService personService;

    @Mock
    private PersonTypeService personTypeService;

    @InjectMocks
    private PersonController underTest = new PersonController();

    @Test
    public void testList() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();

        List<PersonDTO> list = Arrays.asList(new PersonDTO[]{ });

        when(personService.findAllPersons()).thenReturn(list);

        ModelAndView mv = underTest.list(request);
        assertNotNull(mv);
        assertEquals("person/list", mv.getViewName());
        assertEquals("person.list.title", mv.getModel().get("title"));
        assertSame(list, mv.getModel().get("listItems"));
    }

    @Test
    public void testDoSearch() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("search", "searchFor");

        List<PersonDTO> list = Arrays.asList(new PersonDTO[]{ });
        when(personService.findPersons("searchFor")).thenReturn(list);

        ModelAndView mv = underTest.doSearch(request);
        assertNotNull(mv);
        assertEquals("person/list", mv.getViewName());
        assertEquals("person.list.title", mv.getModel().get("title"));
        assertEquals(list, mv.getModel().get("listItems"));
    }

    @Test
    public void testDoOpenEdit_new() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("uuid", "");

        when(messageSource.getMessage("person.detail.new", null, new Locale("nl", "BE"))).thenReturn("NIEUW PERSOON");

        ModelAndView mv = underTest.doOpenEdit(request);
        assertNotNull(mv);
        assertEquals("person/edit", mv.getViewName());
        assertEquals("NIEUW PERSOON", mv.getModel().get("title"));
        assertNotNull(mv.getModel().get("entity"));
    }

    @Test
    public void testDoOpenEdit_existing() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("uuid", "uuid");

        PersonDTO entity = new PersonDTO();
        entity.setId("uuid");
        entity.setPersonTypeId("personType-uuid");
        entity.setFirstName("firstName");
        entity.setLastName("lastName");
        entity.setAddressLine1("addressLine1");
        entity.setAddressLine2("addressLine2");
        entity.setTelephone("telephone");
        entity.setGsm("gsm");
        entity.setEmail("email");

        when(personService.findPerson("uuid")).thenReturn(entity);

        ModelAndView mv = underTest.doOpenEdit(request);
        assertNotNull(mv);
        assertEquals("person/edit", mv.getViewName());
        assertEquals("firstName - lastName", mv.getModel().get("title"));
        assertNotNull(mv.getModel().get("entity"));
    }

    @Test
    public void testDoSave() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("uuid", "uuid");
        request.addParameter("personTypeId", "personType-uuid");
        request.addParameter("firstName", "firstName");
        request.addParameter("lastName", "lastName");
        request.addParameter("addressLine1", "addressLine1");
        request.addParameter("addressLine2", "addressLine2");
        request.addParameter("telephone", "telephone");
        request.addParameter("gsm", "gsm");
        request.addParameter("email", "email");

        ModelAndView mv = underTest.doSave(request);
        assertNotNull(mv);
        assertEquals("person/edit", mv.getViewName());
        assertEquals("firstName - lastName", mv.getModel().get("title"));
        assertNotNull(mv.getModel().get("entity"));

        ArgumentCaptor<PersonDTO> p = ArgumentCaptor.forClass(PersonDTO.class);
        verify(personService).savePerson(p.capture());

        PersonDTO arg = p.getValue();
        assertNotNull(arg);
        assertEquals("uuid", arg.getId());
        assertEquals("personType-uuid", arg.getPersonTypeId());
        assertEquals("firstName", arg.getFirstName());
        assertEquals("lastName", arg.getLastName());
        assertEquals("addressLine1", arg.getAddressLine1());
        assertEquals("addressLine2", arg.getAddressLine2());
        assertEquals("telephone", arg.getTelephone());
        assertEquals("gsm", arg.getGsm());
        assertEquals("email", arg.getEmail());
    }

    @Test
    public void testDoDelete() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("uuid", "uuid");

        List<PersonDTO> list = Arrays.asList(new PersonDTO[]{ });
        when(personService.findAllPersons()).thenReturn(list);

        ModelAndView mv = underTest.doDelete(request);
        assertNotNull(mv);
        assertEquals("person/list", mv.getViewName());
        assertEquals("person.list.title", mv.getModel().get("title"));
        assertEquals(list, mv.getModel().get("listItems"));

        ArgumentCaptor<String> s = ArgumentCaptor.forClass(String.class);
        verify(personService).deletePerson(s.capture());

        String arg = s.getValue();
        assertNotNull(arg);
        assertEquals("uuid", arg);
    }

    @Test
    public void testAddDetailModelParameters() throws Exception {
        PersonTypeDTO personType = new PersonTypeDTO("uuid", "shortCode", "name");
        when(personTypeService.findAllPersonTypes()).thenReturn(Arrays.asList(new PersonTypeDTO[] {personType}));

        ModelAndView mv = new ModelAndView();
        underTest.addDetailModelParameters(mv);

        List<PersonTypeDTO> personTypes = (List<PersonTypeDTO>) mv.getModel().get("personTypes");
        assertNotNull(personTypes);
        assertEquals(1, personTypes.size(), 0);
        assertEquals("uuid", personTypes.get(0).getId());
        assertEquals("shortCode", personTypes.get(0).getShortCode());
        assertEquals("name", personTypes.get(0).getName());
    }

}