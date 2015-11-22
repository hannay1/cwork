import java.security.MessageDigest;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.UUID;


public class Cryptfunc extends Interface  {
  static String IV = "23KNZ1L4HFBB7WBE"; //fixed size initialization vector, same length as encryption key
  
  public static byte[] encrypt(String plainText, String encryptionKey) 
  throws Exception {
    Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE"); //AES/CBC/NoPadding is a 128 bit cipher translation. implements this transformation with sunjce provider
    SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES"); //getByte returns the byte array via AES
    cipher.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));//sets cipher to encrypt, takes in bytes parsed from IV along with key in byte format
    return cipher.doFinal(plainText.getBytes("UTF-8")); //concludes encryption 
  }

  public static String decrypt(byte[] cipherText, String encryptionKey) 
  throws 
  Exception{

    Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
    SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
    cipher.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
    return new String(cipher.doFinal(cipherText),"UTF-8");
  }
}