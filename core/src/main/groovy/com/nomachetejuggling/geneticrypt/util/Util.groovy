package com.nomachetejuggling.geneticrypt.util

class Util {
    public static String shuffle(String s, Random r=new Random()) {
        def x = new ArrayList<String>(s.toList())
        Collections.shuffle(x, r)
        x.join("")
    }

    public static double similarity(String left, String right) {
        left = left.toUpperCase()
        right = right.toUpperCase()

        int differences = Math.abs(left.size() - right.size());
        for(int i=0;i<Math.min(left.size(), right.size());i++) {
            String leftChar = left[i]
            String rightChar = right[i]
            if(!leftChar.equals(rightChar)) {
                differences++
            }
        }
        1-(differences / (double)Math.max(left.size(), right.size()))
    }
}
