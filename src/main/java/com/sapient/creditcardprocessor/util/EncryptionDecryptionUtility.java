package com.sapient.creditcardprocessor.util;

import com.sapient.creditcardprocessor.exception.EncryptionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

@Component
@Slf4j
public class EncryptionDecryptionUtility {
  private static SecretKeySpec secretKey;

  private EncryptionDecryptionUtility(@Value("credit-card.encryption.secret:testKey") String secret) {
    secretKey = getKey(secret);
  }

  private SecretKeySpec getKey(String myKey) {
    byte[] key = null;
    MessageDigest sha = null;
    try {
      key = myKey.getBytes("UTF-8");
      sha = MessageDigest.getInstance("SHA-1");
      key = sha.digest(key);
      key = Arrays.copyOf(key, 16);
      return new SecretKeySpec(key, "AES");
    } catch (NoSuchAlgorithmException e) {
      throw new EncryptionException("Error initiating encryption module", e);
    } catch (UnsupportedEncodingException e) {
      throw new EncryptionException("Error initiating encryption module", e);
    }
  }

  public static String encrypt(final String unencryptedData) {

    try {

      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
      cipher.init(Cipher.ENCRYPT_MODE, secretKey);
      String encryptedStr = Base64.getEncoder()
        .encodeToString(cipher.doFinal(unencryptedData.trim().getBytes("UTF-8")));

      return encryptedStr;

    } catch (Exception e) {
      throw new EncryptionException("Error encrypting Credit Number", e);
    }
  }

  public static String decrypt(final String encryptedString) {
    try {

      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
      cipher.init(Cipher.DECRYPT_MODE, secretKey);
      String decryptedStr = new String(cipher.doFinal(Base64.getDecoder().decode(encryptedString.trim())));

      return decryptedStr;

    } catch (Exception e) {
      throw new EncryptionException("Error decrypting Credit Number", e);
    }
  }
}
