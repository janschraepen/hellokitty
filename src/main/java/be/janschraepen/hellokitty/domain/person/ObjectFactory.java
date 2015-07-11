package be.janschraepen.hellokitty.domain.person;

import be.janschraepen.hellokitty.domain.cat.CatDTO;
import be.janschraepen.hellokitty.domain.cat.CatPerson;
import be.janschraepen.hellokitty.domain.persontype.PersonType;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * ObjectFactory class. This class must be used to create new instances
 * of PersonDTO.
 */
public final class ObjectFactory {

    private static ObjectFactory instance;

    /**
     * Get the ObjectFactory instance.
     *
     * @return ObjectFactory instance
     */
    public static ObjectFactory getInstance() {
        if (instance == null) {
            instance = new ObjectFactory();
        }
        return instance;
    }

    /**
     * Create PersonDTO object.
     *
     * @param p the Person
     * @return PersonDTO object
     */
    public PersonDTO createPersonDTO(Person p) {
        PersonDTO dto = new PersonDTO();
        dto.setId(p.getId());
        dto.setFirstName(p.getFirstName());
        dto.setLastName(p.getLastName());
        dto.setAddressLine1(p.getAddressLine1());
        dto.setAddressLine2(p.getAddressLine2());
        dto.setExtraInfo(p.getExtraInfo());
        dto.setContacts(createListPersonContactDTOs(p.getContacts()));

        if (CollectionUtils.isNotEmpty(p.getCatPersons())) {
            List<CatDTO> cats = new ArrayList<>();
            for (CatPerson catPerson : p.getCatPersons()) {
                if (PersonType.OWNER.equals(catPerson.getType().getShortCode())) {
                    cats.add(be.janschraepen.hellokitty.domain.cat.ObjectFactory.getInstance().createCatDTO(catPerson.getCat()));
                }
            }
            dto.setCats(cats);
        }

        return dto;
    }

    /**
     * Create PersonDTO object.
     *
     * @param id           the uuid
     * @param firstName    the firstName
     * @param lastName     the lastName
     * @param addressLine1 the addressLine1
     * @param addressLine2 the addressLine2
     * @param extraInfo    the extraInfo
     * @return PersonDTO object
     */
    public PersonDTO createPersonDTO(String id, String firstName, String lastName, String addressLine1, String addressLine2, String extraInfo) {
        PersonDTO dto = new PersonDTO();
        dto.setId(id);
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setAddressLine1(addressLine1);
        dto.setAddressLine2(addressLine2);
        dto.setExtraInfo(extraInfo);
        return dto;
    }

    /**
     * Create List of PersonDTO objects.
     *
     * @param l the list of Persons
     * @return List<PersonDTO> a list of PersonDTOs
     */
    public List<PersonDTO> createListPersonDTOs(List<Person> l) {
        List<PersonDTO> list = new ArrayList<>();
        for (Person p : l) {
            list.add(createPersonDTO(p));
        }
        return list;
    }

    /**
     * Create PersonContactDTO object.
     *
     * @param p the PersonContact
     * @return PersonContactDTO object
     */
    public PersonContactDTO createPersonContactDTO(PersonContact p) {
        PersonContactDTO dto = new PersonContactDTO();
        dto.setId(p.getId());
        dto.setPersonId(p.getPerson().getId());
        dto.setType(p.getType());
        dto.setValue(p.getValue());
        return dto;
    }

    /**
     * Create PersonContactDTO object.
     *
     * @param id    the uuid
     * @param type  the ContactType
     * @param value the value
     * @return PersonContactDTO object
     */
    public PersonContactDTO createPersonContactDTO(String id, ContactType type, String value) {
        PersonContactDTO dto = new PersonContactDTO();
        dto.setPersonId(id);
        dto.setType(type);
        dto.setValue(value);
        return dto;
    }

    /**
     * Create List of PersonContactDTO objects.
     *
     * @param l the list of PersonContacts
     * @return List<PersonContactDTO> a list of PersonContactDTOs
     */
    public List<PersonContactDTO> createListPersonContactDTOs(List<PersonContact> l) {
        List<PersonContactDTO> list = new ArrayList<>();
        if (l != null) {
            for (PersonContact p : l) {
                list.add(createPersonContactDTO(p));
            }
        }
        return list;
    }

}
