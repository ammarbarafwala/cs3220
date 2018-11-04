package cs3220.servlet.homework03.model;

import java.util.Date;

public class File {
	int id;
	String name;
	String type; // content type of the uploaded file
	long size;
	Date date;
	File parent;
	boolean isFolder;
	public File(int id2, String name, String type, long size, File parent, boolean isFolder) {
		super();
		this.id = id2;
		this.name = name;
		this.type = type;
		this.size = size;
		date = new Date();
		this.parent = parent;
		this.isFolder = isFolder;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public File getParent() {
		return parent;
	}
	public void setParent(File parent) {
		this.parent = parent;
	}
	public boolean isFolder() {
		return isFolder;
	}
	public void setFolder(boolean isFolder) {
		this.isFolder = isFolder;
	}
	
}
