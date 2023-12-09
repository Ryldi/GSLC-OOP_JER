package main.Repository;
//package main.Models;

import java.util.ArrayList;

import main.Connection;
import main.Models.Team;
import main.Models.User;

public class UserRepository implements Repository {
	
	public ArrayList<User> userList = new ArrayList<User>();
	public ArrayList<String> teamInfo = new ArrayList<String>();
	
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
	
	public void getTeamInfo(Connection conn) {

		ArrayList<String> rawTeam = conn.readFile("Team");

		for (String team : rawTeam) {
			String[] splitData = team.split(",");
			Integer idTeam = Integer.parseInt(splitData[0]);
			while (teamInfo.size() <= idTeam) {
				teamInfo.add(new String());
			}
			this.teamInfo.add(idTeam, splitData[1]);
		}

//		for (int i = 0; i < teamInfo.size(); i++) {
//			System.out.println(teamInfo.get(i));
//		}
		
	}
	
	public void getDataUser(Connection conn) {
		ArrayList<String> userCSV = new ArrayList<String>();
		userCSV = conn.readFile("User");
		stringToObject(userCSV);
	}
	
	// cari sign
	public int getSign(String[] condition){
		if(condition[0].equals("=")) return 1;
		return 0;
	}
	
	public ArrayList<User> filterUser(String col, String[] condition){
		ArrayList<User> answer = new ArrayList<User>();
		
		for (User user : this.userList) {
			int sign = getSign(condition);
			String requiredValue = condition[1];
			if(col.equals("name")){
				if(sign == 1 && !user.userName.equals(requiredValue)) continue;
				if(sign == 0 && user.userName.equals(requiredValue)) continue;
				answer.add(user);
			}
			else if(col.equals("id")){
				if(sign == 1 && user.teamID != Integer.parseInt(requiredValue)) continue;
				if(sign == 0 && user.teamID == Integer.parseInt(requiredValue)) continue;
				answer.add(user);
			}else if(col.equals("nim")) {
				if(sign == 1 && !user.userNim.equals(requiredValue)) continue;
				if(sign == 0 && user.userNim.equals(requiredValue)) continue;
				answer.add(user);
			}
		}
		
		return answer;
	}

	public Boolean validate(String col, String[] condition, Boolean join, String tableJoin, Connection conn){
		//teamRepo.find("Nama", pass , false, null, conn);
		
		// hubungan antara col dengan condition
        if(col == null && condition != null) return false;
        if(col != null && condition == null) return false;
		if(col != null &&  (col.equals("id") || col.equals("name") || col.equals("nim")) == false)return false;

        // untuk condition jika ada
		if(condition != null){
			if(condition.length !=2)return false;
			if(condition[0] == null || condition[1] == null)return false;
			if((condition[0].equals("=") || condition[0].equals("!=")) == false)return false;
		}

        // kalau ga join gabisa output
        // join
        if(join == false && tableJoin != null)return false;
		if(join == true){
			if(tableJoin == null)return false;
			if(tableJoin.equals("Team") == false)return false;
		}

        if(conn == null)return false;
        return true;
    }

    public void find(String col, String[] condition, Boolean join, String tableJoin, Connection conn) {
        if(validate(col, condition, join, tableJoin, conn).equals(false)){
			System.out.println("error [wrong condition/parameter]");
			return;
		}

        this.getDataUser(conn);
        this.getTeamInfo(conn);
		if(condition == null) {
			if(join.equals(false)){
				System.out.println("ID| User NIM   | User Name");
				System.out.println("-----------------------------");
				for (User user : userList) {
					System.out.print(user.teamID);
					System.out.print(" | " + user.userNim);
					System.out.print(" | " + user.userName);
					System.out.println();
				}
			}else{
				User user_now = new User(null, null, null);
				for(int i=0; i < userList.size(); i++){
					user_now = userList.get(i);
					if(i==0) {
						System.out.println("ID| User NIM   | User Name   | Team Name");
						System.out.println("-----------------------------------------------");
					}
					System.out.print(user_now.teamID);
					System.out.print(" | " + user_now.userNim);
					System.out.print(" | " + user_now.userName);
					System.out.print(" | " + teamInfo.get(user_now.teamID));
					System.out.println();
				}
			}
		}
		else if(condition != null){
			// get information of team each member
			ArrayList<User> userAnswer = filterUser(col, condition);
			User user_now = new User(null, null, null);
			for(int i = 0; i < userAnswer.size(); i++){
				user_now = userAnswer.get(i);
//				// keluarin data semua user yg ada di team ini kalau table join
				if(join == true && validate(col, condition, join, tableJoin, conn).equals(true)) {
					if(i==0) {
						System.out.println("ID| User NIM   | User Name   | Team Name");
						System.out.println("-----------------------------------------------");
					}
					System.out.print(user_now.teamID);
					System.out.print(" | " + user_now.userNim);
					System.out.print(" | " + user_now.userName);
					System.out.print(" | " + teamInfo.get(user_now.teamID));
					System.out.println();
				}else { // kalau engga join berarti cuma print teamName sama teamID
					if(i==0) {
						System.out.println("ID| User NIM   | User Name ");
						System.out.println("------------------------------");
					}
					System.out.print(user_now.teamID);
					System.out.print(" | " + user_now.userNim);
					System.out.print(" | " + user_now.userName);
					System.out.println();
				}	
			}
		}
        return;
    }

    public User findOne(String col, String[] condition, Boolean join, String tableJoin, Connection conn) {
		if(validate(col, condition, join, tableJoin, conn).equals(false)){
			System.out.println("error kondisi salah");
			return null;
		}

	    this.getDataUser(conn);
	    ArrayList<User> userAnswer = filterUser(col, condition);
	    if(userAnswer.size() == 0) return null;
	    
	    User userResult = userAnswer.get(0);
	    return userResult;
	}

	
	public void insert(String[] userData, Connection conn) {
//    	userRepository.insert([”user1”,”2600150125”,”Team1”],conn);
    	
    	TeamRepository teamRepo = new TeamRepository();
    	teamRepo.getDataTeam(conn);
    	teamRepo.fillTeamMember(conn);
    	
    	Integer teamID = 0;
    	for (Team team : teamRepo.teamList) {
			if(team.teamName.equals(userData[2])) {
				teamID = team.teamID;
			}
		}
    	
    	if(teamID.equals(0)) { //Insert when the team is not existed
    		String[] teamData = new String[]{userData[2]};
    		teamRepo.insert(teamData, conn); //Insert new team into csv
    		
    		teamRepo.getDataTeam(conn); //Refresh Team Data
    		
    		Integer newTeamID=0; //Variable for the new team id
    		for (Team team : teamRepo.teamList) {
				if(team.teamName.equals(userData[2])) {
					newTeamID = team.teamID;
				}
			}
    		
    		String passInsert = userData[1] + "," + userData[0] + "," + newTeamID.toString();
    		conn.writeFile("User", passInsert);// Insert new user into csv
    		
    		System.out.println("New User has been registered.");
    		
    	}else {//Insert when the team exist
    		
    		if(teamRepo.teamMember.get(teamID).size() >= 3 ) {
    			System.out.println("The team is full.");
    			return;
    		}
    		
    		for (Team team : teamRepo.teamList) {
				if(team.teamName.equals(userData[2])) {
					teamID = team.teamID;
				}
			}
    		String passInsert = userData[1] + "," + userData[0] + "," + teamID.toString();
    		conn.writeFile("User", passInsert);// Insert new user into csv
    		
    		System.out.println("New User has been registered.");
    	}
    	
    }




}
