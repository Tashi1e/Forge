package tcejorptset.servl.bean;

import java.io.Serializable;
import java.time.Instant;

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
public class News implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private int id=0;
	private String title="";
	private String image="";
	private String brief="";
	private String content="";
	private Instant date;
	private int userId;
	private short status=0;
}
