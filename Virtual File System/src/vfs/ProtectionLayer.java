package vfs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

public class ProtectionLayer {
	
	static TreeMap<String, String> users;
	static String currentUser;
	final String USERS_FILE = "user.txt"; 
	public ProtectionLayer(){
		
		users = new TreeMap<>();
		users.put("admin", "admin");
		currentUser = "";
		readUsersFile();
	}
	
	private void readUsersFile() {
		File file = new File(USERS_FILE);
		try {
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line;
	    
			while ((line = br.readLine()) != null) {
				String userName = line.substring(0, line.indexOf(","));
				String pass = line.substring(line.indexOf(",")+1);
				users.put(userName, pass);
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	}

	public String tellUser(){
		System.out.println(currentUser);
		return currentUser;
	}
	
	public boolean login(String name, String pass){
		
		if(users.get(name) == null){
			System.out.println("User doesn't exists.");
			return false;
		}
		else{
			
			if(!users.get(name).equals(pass)){
				System.out.println("Wrong Password.");
				return false;
			}
			else{
				
				currentUser = name;
				return true;
			}
		}
	}
	

	public boolean createUser(String name, String pass){
		
		if(!currentUser.equals("admin")){
			System.out.println("Permission Denied");
			return false;
		}
		else{
			
			if(users.get(name) == null){				
				users.put(name, pass);
				writeUserToFile(name,pass);
				System.out.println("User Created");
				return true;
			}
			else{
				
				System.out.println("User already exists!");
				return false;
			}
			
			
		}
	}

	private void writeUserToFile(String name, String pass) {
		File file = new File(USERS_FILE);
		try {
			
			if(!file.exists()) {
				file.createNewFile();
			} 
			
			FileWriter fileWriter = new FileWriter(file, true);
			fileWriter.write(System.getProperty( "line.separator" ));
			fileWriter.write(name+","+pass);
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	

	
}
