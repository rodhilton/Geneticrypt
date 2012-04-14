package com.nomachetejuggling.geneticrypt.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;

public final class Dictionary {

    public Set<String> asSet() {
        return DictionaryHolder.getInstance().getSet();
    }

    public List<String> asList() {
        return DictionaryHolder.getInstance().getList();
    }

    public String[] asArray() {
        return DictionaryHolder.getInstance().getArray();
    }

    private static class DictionaryHolder {

        private static final Pattern wordPattern = Pattern.compile("^[a-z]+$");

        private static final DictionaryHolder INSTANCE = new DictionaryHolder();

        public static synchronized DictionaryHolder getInstance() {
            return INSTANCE;
        }

        private List<String> wordList;
        private Set<String> wordSet;
        private String[] wordArray;

        private DictionaryHolder() {

            try {
                //Using a sorted Set so that we don't have to sort the huge input later
                wordSet = new TreeSet<String>();

                InputStream in = this.getClass().getClassLoader().getResourceAsStream("dictionary.txt");
                String line;
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                while ((line = reader.readLine()) != null && !line.isEmpty()) {
                    String word = line.trim().toLowerCase();
                    if ( wordPattern.matcher(word).find() ) {
                        wordSet.add(word);
                    } else {
                        throw new RuntimeException("Word " + word + " was in supplied dictionary.  This is probably an error");
                    }
                }

                //Build the list and array versions from the set to preserve uniqueness and ordering
                wordList = new ArrayList<String>(wordSet);
                wordArray = wordList.toArray(new String[]{});

            } catch (IOException e) {
                throw new RuntimeException("Could not load word list.  This is likely an environmental error.");
            }
        }

        public Set<String> getSet() { return wordSet; }

        public List<String> getList() { return wordList; }

        public String[] getArray() { return wordArray; }

    }
}
