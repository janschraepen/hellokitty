package be.janschraepen.hellokitty.util;

import be.janschraepen.hellokitty.domain.person.PersonContact;

import java.util.List;

/**
 * EmailExporter utility class. This class holds functionality to generate lists
 * of email addresses that can be exported.
 */
public final class EmailExporter {

    /**
     * private constructor. No instance allowed.
     */
    private EmailExporter() {

    }

    /**
     * generates a Comma Separated Value list from given list of email contacts.
     * @param emailContacts the email contacts
     * @return String a CSV list of email contacts
     */
    public static String generateCSV(List<PersonContact> emailContacts) {
        StringBuilder builder = new StringBuilder("");
        for (PersonContact contact : emailContacts) {
            builder.append(contact.getValue()).append(",\n");
        }
        return builder.toString();
    }

}
