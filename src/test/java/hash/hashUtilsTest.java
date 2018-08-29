package hash;

import org.junit.Test;

import javax.xml.bind.DatatypeConverter;
import javax.xml.crypto.Data;

import java.util.UUID;

import static org.junit.Assert.*;

public class hashUtilsTest {

    @Test
    public void generateRandomSalt() {
        byte[] salt = hashUtils.generateRandomSalt();
        assertNotNull(salt);
        System.out.println(DatatypeConverter.printHexBinary(salt));
    }

    @Test
    public void createSHA2Hash() throws Exception{
        byte[] salt = hashUtils.generateRandomSalt();
        String value = UUID.randomUUID().toString();
        byte [] hash = hashUtils.createSHA2Hash(value,salt);
        assertNotNull(hash);
        byte [] hash2 = hashUtils.createSHA2Hash(value, salt);
        assertEquals(DatatypeConverter.printHexBinary(hash),DatatypeConverter.printHexBinary(hash2));
    }

    @Test
    public void testPasswordRoutine(){
        String secretPhrase = "correct horse battery staple";
        String passwordHash = hashUtils.hasPassword(secretPhrase);
        System.out.println(passwordHash);
        assertTrue(hashUtils.verifyPassword(secretPhrase,passwordHash));
    }
}