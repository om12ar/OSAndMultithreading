package vfs;
import java.io.Serializable;
import java.util.Date;

public class FileModel implements Serializable{

	String name;
	String path;
	Integer size;
	Date CreationDate ;
	Date LastModificationDate;
	
	public FileModel(String name,String path , Integer size) {
		
		this.name = name;
		this.path = path;
		this.size = size;
		Date d= new Date();
		CreationDate = d;
		LastModificationDate = d;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
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
