package main;

import java.util.ArrayList;

public class TeamRepository implements Repository{
	
	ArrayList<Team> teamList;

	public ArrayList<Model> stringToObject(ArrayList<String> dataTeam) {
		ArrayList<Model> result = new ArrayList<Model>();
		
		for (String user : dataTeam) {
			String[] splitData = user.split(",");
			result.add(new Team(Integer.parseInt(splitData[0]), splitData[1]));
		}
		
		return result;
	}
	
	public ArrayList<Team> modelToTeam(ArrayList<String> dataTeam){
		
		ArrayList<Model> modelUser = this.stringToObject(dataTeam);
		ArrayList<Team> teamList = new ArrayList<Team>();
		
		for (Model model : modelUser) {
			teamList.add((Team)model);
		}
		
		return teamList;
	}

	public Model find(String col, String condition, Boolean join, String tableJoin, Connection conn) {
		
		
		
		return null;
	}

	@Override
	public Model findOne() {
		// TODO Auto-generated method stub
		return null;
	}

}
