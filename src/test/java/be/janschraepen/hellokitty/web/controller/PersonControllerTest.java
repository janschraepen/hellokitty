package be.janschraepen.hellokitty.web.controller;

import be.janschraepen.hellokitty.domain.person.ContactType;
import be.janschraepen.hellokitty.domain.person.PersonContactDTO;
import be.janschraepen.hellokitty.domain.person.PersonDTO;
import be.janschraepen.hellokitty.services.PersonService;
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
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
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
        entity.setFirstName("firstName");
        entity.setLastName("lastName");
        entity.setAddressLine1("addressLine1");
        entity.setAddressLine2("addressLine2");

        when(personService.findPerson("uuid")).thenReturn(entity);

        ModelAndView mv = underTest.doOpenEdit(request);
        assertNotNull(mv);
        assertEquals("person/edit", mv.getViewName());
        assertEquals("firstName lastName", mv.getModel().get("title"));
        assertNotNull(mv.getModel().get("entity"));
    }

    @Test
    public void testDoSave() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter(RequestParameter.UUID, "uuid");
        request.addParameter(RequestParameter.FIRSTNAME, "firstName");
        request.addParameter(RequestParameter.LASTNAME, "lastName");
        request.addParameter(RequestParameter.ADDRESSLINE1, "addressLine1");
        request.addParameter(RequestParameter.ADDRESSLINE2, "addressLine2");

        PersonDTO person = new PersonDTO();
        person.setId("uuid");
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
        assertEquals("firstName lastName", mv.getModel().get("title"));
        assertNotNull(mv.getModel().get("entity"));

        PersonDTO arg = p.getValue();
        assertNotNull(arg);
        assertEquals("uuid", arg.getId());
        assertEquals("firstName", arg.getFirstName());
        assertEquals("lastName", arg.getLastName());
        assertEquals("addressLine1", arg.getAddressLine1());
        assertEquals("addressLine2", arg.getAddressLine2());
    }

    @Test
    public void testDoSave_onConstraintViolationExceptionShowsErrorMessage() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter(RequestParameter.UUID, "uuid");
        request.addParameter(RequestParameter.FIRSTNAME, "firstName");
        request.addParameter(RequestParameter.LASTNAME, "lastName");
        request.addParameter(RequestParameter.ADDRESSLINE1, "addressLine1");
        request.addParameter(RequestParameter.ADDRESSLINE2, "addressLine2");

        StubConstraintViolation<String> violation_1 = new StubConstraintViolation<>("{messageTemplate-1}");
        StubConstraintViolation<String> violation_2 = new StubConstraintViolation<>("{messageTemplate-1}");

        Set<ConstraintViolation<String>> violations = new HashSet<>();
        violations.add(violation_1);
        violations.add(violation_2);

        ConstraintViolationException exception = new ConstraintViolationException(violations);

        when(personService.savePerson(anyObject())).thenThrow(exception);
        when(messageSource.getMessage(anyObject(), anyObject(), anyObject())).thenReturn("Violation message");

        PersonDTO person = new PersonDTO();
        person.setId("uuid");
        person.setFirstName("firstName");
        person.setLastName("lastName");
        person.setAddressLine1("addressLine1");
        person.setAddressLine2("addressLine2");
        when(personService.findPerson("uuid")).thenReturn(person);

        ModelAndView mv = underTest.doSave(request);
        assertNotNull(mv);
        assertEquals("person/edit", mv.getViewName());
        assertEquals("firstName lastName", mv.getModel().get("title"));
        assertNotNull(mv.getModel().get("entity"));

        String attr = (String) request.getAttribute(RequestAttribute.ERROR_MSG);
        assertNotNull(attr);
        assertEquals("<ul><li>Violation message</li><li>Violation message</li></ul>", attr);
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

        ArgumentCaptor<String[]> s = ArgumentCaptor.forClass(String[].class);
        verify(personService).deletePersons(s.capture());

        String[] arg = s.getValue();
        assertNotNull(arg);
        assertEquals("uuid", arg[0]);
    }

    @Test
    public void testDoDeleteContact() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter(RequestParameter.UUID, "uuid");
        request.addParameter(RequestParameter.CONTACT_UUID, "uuid");

        PersonDTO entity = new PersonDTO();
        entity.setId("uuid");
        entity.setFirstName("firstName");
        entity.setLastName("lastName");
        entity.setAddressLine1("addressLine1");
        entity.setAddressLine2("addressLine2");

        when(personService.findPerson("uuid")).thenReturn(entity);

        ModelAndView mv = underTest.doDeleteContact(request);
        assertNotNull(mv);
        assertEquals("person/edit", mv.getViewName());
        assertEquals("firstName lastName", mv.getModel().get("title"));
        assertNotNull(mv.getModel().get("entity"));
        assertEquals(1, mv.getModel().get("activeTab"));

        ArgumentCaptor<String[]> s = ArgumentCaptor.forClass(String[].class);
        verify(personService).deletePersonContacts(s.capture());

        String[] arg = s.getValue();
        assertNotNull(arg);
        assertEquals("uuid", arg[0]);
    }

    @Test
    public void testDoSaveContact() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter(RequestParameter.UUID, "uuid");
        request.addParameter(RequestParameter.CONTACT_TYPE, "CELLULAR");
        request.addParameter(RequestParameter.CONTACT_VALUE, "value");

        PersonDTO entity = new PersonDTO();
        entity.setId("uuid");
        entity.setFirstName("firstName");
        entity.setLastName("lastName");
        entity.setAddressLine1("addressLine1");
        entity.setAddressLine2("addressLine2");

        when(personService.findPerson("uuid")).thenReturn(entity);

        ModelAndView mv = underTest.doSaveContact(request);
        assertNotNull(mv);
        assertEquals("person/edit", mv.getViewName());
        assertEquals("firstName lastName", mv.getModel().get("title"));
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
    public void testDoSaveContact_onIllegalArgumentExceptionShowsErrorMessage() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter(RequestParameter.UUID, "uuid");
        request.addParameter(RequestParameter.CONTACT_TYPE, "-1");
        request.addParameter(RequestParameter.CONTACT_VALUE, "value");

        PersonDTO entity = new PersonDTO();
        entity.setId("uuid");
        entity.setFirstName("firstName");
        entity.setLastName("lastName");
        entity.setAddressLine1("addressLine1");
        entity.setAddressLine2("addressLine2");

        when(messageSource.getMessage("error.personContact.invalid.contactType", null, new Locale("nl", "BE"))).thenReturn("message");
        when(personService.findPerson("uuid")).thenReturn(entity);

        ModelAndView mv = underTest.doSaveContact(request);
        assertNotNull(mv);
        assertEquals("person/edit", mv.getViewName());
        assertEquals("firstName lastName", mv.getModel().get("title"));
        assertNotNull(mv.getModel().get("entity"));
        assertEquals(1, mv.getModel().get("activeTab"));

        String attr = (String) request.getAttribute(RequestAttribute.ERROR_MSG);
        assertNotNull(attr);
        assertEquals("message", attr);
    }

    @Test
    public void testDoSaveContact_onConstraintViolationExceptionShowsErrorMessage() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter(RequestParameter.UUID, "uuid");
        request.addParameter(RequestParameter.CONTACT_TYPE, "CELLULAR");
        request.addParameter(RequestParameter.CONTACT_VALUE, "value");

        StubConstraintViolation<String> violation_1 = new StubConstraintViolation<>("{messageTemplate-1}");
        StubConstraintViolation<String> violation_2 = new StubConstraintViolation<>("{messageTemplate-1}");

        Set<ConstraintViolation<String>> violations = new HashSet<>();
        violations.add(violation_1);
        violations.add(violation_2);

        ConstraintViolationException exception = new ConstraintViolationException(violations);

        when(personService.savePersonContact(anyObject())).thenThrow(exception);
        when(messageSource.getMessage(anyObject(), anyObject(), anyObject())).thenReturn("Violation message");

        PersonDTO entity = new PersonDTO();
        entity.setId("uuid");
        entity.setFirstName("firstName");
        entity.setLastName("lastName");
        entity.setAddressLine1("addressLine1");
        entity.setAddressLine2("addressLine2");
        when(personService.findPerson("uuid")).thenReturn(entity);

        ModelAndView mv = underTest.doSaveContact(request);
        assertNotNull(mv);
        assertEquals("person/edit", mv.getViewName());
        assertEquals("firstName lastName", mv.getModel().get("title"));
        assertNotNull(mv.getModel().get("entity"));
        assertEquals(1, mv.getModel().get("activeTab"));

        String attr = (String) request.getAttribute(RequestAttribute.ERROR_MSG);
        assertNotNull(attr);
        assertEquals("<ul><li>Violation message</li><li>Violation message</li></ul>", attr);
    }

    @Test
    public void testAddDetailModelParameters() throws Exception {
        ModelAndView mv = new ModelAndView();
        underTest.addDetailModelParameters(mv);

        List<ContactType> contactTypes = (List<ContactType>) mv.getModel().get("contactTypes");
        assertNotNull(contactTypes);
        assertEquals(3, contactTypes.size(), 0);
        assertEquals(ContactType.TELEPHONE, contactTypes.get(0));
        assertEquals(ContactType.CELLULAR, contactTypes.get(1));
        assertEquals(ContactType.EMAIL, contactTypes.get(2));
    }

}