package util;

import java.security.Key;
import java.security.MessageDigest;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import exception.InvalidEncryptedTextException;

public final class SecurityUtils {
	private static final char[] HEX = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
	private static final byte[] BYTE = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 0, 0, 0, 0, 0, 0, 10, 11, 12, 13, 14, 15};
	private static final String DES = "DES";
	private static Key desKey;
	static {
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance(DES);
			keyGen.init(new SecureRandom());
			desKey = keyGen.generateKey();
		} catch (Exception e) {
			desKey = null;
			e.printStackTrace();
		}
	}
	
	/* method */
	public static final String encryptPassword(String loginName, String password) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(loginName.toUpperCase().getBytes("UTF-8"));
			md5.update(password.getBytes("UTF-8"));
			byte[] ba = md5.digest();
			return byte2hex(ba);
		} catch (Exception e) {
			return password;
		}
	}
	/**
	 * Each time this class is loaded by ClassLoader, the 'desKey' used by DES encrypt/decrypt is changed.
	 * Thus, the serialization or persistent storage of the method result is meaningless.
	 * @param value
	 */
	public static final String volatileEncrypt(String value) {
		try {
			Cipher cipher = Cipher.getInstance(DES);
			cipher.init(Cipher.ENCRYPT_MODE, desKey);
			byte[] bs = cipher.doFinal(value.getBytes());
			return byte2hex(bs);
		} catch (Exception e) {
			return String.valueOf(value);
		}
	}
	/**
	 * Invoke volatileEncrypt(String.valueOf(obj))
	 */
	public static final String volatileEncrypt(Object obj) {
		return volatileEncrypt(String.valueOf(obj));
	}
	/**
	 * Invoke volatileEncrypt(String.valueOf(value))
	 */
	public static final String volatileEncrypt(int value) {
		return volatileEncrypt(String.valueOf(value));
	}
	public static final String volatileDecrypt(String text) throws InvalidEncryptedTextException {
		try {
			Cipher cipher = Cipher.getInstance(DES);
			cipher.init(Cipher.DECRYPT_MODE, desKey);
			byte[] bs = cipher.doFinal(hex2byte(text));
			if (bs == null) {
				throw new InvalidEncryptedTextException();
			}
			return new String(bs);
		} catch (Exception e) {
			throw new InvalidEncryptedTextException(e);
		}
	}
	/**
	 * Invoke volatileDecrypt(String.valueOf(obj))
	 * @throws InvalidEncryptedTextException
	 */
	public static final String volatileDecrypt(Object obj) throws InvalidEncryptedTextException {
		return volatileDecrypt(String.valueOf(obj));
	}
	private static final String byte2hex(final byte[] bs) {
		char[] c = new char[bs.length * 2];
		for (int i = 0; i < bs.length; ++i) {
			c[i * 2] = HEX[bs[i] >>> 4 & 0x0F];
			c[i * 2 + 1] = HEX[bs[i] & 0x0F];
		}
		return String.valueOf(c);
	}
	private static final byte[] hex2byte(String val) {
		if (val.length() % 2 == 0) {
			byte[] bs = new byte[val.length() / 2];
			for (int i = 0; i < bs.length; i++) {
				bs[i] = (byte) (BYTE[val.charAt(i * 2) - '0'] << 4 | BYTE[val.charAt(i * 2 + 1) - '0']);
			}
			return bs;
		}
		return null;
	}

	public static void main(String[] args) {
		try {
			System.out.println(encryptPassword("111", "111"));
			System.out.println(encryptPassword("222", "222"));
			String[] ss = {"1", "2", "3", "aaa", "aab", "abc", "a long text test for encrypt decrypt"};
			for (String val : ss) {
				String s = volatileEncrypt(val.toString());
				System.out.println(s + " : " + volatileDecrypt(s));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
