package main;

import java.util.ArrayList;

public class TeamRepository implements Repository{

	public ArrayList<Model> stringToObject(ArrayList<String> dataTeam) {
		ArrayList<Model> result = new ArrayList<Model>();
		
		for (String user : dataTeam) {
			String[] splitData = user.split(",");
			result.add(new Team(Integer.parseInt(splitData[0]), splitData[1]));
		}
		
		return result;
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
