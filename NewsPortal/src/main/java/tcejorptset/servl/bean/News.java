package tcejorptset.servl.bean;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class News implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id=0;
	private String title="";
	private String image="";
	private String brief="";
	private String content="";
	private String date="";
	private String author="";
}
