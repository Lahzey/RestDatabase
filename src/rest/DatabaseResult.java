package rest;

import java.sql.ResultSet;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DatabaseResult {

	private boolean success;
	
	private ResultSet results;
	private ResultSet generatedKeys;
	private Throwable exception;
	
	public DatabaseResult(){
		
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public ResultSet getResults() {
		return results;
	}

	public void setResults(ResultSet results) {
		this.results = results;
	}

	public ResultSet getGeneratedKeys() {
		return generatedKeys;
	}

	public void setGeneratedKeys(ResultSet generatedKeys) {
		this.generatedKeys = generatedKeys;
	}

	public Throwable getException() {
		return exception;
	}

	public void setException(Throwable exception) {
		this.exception = exception;
	}
	
	
}
