package util;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.io.CharStreams;

import javax.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;

public class SampleFrequencies {

    private static String SAMPLE_PATH="/sampletext/";
    private static String[] SAMPLES = new String[]{"hamlet", "novelascortas", "princessofmars", "huckfinn", "aliceinwonderland", "prideandprejudice"};
    
    public static Map<String, Double> getMonograms() {
        return SampleFrequencyHolder.getInstance().getMonograms();
    }

    public static Map<String,Double> getBigrams() {
        return SampleFrequencyHolder.getInstance().getBigrams();
    }

    public static Map<String,Double> getTrigrams() {
        return SampleFrequencyHolder.getInstance().getTrigrams();
    }



    private static class SampleFrequencyHolder {
        private static final SampleFrequencyHolder INSTANCE = new SampleFrequencyHolder();
        private FrequencyAnalyzer frequencyAnalyzer;

        public static synchronized SampleFrequencyHolder getInstance() {
            return INSTANCE;
        }

        private SampleFrequencyHolder() {    
            Collection<String> samples = ResourceList.getResources(Pattern.compile(".*/sampletext/.*"));
            
            String[] shit = Lists.transform(Arrays.asList(SAMPLES), new Function<String, String>() {

                @Override
                public String apply(String sample) {
                    InputStream is = getClass().getResourceAsStream(SAMPLE_PATH+sample);
                    try {
                        return CharStreams.toString(new InputStreamReader(is));
                    } catch (IOException e) {
                        throw new RuntimeException("The was a major problem loading "+sample, e);
                    }

                }
            }).toArray(new String[]{});

            frequencyAnalyzer = new FrequencyAnalyzer(shit);
        }


        public Map<String, Double> getMonograms() {
            return frequencyAnalyzer.getMonograms();
        }

        public Map<String,Double> getBigrams() {
            return frequencyAnalyzer.getBigrams();
        }

        public Map<String,Double> getTrigrams() {
            return frequencyAnalyzer.getTrigrams();
        }
    }
}
