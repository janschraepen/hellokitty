package be.janschraepen.hellokitty.domain.person;

/**
 * ContactType enum. This enum defines all possible/allowed
 * types of contact.
 */
public enum ContactType {

    TELEPHONE("enum.telephone"),
    CELLULAR("enum.cellular"),
    EMAIL("enum.email");

    private String labelKey;

    /**
     * Instantiates a new ContactType.
     * @param labelKey the labelKey
     */
    private ContactType(String labelKey) {
        this.labelKey = labelKey;
    }

    /**
     *
     * @return the labelKey
     */
    public String getLabelKey() {
        return labelKey;
    }

}
