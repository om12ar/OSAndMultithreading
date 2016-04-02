package vfs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);

		Disk d = new IndexedAllocation(10);
		//Disk d = new ContiguousAllocation(10);
		String cmd = "";

		while (!cmd.equals("0")) {
			
			cmd = in.nextLine();
		
			ArrayList<String> tokens = new ArrayList<>();
		
			tokens.addAll(Arrays.asList(cmd.split("\\s+")));
		
			switch (tokens.get(0)) {
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
			default:
				d.SaveDiskToFile();
				System.out.println("Enter a vaild comaand.");
				break;
			}
		}

	}

}
