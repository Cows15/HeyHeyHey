package assymmetricKeyFolder;

import org.junit.Test;

import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.security.KeyPair;

import static org.junit.Assert.*;

public class assymmetricKeyTest {

    @Test
    public void generateRSAKeyPair() throws Exception{

        KeyPair keyPair = assymmetricKey.generateRSAKeyPair();
        assertNotNull(keyPair);
        System.out.println("Private Key = " + DatatypeConverter.printHexBinary(keyPair.getPrivate().getEncoded()));
        System.out.println("Public Key  = " + DatatypeConverter.printHexBinary(keyPair.getPublic().getEncoded()));
    }

    @Test
    public void testRSACryptoRoutine () throws Exception{
        KeyPair keyPair = assymmetricKey.generateRSAKeyPair();
        String plainText = "This data will be hiding in plain sight";
        byte[] cipherText = assymmetricKey.performRSAEncryption(plainText,keyPair.getPrivate());
        assertNotNull(cipherText);
        System.out.println(DatatypeConverter.printHexBinary(cipherText));
        String decryptedText = assymmetricKey.performRSADecryption(cipherText,keyPair.getPublic());
        assertEquals(plainText,decryptedText);
    }
}