package main;

public class User extends Model{
	String userName;
	String userNim;
	
	public User(String userName, String userNim, Integer teamID) {
		super(teamID);
		this.userName = userName;
		this.userNim = userNim;
	}

}
