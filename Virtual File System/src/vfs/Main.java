package vfs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);



		Disk d = new ContiguousAllocation(10000000);

		String cmd = "";

		while (!cmd.equals("0")) {
			cmd = in.nextLine();
			ArrayList<String> tokens = new ArrayList<>();
			tokens.addAll(Arrays.asList(cmd.split("\\s+")));
			//System.err.println(tokens);

			switch (tokens.get(0)) {
			case "CFile":{
				if(tokens.size()!=3){
					System.out.println("Wrong Command");
					break;
				}
				String path = tokens.get(1);
				int size = Integer.parseInt(tokens.get(2));
				d.CFile(path, size);		
				break;
			}
				
			case "CFolder":{
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
			case "DisplayDiskStatus":

				break;

			//case "DisplayDiskStructure":
			case "d":
				d.DisplayTreeStructure();
				System.out.println("----------------------------------------");
				System.out.println(d.toString());
				System.out.println("----------------------------------------");
				break;


			default:
				break;
			}
		}
/*
		d.CFolder("/", "om12ar");
		d.DisplayTreeStructure();
		d.CFolder("/", "another");
		d.DisplayTreeStructure();
		d.CFolder("/om12ar", "test");
		d.DisplayTreeStructure();
		d.CFolder("/om12ar/test", "hi");
		d.DisplayTreeStructure();

		d.CFile("/", "file", 100);
		d.CFile("/", "file2", 100);
		d.CFile("/om12ar", "mine", 100);
		d.DisplayTreeStructure();*/

		// Path path = Paths.get(drive.getAbsolutePath() , "dir1");

		// Files.createDirectories(path);

		/*
		 * Path path1 = Paths.get("/", "Parent","Child1");
		 * 
		 * //creates Directory as Directory Child1 under as D:\Parent
		 * Files.createDirectory(path1);
		 * 
		 * //creates temp directory in file system temp directory
		 * Files.createTempDirectory("Concretepage");
		 * 
		 * //creates temp directory in the specified path
		 * Files.createTempDirectory(path,"Concretepage");
		 */
	}

}
