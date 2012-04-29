package com.nomachetejuggling.geneticrypt.util;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.io.CharStreams;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.collect.Lists.newArrayList;

public class SampleText {

    private static final String SAMPLE_PATH = SampleFrequencies.class.getPackage().getName().replace(".","/");
    public static final Pattern SAMPLE_TEXT_PATTERN = Pattern.compile(".*" + SAMPLE_PATH + "/(.*\\.txt)$");

    public static String getText() {
        return SampleTextHolder.getInstance().getText();
    }

    private static class SampleTextHolder {
        public static final Pattern PATTERN = Pattern.compile("[A-Za-z]");
        private static final SampleTextHolder INSTANCE = new SampleTextHolder();
        private String text;

        public static synchronized SampleTextHolder getInstance() {
            return INSTANCE;
        }

        private SampleTextHolder() {
            List<String> samples = newArrayList(ResourceList.getResources(SAMPLE_TEXT_PATTERN));

            final StringBuilder textBuilder = new StringBuilder();

            for(String samplePath: samples) {

                    Matcher matcher = SAMPLE_TEXT_PATTERN.matcher(samplePath);
                    if (matcher.matches()) {
                        String sampleName = matcher.group(1);
                        try {
                            InputStream is = getClass().getResourceAsStream(sampleName);
                            Reader r = new InputStreamReader(is, "US-ASCII");
                            int intch;
                            while ((intch = r.read()) != -1) {
                                char ch = (char) intch;
                                if(PATTERN.matcher(ch + "").matches()) {
                                    textBuilder.append(ch);
                                }
                            }
                        } catch (IOException e) {
                            throw new RuntimeException("The was a major problem loading " + sampleName, e);
                        }
                    } else {
                        throw new RuntimeException("The was a major problem loading " + samplePath);
                    }
                }

            text = textBuilder.toString();
        }


        public String getText() {
            return text;
        }
    }
}
