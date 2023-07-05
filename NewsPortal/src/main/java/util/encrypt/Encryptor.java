package util.encrypt;

public interface Encryptor {
	
	String encrypt (String to_encrypt);
	boolean compare (String regular, String encrypted);
	

}
