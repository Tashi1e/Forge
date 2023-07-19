package tcejorptset.servl.bean;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor

public class User implements Serializable{
	
	private static final long serialVersionUID = -509093266405319422L;
	
	private String login;
	private String password;
	private String role;

}
