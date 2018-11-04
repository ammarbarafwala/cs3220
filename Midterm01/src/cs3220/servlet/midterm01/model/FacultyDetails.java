package cs3220.servlet.midterm01.model;

import java.util.ArrayList;
import java.util.List;

public class FacultyDetails {

	String departmentName;
	List<String> facultyNameList;
	
	public FacultyDetails(String departmentName) {
		super();
		this.departmentName = departmentName;
		facultyNameList=new ArrayList<>();
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public List<String> getFacultyNameList() {
		return facultyNameList;
	}

	public void setFacultyNameList(List<String> facultyNameList) {
		this.facultyNameList = facultyNameList;
	}
	
}
