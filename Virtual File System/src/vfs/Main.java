package vfs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		
		ProtectionLayer system = new ProtectionLayer();
		boolean loggedIn;
		
		do{
			System.out.println("Login: ");
			loggedIn = system.login(in.next(), in.next());
			
		}while(!loggedIn);
		
		Disk d = null ;

		d = new ContiguousAllocation(1000000);
		
		//d = new IndexedAllocation(1000000);
						
		String cmd = "";

		while (!cmd.equals("0")) {
			
			cmd = in.nextLine();
		
			ArrayList<String> tokens = new ArrayList<>();
		
			tokens.addAll(Arrays.asList(cmd.split("\\s+")));
		
			switch (tokens.get(0)) {
			
			case "Login":{
				if(tokens.size()!=3){
					System.out.println("Wrong Command");
					break;
				}
				system.login(tokens.get(1), tokens.get(2));
				break;
			}
			case "TellUser":{
				if(tokens.size()!=1){
					System.out.println("Wrong Command");
					break;
				}
				system.tellUser();
				break;
			}
			case "CreateUser":{
				if(tokens.size()!=3){
					System.out.println("Wrong Command");
					break;
				}
				system.createUser(tokens.get(1), tokens.get(2));
				break;
			}
			case "Grant":{
				if(tokens.size()!=4){
					System.out.println("Wrong Command");
					break;
				}
				d.chmod(tokens.get(2), tokens.get(1), tokens.get(3));
				System.out.println("done");
				break;
			}
			case "CreateFile":{
				if(tokens.size()!=3){
					System.out.println("Wrong Command");
					break;
				}
				String path = tokens.get(1);
				int size = Integer.parseInt(tokens.get(2));
				d.CFile(path, size);		
				break;
			}
				
			case "CreateFolder":{
				if(tokens.size()!=2){
					System.out.println("Wrong Command");
					break;
				}
				String path = tokens.get(1);
				d.CFolder(path);
				break;
			}

				
			case "DeleteFile":{
				if(tokens.size()!=2){
					System.out.println("Wrong Command");
					break;
				}
				String path = tokens.get(1);
				d.DFile(path);
				break ;
			}

			case "DeleteFolder":{
				if(tokens.size()!=2){
					System.out.println("Wrong Command");
					break;
				}
				String path = tokens.get(1);
				d.DFolder(path);
				break ;
			}
			case "DiskStatus":
				d.DisplayStatus();

				break;

			case "DiskStructure":
				d.DisplayTreeStructure();
				System.out.println("----------------------------------------");
				System.out.println(d.toString());
				System.out.println("----------------------------------------");
				break;

			case "FreeSpaceManager":
			
				d.printFreeSpaceManager();
				
				break;
			case "SaveDisk":
				
				d.SaveDiskToFile();
				
				break;
			default:
				
				System.out.println("Enter a vaild comaand.");
				break;
			}
		}

	}

}
