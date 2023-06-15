package bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class News implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer idNews=0;
	private String title="";
	private String imagePath="";
	private String briefNews="";
	private String content="";
	private String newsDate="";
}
