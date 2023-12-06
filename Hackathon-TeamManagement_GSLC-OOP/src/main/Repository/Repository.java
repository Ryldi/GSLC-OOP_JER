package main.Repository;

import main.Connection;

import java.util.ArrayList;

public interface Repository {
	public void stringToObject(ArrayList<String> data);
	public void find(String col, String[] condition, Boolean join, String tableJoin, Connection conn);
	public void insert(String[] teamName, Connection conn);
}
