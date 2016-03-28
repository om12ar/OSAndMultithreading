package vfs;
import java.util.Date;
public class Folder {
	String name;
	String path;
	Date CreationDate;
	Date LastModificationDate;
	
	public Folder(String name, String path, Date creationDate, Date lastModificationDate) {		
		this.name = name;
		this.path = path;
		CreationDate = creationDate;
		LastModificationDate = lastModificationDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
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
