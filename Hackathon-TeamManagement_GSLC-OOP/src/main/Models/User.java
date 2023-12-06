package main.Models;


public class User extends Model {
	public String userName;
	public String userNim;
	
	public User(String userName, String userNim, Integer teamID) {
		super(teamID);
		this.userName = userName;
		this.userNim = userNim;
	}

}
