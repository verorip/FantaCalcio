
public class Player {
	
	String Nome;
	String Team;
	
	public Player(String Nome, String Team){
		this.Nome=Nome;
		this.Team=Team;
	}
	
	public String getNome(){
		return this.Nome;
	}
	
	public String getTeam(){
		return this.Team;
	}
}
