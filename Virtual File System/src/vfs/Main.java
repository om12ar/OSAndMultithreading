package vfs;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileStore;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileAttributeView;
import java.nio.file.attribute.FileStoreAttributeView;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);

		try {
			Files.createFile(Paths.get("VFSD.vfs"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		// RandomAccessFile drive = new RandomAccessFile("VFSD.vfs", "rw");
		// drive.setLength(1000000 * 1000);

		Disk d = new Disk(1 , 100);

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
				System.out.println(d.at.toString());
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
