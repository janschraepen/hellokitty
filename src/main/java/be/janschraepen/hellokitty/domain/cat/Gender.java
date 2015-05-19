package be.janschraepen.hellokitty.domain.cat;

/**
 * Gender enum. This enum defines all possible/allowed
 * types of gender.
 */
public enum Gender {

    M("enum.male"),
    V("enum.female");

    private String labelKey;

    /**
     * Instantiates a new Gender.
     * @param labelKey the labelKey
     */
    private Gender(String labelKey) {
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
