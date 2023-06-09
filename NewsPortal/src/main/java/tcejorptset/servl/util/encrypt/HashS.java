package tcejorptset.servl.util.encrypt;

import com.lambdaworks.crypto.SCryptUtil;

public class HashS implements Encryptor {

	@Override
	public String encrypt(String to_encrypt) {
		return SCryptUtil.scrypt(to_encrypt, 16, 16, 16);
	}
	

	@Override
	public boolean compare (String regular, String encrypted) {
		return SCryptUtil.check(regular, encrypted);
	}

}
