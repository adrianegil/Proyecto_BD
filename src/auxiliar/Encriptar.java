package auxiliar;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class Encriptar {

   public static String getMd5(String chain) {
      MessageDigest md5;
      String returned = "";
      try {
         md5 = MessageDigest.getInstance("MD5");
         md5.update(chain.getBytes());
         byte[] keys = md5.digest();
         returned = new String(new BASE64Encoder().encode(keys));
      } catch (NoSuchAlgorithmException e) {
         e.printStackTrace();
      }
      return returned;
   }
}
