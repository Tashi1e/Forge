package tcejorptset.servl.bean;

import java.io.Serializable;
import java.time.Instant;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor

public class UserInfo implements Serializable{

	private static final long serialVersionUID = -1421192482328889996L;
	
	private int userId;
	private String firstName;
	private String lastName;
	private String nickName;
	private String email;
	private Instant userRegDate;
	
}
