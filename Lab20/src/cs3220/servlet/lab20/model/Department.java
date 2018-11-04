package cs3220.servlet.lab20.model;

import java.util.ArrayList;
import java.util.List;

public class Department {

    private String name;

    private List<Faculty> faculty;
    
    public Department(String name) {
		this.name = name;
		this.faculty = new ArrayList<>();
	}

	public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public List<Faculty> getFaculty()
    {
        return faculty;
    }

    public void setFaculty( List<Faculty> faculty )
    {
        this.faculty = faculty;
    }

}