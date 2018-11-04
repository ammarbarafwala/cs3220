package cs3220.servlet.lab15.model;

public class Reservation {

	String time;
	String day;
	String name;
	public Reservation(String time, String day, String name) {
		
		this.time = time;
		this.day = day;
		this.name = name;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
