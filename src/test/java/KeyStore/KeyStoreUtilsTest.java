package KeyStore;

import org.junit.Test;
import symmetricKeyFolder.SymmetricKey;

import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

import java.security.KeyStore;

import static org.junit.Assert.*;

public class KeyStoreUtilsTest {

    @Test
    public void createPrivateKeyJavaKeyStore() throws Exception{
        SecretKey secretKey = SymmetricKey.createAESKey();
        String secretKeyHex = DatatypeConverter.printHexBinary(secretKey.getEncoded());
        KeyStore keyStore = KeyStoreUtils.createPrivateKeyJavaKeyStore("password", "Foo", secretKey, "keyPassword");
        assertNotNull(keyStore);

        keyStore.load(null,"password".toCharArray());
        KeyStore.ProtectionParameter entryPassword = new KeyStore.PasswordProtection("keyPassword".toCharArray());
        KeyStore.SecretKeyEntry resultKeyEntry = (KeyStore.SecretKeyEntry) keyStore.getEntry("foo", entryPassword);
        SecretKey result = resultKeyEntry.getSecretKey();
        String resultKeyHex = DatatypeConverter.printHexBinary(result.getEncoded());
        assertEquals(secretKeyHex,resultKeyHex);

    }
}