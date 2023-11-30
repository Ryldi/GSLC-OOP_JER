package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Connection {
	
	Scanner scan = new Scanner(System.in);
	
	public void writeFile(String option, String insert) {
		FileWriter file = null;
		PrintWriter print = null;
		
		
		if(option.equals("User")) {
			try {
				file = new FileWriter("user.csv", true);
				print = new PrintWriter(file, true);
				
				print.println(insert);
				
				file.close();
				print.close();
			} catch (Exception e) {
				System.out.println("gagal om");
			}
		}else if(option.equals("Team")) {
			try {
				file = new FileWriter("teams.csv", true);
				print = new PrintWriter(file, true);
				
				print.println(insert);
				
				file.close();
				print.close();
			} catch (Exception e) {
				System.out.println("gagal om");
			}
		}	
	}
	
	public ArrayList<String> readFile(String option) {
		
		ArrayList<String> rawData = new ArrayList<String>();
		
		if(option.equals("User")) {
			try (BufferedReader reader = new BufferedReader(new FileReader("user.csv"))) {
				//-------------------------------------
				String headerLine = reader.readLine();
				//ngambil header di file csv
				//https://stackoverflow.com/questions/18306270/skip-first-line-while-reading-csv-file-in-java
				//-------------------------------------
				
	            String line;
	            while ((line = reader.readLine()) != null) {
	                rawData.add(line);
	            }
	        } catch (Exception e) {
	        	System.out.println("kosong");
	        }
		}else if(option.equals("Team")) {
			try (BufferedReader reader = new BufferedReader(new FileReader("teams.csv"))) {
				//-------------------------------------
				String headerLine = reader.readLine();
				//ngambil header di file csv
				//https://stackoverflow.com/questions/18306270/skip-first-line-while-reading-csv-file-in-java
				//-------------------------------------
				
	            String line;
	            while ((line = reader.readLine()) != null) {
	                rawData.add(line);
	            }
	        } catch (Exception e) {
	        	System.out.println("kosong");
	        }
		}
		
//		for (String string : rawData) {
//			System.out.println(string);
//		}
		return rawData;
	}

}
