package be.janschraepen.hellokitty.services.impl;

import be.janschraepen.hellokitty.domain.persontype.PersonType;
import be.janschraepen.hellokitty.domain.persontype.PersonTypeDTO;
import be.janschraepen.hellokitty.repository.PersonTypeRepository;
import be.janschraepen.hellokitty.services.impl.PersonTypeServiceImpl;
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
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PersonTypeServiceImplTest {

    public static final String SEARCH_FOR = "_2";
    public static final String UUID = "uuid";

    @Mock
    private PersonTypeRepository personTypeRepository;

    @InjectMocks
    private PersonTypeServiceImpl underTest = new PersonTypeServiceImpl();

    @Test
    public void testFindPersonType() throws Exception {
        PersonType personType_1 = createPersonType("shortCode_1", "name_1");

        when(personTypeRepository.findById(UUID)).thenReturn(personType_1);

        PersonTypeDTO dto = underTest.findPersonType(UUID);
        assertNotNull(dto);
        assertEquals("shortCode_1", dto.getShortCode());
        assertEquals("name_1", dto.getName());
    }

    @Test
    public void testFindPersonType_noResultReturnsNull() throws Exception {
        when(personTypeRepository.findById(UUID)).thenReturn(null);

        PersonTypeDTO dto = underTest.findPersonType(UUID);
        assertNull(dto);
    }

    @Test
    public void testFindPersonTypes() throws Exception {
        PersonType personType_1 = createPersonType("shortCode_1", "name_1");
        PersonType personType_2 = createPersonType("shortCode_2", "name_2");

        when(personTypeRepository.find(SEARCH_FOR)).thenReturn(Arrays.asList(personType_2));

        List<PersonTypeDTO> list = underTest.findPersonTypes(SEARCH_FOR);
        assertNotNull(list);
        assertEquals(1, list.size());

        assertEquals("shortCode_2", list.get(0).getShortCode());
        assertEquals("name_2", list.get(0).getName());
    }

    @Test
    public void testFindPersonTypes_noResultsReturnsEmptyList() throws Exception {
        when(personTypeRepository.find(SEARCH_FOR)).thenReturn(Collections.emptyList());

        List<PersonTypeDTO> list = underTest.findPersonTypes(SEARCH_FOR);
        assertNotNull(list);
        assertEquals(0, list.size());
    }

    @Test
    public void testFindAllPersonTypes() throws Exception {
        PersonType personType_1 = createPersonType("shortCode_1", "name_1");
        PersonType personType_2 = createPersonType("shortCode_2", "name_2");

        when(personTypeRepository.findAll()).thenReturn(Arrays.asList(personType_1, personType_2));

        List<PersonTypeDTO> list = underTest.findAllPersonTypes();
        assertNotNull(list);
        assertEquals(2, list.size());

        assertEquals("shortCode_1", list.get(0).getShortCode());
        assertEquals("name_1", list.get(0).getName());

        assertEquals("shortCode_2", list.get(1).getShortCode());
        assertEquals("name_2", list.get(1).getName());
    }

    @Test
    public void testFindAllPersonTypes_noResultsReturnsEmptyList() throws Exception {
        when(personTypeRepository.findAll()).thenReturn(Collections.emptyList());

        List<PersonTypeDTO> list = underTest.findAllPersonTypes();
        assertNotNull(list);
        assertEquals(0, list.size());
    }

    @Test
    public void testSavePersonType_newInstance() throws Exception {
        PersonType update = createPersonType(UUID, "shortCode_3", "name_3");
        PersonTypeDTO _new = createPersonTypeDTO("shortCode_3", "name_3");

        ArgumentCaptor<PersonType> p = ArgumentCaptor.forClass(PersonType.class);
        when(personTypeRepository.saveAndFlush(p.capture())).thenReturn(update);

        underTest.savePersonType(_new);

        PersonType arg = p.getValue();
        assertNotNull(arg);
        assertEquals("shortCode_3", arg.getShortCode());
        assertEquals("name_3", arg.getName());
    }

    @Test
    public void testSavePersonType_existingInstance() throws Exception {
        PersonType toUpdate = createPersonType(UUID, "shortCode_3", "name_3");
        PersonType update = createPersonType(UUID, "shortCode_3", "name_4");
        PersonTypeDTO updated = createPersonTypeDTO(UUID, "shortCode_3", "name_4");

        when(personTypeRepository.findById(UUID)).thenReturn(toUpdate);

        ArgumentCaptor<PersonType> p = ArgumentCaptor.forClass(PersonType.class);
        when(personTypeRepository.saveAndFlush(p.capture())).thenReturn(update);

        underTest.savePersonType(updated);

        PersonType arg = p.getValue();
        assertNotNull(arg);
        assertEquals("shortCode_3", arg.getShortCode());
        assertEquals("name_4", arg.getName());
    }

    @Test
    public void testDeletePersonType_nonExistingInstance() throws Exception {
        when(personTypeRepository.findById(UUID)).thenReturn(null);

        underTest.deletePersonType(UUID);
    }

    @Test
    public void testDeletePersonType_existingInstance() throws Exception {
        PersonType toDelete = createPersonType(UUID, "shortCode_3", "name_3");

        when(personTypeRepository.findById(UUID)).thenReturn(toDelete);

        underTest.deletePersonType(UUID);

        verify(personTypeRepository).delete(toDelete);
    }

    @Test
    public void testDeletePersonTypes_existingInstance() throws Exception {
        PersonType toDelete = createPersonType(UUID, "shortCode_3", "name_3");

        when(personTypeRepository.findById(UUID)).thenReturn(toDelete);

        underTest.deletePersonTypes(new String[] {UUID});

        ArgumentCaptor<PersonType> p = ArgumentCaptor.forClass(PersonType.class);
        verify(personTypeRepository).delete(p.capture());

        PersonType arg = p.getValue();
        assertNotNull(arg);
        assertEquals("shortCode_3", arg.getShortCode());
        assertEquals("name_3", arg.getName());
    }

    private PersonTypeDTO createPersonTypeDTO(String shortCode, String name) {
        return createPersonTypeDTO(null, shortCode, name);
    }

    private PersonTypeDTO createPersonTypeDTO(String uuid, String shortCode, String name) {
        return new PersonTypeDTO(uuid, shortCode, name);
    }

    private PersonType createPersonType(String shortCode, String name) {
       return createPersonType(null, shortCode, name);
    }

    private PersonType createPersonType(String uuid, String shortCode, String name) {
        PersonType personType = new PersonType();
        personType.setId(uuid);
        personType.setShortCode(shortCode);
        personType.setName(name);
        return personType;
    }

}