package tcejorptset.servl.bean;

import java.io.Serializable;
import java.time.Instant;
import jakarta.servlet.http.Part;

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
	private String title;
	private String image;
	private String brief;
	private String content;
	private Instant date;
	private Part imgPart;
	private int userId=0;
	private short status=0;
}
