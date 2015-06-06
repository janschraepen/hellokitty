package be.janschraepen.hellokitty.web.controller;

import be.janschraepen.hellokitty.domain.cat.CatDTO;
import be.janschraepen.hellokitty.domain.cat.CatPersonDTO;
import be.janschraepen.hellokitty.domain.cat.CatPictureDTO;
import be.janschraepen.hellokitty.domain.cat.Gender;
import be.janschraepen.hellokitty.domain.person.PersonDTO;
import be.janschraepen.hellokitty.domain.persontype.PersonType;
import be.janschraepen.hellokitty.domain.persontype.PersonTypeDTO;
import be.janschraepen.hellokitty.services.CatService;
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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CatControllerTest {

    @Mock
    private MessageSource messageSource;

    @Mock
    private CatService catService;

    @Mock
    private PersonTypeService personTypeService;

    @Mock
    private PersonService personService;

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

        CatDTO cat = new CatDTO();
        cat.setId("uuid");
        cat.setName("name");
        cat.setBreed("breed");
        cat.setAge("age");
        cat.setGender(Gender.V);
        cat.setNeutered(true);
        cat.setChipped(false);
        cat.setAttention("attention");
        cat.setBehavioral("behavioral");
        cat.setNutrition("nutrition");

        ArgumentCaptor<CatDTO> c = ArgumentCaptor.forClass(CatDTO.class);
        when(catService.saveCat(c.capture())).thenReturn(cat);

        ModelAndView mv = underTest.doSave(request);
        assertNotNull(mv);
        assertEquals("cat/edit", mv.getViewName());
        assertEquals("name", mv.getModel().get("title"));
        assertNotNull(mv.getModel().get("entity"));

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
    public void testDoSave_onNullPointerExceptionShowsErrorMessage() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter(RequestParameter.UUID, "uuid");
        request.addParameter(RequestParameter.NAME, "name");
        request.addParameter(RequestParameter.BREED, "breed");
        request.addParameter(RequestParameter.AGE, "age");
        request.addParameter(RequestParameter.NEUTERED, "TRUE");
        request.addParameter(RequestParameter.CHIPPED, "FALSE");
        request.addParameter(RequestParameter.ATTENTION, "attention");
        request.addParameter(RequestParameter.BEHAVIORAL, "behavioral");
        request.addParameter(RequestParameter.NUTRITION, "nutrition");

        CatDTO cat = new CatDTO();
        cat.setId("uuid");
        cat.setName("name");
        cat.setBreed("breed");
        cat.setAge("age");
        cat.setGender(Gender.V);
        cat.setNeutered(true);
        cat.setChipped(false);
        cat.setAttention("attention");
        cat.setBehavioral("behavioral");
        cat.setNutrition("nutrition");

        when(catService.findCat("uuid")).thenReturn(cat);

        ModelAndView mv = underTest.doSave(request);
        assertNotNull(mv);
        assertEquals("cat/edit", mv.getViewName());
        assertEquals("name", mv.getModel().get("title"));
        assertNotNull(mv.getModel().get("entity"));

        String attr = (String) request.getAttribute(RequestAttribute.ERROR_MSG);
        assertNotNull(attr);
        assertEquals("Ongeldig Geslacht geselecteerd!", attr);
    }

    @Test
    public void testDoSave_onConstraintViolationExceptionShowsErrorMessage() throws Exception {
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

        StubConstraintViolation<String> violation_1 = new StubConstraintViolation<>("{messageTemplate-1}");
        StubConstraintViolation<String> violation_2 = new StubConstraintViolation<>("{messageTemplate-1}");

        Set<ConstraintViolation<String>> violations = new HashSet<>();
        violations.add(violation_1);
        violations.add(violation_2);

        ConstraintViolationException exception = new ConstraintViolationException(violations);

        when(catService.saveCat(anyObject())).thenThrow(exception);
        when(messageSource.getMessage(anyObject(), anyObject(), anyObject())).thenReturn("Violation message");

        CatDTO cat = new CatDTO();
        cat.setId("uuid");
        cat.setName("name");
        cat.setBreed("breed");
        cat.setAge("age");
        cat.setGender(Gender.V);
        cat.setNeutered(true);
        cat.setChipped(false);
        cat.setAttention("attention");
        cat.setBehavioral("behavioral");
        cat.setNutrition("nutrition");

        when(catService.findCat("uuid")).thenReturn(cat);

        ModelAndView mv = underTest.doSave(request);
        assertNotNull(mv);
        assertEquals("cat/edit", mv.getViewName());
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

    @Test
    public void testDoDeletePerson() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter(RequestParameter.UUID, "uuid");
        request.addParameter(RequestParameter.PERSON_UUID, "uuid");

        CatDTO entity = new CatDTO();
        entity.setId("uuid");
        entity.setName("name");

        when(catService.findCat("uuid")).thenReturn(entity);

        ModelAndView mv = underTest.doDeletePerson(request);
        assertNotNull(mv);
        assertEquals("cat/edit", mv.getViewName());
        assertEquals("name", mv.getModel().get("title"));
        assertNotNull(mv.getModel().get("entity"));
        assertEquals(1, mv.getModel().get("activeTab"));

        ArgumentCaptor<String[]> s = ArgumentCaptor.forClass(String[].class);
        verify(catService).deleteCatPersons(s.capture());

        String[] arg = s.getValue();
        assertNotNull(arg);
        assertEquals("uuid", arg[0]);
    }

    @Test
    public void testDoSaveContact() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter(RequestParameter.UUID, "uuid");
        request.addParameter(RequestParameter.PERSON_TYPE, "personType-uuid");
        request.addParameter(RequestParameter.PERSON, "person-uuid");

        CatDTO entity = new CatDTO();
        entity.setId("uuid");
        entity.setName("name");

        when(catService.findCat("uuid")).thenReturn(entity);

        ModelAndView mv = underTest.doSavePerson(request);
        assertNotNull(mv);
        assertEquals("cat/edit", mv.getViewName());
        assertEquals("name", mv.getModel().get("title"));
        assertNotNull(mv.getModel().get("entity"));
        assertEquals(1, mv.getModel().get("activeTab"));

        ArgumentCaptor<CatPersonDTO> c = ArgumentCaptor.forClass(CatPersonDTO.class);
        verify(catService).saveCatPerson(c.capture());

        CatPersonDTO arg = c.getValue();
        assertNotNull(arg);
        assertEquals("uuid", arg.getCatId());
        assertEquals("personType-uuid", arg.getPersonTypeId());
        assertEquals("person-uuid", arg.getPersonId());
    }

    @Test
    public void testDoSaveContact_onDataIntegrityViolationExceptionShowsErrorMessage() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter(RequestParameter.UUID, "uuid");
        request.addParameter(RequestParameter.PERSON_TYPE, "personType-uuid");
        request.addParameter(RequestParameter.PERSON, "person-uuid");

        when(catService.saveCatPerson(anyObject())).thenThrow(new DataIntegrityViolationException("data integrity violation"));

        CatDTO entity = new CatDTO();
        entity.setId("uuid");
        entity.setName("name");

        when(catService.findCat("uuid")).thenReturn(entity);

        ModelAndView mv = underTest.doSavePerson(request);
        assertNotNull(mv);
        assertEquals("cat/edit", mv.getViewName());
        assertEquals("name", mv.getModel().get("title"));
        assertNotNull(mv.getModel().get("entity"));
        assertEquals(1, mv.getModel().get("activeTab"));

        String attr = (String) request.getAttribute(RequestAttribute.ERROR_MSG);
        assertNotNull(attr);
        assertEquals("Ongeldig PersoonType en/of Persoon geselecteerd!", attr);
    }

    @Test
    public void testDoSavePicture() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter(RequestParameter.UUID, "uuid");

        CatDTO entity = new CatDTO();
        entity.setId("uuid");
        entity.setName("name");

        when(catService.findCat("uuid")).thenReturn(entity);

        MultipartFile file = new TestMultiPartFile();

        underTest.doSavePicture(request, file);

        ArgumentCaptor<CatPictureDTO> p = ArgumentCaptor.forClass(CatPictureDTO.class);

        verify(catService).updateCatPicture(p.capture());

        CatPictureDTO arg = p.getValue();
        assertNotNull(arg);
        assertEquals("uuid", arg.getCatId());
        assertNotNull(arg.getPicture());
        assertEquals(file.getSize(), arg.getSize(), 0);
        assertEquals(file.getContentType(), arg.getContentType());
    }

    @Test
    public void testAddDetailModelParameters() throws Exception {
        PersonTypeDTO personType = new PersonTypeDTO();
        when(personTypeService.findAllPersonTypes()).thenReturn(Arrays.asList(new PersonTypeDTO[] {personType}));

        PersonDTO person_1 = new PersonDTO();
        person_1.setId("uuid-1");
        PersonDTO person_2 = new PersonDTO();
        person_2.setId("uuid-2");
        when(personService.findAllPersons()).thenReturn(Arrays.asList(new PersonDTO[] {person_1, person_2}));

        ModelAndView mv = new ModelAndView();
        underTest.addDetailModelParameters(mv);

        List<PersonTypeDTO> personTypes = (List<PersonTypeDTO>) mv.getModel().get("personTypes");
        assertNotNull(personTypes);
        assertEquals(1, personTypes.size(), 0);

        List<PersonDTO> persons = (List<PersonDTO>) mv.getModel().get("persons");
        assertNotNull(persons);
        assertEquals(2, persons.size(), 0);
    }

    private class TestMultiPartFile implements MultipartFile {

        @Override
        public String getName() {
            return null;
        }

        @Override
        public String getOriginalFilename() {
            return null;
        }

        @Override
        public String getContentType() {
            return "image/jpeg";
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public long getSize() {
            return 20L;
        }

        @Override
        public byte[] getBytes() throws IOException {
            return "test".getBytes();
        }

        @Override
        public InputStream getInputStream() throws IOException {
            return null;
        }

        @Override
        public void transferTo(File file) throws IOException, IllegalStateException {

        }

    }

}