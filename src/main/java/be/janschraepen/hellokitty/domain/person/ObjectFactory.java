package be.janschraepen.hellokitty.domain.person;

import be.janschraepen.hellokitty.domain.persontype.PersonType;
import be.janschraepen.hellokitty.domain.persontype.PersonTypeDTO;

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
    public PersonDTO createDTO(Person p) {
        PersonDTO dto = new PersonDTO();
        dto.setId(p.getId());
        dto.setPersonTypeId(p.getType().getId());
        dto.setFirstName(p.getFirstName());
        dto.setLastName(p.getLastName());
        dto.setAddressLine1(p.getAddressLine1());
        dto.setAddressLine2(p.getAddressLine2());
        dto.setTelephone(p.getTelephone());
        dto.setGsm(p.getGsm());
        dto.setEmail(p.getEmail());
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
     * @param telephone    the telephone
     * @param gsm          the gsm
     * @param email        the email
     * @return PersonDTO object
     */
    public PersonDTO createDTO(String id, String personTypeId, String firstName, String lastName, String addressLine1, String addressLine2, String telephone, String gsm, String email) {
        PersonDTO dto = new PersonDTO();
        dto.setId(id);
        dto.setPersonTypeId(personTypeId);
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setAddressLine1(addressLine1);
        dto.setAddressLine2(addressLine2);
        dto.setTelephone(telephone);
        dto.setGsm(gsm);
        dto.setEmail(email);
        return dto;
    }

    /**
     * Create List of PersonDTO objects.
     *
     * @param l the list of Persons
     * @return List<PersonDTO> a list of PersonDTOs
     */
    public List<PersonDTO> createListDTOs(List<Person> l) {
        List<PersonDTO> list = new ArrayList<>();
        for (Person p : l) {
            list.add(createDTO(p));
        }
        return list;
    }

}
