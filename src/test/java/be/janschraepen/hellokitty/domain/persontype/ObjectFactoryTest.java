package be.janschraepen.hellokitty.domain.persontype;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ObjectFactoryTest {

    private static final String SHORT_CODE = "shortCode";
    private static final String NAME = "name";

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
    public void testCreateDTO_withEntity() throws Exception {
        PersonType personType = new PersonType();
        personType.setShortCode(SHORT_CODE);
        personType.setName(NAME);

        PersonTypeDTO dto = underTest.createDTO(personType);
        assertNotNull(dto);
        assertEquals(SHORT_CODE, dto.getShortCode());
        assertEquals(NAME, dto.getName());
    }

    @Test
    public void testCreateDTO_withParams() throws Exception {
        PersonTypeDTO dto = underTest.createDTO("uuid", SHORT_CODE, NAME);
        assertNotNull(dto);
        assertEquals(SHORT_CODE, dto.getShortCode());
        assertEquals(NAME, dto.getName());
    }

    @Test
    public void testCreateListDTOs() throws Exception {
        PersonType personType = new PersonType();
        personType.setShortCode(SHORT_CODE);
        personType.setName(NAME);

        List<PersonTypeDTO> dtos = underTest.createListDTOs(Arrays.asList(new PersonType[] {personType}));
        assertNotNull(dtos);
        assertEquals(1, dtos.size(), 0);

        PersonTypeDTO dto = dtos.get(0);
        assertNotNull(dto);
        assertEquals(SHORT_CODE, dto.getShortCode());
        assertEquals(NAME, dto.getName());
    }

}