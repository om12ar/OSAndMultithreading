package vfs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Disk {
	Tree tree;
	int diskSize;
	int numOfBlocks;
	RandomAccessFile drive;

	StringBuilder freeSpaceManager;

	public Disk(int numOfBlocks) throws IOException {
		File f = new File("DiskStructure.vfs");
		if(f.exists()) { 
			ReadDiskFromFile();
		}
		else{
			tree = new Tree();
			diskSize = numOfBlocks * 1024;
			this.numOfBlocks = numOfBlocks;
			drive = new RandomAccessFile("VFSD.vfs", "rw");

			drive.setLength(diskSize);

			String temp = String.format("%" + numOfBlocks + "s", "").replaceAll(" ", "0");

			freeSpaceManager = new StringBuilder(temp);
		}
		
	}

	public void DisplayStatus() {

		System.out.println("Disk Status:");
		System.out.println("Total Size: " + (numOfBlocks * 1024) + "KBs");
		System.out.println("Free Space: " + (getFreeBlocks() * 1024) + "KBs");
		System.out.println("Allocated Space: " + (getAllocatedBlocks() * 1024) + "KBs");
		System.out.println("No. of Free Blocks: " + getFreeBlocks());
		System.out.println("No. of Allocated Blocks: " + getAllocatedBlocks());
	}

	public void DisplayTreeStructure() {
		tree.printTree(tree.getRootNode(), 0);
	}

	public void CFolder(String pathString) {

		ArrayList<String> path = new ArrayList<>();

		path.addAll(Arrays.asList(pathString.split("/")));

		FolderModel foldermodel = new FolderModel(path.get(path.size() - 1), pathString);

		tree.createFolder(path, foldermodel);

	}

	public void CFile(String pathString, int fileSize) {

	}

	public void DFile(String pathString) {

	}

	public void DFolder(String pathString) {

	}

	public int getFreeBlocks() {

		int counter = 0;
		for (int i = 0; i < freeSpaceManager.length(); i++) {

			if (freeSpaceManager.charAt(i) == '0') {
				counter++;
			}
		}

		return counter;
	}

	public int getAllocatedBlocks() {

		int counter = 0;
		for (int i = 0; i < freeSpaceManager.length(); i++) {

			if (freeSpaceManager.charAt(i) == '1') {
				counter++;
			}
		}

		return counter;
	}

	public void printFreeSpaceManager() {

		System.out.println(freeSpaceManager.toString());
	}

	public void SaveDiskToFile() {

		try {
			FileOutputStream fos = null;
			ObjectOutputStream oos = null;

			fos = new FileOutputStream("DiskStructure.vfs");

			oos = new ObjectOutputStream(fos);
			oos.writeObject(tree);
			oos.writeInt(diskSize);
			oos.writeInt( numOfBlocks);
			oos.writeUTF(freeSpaceManager.toString());

			oos.close();
			fos.close();

		} catch (FileNotFoundException ex) {
			Logger.getLogger(Disk.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(Disk.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public void ReadDiskFromFile() {
		try {
			FileInputStream fis = null;
			ObjectInputStream ois = null;

			fis = new FileInputStream("DiskStructure.vfs");
			ois = new ObjectInputStream(fis);
			tree = (Tree) ois.readObject();
			diskSize = ois.readInt();
			numOfBlocks = ois.readInt();
			drive = new RandomAccessFile("VFSD.vfs", "rw");
			drive.setLength(diskSize);
			String temp = ois.readUTF();
			freeSpaceManager = new StringBuilder(temp);
			ois.close();
			fis.close();

		} catch (FileNotFoundException ex) {
			Logger.getLogger(Disk.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(Disk.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(Disk.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

}

