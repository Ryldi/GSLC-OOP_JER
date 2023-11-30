package main;

import java.util.Scanner;

public class Main {
	Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		new Main();
	}
	
	public Main() {
		mainMenu();
	}
	
	public void mainMenu() {
		System.out.println("-----Main Menu-----");
		System.out.println("1. Insert Data");
		System.out.println("2. Show Data");
		System.out.println("3. Exit");
		System.out.print("input: ");
		Integer inp = scan.nextInt(); scan.nextLine();
		
		if(inp.equals(1)) {
			insertMenu();
		}else if(inp.equals(2)) {
			showMenu();
		}else if(inp.equals(3)) {
			System.exit(0);
		}else {
			mainMenu();
		}
	}
	
	public void insertMenu() {
		System.out.println("");
		System.out.println("---Insert---");
		System.out.println("Which table to insert?");
		System.out.println("1. User");
		System.out.println("2. Team");
		System.out.print("input: ");
		Integer inp = scan.nextInt(); scan.nextLine();
		
		if(inp.equals(1)) {
			insertUser();
		}else if(inp.equals(2)) {
			insertTeam();
		}
		
		
		mainMenu();
	}
	
	public void insertUser() {
		String userName;
		String userNim;
		String teamName;
		
		System.out.print("Add Name: ");
		userName = scan.nextLine();
		System.out.println("Add Nim: ");
		userNim = scan.nextLine();
		System.out.println("Add Team Name: ");
		teamName = scan.nextLine();
	}
	
	public void insertTeam() {
		String teamName;
		
		System.out.print("Add Team Name: ");
		teamName = scan.nextLine();
	}
	
}
