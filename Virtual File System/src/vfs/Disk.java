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
		this.numOfBlocks = numOfBlocks;
		drive = new RandomAccessFile("VFSD.vfs", "rw");
		
		drive.setLength(diskSize);
		
		String temp = String.format("%" + numOfBlocks + "s","").replaceAll(" ", "0");
		
		freeSpaceManager = new StringBuilder(temp);

	}
	public void DisplayStatus(){
	
		System.out.println("Disk Status:");
		System.out.println("Total Size: " + (numOfBlocks * 1024) +"KBs");
		System.out.print("Free Space: " + (getFreeBlocks() * 1024) +"KBs");
		System.out.print("Allocated Space: " + (getAllocatedBlocks() * 1024) +"KBs");
		System.out.print("No. of Free Blocks: " + getFreeBlocks());
		System.out.print("No. of Allocated Blocks: " + getAllocatedBlocks());
	}
	
	public void DisplayTreeStructure(){
		tree.printTree(tree.getRootNode(),0);
	}
	
	
	public void CFolder(String pathString) {
		
		ArrayList<String> path = new ArrayList<>();
		
		path.addAll(Arrays.asList(pathString.split("/")));
		
		FolderModel foldermodel = new FolderModel(path.get(path.size()-1),pathString);
		
		tree.createFolder(path, foldermodel);
		
	}
	
	public void CFile(String pathString ,int fileSize){
		

		
	}
	
	public void DFile(String pathString) {
		

	}
	
	public void DFolder(String pathString) {

		
	}
	
	public int getFreeBlocks(){
		
		int counter = 0;
		for(int i = 0; i < freeSpaceManager.length(); i++){
			
			if(freeSpaceManager.charAt(i) == '0'){
				counter++;
			}
		}
		
		return counter;
	}
	
	
	public int getAllocatedBlocks(){
		
		int counter = 0;
		for(int i = 0; i < freeSpaceManager.length(); i++){
			
			if(freeSpaceManager.charAt(i) == 'i'){
				counter++;
			}
		}
		
		return counter;
	}
	
	public void printFreeSpaceManager(){
		
		System.out.println(freeSpaceManager.toString());
	}
	
	
}
