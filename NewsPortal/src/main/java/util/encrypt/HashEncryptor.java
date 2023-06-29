package util.encrypt;

import org.mindrot.jbcrypt.BCrypt;

public class HashEncryptor implements Encryptor {

	private final static HashEncryptor instance = new HashEncryptor();

	private HashEncryptor() {};

	@Override
	public String encrypt(String to_encrypt) {
		String salt = BCrypt.gensalt();
		return BCrypt.hashpw(to_encrypt, salt);
	}

	@Override
	public boolean compare (String regular, String encrypted) {
		return BCrypt.checkpw(regular, encrypted);
	}

	public static HashEncryptor getInstance() {
		return instance;
	}
}
