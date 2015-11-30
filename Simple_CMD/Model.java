package cmd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Model {


	public static File file = new File("c:\\");

	public static ArrayList<String> cat(ArrayList<String> fileName) {
		ArrayList<String> lines = new ArrayList<>();
		Scanner input = null;
		for(int i =0 ;i < fileName.size()-1 ; i++){
			String s = fileName.get(i);
			File catfile = openFile(s);
			
			if (!catfile.exists()) {
				lines.add("File Not found !");
				continue ;
			}
			try {
				input = new Scanner(catfile);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			while (input.hasNextLine()) {
				String data = input.nextLine();
				lines.add(data);
			}
		}
		return lines;
	}

	public static ArrayList<String> cd(String path) {

		ArrayList<String> message = new ArrayList<>();

		if (path.equals("")|| path.equals("\n")) {
			path = "c:\\";
		} else if (path.length()>2 && path.substring(0, 2).equals(".\\")) {
			path = file.getAbsolutePath() + path.substring(1);
		} else if (path.equals("..")) {

			path = file.getParent();

		}
		File newFile = new File(path);

		if (!newFile.exists()) {
			message.add("No such file or directory.");

		} else {
			file = new File(path);
		}

		message.add(file.getAbsolutePath() + " ");

		return message;
	}

	public static ArrayList<String> listFiles() {

		ArrayList<String> content = new ArrayList<>();
		for (int i = 0; i < file.list().length; i++) {
			content.add(file.list()[i]);
		}
		content.add(file.getAbsolutePath() + " ");
		return content;
	}

	public static ArrayList<String> makeDir(ArrayList<String> paths) {
		ArrayList<String> messages = new ArrayList<>();

		if (paths.get(0).equals("-p")) {

			for (int i = 1; i < paths.size(); i++) {

				if (paths.get(i).contains("/") || paths.get(i).contains("*") || paths.get(i).contains(":")
						|| paths.get(i).contains("<") || paths.get(i).contains(">") || paths.get(i).contains("|")) {

					messages.add(paths.get(i) + ": is an invalid file name or directory.");
				} else {

					File newFile;
					File rootDirectory = new File(paths.get(i).substring(0, paths.get(i).indexOf("\\") + 1)); // checks
																												// root
																												// dir.

					if (!rootDirectory.exists()) {
						newFile = new File(file.getAbsolutePath() + "\\" + paths.get(i));
					} else {
						newFile = new File(paths.get(i));
					}

					newFile.mkdirs();
				}
			}

		} else {

			for (int i = 0; i < paths.size(); i++) {

				if (paths.get(i).contains("/") || paths.get(i).contains("\\") || paths.get(i).contains("*")
						|| paths.get(i).contains(":") || paths.get(i).contains("<") || paths.get(i).contains(">")
						|| paths.get(i).contains("|")) {

					messages.add(paths.get(i) + ": is an invalid file name.");
				} else {
					File newFile = new File(file.getAbsolutePath() + "\\" + paths.get(i));
					newFile.mkdir();
				}
			}
		}

		return messages;
	}

	public static ArrayList<String> removeDir(ArrayList<String> paths) {
		ArrayList<String> messages = new ArrayList<>();

		if (paths.get(0).equals("-p")) {

			for (int i = 1; i < paths.size()-1; i++) {

				File newFile = new File(paths.get(i));
				if (!newFile.exists()) {
					messages.add(paths.get(i) + ": is an invalid file or directory.");
				} else if (newFile.list().length != 0) {
					messages.add(paths.get(i) + ": directory is not empty.");
				} else {
					String directory;
					while ((directory = newFile.getParent()) != null) {

						if (newFile.list().length != 0) {
							break;
						}
						newFile.delete();
						newFile = new File(directory);
					}

					newFile.delete();
				}

			}

		} else {

			for (int i = 0; i < paths.size()-1; i++) {

				File newFile = new File(paths.get(i));
				if(!newFile.isDirectory()){
					newFile = new File(file.getAbsolutePath() + "\\" + paths.get(i));
				}
				if (!newFile.exists()) {
					messages.add(paths.get(i) + ": is an invalid file or directory.");
				} else if (newFile.list().length != 0) {
					messages.add(paths.get(i) + ": Directory is not empty.");
				} else {
					newFile.delete();
				}
			}

		}

		return messages;

	}

	public static ArrayList<String> pwd() {
		ArrayList<String> returns = new ArrayList<>();
		returns.add(file.getAbsolutePath());
		return returns;
	}
	
	public static String args(String command) {
		String returns  = "";
		switch (command) {
		case "cd":
			returns= ".\\[Path]  , \"..\" , No args ";
			break;
		case "ls":
			returns= "No args";
			break;
		case "clear":
			returns= "No args";
			break;
		case "pwd":
			returns= "No args";
			break;
		case "date":
			returns= "No args";
			break;
		case "help":
			returns= "No args";
			break;
		case "cat":
			returns= "[File name]";
			break;
		case "more":
			returns= "[File name]";
			break;
		case "less":
			returns= "[File name]";
			break;
		case "mkdir":
			returns= "[directory name] , [directory name] , .. \n -p for nested directory creation";
			break;
		case "rmdir":
			returns= "[directory name] , [directory name] , ..";
			break;
		case "rm":
			returns= "[File1 name] \n -r for recursive delete";
			break;
		case "mv":
			returns= "[File1 path] , [File2 path]";
			break;
		case "cp":
			returns= "[File1 path] , [File2 path]";
			break;
		case "find":
			returns= "[File name]";
			break;
		case "args":
			returns= "[Command name]";
			break;
		default:
			returns = "Wrong command";
			break;
		}
		return returns;

	}

	public static ArrayList<String> date() {
		ArrayList<String> returns = new ArrayList<>();
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		returns.add(df.format(date));
		System.err.println(df.format(date));
		return returns;
	}

	public static ArrayList<String> copyFiles(ArrayList<String> paths) throws IOException  {

		ArrayList<String> message = new ArrayList<>();
		File source = openFile(paths.get(0));
		File destination = openFile(paths.get(1));

		if(source.exists() && destination.isDirectory() && destination.exists()){
			destination = new File(destination.getAbsolutePath() + "\\" +source.getName());
			Files.copy(source.toPath(), destination.toPath());
		}
		else{

			message.add("Invalid files or directories.");
		}

		return message;


	}

	public static ArrayList<String> remove(ArrayList<String> paths) {
		ArrayList<String> messages = new ArrayList<>();

		if(paths.get(0).equals("-r")){

			for(int i = 1; i < paths.size()-1; i++ ){

				File newFile = openFile(paths.get(i));

				if(newFile.exists()){
					recursiveDelete(newFile);
				}

				else{
					messages.add(paths.get(i) + ": is an invalid file or directory.");
				}
			}

		}
		else{

			for (int i = 0; i < paths.size()-1; i++){

				File newFile = openFile(paths.get(i));

				if(!newFile.exists()){
					messages.add(paths.get(i) + ": is an invalid file or directory.");
				}
				else if(newFile.isFile()){

					newFile.delete();
				}
				else if(newFile.isDirectory()){
					messages.add(file.getName() + " is a directory.");
				}


			}
		}
		return messages;
	}

	public static void recursiveDelete(File newFile) {

		if (newFile.isDirectory() && newFile.list().length != 0) {
			File[] array = newFile.listFiles();
			for (int i = 0; i < array.length; i++) {
				recursiveDelete(array[i]);
			}
		}
		newFile.delete();
	}

	public static ArrayList<String> move(ArrayList<String> paths) {

		ArrayList<String> message = new ArrayList<>();
		File source = openFile(paths.get(0));
		File destination = openFile(paths.get(1));
		

		if (!source.exists()) {
			message.add(source.getName() + ": is an invalid file or directory.");
		} else if (destination.isDirectory() && destination.exists()) {
			destination = new File(destination.getAbsolutePath() + "\\" + source.getName());
			try {
				Files.move(source.toPath(), destination.toPath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			
			source.renameTo(destination);
		}

		return message;
	}

	public static ArrayList<String> help() { 
		ArrayList<String> returns = new ArrayList<>();
		returns.add("cd : Change Directory ");
		returns.add(args("cd"));
		returns.add("");
		returns.add("cat : Concatenate and print (display) the content of files ");
		returns.add(args("cat"));
		returns.add("");
		returns.add("clear : Clear terminal screen");
		returns.add(args("clear"));
		returns.add("");
		returns.add("ls : list files names");
		returns.add(args("ls"));
		returns.add("");
		returns.add("cp : Copy a file to another location");
		returns.add(args("cp"));
		returns.add("");
		returns.add("mv : Move or rename files or directories");
		returns.add(args("mv"));
		returns.add("");
		returns.add("rm : Remove files");
		returns.add(args("rm"));
		returns.add("");
		returns.add("mkdir : creates a new directory ");
		returns.add(args("mkdir"));
		returns.add("");
		returns.add("rmdir : removes a directory ");
		returns.add(args("rmdir"));
		returns.add("");
		returns.add("pwd : Print Working Directory");
		returns.add(args("pwd"));
		returns.add("");
		returns.add("more : Display output one screen at a time");
		returns.add(args("more"));
		returns.add("");
		returns.add("less : Display output one screen at a time");
		returns.add(args("less"));
		returns.add("");
		returns.add("date : Display the date & time");
		returns.add(args("date"));
		returns.add("");
		returns.add("help : Display help for a built-in commands");
		returns.add(args("help"));
		returns.add("");
		returns.add("args : list all parameters on the command line, numbers or strings for specific command");
		returns.add(args("args"));
		returns.add("");
		return returns;

	}
	
	public static ArrayList<String> moreLess(String path , int startLine , int endLine){
		ArrayList<String> temp = new ArrayList<>();
		temp.add(path);
		ArrayList<String> lines = new ArrayList<>(cat(temp));
		if(endLine<startLine){
			lines.clear();
		}
			
		else if(endLine<lines.size())
			lines = new ArrayList<>(lines.subList(startLine, endLine));
		
		return lines;
		
	}
	
	public static ArrayList<String> redirect(ArrayList<String> paths) throws IOException{

		ArrayList<String> message = new ArrayList<>();
		System.err.println("RED : " + paths.get(1));
		if(!paths.get(1).substring(paths.get(1).length()-4).equals(".txt")){
			message.add("Invalid file.");
		}
		else{
		
			File overWritten = openFile(paths.get(1));
		
			FileWriter writer = new FileWriter(overWritten);
			if(!overWritten.exists()){
				overWritten.createNewFile();
			}
			writer.write(paths.get(0));
			writer.close();
		}
		

		return message;
	}

	public static ArrayList<String> doubleRedirect(ArrayList<String> paths) throws IOException{

		ArrayList<String> message = new ArrayList<>();
		
		if(!paths.get(1).substring(paths.get(1).length()-4).equals(".txt")){
			message.add("Invalid files.");
		}
		else{
			
			File overWritten = openFile(paths.get(1));
			
			RandomAccessFile writer = new RandomAccessFile(overWritten, "rw");
			writer.seek(overWritten.length());
			writer.writeBytes(paths.get(0));
			writer.close();
		}
		

		return message;
	}

	public static File openFile(String path){
		File openedFile;
		
		if(!path.contains("\\")){
			
			openedFile = new File(file.getAbsolutePath() + "\\" + path);
		}
		else{
			openedFile = new File(path);
		}
		
		return openedFile;
	}
	
	public static ArrayList<String> find(String name , File file ){
		ArrayList<String> returns= new ArrayList<>();
		  File[] files = file.listFiles();
	        if(files!=null)
	        for (File f : files)
	        {
	            if (f.isDirectory())
	            {
	            	returns.addAll(find(name,f));
	            }
	            else if (name.equalsIgnoreCase(f.getName()))
	            {
	            	String t = f.getParentFile().toString();
	            	returns.add(t);
	                System.out.println(f.getParentFile());
	            }
	        }
	        return returns;
	}
	
	public static ArrayList<String> grep(ArrayList <String> paths) throws IOException{
		ArrayList<String> content = new ArrayList<>();
		
		File newFile = openFile(paths.get(1));
		
		if(!newFile.exists()){
			content.add(newFile.getAbsolutePath() + ": is an invalid file or directory.");
		}
		else{
			
			String line;
			Scanner reader = new Scanner(newFile);
			while(reader.hasNextLine()){
				line = reader.nextLine();
				if(line.contains(paths.get(0))){
					content.add(line);
				}
			}
			
			if(content.isEmpty()){
				content.add(paths.get(0) + ": is not available in this file.");
			}
		}
		
		
		return content;
	}

}
