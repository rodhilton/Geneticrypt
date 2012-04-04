package com.geneticrypt.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;
import static org.junit.matchers.JUnitMatchers.hasItems;

public class DictionaryTest {

    private static final int HOW_MANY_TO_RANDOMLY_CHECK = 1000;

    @Test
    public void shouldHaveSameCounts() {
        assertThat(new Dictionary().asArray().length, is(new Dictionary().asList().size()));
        assertThat(new Dictionary().asList().size(), is(new Dictionary().asSet().size()));
    }

    @Test
    public void shouldHaveLotsOfWords() {
        Set<String> dictionary = new Dictionary().asSet();

        assertThat(dictionary.size(), is(greaterThan(1000)));
    }

    @Test
    public void shouldConstructQuickly() {
        //Store each dictionary to prevent the JVM from optimizing away the construction of dictionaries.
        List<Dictionary> dictionaries = new ArrayList<Dictionary>();

        long startTime = System.currentTimeMillis();

        for(int i=0;i<10000;i++) {
            dictionaries.add(new Dictionary());
        }

        long endTime = System.currentTimeMillis();

        assertThat(endTime - startTime, lessThan(100L));
    }

    @Test
    public void shouldContainCommonWords() {
        Set<String> dictionary = new Dictionary().asSet();

        assertThat(dictionary, hasItems("i", "saw", "the", "a", "quick", "quickest", "quicker", "brown", "fox", "jumps", "jumped", "right", "over", "the", "lazy", "laziest", "lazier", "dog", "dogs"));
    }

    @Test
    public void shouldAllBeIdentical() {
        String[] wordArray = new Dictionary().asArray();

        Random rand = new Random();

        for (int i = 0; i < HOW_MANY_TO_RANDOMLY_CHECK; i++) {
            int randomIndex = rand.nextInt(wordArray.length);
            String currentWord = wordArray[randomIndex];

            assertThat(new Dictionary().asList().get(randomIndex), is(currentWord));
            assertThat(new Dictionary().asSet(), hasItem(currentWord));
        }
    }

    @Test
    public void arrayShouldBeSorted() {
        String[] wordArray = new Dictionary().asArray();

        Random rand = new Random();

        for (int i = 0; i < HOW_MANY_TO_RANDOMLY_CHECK; i++) {
            int randomIndex = rand.nextInt(wordArray.length - 1);
            String currentWord = wordArray[randomIndex];
            String nextWord = wordArray[randomIndex + 1];
            assertThat(nextWord, greaterThan(currentWord));
        }
    }

    @Test
    public void shouldNotContainNull() {
        for (String word : new Dictionary().asList()) {
            assertThat(word, notNullValue());
        }
    }

    @Test
    public void shouldOnlyContainValidLowerCaseWords() {
        for (String word : new Dictionary().asSet()) {
            assertThat("[" + word + "] was not valid", word.matches("^[a-z]+$"), is(true));
        }
    }
}
