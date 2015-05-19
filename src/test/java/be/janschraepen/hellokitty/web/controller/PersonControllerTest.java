package be.janschraepen.hellokitty.web.controller;

import be.janschraepen.hellokitty.domain.person.ContactType;
import be.janschraepen.hellokitty.domain.person.Person;
import be.janschraepen.hellokitty.domain.person.PersonContactDTO;
import be.janschraepen.hellokitty.domain.person.PersonDTO;
import be.janschraepen.hellokitty.domain.persontype.PersonTypeDTO;
import be.janschraepen.hellokitty.services.PersonService;
import be.janschraepen.hellokitty.services.PersonTypeService;
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
        request.addParameter(RequestParameter.SEARCH, "searchFor");

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
        request.addParameter(RequestParameter.UUID, "");

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
        request.addParameter(RequestParameter.UUID, "uuid");

        PersonDTO entity = new PersonDTO();
        entity.setId("uuid");
        entity.setPersonTypeId("personType-uuid");
        entity.setFirstName("firstName");
        entity.setLastName("lastName");
        entity.setAddressLine1("addressLine1");
        entity.setAddressLine2("addressLine2");

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
        request.addParameter(RequestParameter.UUID, "uuid");
        request.addParameter(RequestParameter.PERSON_TYPE_ID, "personType-uuid");
        request.addParameter(RequestParameter.FIRSTNAME, "firstName");
        request.addParameter(RequestParameter.LASTNAME, "lastName");
        request.addParameter(RequestParameter.ADDRESSLINE1, "addressLine1");
        request.addParameter(RequestParameter.ADDRESSLINE2, "addressLine2");

        PersonDTO person = new PersonDTO();
        person.setId("uuid");
        person.setPersonTypeId("personType-uuid");
        person.setFirstName("firstName");
        person.setLastName("lastName");
        person.setAddressLine1("addressLine1");
        person.setAddressLine2("addressLine2");

        when(personService.findPerson("uuid")).thenReturn(person);
        ArgumentCaptor<PersonDTO> p = ArgumentCaptor.forClass(PersonDTO.class);
        when(personService.savePerson(p.capture())).thenReturn(person);

        ModelAndView mv = underTest.doSave(request);
        assertNotNull(mv);
        assertEquals("person/edit", mv.getViewName());
        assertEquals("firstName - lastName", mv.getModel().get("title"));
        assertNotNull(mv.getModel().get("entity"));

        PersonDTO arg = p.getValue();
        assertNotNull(arg);
        assertEquals("uuid", arg.getId());
        assertEquals("personType-uuid", arg.getPersonTypeId());
        assertEquals("firstName", arg.getFirstName());
        assertEquals("lastName", arg.getLastName());
        assertEquals("addressLine1", arg.getAddressLine1());
        assertEquals("addressLine2", arg.getAddressLine2());
    }

    @Test
    public void testDoDelete() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter(RequestParameter.UUID, "uuid");

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
    public void testDoDeleteContact() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter(RequestParameter.UUID, "uuid");
        request.addParameter(RequestParameter.CONTACT_UUID, "uuid");

        PersonDTO entity = new PersonDTO();
        entity.setId("uuid");
        entity.setPersonTypeId("personType-uuid");
        entity.setFirstName("firstName");
        entity.setLastName("lastName");
        entity.setAddressLine1("addressLine1");
        entity.setAddressLine2("addressLine2");

        when(personService.findPerson("uuid")).thenReturn(entity);

        ModelAndView mv = underTest.doDeleteContact(request);
        assertNotNull(mv);
        assertEquals("person/edit", mv.getViewName());
        assertEquals("firstName - lastName", mv.getModel().get("title"));
        assertNotNull(mv.getModel().get("entity"));
        assertEquals(1, mv.getModel().get("activeTab"));

        ArgumentCaptor<String> s = ArgumentCaptor.forClass(String.class);
        verify(personService).deletePersonContact(s.capture());

        String arg = s.getValue();
        assertNotNull(arg);
        assertEquals("uuid", arg);
    }

    @Test
    public void testDoSaveContact() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter(RequestParameter.UUID, "uuid");
        request.addParameter(RequestParameter.CONTACT_TYPE, "CELLULAR");
        request.addParameter(RequestParameter.CONTACT_VALUE, "value");

        PersonDTO entity = new PersonDTO();
        entity.setId("uuid");
        entity.setPersonTypeId("personType-uuid");
        entity.setFirstName("firstName");
        entity.setLastName("lastName");
        entity.setAddressLine1("addressLine1");
        entity.setAddressLine2("addressLine2");

        when(personService.findPerson("uuid")).thenReturn(entity);

        ModelAndView mv = underTest.doSaveContact(request);
        assertNotNull(mv);
        assertEquals("person/edit", mv.getViewName());
        assertEquals("firstName - lastName", mv.getModel().get("title"));
        assertNotNull(mv.getModel().get("entity"));
        assertEquals(1, mv.getModel().get("activeTab"));

        ArgumentCaptor<PersonContactDTO> p = ArgumentCaptor.forClass(PersonContactDTO.class);
        verify(personService).savePersonContact(p.capture());

        PersonContactDTO arg = p.getValue();
        assertNotNull(arg);
        assertEquals("uuid", arg.getPersonId());
        assertEquals(ContactType.CELLULAR, arg.getType());
        assertEquals("value", arg.getValue());
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