package cs3220.servlet.finalLab.model;

public class CourseMapping {

	String quarterCourseName;
	String semesterCourseName;
	
	public CourseMapping(String quarterCourseName, String semesterCourseName) {
		
		this.quarterCourseName = quarterCourseName;
		this.semesterCourseName = semesterCourseName;
	}

	public String getQuarterCourseName() {
		return quarterCourseName;
	}

	public void setQuarterCourseName(String quarterCourseName) {
		this.quarterCourseName = quarterCourseName;
	}

	public String getSemesterCourseName() {
		return semesterCourseName;
	}

	public void setSemesterCourseName(String semesterCourseName) {
		this.semesterCourseName = semesterCourseName;
	}
	
}
