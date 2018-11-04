package cs3220.servlet.finalLab.model;

import java.util.ArrayList;
import java.util.List;

public class CourseDetails {

	List<String> quarterCourseList;
	List<String> semesterCourseList;
	List<CourseMapping> courseMapping;
	public CourseDetails() {
		quarterCourseList = new ArrayList<>();
		semesterCourseList = new ArrayList<>();
		courseMapping = new ArrayList<>();
	}
	public List<String> getQuarterCourseList() {
		return quarterCourseList;
	}
	public void setQuarterCourseList(List<String> quarterCourseList) {
		this.quarterCourseList = quarterCourseList;
	}
	public List<String> getSemesterCourseList() {
		return semesterCourseList;
	}
	public void setSemesterCourseList(List<String> semesterCourseList) {
		this.semesterCourseList = semesterCourseList;
	}
	public List<CourseMapping> getCourseMapping() {
		return courseMapping;
	}
	public void setCourseMapping(List<CourseMapping> courseMapping) {
		this.courseMapping = courseMapping;
	}
	
}
