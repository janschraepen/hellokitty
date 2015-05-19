package be.janschraepen.hellokitty.web;

/**
 * Event class. Used for defining EVENTs that can be used within
 * the Controller classes.
 */
public final class Event {

    /**
     * private constructor. no instance allowed.
     */
    private Event() {

    }

    public static final String NEW = "new";
    public static final String EDIT = "edit";
    public static final String SAVE = "save";
    public static final String DELETE = "delete";
    public static final String SEARCH = "search";
    public static final String BACK = "back";

    public static final String DELETE_CONTACT = "delete-contact";
    public static final String ADD_CONTACT = "add-contact";

}
