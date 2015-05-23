package be.janschraepen.hellokitty.services.impl;

import be.janschraepen.hellokitty.domain.cat.*;
import be.janschraepen.hellokitty.domain.person.Person;
import be.janschraepen.hellokitty.domain.person.PersonDTO;
import be.janschraepen.hellokitty.domain.persontype.PersonType;
import be.janschraepen.hellokitty.repository.CatPersonRepository;
import be.janschraepen.hellokitty.repository.CatRepository;
import be.janschraepen.hellokitty.repository.PersonRepository;
import be.janschraepen.hellokitty.repository.PersonTypeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CatServiceImplTest {

    public static final String UUID = "uuid";
    public static final String SEARCH_FOR = "searchFor";

    @Mock
    private CatRepository catRepository;

    @Mock
    private CatPersonRepository catPersonRepository;

    @Mock
    private PersonTypeRepository personTypeRepository;

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private CatServiceImpl underTest = new CatServiceImpl();

    @Test
    public void testFindCat() throws Exception {
        Cat cat_1 = createCat("name_1", "breed_1", "age_1", Gender.M, true, true, "attention_1", "behavioral_1", "nutrition_1");

        when(catRepository.findById(UUID)).thenReturn(cat_1);

        CatDTO dto = underTest.findCat(UUID);
        assertNotNull(dto);
        assertEquals("name_1", dto.getName());
        assertEquals("breed_1", dto.getBreed());
        assertEquals("age_1", dto.getAge());
        assertEquals(Gender.M, dto.getGender());
        assertTrue(dto.isNeutered());
        assertTrue(dto.isChipped());
        assertEquals("attention_1", dto.getAttention());
        assertEquals("behavioral_1", dto.getBehavioral());
        assertEquals("nutrition_1", dto.getNutrition());
    }

    @Test
    public void testFindCat_noResultReturnsNull() throws Exception {
        when(catRepository.findById(UUID)).thenReturn(null);

        CatDTO dto = underTest.findCat(UUID);
        assertNull(dto);
    }

    @Test
    public void testFindCats() throws Exception {
        Cat cat_1 = createCat("name_1", "breed_1", "age_1", Gender.M, true, true, "attention_1", "behavioral_1", "nutrition_1");
        Cat cat_2 = createCat("name_2", "breed_2", "age_2", Gender.M, true, true, "attention_2", "behavioral_2", "nutrition_2");

        when(catRepository.find(SEARCH_FOR)).thenReturn(Arrays.asList(cat_2));

        List<CatDTO> list = underTest.findCats(SEARCH_FOR);
        assertNotNull(list);
        assertEquals(1, list.size());

        assertEquals("name_2", list.get(0).getName());
        assertEquals("breed_2", list.get(0).getBreed());
        assertEquals("age_2", list.get(0).getAge());
        assertEquals(Gender.M, list.get(0).getGender());
        assertTrue(list.get(0).isNeutered());
        assertTrue(list.get(0).isChipped());
        assertEquals("attention_2", list.get(0).getAttention());
        assertEquals("behavioral_2", list.get(0).getBehavioral());
        assertEquals("nutrition_2", list.get(0).getNutrition());
    }

    @Test
    public void testFindCats_noResultsReturnsEmptyList() throws Exception {
        when(catRepository.find(SEARCH_FOR)).thenReturn(Collections.emptyList());

        List<CatDTO> list = underTest.findCats(SEARCH_FOR);
        assertNotNull(list);
        assertEquals(0, list.size());
    }

    @Test
    public void testFindAllCats() throws Exception {
        Cat cat_1 = createCat("name_1", "breed_1", "age_1", Gender.M, true, true, "attention_1", "behavioral_1", "nutrition_1");
        Cat cat_2 = createCat("name_2", "breed_2", "age_2", Gender.M, true, true, "attention_2", "behavioral_2", "nutrition_2");

        when(catRepository.findAll()).thenReturn(Arrays.asList(cat_1, cat_2));

        List<CatDTO> list = underTest.findAllCats();
        assertNotNull(list);
        assertEquals(2, list.size());

        assertEquals("name_1", list.get(0).getName());
        assertEquals("breed_1", list.get(0).getBreed());
        assertEquals("age_1", list.get(0).getAge());
        assertEquals(Gender.M, list.get(0).getGender());
        assertTrue(list.get(0).isNeutered());
        assertTrue(list.get(0).isChipped());
        assertEquals("attention_1", list.get(0).getAttention());
        assertEquals("behavioral_1", list.get(0).getBehavioral());
        assertEquals("nutrition_1", list.get(0).getNutrition());

        assertEquals("name_2", list.get(1).getName());
        assertEquals("breed_2", list.get(1).getBreed());
        assertEquals("age_2", list.get(1).getAge());
        assertEquals(Gender.M, list.get(1).getGender());
        assertTrue(list.get(1).isNeutered());
        assertTrue(list.get(1).isChipped());
        assertEquals("attention_2", list.get(1).getAttention());
        assertEquals("behavioral_2", list.get(1).getBehavioral());
        assertEquals("nutrition_2", list.get(1).getNutrition());
    }

    @Test
    public void testFindAllCats_noResultsReturnsEmptyList() throws Exception {
        when(catRepository.findAll()).thenReturn(Collections.emptyList());

        List<CatDTO> list = underTest.findAllCats();
        assertNotNull(list);
        assertEquals(0, list.size());
    }

    @Test
    public void testSaveCat_newInstance() throws Exception {
        Cat update = createCat(UUID, "name_3", "breed_3", "age_3", Gender.V, true, false, "attention_3", "behavioral_3", "nutrition_3");
        CatDTO _new = createCatDTO("name_3", "breed_3", "age_3", Gender.V, true, false, "attention_3", "behavioral_3", "nutrition_3");

        ArgumentCaptor<Cat> c = ArgumentCaptor.forClass(Cat.class);
        when(catRepository.saveAndFlush(c.capture())).thenReturn(update);

        underTest.saveCat(_new);

        Cat arg = c.getValue();
        assertNotNull(arg);
        assertEquals("name_3", arg.getName());
        assertEquals("breed_3", arg.getBreed());
        assertEquals("age_3", arg.getAge());
        assertEquals(Gender.V, arg.getGender());
        assertTrue(arg.isNeutered());
        assertFalse(arg.isChipped());
        assertEquals("attention_3", arg.getAttention());
        assertEquals("behavioral_3", arg.getBehavioral());
        assertEquals("nutrition_3", arg.getNutrition());
    }

    @Test
    public void testSaveCat_existingInstance() throws Exception {
        Cat toUpdate = createCat(UUID, "name_3", "breed_3", "age_3", Gender.V, true, false, "attention_3", "behavioral_3", "nutrition_3");
        Cat update = createCat(UUID, "name_3", "breed_3", "age_4", Gender.V, true, false, "attention_3", "behavioral_3", "nutrition_3");
        CatDTO updated = createCatDTO("name_3", "breed_3", "age_4", Gender.V, true, false, "attention_3", "behavioral_3", "nutrition_3");

        when(catRepository.findById(UUID)).thenReturn(toUpdate);

        ArgumentCaptor<Cat> c = ArgumentCaptor.forClass(Cat.class);
        when(catRepository.saveAndFlush(c.capture())).thenReturn(update);

        underTest.saveCat(updated);

        Cat arg = c.getValue();
        assertNotNull(arg);
        assertEquals("name_3", arg.getName());
        assertEquals("breed_3", arg.getBreed());
        assertEquals("age_4", arg.getAge());
        assertEquals(Gender.V, arg.getGender());
        assertTrue(arg.isNeutered());
        assertFalse(arg.isChipped());
        assertEquals("attention_3", arg.getAttention());
        assertEquals("behavioral_3", arg.getBehavioral());
        assertEquals("nutrition_3", arg.getNutrition());
    }

    @Test
    public void testDeleteCat_nonExistingInstance() throws Exception {
        when(catRepository.findById(UUID)).thenReturn(null);

        underTest.deleteCat(UUID);
    }

    @Test
    public void testDeleteCat_existingInstance() throws Exception {
        Cat toDelete = createCat(UUID, "name_3", "breed_3", "age_3", Gender.V, true, false, "attention_3", "behavioral_3", "nutrition_3");

        when(catRepository.findById(UUID)).thenReturn(toDelete);

        underTest.deleteCat(UUID);

        verify(catRepository).delete(toDelete);
    }

    @Test
    public void testDeleteCats() throws Exception {
        Cat toDelete = createCat(UUID, "name_3", "breed_3", "age_3", Gender.V, true, false, "attention_3", "behavioral_3", "nutrition_3");

        when(catRepository.findById(UUID)).thenReturn(toDelete);

        underTest.deleteCats(new String[] {UUID});

        verify(catRepository).delete(toDelete);
    }

    @Test
    public void testSaveCatPerson_newInstance() throws Exception {
        CatPersonDTO _new = new CatPersonDTO();
        _new.setCatId("cat-uuid");
        _new.setPersonTypeId("personType-uuid");
        _new.setPersonId("person-uuid");

        Cat cat = new Cat();
        PersonType personType = new PersonType();
        personType.setId("personType-uuid");
        personType.setName("personType");
        Person person = new Person();
        person.setId("person-uuid");
        person.setFirstName("firstName");
        person.setLastName("lastName");

        CatPerson update = new CatPerson();
        update.setId("uuid");
        update.setCat(cat);
        update.setType(personType);
        update.setPerson(person);


        when(catRepository.findById("cat-uuid")).thenReturn(cat);
        when(personTypeRepository.findById("personType-uuid")).thenReturn(personType);
        when(personRepository.findById("person-uuid")).thenReturn(person);

        ArgumentCaptor<CatPerson> c = ArgumentCaptor.forClass(CatPerson.class);
        when(catPersonRepository.saveAndFlush(c.capture())).thenReturn(update);

        underTest.saveCatPerson(_new);

        CatPerson arg = c.getValue();
        assertNotNull(arg);
        assertSame(cat, arg.getCat());
        assertSame(personType, arg.getType());
        assertSame(person, arg.getPerson());
    }

    private CatDTO createCatDTO(String name, String breed, String age, Gender gender, boolean neutered, boolean chipped, String attention, String behavioral, String nutrition) {
        return createCatDTO(null, name, breed, age, gender, neutered, chipped, attention, behavioral, nutrition);
    }

    private CatDTO createCatDTO(String uuid, String name, String breed, String age, Gender gender, boolean neutered, boolean chipped, String attention, String behavioral, String nutrition) {
        CatDTO dto = new CatDTO();
        dto.setId(uuid);
        dto.setName(name);
        dto.setBreed(breed);
        dto.setAge(age);
        dto.setGender(gender);
        dto.setNeutered(neutered);
        dto.setChipped(chipped);
        dto.setAttention(attention);
        dto.setBehavioral(behavioral);
        dto.setNutrition(nutrition);
        return dto;
    }

    private Cat createCat(String name, String breed, String age, Gender gender, boolean neutered, boolean chipped, String attention, String behavioral, String nutrition) {
        return createCat(null, name, breed, age, gender, neutered, chipped, attention, behavioral, nutrition);
    }

    private Cat createCat(String uuid, String name, String breed, String age, Gender gender, boolean neutered, boolean chipped, String attention, String behavioral, String nutrition) {
        Cat cat = new Cat();
        cat.setId(uuid);
        cat.setName(name);
        cat.setBreed(breed);
        cat.setAge(age);
        cat.setGender(gender);
        cat.setNeutered(neutered);
        cat.setChipped(chipped);
        cat.setAttention(attention);
        cat.setBehavioral(behavioral);
        cat.setNutrition(nutrition);
        return cat;
    }

}