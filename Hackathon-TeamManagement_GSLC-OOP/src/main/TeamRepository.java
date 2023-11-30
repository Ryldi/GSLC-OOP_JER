package main;

import java.util.ArrayList;

public class TeamRepository implements Repository{
	
	ArrayList<Team> teamList = new ArrayList<Team>();

	public void stringToObject(ArrayList<String> dataTeam) {
		ArrayList<Team> result = new ArrayList<Team>();
		
		for (String team : dataTeam) {
			String[] splitData = team.split(",");
			result.add(new Team(Integer.parseInt(splitData[0]), splitData[1]));
		}
		
		this.teamList = result;
		return;
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
