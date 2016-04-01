package vfs;
import java.util.Date;

public class FileModel {

	String name;
	String Type;
//	String Location;
	Integer size;
	Date CreationDate ;
	Date LastModificationDate;
	
	public FileModel(String name, String type, String location, Integer size, Date creationDate, Date lastModificationDate) {		
		this.name = name;
		Type = type;
	//	Location = location;
		this.size = size;
		CreationDate = creationDate;
		LastModificationDate = lastModificationDate;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	/*public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}*/
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
