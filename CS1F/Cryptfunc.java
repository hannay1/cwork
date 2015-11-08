import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.UUID;

/*
------need an interface class, similar to exam2------
we need a way of:
*accepting user input for password (8-16 bits)
*parsing password with padding if 8 to 16 bits long
*generate a random SecretKey (16 bit), give to user
*encrypted as a byte []
*prompt user to press enter to encrypt + push password to arraylist 
(of type byte, so an byte array of byte array (byte[][]))


then:

*implement a search method taking user prompt 
(i.e "show array to see array in a table") for password in arraylist (recursion?), 
*type in password #, hit "d" to decrypt
* prompted to enter key, then password is decrypted
*/

public class Cryptfunc extends Interface  {
  static String IV = "XXXXXXXXXXXXXXXX"; //initialization vector
  //static String usrpass = "";
  static String padding = "\0\0\0"; // 
  static String plaintext = ""; //need to figure out a way of padding out user answers 
  static String encryptionKey = "0123456789abcdef";
  public static void main(String [] args) {
    try {
      
      System.out.println(plaintext.length());
      System.out.println(encryptionKey.length());
      System.out.println("==Java==");
      //System.out.println("this is from Interface " + Interface.mess);
      System.out.println("plain:   " + plaintext);

      byte[] cipher = encrypt(plaintext, encryptionKey);

      System.out.print("cipher:  ");
      for (int i=0; i<cipher.length; i++)
      {
      System.out.print(new Integer(cipher[i])+" ");
      }
      System.out.println("");


      String decrypted = decrypt(cipher, encryptionKey);

      System.out.println("decrypt: " + decrypted);

    } catch (Exception e) {
      e.printStackTrace();
    } 
  }

  public static byte[] encrypt(String plainText, String encryptionKey) 
  throws Exception {
    Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE"); //AES/CBC/NoPadding is a 128 bit cipher translation. implements this transformation

    SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES"); //getByte returns the byte[] array

    cipher.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));

    return cipher.doFinal(plainText.getBytes("UTF-8"));
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