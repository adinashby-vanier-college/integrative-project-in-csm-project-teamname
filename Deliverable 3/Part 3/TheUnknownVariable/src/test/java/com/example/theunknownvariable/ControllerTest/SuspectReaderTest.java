package com.example.theunknownvariable.ControllerTest;

import com.example.theunknownvariable.Controller.SuspectReader;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SuspectReaderTest {

    @Test
    public void testGetSuspectInfo_existingSuspect() {
        SuspectReader reader = new SuspectReader();
        String info = reader.getSuspectInfo("Sara");

        System.out.println("Returned info:\n" + info); // debug line

        assertTrue(info.contains("Height: 175"));
    }

    @Test
    void testGetSuspectInfo_nonExistingSuspect() {
        SuspectReader reader = new SuspectReader();
        String info = reader.getSuspectInfo("Nonexistent Name");
        assertEquals("No information available.", info);
    }
}