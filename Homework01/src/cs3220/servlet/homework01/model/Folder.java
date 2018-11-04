package cs3220.servlet.homework01.model;

public class Folder {

	Integer id;
	String name;
	Folder parent;

	public Folder(Integer id, String name, Folder parent) {
		this.id = id;
		this.name = name;
		this.parent = parent;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Folder getParent() {
		return parent;
	}

	public void setParent(Folder parent) {
		this.parent = parent;
	}

}
