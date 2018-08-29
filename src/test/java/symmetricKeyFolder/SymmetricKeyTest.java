package symmetricKeyFolder;

import org.junit.Test;
import symmetricKeyFolder.SymmetricKey;

import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

public class SymmetricKeyTest {


    @Test
    public void createAESKey() throws Exception {
        SecretKey secretKey = SymmetricKey.createAESKey();
        assertNotNull(secretKey);
        System.out.println(DatatypeConverter.printHexBinary(secretKey.getEncoded()));

    }

    @Test
    public void testAECCryptoRoutine() throws Exception {
        SecretKey key = SymmetricKey.createAESKey();
        byte[] initializationVector = SymmetricKey.createInitialisationVector();
        String plaintText = "This is the text we are going to test";
        byte[] cipherText = SymmetricKey.performAESEncryption(plaintText,key,initializationVector);
        assertNotNull(cipherText);
        System.out.println(DatatypeConverter.printHexBinary(cipherText));
        String decryptedText = SymmetricKey.performAESDecryption(cipherText,key,initializationVector);
        assertEquals(plaintText,decryptedText);
    }
}
