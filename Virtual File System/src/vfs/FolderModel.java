package vfs;
import java.util.Date;
public class FolderModel {
	String name;
	String path;
	Date CreationDate;
	Date LastModificationDate;
	
	
	public FolderModel(String name , String path) {		
		this.name = name;		
		this.path = path;
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
