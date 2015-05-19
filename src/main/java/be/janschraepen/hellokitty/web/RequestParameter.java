package be.janschraepen.hellokitty.web;

/**
 * RequestParameter class. Used for defining Request parameters that
 * can be used within the Controller classes.
 */
public final class RequestParameter {

    /**
     * private constructor. No instance allowed.
     */
    private RequestParameter() {

    }

    public static final String ACTION_URL = "actionUrl";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String LIST_ITEMS = "listItems";
    public static final String ENTITY = "entity";

    public static final String SEARCH = "search";
    public static final String UUID = "uuid";

    public static final String SHORT_CODE = "shortCode";
    public static final String NAME = "name";

    public static final String PERSONTYPES = "personTypes";
    public static final String CONTACTTYPES = "contactTypes";

    public static final String PERSON_TYPE_ID = "personTypeId";
    public static final String FIRSTNAME = "firstName";
    public static final String LASTNAME = "lastName";
    public static final String ADDRESSLINE1 = "addressLine1";
    public static final String ADDRESSLINE2 = "addressLine2";

    public static final String CONTACT_UUID = "contact-uuid";
    public static final String CONTACT_TYPE = "contactType";
    public static final String CONTACT_VALUE = "contactValue";

    public static final String ACTIVE_TAB = "activeTab";

}
