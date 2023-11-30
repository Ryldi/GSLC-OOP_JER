package main;

import java.util.ArrayList;

public class UserRepository implements Repository{
	ArrayList<User> userData = new ArrayList<User>();
	
	public ArrayList<Model> stringToObject(ArrayList<String> dataUser) {
		ArrayList<Model> result = new ArrayList<Model>();
		
		for (String user : dataUser) {
			String[] splitData = user.split(",");
			result.add(new User(splitData[0], splitData[1], Integer.parseInt(splitData[0])));
		}
		
		return result;
	}
	
	public ArrayList<User> modelToUser(ArrayList<String> dataUser){
		
		ArrayList<Model> modelUser = this.stringToObject(dataUser);
		ArrayList<User> userList = new ArrayList<User>();
		
		for (Model model : modelUser) {
			userList.add((User)model);
		}
		
		return userList;
	}

	public Model findOne(Connection conn) {
//		this.userData = (User) stringToObject()
				
		
		return null;
	}

	@Override
	public Model findOne() {
		// TODO Auto-generated method stub
		return null;
	}


}
