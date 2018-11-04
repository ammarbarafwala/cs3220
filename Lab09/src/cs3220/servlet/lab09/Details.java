package cs3220.servlet.lab09;

import java.util.Date;
public class Details {

	private String name;
	Date date;
	long size;
	public Details(String name, Date date, long l) {
		super();
		this.name = name;
		this.date = date;
		this.size = l;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	
}
