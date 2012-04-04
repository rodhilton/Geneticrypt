package com.geneticrypt.ciphers

import org.junit.Test
import static org.hamcrest.Matchers.equalTo
import static org.junit.Assert.assertThat

public class MonoSubstitutionCipherTest {

    @Test
    void shouldNoop() {
        def cipher = new MonoSubstitutionCipher("ABCDEFGHIJKLMNOPQRSTUVWXYZ")
        assertThat(cipher.encrypt("HELLO"), equalTo("HELLO"))
    }

    @Test
    void shouldIgnoreSpacingAndPunctuation() {
        def cipher = new MonoSubstitutionCipher("ABCDEFGHIJKLMNOPQRSTUVWXYZ")
        assertThat(cipher.encrypt("'HELLO,' SHE SAID!"), equalTo("'HELLO,' SHE SAID!"))
    }


    @Test
    void shouldEncrypt() {
        def cipher = new MonoSubstitutionCipher("BCDEFGHIJKLMNOPQRSTUVWXYZA")
        assertThat(cipher.encrypt("HELLO"), equalTo("IFMMP"))
    }

    @Test
    void shouldEncryptLowerCase() {
        def cipher = new MonoSubstitutionCipher("BCDEFGHIJKLMNOPQRSTUVWXYZA")
        assertThat(cipher.encrypt("hello"), equalTo("IFMMP"))
    }

    @Test(expected = Exception)
    void shouldRequireEveryLetter() {
        new MonoSubstitutionCipher("ABCDEFGHIJKLMNOPQRSTUVWXY")
    }

    @Test(expected = Exception)
    void shouldNotAllowDuplicates() {
        new MonoSubstitutionCipher("AAAABCDEFGHIJKLMNOPQRSTUVWXY")
    }

    @Test
    void shouldBeReversible() {
        def random = new Random()

        def alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        def key = alphabet.toList().sort {random.nextInt() }.join("")
        def plaintext = (0..50).collect {alphabet[random.nextInt(alphabet.length())]}.join("")

        def cipher = new MonoSubstitutionCipher(key)

        def ciphertext = cipher.encrypt(plaintext)

        assertThat(cipher.decrypt(ciphertext), equalTo(plaintext))
    }
}
