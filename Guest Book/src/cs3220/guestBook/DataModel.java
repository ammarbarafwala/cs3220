package cs3220.guestBook;

public class DataModel {

	String name, comment;

	public DataModel(String name, String comment) {
		super();
		this.name = name;
		this.comment = comment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
