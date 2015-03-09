package pag.com.beans;

import java.io.Serializable;

public class ActorBean implements Serializable {
	
	private static final long serialVersionUID = -3168349974480377280L;
	
	private String character;
	private String actorName;
	private String posterURL;
	
	public ActorBean() {
		
	}
	
	public String getCharacter() {
		return character;
	}
	public void setCharacter(String character) {
		this.character = character;
	}
	public String getActorName() {
		return actorName;
	}
	public void setActorName(String actorName) {
		this.actorName = actorName;
	}
	public String getPosterURL() {
		return posterURL;
	}
	public void setPosterURL(String posterURL) {
		this.posterURL = posterURL;
	}

}
