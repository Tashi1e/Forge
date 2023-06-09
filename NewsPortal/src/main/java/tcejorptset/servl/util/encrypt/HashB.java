package tcejorptset.servl.util.encrypt;

import org.mindrot.jbcrypt.BCrypt;

public class HashB implements Encryptor {

	@Override
	public String encrypt(String to_encrypt) {
		return BCrypt.hashpw(to_encrypt, BCrypt.gensalt());
	}
	

	@Override
	public boolean compare (String regular, String encrypted) {
		return BCrypt.checkpw(regular, encrypted);
	}

}
