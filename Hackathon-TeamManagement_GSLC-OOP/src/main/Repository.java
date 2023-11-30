package main;

import java.util.ArrayList;

public interface Repository {
	public ArrayList<Model> stringToObject(ArrayList<String> data);
	public Model findOne();

}
