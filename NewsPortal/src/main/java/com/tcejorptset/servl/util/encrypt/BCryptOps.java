package com.tcejorptset.servl.util.encrypt;

import org.mindrot.jbcrypt.BCrypt;

public class BCryptOps implements Encryptor {

	@Override
	public String encrypt(String toEncrypt) {
		return BCrypt.hashpw(toEncrypt, BCrypt.gensalt());
	}
	

	@Override
	public boolean compare (String regular, String encrypted) {
		return BCrypt.checkpw(regular, encrypted);
	}

}
