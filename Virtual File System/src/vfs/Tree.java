package vfs;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Tree  implements Serializable  {
    private Node<FolderModel> rootNode;

    public Tree() {
    	Date d = new Date();
		System.out.println("Disk.Disk() + " + d.toString());
		
    	FolderModel rootFolder = new FolderModel("root" , "root" );
		rootNode = new Node<>(rootFolder);
	}
	public Node<FolderModel> getRootNode() {
		return rootNode;
	}

	public void setRootNode(Node<FolderModel> rootNode) {
		this.rootNode = rootNode;
	}    
	
	
	Node<FolderModel> findFolder(ArrayList<String> path){
		
		Node<FolderModel> traverse = new Node<>();
		traverse= rootNode;
		
		//Check if path to folder exists 
		for(int i= 1 ; i < path.size()-1  ;i++){
			boolean found = false;
			
			for (int j = 0; j < traverse.getFolders().size(); j++) {
			
				if(traverse.getFolders().get(j).getData().getName().equals(path.get(i))){
					
					traverse = traverse.getFolders().get(j);
					found = true;
					break;
					
				}
			}
			if(!found){
				System.out.println("This path is not found" );
				return null;
			}
			
			
		}
		System.out.println(path.toString());
		System.out.println(path.get(path.size() - 1));
		System.out.println(traverse.getData().getName());
		for (int i = 0; i < traverse.getFolders().size(); i++) {
			
			if( traverse.getFolders().get(i).getData().getName().equals(path.get(path.size() - 1))){
				System.out.println(traverse.getData().getName());
				return traverse.getFolders().get(i);
			}
		}
		
		return null;
	}
	public Boolean createFolder(ArrayList<String> path, FolderModel newFolder){
		Node<FolderModel> traverse = new Node<>();
		traverse= rootNode;
		
		//Check if path to folder exists 
		for(int i= 1 ; i < path.size()-1  ;i++){
			boolean found = false;
			
			for (int j = 0; j < traverse.getFolders().size(); j++) {
			
				if(traverse.getFolders().get(j).getData().getName().equals(path.get(i))){
					traverse = traverse.getFolders().get(j);
					found= true;
					break;
				}
			}
			if(!found){
				System.out.println("This path is not found" );
				return false;
			}
		}
	
		
		// check if this folder already exists in this path	
		for (int i = 0; i < traverse.getFolders().size(); i++) {
			
			if( traverse.getFolders().get(i).getData().getName().equals(newFolder.getName())){
				System.out.println("path Already exists" );
				return false;
			}
		}
		
		
		if(traverse.getData().getPermissions().get(ProtectionLayer.currentUser) == null ||
				traverse.getData().getPermissions().get(ProtectionLayer.currentUser).charAt(0) == '0'){
			System.out.println("Permission denied");
		}
		else{
			Node<FolderModel> newFolderNode = new Node<FolderModel>(newFolder);
			newFolderNode.getData().setPermissions("admin", "11");
			newFolderNode.getData().setPermissions(ProtectionLayer.currentUser, "11");
			traverse.getFolders().add(newFolderNode);
		}
		return true;
		
	}
	
	public Boolean deleteFolder(ArrayList<String> path){
		Node<FolderModel> traverse = new Node<>();
		traverse= rootNode;
		String FileName = path.get(path.size()-1);
		//Check if path to folder exists 
		for(int i= 1 ; i < path.size()-1  ;i++){
			boolean found = false;
			
			for (int j = 0; j < traverse.getFolders().size(); j++) {
			
				if(traverse.getFolders().get(j).getData().getName().equals(path.get(i))){
					traverse = traverse.getFolders().get(j);
					found= true;
					break;
				}
			}
			if(!found){
				System.out.println("This path is not found" );
				return false;
			}
		}
	
		
		// check if this folder already exists in this path	
		for (int i = 0; i < traverse.getFolders().size(); i++) {
			
			if( traverse.getFolders().get(i).getData().getName().equals(FileName)){
				//System.out.println("in main del " + traverse.getFolders().get(i).getData().getName());
				boolean couldDelete = 	couldDelete(traverse.getFolders().get(i));
				
				//System.out.println("CouldDelete: " + couldDelete);
				if(couldDelete){
					traverse.getFolders().remove(i);
					return true;
				}
				else{
					System.out.println("Permission denied.");
					return false;
				}
				
			}
		}
		
		System.out.println("Folder Not Found in path" );
		return false;
		
	}
	
	public Boolean createFile(ArrayList<String> path, FileModel newFile){
		Node<FolderModel> traverse = new Node<>();
		traverse= rootNode;
		
		//Check if path to folder exists 
		for(int i= 1 ; i < path.size()-1  ;i++){
			boolean found = false;
			
			for (int j = 0; j < traverse.getFolders().size(); j++) {
			
				if(traverse.getFolders().get(j).getData().getName().equals(path.get(i))){
					traverse = traverse.getFolders().get(j);
					found= true;
					break;
				}
			}
			if(!found){
				System.out.println("This path is not found !"  );
				return false;
			}
		}
	
		
		// check if this file already exists in this path	
		for (int i = 0; i < traverse.getFiles().size(); i++) {
			if( traverse.getFiles().get(i).getData().getName().equals(newFile.getName())){
				System.out.println("this File Already exists !" );
				return false;
			}
		}
		
		if(traverse.getData().getPermissions().get(ProtectionLayer.currentUser) == null ||
				traverse.getData().getPermissions().get(ProtectionLayer.currentUser).charAt(0) == '0'){
			System.out.println("Permission denied");
		}
		else{
				Node<FileModel> newFileNode = new Node<FileModel>(newFile);
				traverse.getFiles().add(newFileNode);
			}
		return true;
		
	}
	
	public boolean couldDelete(Node<FolderModel> node){
		
	//	System.out.println("node: " + node.getData().getName());
	//	System.out.println("size:" + node.getFolders().size());
		
		if (node.getFolders().size() == 0){
		//	System.out.println("Deepest " + node.getData().getName());
			if(node.getData().getPermissions().get(ProtectionLayer.currentUser) == null || node.getData().getPermissions().get(ProtectionLayer.currentUser).charAt(1) == '0'){
			//	System.out.println(" false ");
				return false;
			}
			
			else{
			//	System.out.println(" true ");
				return true;
			}
		}
		
		boolean couldDel = true;
		for(int i =0; i < node.getFolders().size(); i++){
			
			System.out.println("curr folder " + node.getFolders().get(i).getData().getName());
			if (couldDelete(node.getFolders().get(i)) == false) 
				couldDel = false;
				break;
		}
		
		return couldDel;
	}
	public Boolean deleteFile(ArrayList<String> path) {
		
		Node<FolderModel> traverse = new Node<>();
		traverse= rootNode;
		//Check if path to folder exists 
		
		String fileName = path.get(path.size()-1);
		for(int i= 1 ; i < path.size()-1  ;i++){
			boolean found = false;
			
			for (int j = 0; j < traverse.getFolders().size(); j++) {
				if(traverse.getFolders().get(j).getData().getName().equals(path.get(i))){
					traverse = traverse.getFolders().get(j);
					found= true;
					break;
				}
			}
			if(!found){
				System.out.println("This path is not found" );
				return false;
			}
		}
		
		if(traverse.getData().getPermissions().get(ProtectionLayer.currentUser) == null ||
				traverse.getData().getPermissions().get(ProtectionLayer.currentUser).charAt(0) == '1'){
				System.out.println("Permission denied");
				return false;
		}
		
		for (int i = 0; i < traverse.getFiles().size(); i++) {
			
			if( traverse.getFiles().get(i).getData().getName().equals(fileName)){
				traverse.getFiles().remove(i);
				return true;
			}
		}
		System.out.println("File Not Found in path" );
		return false;
		
	}
	
	
	public void printTree(Node<FolderModel> traverse , int d){
		String indent="";
		if(d>0){
			indent = String.format(String.format("%%%ds", d), " ").replace(" ","\t");
			System.out.print(indent);
		}
		
		System.out.println(traverse.getData().getName() +" :");
		for(int i=0; i < traverse.getFolders().size() ;i++){
			
			printTree(traverse.getFolders().get(i) , d+1);
			//System.out.println(traverse.getData().getName() +" UP " + d);

		}
		if(traverse.getFiles().size()>0){
			System.out.print(indent+"\t");
		}
		for(int j =0 ;j < traverse.getFiles().size() ;j++){
			System.out.print(traverse.getFiles().get(j).getData().getName() +" , ");
			if(j == traverse.getFiles().size()-1){
				System.out.println();
			}
		}
	}
	
	
	
}