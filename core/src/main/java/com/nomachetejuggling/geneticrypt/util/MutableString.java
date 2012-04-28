package com.nomachetejuggling.geneticrypt.util;

public class MutableString implements CharSequence {
    String string = null;

    public void set(String s) {
        string = s;
    }

    public String toString() {
        return string;
    }

    @Override
    public int length() {
        return string.length();
    }

    @Override
    public char charAt(int i) {
        return string.charAt(i);
    }

    @Override
    public CharSequence subSequence(int i, int i1) {
        return string.subSequence(i, i1);
    }
}
