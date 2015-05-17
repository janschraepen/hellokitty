package be.janschraepen.hellokitty.domain.person;

import org.junit.Test;

import static org.junit.Assert.*;

public class ContactTypeTest {

    @Test
    public void testGetLabelKey() throws Exception {
        assertEquals("enum.telephone", ContactType.TELEPHONE.getLabelKey());
        assertEquals("enum.cellular", ContactType.CELLULAR.getLabelKey());
        assertEquals("enum.email", ContactType.EMAIL.getLabelKey());
    }

}