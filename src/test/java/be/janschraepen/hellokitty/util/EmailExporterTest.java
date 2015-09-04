package be.janschraepen.hellokitty.util;

import be.janschraepen.hellokitty.domain.person.ContactType;
import be.janschraepen.hellokitty.domain.person.PersonContact;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class EmailExporterTest {

    @Test
    public void testGenerateCSV() throws Exception {
        PersonContact emailContact_1 = createPersonContact("uuid_1", ContactType.EMAIL, "email_1");
        PersonContact emailContact_2 = createPersonContact("uuid_2", ContactType.EMAIL, "email_2");

        List<PersonContact> emailContacts = Arrays.asList(emailContact_1, emailContact_2);

        String result = EmailExporter.generateCSV(emailContacts);
        assertNotNull(result);
        assertEquals("email_1,\nemail_2,\n", result);
    }

    private PersonContact createPersonContact(String uuid, ContactType type, String email) {
        PersonContact personContact = new PersonContact();
        personContact.setId(uuid);
        personContact.setType(type);
        personContact.setValue(email);
        return personContact;
    }

}