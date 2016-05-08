package vfs;
import java.io.Serializable;
import java.util.Date;
import java.util.TreeMap;
public class FolderModel implements Serializable{
	String name;
	String path;
	Date CreationDate;
	Date LastModificationDate;
	TreeMap<String, String> usersPermissions;
	
	
	public FolderModel(String name , String path, TreeMap<String, String> usersPermissions) {		
		this.name = name;		
		this.path = path;
		Date d = new Date();
		CreationDate = d;
		LastModificationDate = d;
		this.usersPermissions = new TreeMap<>(usersPermissions);
	
	}
	
	public FolderModel(String name , String path) {		
		this.name = name;		
		this.path = path;
		Date d = new Date();
		CreationDate = d;
		LastModificationDate = d;
		usersPermissions = new TreeMap<>();
		usersPermissions.put("admin", "11");
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Date getCreationDate() {
		return CreationDate;
	}
	public void setCreationDate(Date creationDate) {
		CreationDate = creationDate;
	}
	public Date getLastModificationDate() {
		return LastModificationDate;
	}
	public void setLastModificationDate(Date lastModificationDate) {
		LastModificationDate = lastModificationDate;
	}
	
	public void setPermissions(String user, String permissions){
		if(usersPermissions.get(user) == null){
			usersPermissions.put(user, permissions);
		}
		else{
			usersPermissions.remove(user);
			usersPermissions.put(user, permissions);
		}
	}
	
	TreeMap<String, String> getPermissions(){
		
		return usersPermissions;
	}
}
