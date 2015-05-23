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

}