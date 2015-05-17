package be.janschraepen.hellokitty.domain.person;

import java.util.ArrayList;
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
        dto.setPersonTypeId(p.getType().getId());
        dto.setPersonType(p.getType().getName());
        dto.setFirstName(p.getFirstName());
        dto.setLastName(p.getLastName());
        dto.setAddressLine1(p.getAddressLine1());
        dto.setAddressLine2(p.getAddressLine2());
        dto.setContacts(createListPersonContactDTOs(p.getContacts()));
        return dto;
    }

    /**
     * Create PersonDTO object.
     *
     * @param id           the uuid
     * @param personTypeId the personType uuid
     * @param firstName    the firstName
     * @param lastName     the lastName
     * @param addressLine1 the addressLine1
     * @param addressLine2 the addressLine2
     * @return PersonDTO object
     */
    public PersonDTO createPersonDTO(String id, String personTypeId, String firstName, String lastName, String addressLine1, String addressLine2) {
        PersonDTO dto = new PersonDTO();
        dto.setId(id);
        dto.setPersonTypeId(personTypeId);
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setAddressLine1(addressLine1);
        dto.setAddressLine2(addressLine2);
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
     * @param id       the uuid
     * @param personId the Person uuid
     * @param type     the ContactType
     * @param value    the value
     * @return PersonContactDTO object
     */
    public PersonContactDTO createPersonContactDTO(String id, String personId, ContactType type, String value) {
        PersonContactDTO dto = new PersonContactDTO();
        dto.setId(id);
        dto.setPersonId(personId);
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
