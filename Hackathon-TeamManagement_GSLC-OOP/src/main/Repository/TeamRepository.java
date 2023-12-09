package main.Repository;

import main.Connection;
import main.Models.Team;
import main.Models.User;

import java.util.ArrayList;

public class TeamRepository implements Repository {
	
	public ArrayList<Team> teamList = new ArrayList<Team>();
	public ArrayList<ArrayList<User>> teamMember = new ArrayList<ArrayList<User>>();

	public void stringToObject(ArrayList<String> dataTeam) {
		ArrayList<Team> result = new ArrayList<Team>();
		
		for (String team : dataTeam) {
			String[] splitData = team.split(",");
			result.add(new Team(Integer.parseInt(splitData[0]), splitData[1]));
//			System.out.println(splitData[0]+ splitData[1]);
		}
		
		this.teamList = result;
		return;
	}
	
	// dapetin list of team
	public void getDataTeam(Connection conn) {
		ArrayList<String> teamCSV = new ArrayList<String>();
		teamCSV = conn.readFile("Team");
		stringToObject(teamCSV);
	}
	
	// cari sign
	public int getSign(String[] condition){
		if(condition[0].equals("=")) return 1;
		return 0;
	}
	 
	public ArrayList<Team> filterTeam(String col, String[] condition){
		ArrayList<Team> answer = new ArrayList<Team>();
		
		for (Team team : this.teamList) {
			int sign = getSign(condition);
			String requiredValue = condition[1];
			if(col.equals("name")){
				if(sign == 1 && !team.teamName.equals(requiredValue)) continue;
				if(sign == 0 && team.teamName.equals(requiredValue)) continue;
				answer.add(team);
			}
			else if(col.equals("id")){
				if(sign == 1 && team.teamID != Integer.parseInt(requiredValue)) continue;
				if(sign == 0 && team.teamID == Integer.parseInt(requiredValue)) continue;
				answer.add(team);
			}
		}
//		for (Team team : answer) {
//			System.out.println(team.teamName);
//		}
		
		return answer;
	}
	
	public void fillTeamMember(Connection conn) {
		UserRepository user = new UserRepository();
		user.getDataUser(conn);
		
		ArrayList<User> userList = user.userList;

		while (teamMember.size() <= teamList.size()) {
			teamMember.add(new ArrayList<User>());
		}

		for (User users : userList) {
			User userNow = new User(null,null,null);
			userNow = users;
			int idTeam = userNow.teamID;
			this.teamMember.get(idTeam).add(userNow);
		}
		
	}

	public Boolean validate(String col, String[] condition, Boolean join, String tableJoin, Connection conn){
		//teamRepo.find("Nama", pass , false, null, conn);

		// hubungan antara col dengan condition
		if(col == null && condition != null) return false;
		if(col != null && condition == null) return false;
		if(col != null &&  (col.equals("id") || col.equals("name")) == false)return false;

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
			if(tableJoin.equals("User") == false)return false;
		}

		if(conn == null)return false;
		return true;
	}

    public void find(String col, String[] condition, Boolean join, String tableJoin, Connection conn) {
		if(validate(col, condition, join, tableJoin, conn).equals(false)){
			System.out.println("error [wrong condition/parameter]");
			return;
		}

        this.getDataTeam(conn);
        
		
		if(condition == null) {
			if(join.equals(false)){
				System.out.println("ID| Team Name");
				System.out.println("------------------");
				for (Team team : teamList) {
					System.out.print(team.teamID);
					System.out.print(" | " + team.teamName);
					System.out.println();
				}
			}else{
				Team team_now = new Team(null, null);
				fillTeamMember(conn);
				for(int i=0; i < teamList.size(); i++){
					team_now = new Team(null, null);
					team_now = teamList.get(i);
					User user_now = new User(null, null, null);
					if(i==0) {
						System.out.println("ID| User NIM   | Team Name   | User Name");
						System.out.println("-----------------------------------------------");
					}
					for(int j = 0; j < teamMember.get(team_now.teamID).size(); j++){
						user_now = teamMember.get(team_now.teamID).get(j);
						System.out.print(team_now.teamID);
						System.out.print(" | " + user_now.userNim);
						System.out.print(" | " + team_now.teamName);
						System.out.print(" | " + user_now.userName);
						System.out.println();
					}
				}
			}
		}
		else if(condition != null){
			// get information of team each member
			ArrayList<Team> teamAnswer = filterTeam(col, condition);
			Team team_now = new Team(null, null);
			fillTeamMember(conn);
			for(int i = 0; i < teamAnswer.size(); i++){
				team_now = new Team(null, null);
				team_now = teamAnswer.get(i);
				User user_now = new User(null, null, null);
//				// keluarin data semua user yg ada di team ini kalau table join
				if(join == true && validate(col, condition, join, tableJoin, conn).equals(true)) {
					if(i==0) {
						System.out.println("ID| User NIM   | Team Name   | User Name");
						System.out.println("-----------------------------------------------");
					}
					for(int j = 0; j < teamMember.get(team_now.teamID).size(); j++){
						user_now = teamMember.get(team_now.teamID).get(j);
						System.out.print(team_now.teamID);
						System.out.print(" | " + user_now.userNim);
						System.out.print(" | " + team_now.teamName);
						System.out.print(" | " + user_now.userName);
						System.out.println();
					}
				}else { // kalau engga join berarti cuma print teamName sama teamID
					if(i==0) {
						System.out.println("ID| Team Name ");
						System.out.println("------------------");
					}
					System.out.print(team_now.teamID);
					System.out.print(" | " + team_now.teamName);
					System.out.println();
				}	
			}
		}
        return;
    }

	public Team findOne(String col, String[] condition, Boolean join, String tableJoin, Connection conn) {
//		if(validate(col, condition, false, null, conn).equals(false))return null;
	    this.getDataTeam(conn);
	    ArrayList<Team> teamAnswer = filterTeam(col, condition);
	    if(teamAnswer.size() == 0) return null;
	    
	    Team teamResult = teamAnswer.get(0);
	    return teamResult;
	}
	
	public void insert(String[] teamName, Connection conn) {
		String[] pass = new String[]{"=", teamName[0]};
		
		
		try {
			Team find = findOne("name", pass, false, null, conn);
			if(find.teamName.equals(teamName[0])) {
				System.out.println("Team name is already been used, choose another one.");
			}
		} catch (Exception e) {
			// TODO: handle exception
			Integer max = 0;
			
			for (Team team : teamList) {
				if(team.teamID > max) {
					max = team.teamID;
				}
			}
			
			Integer newTeamID = max+1;
			String newTeamData = newTeamID.toString()+","+ teamName[0];
			conn.writeFile("Team", newTeamData);
			
			System.out.println("New Team has been registered.");
		}
		
	}

}
