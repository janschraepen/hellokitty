package be.janschraepen.hellokitty.domain.person;

import be.janschraepen.hellokitty.domain.cat.CatDTO;
import org.apache.commons.collections4.CollectionUtils;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

/**
 * Person Data Transfer Object. Used for transferring data
 * throughout the layers.
 */
public class PersonDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String firstName;

    private String lastName;

    private String addressLine1;

    private String addressLine2;

    private String extraInfo;

    private List<PersonContactDTO> contacts;

    private List<CatDTO> cats;

    /**
     * Instantiates a new PersonDTO.
     */
    public PersonDTO() {

    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the addressLine1
     */
    public String getAddressLine1() {
        return addressLine1;
    }

    /**
     * @param addressLine1 the addressLine1 to set
     */
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    /**
     * @return the addressLine2
     */
    public String getAddressLine2() {
        return addressLine2;
    }

    /**
     * @param addressLine2 the addressLine2 to set
     */
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    /**
     * @return the extra info
     */
    public String getExtraInfo() {
        return extraInfo;
    }

    /**
     * @param extraInfo the extra info to set
     */
    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    /**
     * @return the contacts
     */
    public List<PersonContactDTO> getContacts() {
        return contacts;
    }

    /**
     * @param contacts the contacts to set
     */
    public void setContacts(List<PersonContactDTO> contacts) {
        this.contacts = contacts;
    }

    /**
     * @return the cats
     */
    public List<CatDTO> getCats() {
        return cats;
    }

    /**
     * @param cats the cats to set
     */
    public void setCats(List<CatDTO> cats) {
        this.cats = cats;
    }

    /**
     * @return a String with contact information separated by <br/> tags
     */
    public String getContactInfo() {
        StringBuilder builder = new StringBuilder();
        if (CollectionUtils.isNotEmpty(contacts)) {
            Iterator<PersonContactDTO> contactIter = contacts.iterator();
            while (contactIter.hasNext()) {
                builder.append(((PersonContactDTO) contactIter.next()).getValue());
                if (contactIter.hasNext()) {
                    builder.append("<br/>");
                }
            }
        }
        return builder.toString();
    }

}
