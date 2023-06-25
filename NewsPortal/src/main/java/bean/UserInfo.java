package bean;

import java.io.Serializable;
import java.time.Instant;

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
	
	private String firstName;
	private String lastName;
	private String nickName;
	private String email;
	private Instant regDate;
	private String login;
	private String password;
	private String role;
	
}
