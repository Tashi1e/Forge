package util.encrypt;

public interface Encryptor {
	
	String encrypt (String to_encrypt);
	String encrypt(String to_encrypt, String salt);
	boolean compare (String regular, String encrypted);
	

}
