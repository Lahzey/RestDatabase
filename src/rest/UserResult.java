package rest;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserResult{
	
	private boolean success;
	
	private final List<Issue> issues = new ArrayList<>();

	
	public UserResult(){
		
	}
	
	public boolean isSuccess() {
		return success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<Issue> getIssues() {
		return issues;
	}


	public static enum Issue{
		EMAIL_INVALID, EMAIL_TAKEN, USERNAME_TAKEN;
	}
	
}
