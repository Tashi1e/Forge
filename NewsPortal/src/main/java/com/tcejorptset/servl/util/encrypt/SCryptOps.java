package com.tcejorptset.servl.util.encrypt;

import com.lambdaworks.crypto.SCryptUtil;

public class SCryptOps implements Encryptor {

	@Override
	public String encrypt(String toEncrypt) {
		return SCryptUtil.scrypt(toEncrypt, 16, 16, 16);
	}
	

	@Override
	public boolean compare (String regular, String encrypted) {
		return SCryptUtil.check(regular, encrypted);
	}

}
