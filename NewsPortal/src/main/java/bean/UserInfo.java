package bean;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode

public class UserInfo implements Serializable{

	private static final long serialVersionUID = -1421192482328889996L;
	
	private String nickName;
	private String email;
	private String country;
	private String phoneNumber;
	private String password;
	private String role;
	
}
