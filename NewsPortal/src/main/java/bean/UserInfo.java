package bean;

import java.io.Serializable;
import java.time.Instant;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper=false)


public class UserInfo extends User implements Serializable{

	private static final long serialVersionUID = -1421192482328889996L;
	
	private String firstName;
	private String lastName;
	private String nickName;
	private String email;
	private Instant userRegDate;
	
}
