package main;

import java.util.ArrayList;

public class TeamRepository implements Repository{
	
	ArrayList<Team> teamList = new ArrayList<Team>();
	ArrayList<ArrayList<User>> teamMember = new ArrayList<ArrayList<User>>();

	public void stringToObject(ArrayList<String> dataTeam) {
		ArrayList<Team> result = new ArrayList<Team>();
		
		for (String team : dataTeam) {
			String[] splitData = team.split(",");
			result.add(new Team(Integer.parseInt(splitData[0]), splitData[1]));
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
		for (int i = 0; i < teamList.size(); i++){
			Team teamNow = teamList.get(i);
			int sign = getSign(condition);
			String requiredValue = condition[1];
			if(col.equals("Nama")){
				if(sign == 1 && teamNow.teamName != requiredValue) continue;
				if(sign == 0 && teamNow.teamName == requiredValue) continue;
//				answer.push({NULL, teamNow});
				answer.add(teamNow);
			}
			else if(col.equals("ID")){
				if(sign == 1 && teamNow.teamID != Integer.parseInt(requiredValue)) continue;
				if(sign == 0 && teamNow.teamID == Integer.parseInt(requiredValue)) continue;
//				answer.push({NULL, teamNow});
				answer.add(teamNow);
			}
			System.out.println(teamNow.teamName);
		}
		return answer;
	}
	
	public void fillTeamMember(Connection conn) {
		UserRepository user = new UserRepository();
		user.getDataUser(conn);
		ArrayList<User> userList = user.userList;
		for (int i = 0; i < userList.size(); i++){
			User userNow = userList.get(i);
			int idUserNow = userNow.teamID;
//			teamMember[idUserNow].push(userNow);
			teamMember.get(idUserNow).add(userNow);
		}
	}
	
	

	public Boolean validate(String col, String[] condition, Boolean join, String tableJoin, Connection conn){
        // hubungan antara col dengan condition
        if(col == null && condition != null) return false;
        if(col != null && condition == null) return false;
        if( (col.equals("Id") || col.equals("Nama")) == false)return false;

        // untuk condition jika ada
        if(condition != null && condition.length !=2)return false;
        if(condition != null && condition[0] == null || condition[1] == null)return false;
        if(condition != null && condition[0] == null || condition[1] == null)return false;

        // join
        if(join == false && tableJoin != null)return false;
        if(join == true && tableJoin == null)return false;
        if(join == true && tableJoin.equals("User") == false)return false;

        if(conn == null)return false;

        // col
        if( (col.equals("Id") || col.equals("Nama")) == false)return false;
        
        return true;
    }

    public void find(String col, String[] condition, Boolean join, String tableJoin, Connection conn) {
        if(validate(col, condition, join, tableJoin, conn).equals(false))return; 
        
        ArrayList<Team> teamAnswer = filterTeam(col, condition);
		Team team_now = new Team(null, null);
		if(join == true){
			// get information of team each member
			fillTeamMember(conn);
			for(int i = 0; i < teamAnswer.size(); i++){
				team_now = teamAnswer.get(i);
				User user_now = new User(null, null, null);
				// keluarin data semua user yg ada di team ini
				for(int j = 0; j < teamMember.get(team_now.teamID).size(); i++){
					user_now = teamMember.get(team_now.teamID).get(j);
					System.out.println(user_now.userName); //teamMember[j].userName
					System.out.println(user_now.userNim);
				}
				System.out.println(team_now.teamID);
				System.out.println(team_now.teamName);
			}
		}
		else{
			// print
			for(int i = 0; i < teamAnswer.size(); i++){
				team_now = teamAnswer.get(i);
				System.out.println(team_now.teamName);
				System.out.println(team_now.teamID);
			}
		}
        return;
    }

	public void findOne(String col, String[] condition, Boolean join, String tableJoin, Connection conn) {
		// TODO Auto-generated method stub
		
	}

}
