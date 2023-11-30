package main;

import java.util.ArrayList;

public interface Repository {
	public void stringToObject(ArrayList<String> data);
	public Model findOne();

}
