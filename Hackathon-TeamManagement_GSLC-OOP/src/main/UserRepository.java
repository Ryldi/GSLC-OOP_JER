package main;

import java.util.ArrayList;

public class UserRepository implements Repository{
	
	ArrayList<User> userList = new ArrayList<User>();
	
	public void stringToObject(ArrayList<String> dataUser) {
		ArrayList<User> result = new ArrayList<User>();
		
		for (String user : dataUser) {
			String[] splitData = user.split(",");
			result.add(new User(splitData[1], splitData[0], Integer.parseInt(splitData[2])));
		}
		
		this.userList = result;
//		for (User user : result) {
//			System.out.println(user.userNim);
//		}
		return;
	}
	
	public void getDataUser(Connection conn) {
		ArrayList<String> userCSV = new ArrayList<String>();
		userCSV = conn.readFile("User");
		stringToObject(userCSV);
	}
	
	public Boolean validate(String col, String[] condition, Boolean join, String tableJoin, Connection conn){
        // hubungan antara col dengan conditon
        if(col == null && condition != null) return false;
        if(col != null && condition == null) return false;
        if( (col.equals("NIM") || col.equals("Name") || col.equals("ID Team")) == false)return false;

        // untuk condition jika ada
        if(condition != null && condition.length !=2)return false;
        if(condition != null && condition[0] == null || condition[1] == null)return false;
        if(condition != null && condition[0] == null || condition[1] == null)return false;

        // join
        if(join == false && tableJoin != null)return false;
        if(join == true && tableJoin == null)return false;
        if(join == true && tableJoin.equals("Team") == false)return false;

        if(conn == null)return false;

        return true;
    }

    public void find(String col, String[] condition, Boolean join, String tableJoin, Connection conn) {
        if(validate(col, condition, join, tableJoin, conn).equals(false))return;

        return;
    }

	public void findOne(String col, String[] condition, Boolean join, String tableJoin, Connection conn) {
		// TODO Auto-generated method stub
		
	}

	





}
