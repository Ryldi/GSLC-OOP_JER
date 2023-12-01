package main;

import java.util.Scanner;

public class Main {
	Scanner scan = new Scanner(System.in);
	Connection conn = new Connection();

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
	
	private void showMenu() {
		System.out.println("");
		System.out.println("---Show---");
		System.out.println("Which table to show?");
		System.out.println("1. User");
		System.out.println("2. Team");
		System.out.print("input: ");
		Integer inp = scan.nextInt(); scan.nextLine();
		
		System.out.println("Want to filter by condition?");
		System.out.println("1. Yes");
		System.out.println("2. No");
		System.out.print("input: ");
		Integer cond = scan.nextInt(); scan.nextLine();
		
		if(inp.equals(1)) {
			if(cond.equals(1)) { // show user
				System.out.println("add condition, separate by semicolon");
				
			}else if(cond.equals(2)) {
				
			}else mainMenu();
		}else if(inp.equals(2)) { // show team
			showTeam(cond);
		}
		
	}
	
	public void showTeam(Integer condition) {
		TeamRepository teamRepo = new TeamRepository();
		
//		
//		teamRepo.find("id", pass , true, "User", conn);
		
		
		if(condition.equals(1)) { //ada kondisi (id/name, cond, join)
			
			System.out.println("add condition, separate by semicolon");
			String con = scan.nextLine();
			String[] sepCon = con.split(";");
			System.out.print("Join table with table User? [y/n]: ");
			String join = scan.next();
			String[] pass = new String[] {sepCon[1], sepCon[2]};
			
			if(join.equals("y")) {
				teamRepo.find(sepCon[0], pass , true, "User", conn);
			}else if(join.equals("n")) {
				teamRepo.find(sepCon[0], pass , false, null, conn);
			}
			
		}else if(condition.equals(2)) {
			teamRepo.find(null, null, false, null, conn);
		}else mainMenu();
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
