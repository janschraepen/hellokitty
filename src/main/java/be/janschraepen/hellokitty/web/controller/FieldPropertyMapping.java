package be.janschraepen.hellokitty.web.controller;

import java.util.HashMap;
import java.util.Map;

/**
 * FieldPropertyMapping class. This class contains a Map<String, String> with
 * mapping for form fields and Entity properties. This to improve error handling
 * on ViolationConstraints.
 */
public final class FieldPropertyMapping {

    /**
     * private constructor. no instance allowed.
     */
    private FieldPropertyMapping() {

    }

    public static Map<String, String> fieldProperty = new HashMap<>();

    static {
        // personType
        fieldProperty.put("shortCode", "Code");
        fieldProperty.put("name", "Omschrijving");

    }

}