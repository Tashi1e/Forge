package com.tcejorptset.servl.util.encrypt;

public interface Encryptor {
	
	String encrypt (String toEncrypt);
	boolean compare (String regular, String encrypted);
	

}
