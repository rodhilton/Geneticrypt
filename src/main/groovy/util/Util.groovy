package util

class Util {
    public static String shuffle(String s, Random r=new Random()) {
        def x = new ArrayList<String>(s.toList())
        Collections.shuffle(x, r)
        x.join("")
    }
}
