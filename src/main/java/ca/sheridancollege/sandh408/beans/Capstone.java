package ca.sheridancollege.sandh408.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Capstone {
	//kept it simple :D
	private Long id;
	@NonNull
	private String title;
	private String link;
	private String teamName;
	//whenever I create a new capstone the rank will
	//be set to 0 by default
	private Integer rank =0;  
	
	public void up() {
		rank++;
	}
	
	public void down() {
		rank--;
	}
}
