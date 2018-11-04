package cs3220.servlet.lab19.model;

public class Lab19File {

	Integer id;
	Integer ownerId;
	Integer parentId;
	String name;
	boolean isFolder;
	
	public Lab19File(Integer id, Integer ownerId, Integer parentId, String name, boolean isFolder) {
		this.id = id;
		this.ownerId = ownerId;
		this.parentId = parentId;
		this.name = name;
		this.isFolder = isFolder;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isFolder() {
		return isFolder;
	}

	public void setFolder(boolean isFolder) {
		this.isFolder = isFolder;
	}
	
}
