package vfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Disk {
	Tree tree = new Tree();
	
	//ArrayList<FileModel> files;
//	ArrayList<FolderModel> folders ;
	
	public Disk() {
	/*	Date d = new Date();
		System.out.println("Disk.Disk() + " + d.toString());
		FolderModel fm = new FolderModel("test", d, d);
		String pathName = "/test";
		
		String[] path = pathName.split("/");
		path[0]="/";
		for(int i=0;i<path.length;i++){
			System.out.println("Disk.paths()" +path[i]);
		}
		tree.createFolder(path, fm);
		DisplayTreeStructure();
		DisplayStatus();*/
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
		if(path.size()>1 && path.get(1).equals(" ")){
			
			path.remove(1);
		}
		//System.err.println("Disk.CFolder()" + path);
		tree.createFolder(path, foldermodel);
		
	}
}
