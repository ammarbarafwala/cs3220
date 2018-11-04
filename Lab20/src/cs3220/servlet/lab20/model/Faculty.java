package cs3220.servlet.lab20.model;

public class Faculty {

    private String name;

    private boolean isChair;
    
    private int id;
    
    private int departmentId;

    public Faculty(String name, boolean isChair, int id, int departmentId) {
		this.name = name;
		this.isChair = isChair;
		this.id = id;
		this.departmentId = departmentId;
	}
    
	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public boolean isChair()
    {
        return isChair;
    }

    public void setChair( boolean isChair )
    {
        this.isChair = isChair;
    }

}