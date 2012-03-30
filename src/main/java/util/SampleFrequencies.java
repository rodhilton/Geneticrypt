package util;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.io.CharStreams;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.collect.Lists.newArrayList;

public class SampleFrequencies {

    private static final String SAMPLE_PATH = "/sampletext/";
    private static final Pattern SAMPLE_TEXT_PATTERN = Pattern.compile(".*" + SAMPLE_PATH + "(.*)$");

    public static Map<String, Double> getMonograms() {
        return SampleFrequencyHolder.getInstance().getMonograms();
    }

    public static Map<String, Double> getBigrams() {
        return SampleFrequencyHolder.getInstance().getBigrams();
    }

    public static Map<String, Double> getTrigrams() {
        return SampleFrequencyHolder.getInstance().getTrigrams();
    }

    private static class SampleFrequencyHolder {
        private static final SampleFrequencyHolder INSTANCE = new SampleFrequencyHolder();
        private FrequencyAnalyzer frequencyAnalyzer;

        public static synchronized SampleFrequencyHolder getInstance() {
            return INSTANCE;
        }

        private SampleFrequencyHolder() {
            List<String> samples = newArrayList(ResourceList.getResources(SAMPLE_TEXT_PATTERN));

            String[] sampleContents = Lists.transform(samples, new Function<String, String>() {

                @Override
                public String apply(String samplePath) {
                    Matcher matcher = SAMPLE_TEXT_PATTERN.matcher(samplePath);
                    if (matcher.matches()) {
                        String sampleName = matcher.group(1);
                        InputStream is = getClass().getResourceAsStream(SAMPLE_PATH + sampleName);
                        try {
                            return CharStreams.toString(new InputStreamReader(is));
                        } catch (IOException e) {
                            throw new RuntimeException("The was a major problem loading " + sampleName, e);
                        }
                    } else {
                        throw new RuntimeException("The was a major problem loading " + samplePath);
                    }
                }
            }).toArray(new String[]{});

            frequencyAnalyzer = new FrequencyAnalyzer(sampleContents);
        }


        public Map<String, Double> getMonograms() {
            return frequencyAnalyzer.getMonograms();
        }

        public Map<String, Double> getBigrams() {
            return frequencyAnalyzer.getBigrams();
        }

        public Map<String, Double> getTrigrams() {
            return frequencyAnalyzer.getTrigrams();
        }
    }
}
