package vfs;
import java.util.Date;
public class FolderModel {
	String name;
	//String path;
	Date CreationDate;
	Date LastModificationDate;
	
	public FolderModel(String name, Date creationDate, Date lastModificationDate) {		
		this.name = name;
		//this.path = path;
		CreationDate = creationDate;
		LastModificationDate = lastModificationDate;
	}
	public FolderModel(String name ) {		
		this.name = name;		
		Date d = new Date();
		CreationDate = d;
		LastModificationDate = d;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/*public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}*/
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
	
}
