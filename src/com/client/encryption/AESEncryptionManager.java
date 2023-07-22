package com.client.encryption;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException; 
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class AESEncryptionManager {
	
	public static byte[] decrypt(byte[] data, String password) {
		byte[] resultBytes = null;
		try {
			resultBytes = decryptData(password, data);
		} catch (InvalidKeyException e) { 
			e.printStackTrace();
		} catch (NoSuchPaddingException e) { 
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) { 
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) { 
			e.printStackTrace();
		} catch (BadPaddingException e) { 
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) { 
			e.printStackTrace();
		} catch (InvalidKeySpecException e) { 
			e.printStackTrace();
		}
		return resultBytes;
	}
	
	private static byte [] decryptData(String key, byte [] encryptedData) 
			throws NoSuchPaddingException, 
			NoSuchAlgorithmException, 
			InvalidAlgorithmParameterException, 
			InvalidKeyException, 
			BadPaddingException, 
			IllegalBlockSizeException, 
			InvalidKeySpecException {
		ByteBuffer byteBuffer = ByteBuffer.wrap(encryptedData);
		int noonceSize = byteBuffer.getInt();
		if (noonceSize < 12 || noonceSize >= 16)
			return null;
		byte[] iv = new byte[noonceSize];
		byteBuffer.get(iv);
		SecretKey secretKey = generateSecretKey(key, iv);
		byte[] cipherBytes = new byte[byteBuffer.remaining()];
		byteBuffer.get(cipherBytes);
		Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
		GCMParameterSpec parameterSpec = new GCMParameterSpec(128, iv);
		cipher.init(Cipher.DECRYPT_MODE, secretKey, parameterSpec);
		return cipher.doFinal(cipherBytes);
	}
	
	private static SecretKey generateSecretKey(String password, byte [] iv) throws NoSuchAlgorithmException, InvalidKeySpecException {
		KeySpec spec = new PBEKeySpec(password.toCharArray(), iv, 65536, 128); // AES-128
		SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] key = secretKeyFactory.generateSecret(spec).getEncoded();
		return new SecretKeySpec(key, "AES");
	}
}