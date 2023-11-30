package main;

public class Team extends Model{
	String teamName;
	
	public Team(Integer teamID, String teamName) {
		super(teamID);
		this.teamName = teamName;
	}
}
