package com.nomachetejuggling.geneticrypt.ciphers

class MonoSubstitutionCipher implements Cipher {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    private String key
    private Map<String, String> reverseMap
    private Map<String, String> map

    MonoSubstitutionCipher(String key) {

        if(key.toList().sort().join("") != ALPHABET) throw new RuntimeException("Key ${key} invalid")

        this.key = key
        this.map = [:]
        this.reverseMap = [:]
        for (int i = 0; i < ALPHABET.length(); i++) {
            map[ALPHABET[i]] = key[i]
            reverseMap[key[i]] = ALPHABET[i]
        }
    }

    @Override
    String encrypt(String plaintext) {
        plaintext.toUpperCase().toList().collect {plainChar ->
            if (map.containsKey(plainChar)) {
                map[plainChar]
            } else {
                plainChar
            }
        }.join("")
    }

    @Override
    String decrypt(String ciphertext) {
        ciphertext.toUpperCase().toList().collect {cipherChar ->
            if (reverseMap.containsKey(cipherChar)) {
                reverseMap[cipherChar]
            } else {
                cipherChar
            }
        }.join("")
    }
}
