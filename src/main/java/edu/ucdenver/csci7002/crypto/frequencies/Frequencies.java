package edu.ucdenver.csci7002.crypto.frequencies;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.io.ByteStreams.toByteArray;

public class Frequencies {
    private static Frequencies instance;
    private Map<Character, Double> frequencyMap;
    private static Pattern frequencyPattern=Pattern.compile("^([A-Za-z]) ([0-9.]+$)");

    public static synchronized Frequencies getInstance() {
        if (instance == null) {
            instance = new Frequencies();
        }
        return instance;
    }

    private Frequencies() {
        ResourceBundle bundle = ResourceBundle.getBundle("project");
        String frequencies = bundle.getString("frequencies.file");

        InputStream in = this.getClass().getClassLoader()
                .getResourceAsStream(frequencies);

        try {
            frequencyMap = newHashMap();
            String thisLine;

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            while ((thisLine = reader.readLine()) != null) {
                Matcher m = frequencyPattern.matcher(thisLine.trim());
                if(m.matches()) {
                    Character letter = m.group(1).toUpperCase().charAt(0);
                    Double freq = Double.parseDouble(m.group(2));
                    frequencyMap.put(letter, freq);
                }
            }
            System.out.println(frequencyMap);
        } catch (IOException e) {
            throw new RuntimeException("Unable to load letter frequences", e);
        }
    }

}
