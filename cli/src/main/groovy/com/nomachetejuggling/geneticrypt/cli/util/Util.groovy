package com.nomachetejuggling.geneticrypt.cli.util

class Util {
    static String readFromStdin() {
        StringBuilder plaintext = new StringBuilder();
        InputStreamReader isReader = new InputStreamReader(System.in);
        BufferedReader bufReader = new BufferedReader(isReader);
        while (true) {
            try {
                String inputStr = null;
                if ((inputStr = bufReader.readLine()) != null) {
                    plaintext.append(inputStr)
                    plaintext.append("\n")
                }
                else {
                    break;
                }
            }
            catch (Exception e) {
                throw new RuntimeException(e)
            }
        }
        plaintext.toString()
    }
}
