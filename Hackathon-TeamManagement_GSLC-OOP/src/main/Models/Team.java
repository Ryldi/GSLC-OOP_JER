package main.Models;


public class Team extends Model {
	public String teamName;
	
	public Team(Integer teamID, String teamName) {
		super(teamID);
		this.teamName = teamName;
	}
}
