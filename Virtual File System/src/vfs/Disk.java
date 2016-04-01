package vfs;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class Disk {
	Tree tree ;
	int diskSize ;
	int numOfBlocks;
	RandomAccessFile drive;
	
	StringBuilder freeSpaceManager;
	
	
	public Disk(int numOfBlocks) throws IOException {

		tree = new Tree();
		diskSize = numOfBlocks * 1024;
		
		drive = new RandomAccessFile("VFSD.vfs", "rw");
		
		drive.setLength(diskSize);
		
		String temp = String.format("%" + numOfBlocks + "s","").replaceAll(" ", "0");
		
		freeSpaceManager = new StringBuilder(temp);

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
		

		
		
	}
	public void DFile(String pathString) {
		

	}
	public void DFolder(String pathString) {

		
	}
	
	
	
}
