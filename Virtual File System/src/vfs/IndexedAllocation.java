package vfs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

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
	
	

}
