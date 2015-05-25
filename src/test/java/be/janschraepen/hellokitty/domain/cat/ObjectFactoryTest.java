package be.janschraepen.hellokitty.domain.cat;

import be.janschraepen.hellokitty.domain.person.*;
import be.janschraepen.hellokitty.domain.persontype.PersonType;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ObjectFactoryTest {

    public static final String NAME = "name";
    public static final String BREED = "breed";
    public static final String AGE = "2000";
    public static final String ATTENTION = "attention";
    public static final String BEHAVIORAL = "behavioral";
    public static final String NUTRITION = "nutrition";
    private ObjectFactory underTest;

    @Before
    public void setUp() throws Exception {
        underTest = ObjectFactory.getInstance();
    }

    @Test
    public void testGetInstance() throws Exception {
        assertNotNull(underTest);
        assertTrue(underTest instanceof ObjectFactory);
    }

    @Test
    public void testCreateCatDTO_withEntity() throws Exception {
        Cat cat = new Cat();
        cat.setName(NAME);
        cat.setBreed(BREED);
        cat.setAge(AGE);
        cat.setGender(Gender.M);
        cat.setNeutered(true);
        cat.setChipped(true);
        cat.setAttention(ATTENTION);
        cat.setBehavioral(BEHAVIORAL);
        cat.setNutrition(NUTRITION);

        CatDTO dto = underTest.createCatDTO(cat);
        assertNotNull(dto);
        assertEquals(NAME, dto.getName());
        assertEquals(BREED, dto.getBreed());
        assertEquals(AGE, dto.getAge());
        assertEquals(Gender.M, dto.getGender());
        assertTrue(dto.isNeutered());
        assertTrue(dto.isChipped());
        assertEquals(ATTENTION, dto.getAttention());
        assertEquals(BEHAVIORAL, dto.getBehavioral());
        assertEquals(NUTRITION, dto.getNutrition());
    }

    @Test
    public void testCreateCatDTO_withParams() throws Exception {
        CatDTO dto = underTest.createCatDTO("uuid", NAME, BREED, AGE, Gender.M, true, true, ATTENTION, BEHAVIORAL, NUTRITION);
        assertNotNull(dto);
        assertEquals(NAME, dto.getName());
        assertEquals(BREED, dto.getBreed());
        assertEquals(AGE, dto.getAge());
        assertEquals(Gender.M, dto.getGender());
        assertTrue(dto.isNeutered());
        assertTrue(dto.isChipped());
        assertEquals(ATTENTION, dto.getAttention());
        assertEquals(BEHAVIORAL, dto.getBehavioral());
        assertEquals(NUTRITION, dto.getNutrition());
    }

    @Test
    public void testCreateListCatDTOs() throws Exception {
        Cat cat = new Cat();
        cat.setName(NAME);
        cat.setBreed(BREED);
        cat.setAge(AGE);
        cat.setGender(Gender.M);
        cat.setNeutered(true);
        cat.setChipped(true);
        cat.setAttention(ATTENTION);
        cat.setBehavioral(BEHAVIORAL);
        cat.setNutrition(NUTRITION);

        List<CatDTO> dtos = underTest.createListCatDTOs(Arrays.asList(new Cat[]{cat}));
        assertNotNull(dtos);
        assertEquals(1, dtos.size(), 0);

        CatDTO dto = dtos.get(0);
        assertNotNull(dto);
        assertEquals(NAME, dto.getName());
        assertEquals(BREED, dto.getBreed());
        assertEquals(AGE, dto.getAge());
        assertEquals(Gender.M, dto.getGender());
        assertTrue(dto.isNeutered());
        assertTrue(dto.isChipped());
        assertEquals(ATTENTION, dto.getAttention());
        assertEquals(BEHAVIORAL, dto.getBehavioral());
        assertEquals(NUTRITION, dto.getNutrition());
    }

    @Test
    public void testCreateCatPersonDTO_withEntity() throws Exception {
        Cat cat = new Cat();
        cat.setId("uuid");

        PersonType personType = new PersonType();
        personType.setId("personType-uuid");
        personType.setName("personType");

        PersonContact personContact = new PersonContact();
        personContact.setValue("+32000000000000");

        Person person = new Person();
        person.setId("person-uuid");
        person.setFirstName("firstName");
        person.setLastName("lastName");
        person.setAddressLine1("addressLine1");
        person.setAddressLine2("addressLine2");
        person.setContacts(Arrays.asList(new PersonContact[] {personContact}));

        CatPerson catPerson = new CatPerson();
        catPerson.setId("catPerson-uuid");
        catPerson.setCat(cat);
        catPerson.setType(personType);
        catPerson.setPerson(person);

        CatPersonDTO dto = underTest.createCatPersonDTO(catPerson);
        assertNotNull(dto);
        assertEquals("catPerson-uuid", dto.getId());
        assertEquals("uuid", dto.getCatId());
        assertEquals("personType-uuid", dto.getPersonTypeId());
        assertEquals("personType", dto.getPersonType());
        assertEquals("person-uuid", dto.getPersonId());
        assertEquals("firstName", dto.getPersonFirstName());
        assertEquals("lastName", dto.getPersonLastName());
        assertEquals("addressLine1", dto.getPersonAddressLine1());
        assertEquals("addressLine2", dto.getPersonAddressLine2());
        assertEquals("+32000000000000", dto.getPersonContacts());
    }

    @Test
    public void testCreateCatPersonDTO_withParams() throws Exception {
        CatPersonDTO dto = underTest.createCatPersonDTO("uuid", "personType-uuid", "person-uuid");
        assertNotNull(dto);
        assertEquals("uuid", dto.getCatId());
        assertEquals("personType-uuid", dto.getPersonTypeId());
        assertEquals("person-uuid", dto.getPersonId());
    }

    @Test
    public void testCreateListCatPersonDTOs() throws Exception {
        Cat cat = new Cat();
        cat.setId("uuid");

        PersonType personType = new PersonType();
        personType.setId("personType-uuid");
        personType.setName("personType");

        PersonContact personContact_1 = new PersonContact();
        personContact_1.setValue("+32000000000000");
        PersonContact personContact_2 = new PersonContact();
        personContact_2.setValue("email@domain.be");

        Person person = new Person();
        person.setId("person-uuid");
        person.setFirstName("firstName");
        person.setLastName("lastName");
        person.setLastName("lastName");
        person.setAddressLine1("addressLine1");
        person.setAddressLine2("addressLine2");
        person.setContacts(Arrays.asList(new PersonContact[] {personContact_1, personContact_2}));


        CatPerson catPerson = new CatPerson();
        catPerson.setId("catPerson-uuid");
        catPerson.setCat(cat);
        catPerson.setType(personType);
        catPerson.setPerson(person);

        List<CatPersonDTO> dtos = underTest.createListCatPersonDTOs(Arrays.asList(new CatPerson[]{catPerson}));
        assertNotNull(dtos);
        assertEquals(1, dtos.size(), 0);

        CatPersonDTO dto = dtos.get(0);
        assertNotNull(dto);
        assertEquals("catPerson-uuid", dto.getId());
        assertEquals("uuid", dto.getCatId());
        assertEquals("personType-uuid", dto.getPersonTypeId());
        assertEquals("personType", dto.getPersonType());
        assertEquals("person-uuid", dto.getPersonId());
        assertEquals("firstName", dto.getPersonFirstName());
        assertEquals("lastName", dto.getPersonLastName());
        assertEquals("addressLine1", dto.getPersonAddressLine1());
        assertEquals("addressLine2", dto.getPersonAddressLine2());
        assertEquals("+32000000000000<br/>email@domain.be", dto.getPersonContacts());
    }

    @Test
    public void testCreateListCatPersonDTOs_nullListReturnsEmptyList() throws Exception {
        List<CatPersonDTO> dtos = underTest.createListCatPersonDTOs(null);
        assertNotNull(dtos);
        assertEquals(0, dtos.size(), 0);
    }

    @Test
    public void testCreateCatPictureDTO_withEntity() throws Exception {
        Cat cat = new Cat();
        cat.setId("uuid");

        CatPicture p = new CatPicture();
        p.setCat(cat);
        p.setPicture(new byte[] { });
        p.setSize(20L);
        p.setContentType("image/jpeg");

        CatPictureDTO dto = underTest.createCatPictureDTO(p);
        assertNotNull(dto);
        assertEquals("uuid", dto.getCatId());
        assertEquals(p.getPicture(), dto.getPicture());
        assertEquals(p.getSize(), dto.getSize(), 0);
        assertEquals(p.getContentType(), dto.getContentType());
    }

    @Test
    public void testCreateCatPictureDTO_withParams() throws Exception {
        String catId = "uuid";
        byte[] picture = new byte[] { };
        Long size = 20L;
        String contentType = "image/jpeg";

        CatPictureDTO dto = underTest.createCatPictureDTO(catId, picture, size, contentType);
        assertNotNull(dto);
        assertEquals(catId, dto.getCatId());
        assertEquals(picture, dto.getPicture());
        assertEquals(size, dto.getSize(), 0);
        assertEquals(contentType, dto.getContentType());
    }

}