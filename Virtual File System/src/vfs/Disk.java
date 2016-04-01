package vfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Disk {
	Tree tree ;
	static int diskSize ;
	AllocationTechnique at ;
	//ArrayList<FileModel> files;
//	ArrayList<FolderModel> folders ;
	
	public Disk(int x ,int diskSize) {
		if(x==1){
			at = new ContiguousAllocation();		
		}
		else{
			at = new IndexedAllocation();
		}
		tree = new Tree();
		this.diskSize = diskSize;
	}
	public void DisplayStatus(){
	
		
	}
	public void DisplayTreeStructure(){
		tree.printTree(tree.getRootNode(),0);
	}
	public void CFolder(String pathString) {
		
		ArrayList<String> path = new ArrayList<>();
		
		path.addAll(Arrays.asList(pathString.split("/")));
		FolderModel foldermodel = new FolderModel(path.get(path.size()-1),pathString);
		//System.err.println("Disk.CFolder()" + path);
		tree.createFolder(path, foldermodel);
		
	}
	public void CFile(String pathString ,int fileSize){
		
		ArrayList<String> path = new ArrayList<>();
		
		path.addAll(Arrays.asList(pathString.split("/")));
		
		FileModel filemodel = new FileModel(path.get(path.size()-1),pathString , fileSize);
		
		//System.err.println("Disk.CFolder()" + path);
		if(at.Save(pathString, fileSize)){
			tree.createFile(path, filemodel);
		}
		else{
			System.out.println("Creation failed");
		}
		
		
	}
	public void DFile(String pathString) {
		
		ArrayList<String> path = new ArrayList<>();
		path.addAll(Arrays.asList(pathString.split("/")));
		if(at.Delete(pathString)){
			tree.deleteFile(path);
		}
	}
	public void DFolder(String pathString) {
		ArrayList<String> path = new ArrayList<>();
		path.addAll(Arrays.asList(pathString.split("/")));
		if(at.Delete(pathString)){
			tree.deleteFolder(path);
		}
		
	}
}
