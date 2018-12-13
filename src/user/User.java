package user;

import java.util.regex.Pattern;

import rest.UserResult;
import rest.UserResult.Issue;

public class User {
	
	private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	private final String email;
	private final String username;
	private final String password;
	
	public User(String email, String username, String password){
		this.email = email;
		this.username = username;
		this.password = password;
	}
	
	public boolean exists(){
		return false;
	}
	
	public boolean emailTaken(){
		return false;
	}
	
	public boolean usernameTaken(){
		return false;
	}
	
	public boolean validateEmail(){
		return EMAIL_PATTERN.matcher(email).matches();
	}
	
	public UserResult register(){
		UserResult result = new UserResult();
		if(!validateEmail()) result.getIssues().add(Issue.EMAIL_INVALID);
		if(emailTaken()) result.getIssues().add(Issue.EMAIL_TAKEN);
		if(usernameTaken()) result.getIssues().add(Issue.USERNAME_TAKEN);
		if(result.getIssues().isEmpty()){
			result.setSuccess(true);
		}
		return result;
	}
}
