package com.atos.rmg.util;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.ByteBuffer;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.log4j.Logger;

import com.atos.rmg.controller.Controller;

/**
 * Class used for encrypting / decrypting the values
 *
 */
public class EncryptableUtilities 
{

	private static final Logger	logger = Logger.getLogger(EncryptableUtilities.class);
	private static Base64 decoder = new Base64();
	private static Base64 encoder = new Base64();
	private static final String transformation = "PBEWithMD5AndDES/CBC/PKCS5Padding";
	private static final String algorithm = "PBEWithMD5AndDES";
	private Cipher encrypter, decrypter;
	private static byte[] salt = new byte[8];
	private static EncryptableUtilities	instance = null;
	final static Logger log = Logger.getLogger(Controller.class.getName());

	static {
		// Hardcoded salt value.
		ByteBuffer.wrap(salt).putLong(8862794074839226269L);
	}

	
	private EncryptableUtilities() throws Exception {
		PBEParameterSpec ps = new javax.crypto.spec.PBEParameterSpec(salt, 20);
		SecretKeyFactory kf = SecretKeyFactory.getInstance(algorithm);
		SecretKey k = kf.generateSecret(new javax.crypto.spec.PBEKeySpec("N0Sha11Pa55".toCharArray()));
		encrypter = Cipher.getInstance(transformation);
		decrypter = Cipher.getInstance(transformation);
		encrypter.init(Cipher.ENCRYPT_MODE, k, ps);
		decrypter.init(Cipher.DECRYPT_MODE, k, ps);
	}

	
	public String encode(String url){
	    log.info("encode called");
		try{
			String encodeURL = URLEncoder.encode(url, "UTF-8");
			 log.info("encode success");
			return encodeURL;
		}catch(UnsupportedEncodingException e) {
			 log.info("encode failed");
			return "Issue while encoding" + e.getMessage();
		}
	}
	
	
	/**
	 * Get the decrypted value of the string
	 * @param key
	 * @return
	 */
	public String getDecryptedValue(String key) {
		if(key != null && !key.equals(""))
			return decrypt(key);
		else
			return "";
	}
	
	/**
	 * Get the encrypted value of the string
	 * @param key
	 * @return
	 */
	public String getEncryptedValue(String key) {
		if(key != null && !key.equals(""))
			return encrypt(key);
		else
			return "";
	}

	private synchronized String decrypt(String str) {
		try {
			byte[] dec = decoder.decode(str);
			byte[] utf8 = decrypter.doFinal(dec);
			return new String(utf8, "UTF-8");
		}
		catch (Exception e) {
			throw new CryptPropsException("Couldn't decrypt property");
		}
	}

	private synchronized String encrypt(String str) {
		try {
			byte[] utf8 = str.getBytes("UTF-8");
			byte[] enc = encrypter.doFinal(utf8);
			return encoder.encodeAsString(enc);
		}
		catch (Exception e) {
			throw new CryptPropsException("Couldn't encrypt property");
		}
	}
	
	public static synchronized EncryptableUtilities getInstance() {
		if (instance == null) {
			try { instance = new EncryptableUtilities(); }
			catch (Exception e) {
				logger.error("Failed to initialize !");
			}
		}
		return instance;
	}
	
	
	public static class CryptPropsException extends RuntimeException {
		private static final long serialVersionUID = -2088352647753980734L;
		public CryptPropsException(String message) { super(message); }
	}
	
}
