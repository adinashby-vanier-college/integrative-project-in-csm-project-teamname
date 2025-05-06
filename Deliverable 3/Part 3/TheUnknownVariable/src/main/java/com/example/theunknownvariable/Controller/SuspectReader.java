package com.example.theunknownvariable.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class SuspectReader {
    private static final String FILE_NAME = "/Info.csv";  // Correct path
    private Map<String, String> suspectData = new HashMap<>();

    public SuspectReader() {
        loadSuspectData();
    }

    private void loadSuspectData() {
        try (InputStream is = getClass().getResourceAsStream(FILE_NAME);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

            if (is == null) {
                throw new IOException("File not found: " + FILE_NAME);
            }

            String line;
            boolean firstLine = true; // Skip header

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] details = line.split(",");
                if (details.length >= 5) {
                    String name = details[0].trim();
                    String height = details[1].trim();
                    String glasses = details[2].trim();
                    String occupation = details[3].trim();
                    String eyes = details[4].trim();

                    String suspectInfo = "Name: " + name + "\n" +
                            "Height: " + height + "\n" +
                            "Glasses: " + glasses + "\n" +
                            "Occupation: " + occupation + "\n" +
                            "Eyes: " + eyes;

                    suspectData.put(name, suspectInfo);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading the CSV file: " + FILE_NAME);
        }
    }

    public String getSuspectInfo(String name) {
        return suspectData.getOrDefault(name, "No information available.");
    }
}