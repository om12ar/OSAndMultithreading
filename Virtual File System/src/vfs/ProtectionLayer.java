package vfs;

import java.util.ArrayList;
import java.util.TreeMap;

public class ProtectionLayer {
	
	static TreeMap<String, String> users;
	static String currentUser;
	
	public ProtectionLayer(){
		
		users = new TreeMap<>();
		users.put("admin", "admin");
		currentUser = "";
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
				return true;
			}
			else{
				
				System.out.println("User already exists!");
				return false;
			}
			
			
		}
	}
	

	
}
