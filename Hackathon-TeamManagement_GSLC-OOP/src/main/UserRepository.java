package main;

import java.util.ArrayList;

public class UserRepository implements Repository{
	
	ArrayList<User> userList = new ArrayList<User>();
	
	public void stringToObject(ArrayList<String> dataUser) {
		ArrayList<User> result = new ArrayList<User>();
		
		for (String user : dataUser) {
			String[] splitData = user.split(",");
			result.add(new User(splitData[0], splitData[1], Integer.parseInt(splitData[2])));
		}
		
		this.userList = result;
		return;
	}

	@Override
	public Model findOne() {
		// TODO Auto-generated method stub
		return null;
	}


}
