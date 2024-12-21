package helpers;

import java.util.ArrayList;

public class User {

	private String username;
	private String password;
	private ArrayList<String> followingUserNames;
	private ArrayList<String> followerUserNames;

	public User(String username, String password) {
		this.username = username;
		this.password = password;
		followingUserNames = new ArrayList<String>();
		followerUserNames = new ArrayList<String>();
	}

	public String getUserName() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public ArrayList<String> getFollowingUsers() {
		return followingUserNames;
	}

	public ArrayList<String> getFollowerUsers() {
		return followerUserNames;
	}

	public void addFollowingUser(String username) {
		followingUserNames.add(username);
	}

	public void addFollowerUser(String username) {
		followerUserNames.add(username);
	}

}
