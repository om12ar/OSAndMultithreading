package vfs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IndexedAllocation extends Disk {

	ArrayList<Pair<String, Integer>> directory;
	TreeMap<Integer, ArrayList<Integer>> indexBlock;
	
	public IndexedAllocation(int numOfBlocks) throws IOException {
		super(numOfBlocks);
		
		directory = new ArrayList<>();
		indexBlock = new TreeMap<>();

	}

	
	
	public void CFile(String filePath ,int fileSize){
		
		ArrayList<String> path = new ArrayList<>();
		
		path.addAll(Arrays.asList(filePath.split("/")));
		
		FileModel filemodel = new FileModel(path.get(path.size()-1),filePath , fileSize);
		

		double blocksPerFile = Math.ceil(fileSize/1024.0) ; 
		
		if(blocksPerFile > getFreeBlocks()){
			
			System.out.println("Creation failed, No enough space.");
		}
		else{
			
			if(tree.createFile(path, filemodel)){
				
				int indexPointer = (int)(Math.random() * numOfBlocks);
				while(freeSpaceManager.charAt(indexPointer) == '1'){
					indexPointer = (int)(Math.random() * numOfBlocks);
				}
				
				directory.add(new Pair<String, Integer>(filePath, indexPointer));
				indexBlock.put(indexPointer, new ArrayList<>());
				
				freeSpaceManager.setCharAt(indexPointer, '1');
				
				
				for(int i = 0; i < blocksPerFile; i++){
					
					int blockNo = (int)(Math.random() * numOfBlocks);
					while(freeSpaceManager.charAt(blockNo) == '1'){
						blockNo = (int)(Math.random() * numOfBlocks);
					}
					
					indexBlock.get(indexPointer).add(blockNo);
					freeSpaceManager.setCharAt(blockNo, '1');
				}
				
				
				
			}
			else{
				System.out.println("Creation failed");
			}
		}
		

	}
	public void DFile(String filePath){
		
		ArrayList<String> path = new ArrayList<>();
		path.addAll(Arrays.asList(filePath.split("/")));
		
		if(tree.deleteFile(path)){
			
		
		for(int i =0 ;i<directory.size();i++ ){
			//to delete all the children 
			 if(directory.get(i).first.startsWith(filePath)){
                 
                 int indexPointer = directory.get(i).second;
                 int blocksPerFile = indexBlock.get(indexPointer).size();
                 
                 freeSpaceManager.setCharAt(indexPointer, '0');
                 
                 for(int j = 0 ; j < blocksPerFile; j++){
                	 
                	int index = indexBlock.get(indexPointer).get(j); 
                	freeSpaceManager.setCharAt(index, '0');
                 }
                 
                 indexBlock.remove(indexPointer);
                 directory.remove(i);
                 i--;
             }
		}
		
		}
		else{
			System.out.println("Error");
		}
		

	}
	public void DFolder(String folderPath){

		
		ArrayList<String> path = new ArrayList<>();
		
		path.addAll(Arrays.asList(folderPath.split("/")));
		
		if(tree.deleteFolder(path)){
			
		
		for(int i =0 ;i<directory.size();i++ ){
			//to delete all the children 
			 if(directory.get(i).first.startsWith(folderPath)){
                
                 
                 int indexPointer = directory.get(i).second;
                 int blocksPerFile = indexBlock.get(indexPointer).size();
                 
                 freeSpaceManager.setCharAt(indexPointer, '0');
                 
                 for(int j = 0 ; j < blocksPerFile; j++){
                	 
                	int index = indexBlock.get(indexPointer).get(j); 
                	freeSpaceManager.setCharAt(index, '0');
                 }
                 
                 indexBlock.remove(indexPointer);
                 directory.remove(i);
                 i--;
             }
		}
		
		}
		else{
			System.out.println("Error");
		}
		
		
	}
	
	
	@Override
	public String toString (){
		
		for(int i = 0; i < directory.size(); i++){
			
			int index = directory.get(i).second;
			System.out.print("[(" + directory.get(i).first +", " + index +")] ");
			System.out.println("[(" + index + ", " + indexBlock.get(index).toString() + ")]");
		}
		
		return "";
		
	}
	
	@Override
	public void SaveDiskToFile() {
		System.out.println("Disk.SaveDiskToFile()");
		try {
			FileOutputStream fos = null;
			ObjectOutputStream oos = null;

			fos = new FileOutputStream("DiskStructure.vfs");

			oos = new ObjectOutputStream(fos);
			oos.writeObject(tree);
			oos.writeInt(diskSize);
			oos.writeInt( numOfBlocks);
			oos.writeUTF(freeSpaceManager.toString());
			oos.writeObject(directory);
			oos.writeObject(indexBlock);
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
			
			directory = (ArrayList<Pair<String, Integer>>) ois.readObject();
			indexBlock = (TreeMap<Integer, ArrayList<Integer>>) ois.readObject();
			
			System.out.println("IndexedAllocation.ReadDiskFromFile()" + directory);
			System.out.println("IndexedAllocation.ReadDiskFromFile()" + indexBlock);
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
