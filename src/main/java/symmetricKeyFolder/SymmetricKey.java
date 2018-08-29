package symmetricKeyFolder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;

public class SymmetricKey {

    private static final String AES = "AES";
    private static final String AES_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";

    public static SecretKey createAESKey() throws Exception {
        SecureRandom secureRandom = new SecureRandom();
        KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
        keyGenerator.init(256,secureRandom);
        return keyGenerator.generateKey();

    }

    public static byte[] createInitialisationVector(){
        byte [] initialisationVector = new byte[16];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(initialisationVector);
        return initialisationVector;
    }

    public static byte[] performAESEncryption(String plainText, SecretKey secretKey, byte[] initalisationVector)throws Exception{
        Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initalisationVector);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey,ivParameterSpec);
        return cipher.doFinal(plainText.getBytes());
    }

    public static String performAESDecryption(byte[] cipherText, SecretKey secretKey, byte[] initialisationVector)throws Exception{
        Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initialisationVector);
        cipher.init(Cipher.DECRYPT_MODE, secretKey,ivParameterSpec);
        byte [] result = cipher.doFinal(cipherText);
        return new String(result);
    }

}
