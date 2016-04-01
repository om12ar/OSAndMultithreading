package vfs;

import java.util.ArrayList;

public class ContiguousAllocation implements AllocationTechnique {
	
	//  file 	start 	#ofblocks
	ArrayList<Pair<String, Pair <Integer ,Integer> > > directory ;
	
	public ContiguousAllocation() {
	 
		directory = new ArrayList<>();
	}
	@Override
	public Boolean Save(String filePath ,int fileSize) {
		//int index =0;
		
		// INsert in a deleted spot ///////////////////////////////
		for(int i =0 ;i < directory.size() ;i++){
			// if file deleted && the space is enough 
			if(directory.get(i).first == "" && directory.get(i).second.second >= fileSize){
				directory.get(i).first = filePath ;
				directory.get(i).second.second = fileSize;
				return true ;
			}
		}
		// Create a new Spot/////////////////////////////////
		int startIndex ;
		
		if(directory.isEmpty()){
			startIndex =0;
		}else{
			startIndex = directory.get(directory.size()-1).second.first + directory.get(directory.size()-1).second.second;
		}
		
		//Disk is full
		if(startIndex+fileSize > Disk.diskSize){
			return false ;
		}
		
		// Insert Done  
		Pair<Integer, Integer> temp = new Pair<Integer, Integer>(startIndex, fileSize);
		directory.add(new Pair<String, Pair<Integer,Integer>>(filePath, temp));
		return true;
	}

	@Override
	public Boolean Delete(String path) {
		boolean found = false;
		for(int i =0 ;i<directory.size();i++ ){
			//to delete all the children 
			 if(directory.get(i).first.startsWith(path)){
                 directory.get(i).first = "";
                 found =true ;
             }
		}
		return found;
	}
	
	@Override
	public String toString (){
		return directory.toString();
		
	}

}
