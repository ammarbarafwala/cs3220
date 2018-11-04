package cs3220.servlet.homework04.model;

import java.util.Map;

public class UserDetails {

	String username,password;
	Map<Long, Map<Long,File>> map;

	public UserDetails(String username, String password, Map<Long, Map<Long,File>> map) {
		this.username = username;
		this.password = password;
		this.map=map;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Map<Long, Map<Long, File>> getMap() {
		return map;
	}

	public void setMap(Map<Long, Map<Long, File>> map) {
		this.map = map;
	}
	
}
