package vfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Disk {
	Tree tree = new Tree();
	
	//ArrayList<FileModel> files;
//	ArrayList<FolderModel> folders ;
	
	public Disk() {
	
	}
	public void DisplayStatus(){
	
		
	}
	public void DisplayTreeStructure(){
		tree.printTree(tree.getRootNode(),0);
	}
	public void CFolder(String pathString , String name) {
		FolderModel foldermodel = new FolderModel(name);
		ArrayList<String> path = new ArrayList<>();
		
		path.addAll(Arrays.asList(pathString.split("/")));
		if(path.size()>0){
			path.set(0, "/");
		}
		else{
			path.add("/");
		}
		
		//System.err.println("Disk.CFolder()" + path);
		tree.createFolder(path, foldermodel);
		
	}
	public void CFile(String pathString , String name ,int fileSize){
		FileModel filemodel = new FileModel(name,fileSize);
		ArrayList<String> path = new ArrayList<>();
		
		path.addAll(Arrays.asList(pathString.split("/")));
		if(path.size()>0){
			path.set(0, "/");
		}
		else{
			path.add("/");
		}
		
		//System.err.println("Disk.CFolder()" + path);
		tree.createFile(path, filemodel);
	}
}
