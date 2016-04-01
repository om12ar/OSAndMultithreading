package vfs;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Tree {
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
			System.out.println(traverse.getFolders().get(i).getData().getName()+" != " + newFolder.getName());
			if( traverse.getFolders().get(i).getData().getName().equals(newFolder.getName())){
				System.out.println("path Already exists" );
				return false;
			}
		}
		
		Node<FolderModel> newFolderNode = new Node<FolderModel>(newFolder);
		traverse.getFolders().add(newFolderNode);
		
		return true;
		
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
	
		
		// check if this folder already exists in this path	
		for (int i = 0; i < traverse.getFiles().size(); i++) {
			if( traverse.getFiles().get(i).getData().getName().equals(newFile.getName())){
				System.out.println("this File Already exists !" );
				return false;
			}
		}
		
		Node<FileModel> newFileNode = new Node<FileModel>(newFile);
		traverse.getFiles().add(newFileNode);
		
		return true;
		
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
		for(int j =0 ;j < traverse.getFiles().size() ;j++){
			System.out.print(indent+"\t"+traverse.getFiles().get(j).getData().getName() +" , ");
			if(j == traverse.getFiles().size()-1){
				System.out.println();
			}
		}
	}
	
	
}