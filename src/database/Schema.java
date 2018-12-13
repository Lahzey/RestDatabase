package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import rest.DatabaseResult;

public class Schema {
	
	private final Map<Long, Connection> connections = new HashMap<>();
	private final Random random = new Random();
	
	private final String username;
	private final String password;
	private final String databaseName;

	public Schema(String username, String password, String databaseName){
		this.username = username;
		this.password = password;
		this.databaseName = databaseName;
	}
	
	public long createConnection() throws SQLException{
		Connection connection = DriverManager.getConnection("", username, password);
		connection.setAutoCommit(false);
		long key = generateConnectionKey();
		connections.put(key, connection);
		return key;
	}
	
	public DatabaseResult execute(String sql, String[] params, long connectionId) throws SQLException, NullPointerException{
		DatabaseResult databaseResult = new DatabaseResult();
		try{
			Connection conn = connections.get(connectionId);
			if(conn == null || conn.isClosed()){
				conn = DriverManager.getConnection("", username, password);
				connections.put(connectionId, conn);
			}
			PreparedStatement stmt = connections.get(connectionId).prepareStatement(sql);
			for(int i = 0; i < params.length; i++){
				stmt.setObject(i, params[i]);
			}
			stmt.execute();
			databaseResult.setResults(stmt.getResultSet());
			databaseResult.setGeneratedKeys(stmt.getGeneratedKeys());
			databaseResult.setSuccess(true);
		}catch(SQLException e){
			databaseResult.setException(e);
		}
		return databaseResult;
	}
	
	public DatabaseResult commit(long connectionId){
		DatabaseResult databaseResult = new DatabaseResult();
		try{
			Connection conn = connections.get(connectionId);
			if(conn == null){
				throw new IllegalArgumentException("Connection with id " + connectionId + " does not exist.");
			}else if(conn.isClosed()){
				throw new IllegalArgumentException("Connection with id " + connectionId + " is closed.");
			}else{
				conn.commit();
				databaseResult.setSuccess(true);
			}
		}catch(Exception e){
			databaseResult.setException(e);
		}
		return databaseResult;
	}
	
	public DatabaseResult rollback(long connectionId){
		DatabaseResult databaseResult = new DatabaseResult();
		try{
			Connection conn = connections.get(connectionId);
			if(conn == null){
				throw new IllegalArgumentException("Connection with id " + connectionId + " does not exist.");
			}else if(conn.isClosed()){
				throw new IllegalArgumentException("Connection with id " + connectionId + " is closed.");
			}else{
				conn.rollback();
				databaseResult.setSuccess(true);
			}
		}catch(Exception e){
			databaseResult.setException(e);
		}
		return databaseResult;
	}
	
	public DatabaseResult close(long connectionId){
		DatabaseResult databaseResult = new DatabaseResult();
		try{
			Connection conn = connections.get(connectionId);
			if(conn == null){
				throw new IllegalArgumentException("Connection with id " + connectionId + " does not exist.");
			}else if(conn.isClosed()){
				throw new IllegalArgumentException("Connection with id " + connectionId + " is alread closed.");
			}else{
				conn.rollback();
				conn.close();
				databaseResult.setSuccess(true);
			}
		}catch(Exception e){
			databaseResult.setException(e);
		}
		return databaseResult;
	}
	
	private long generateConnectionKey(){
		long key = random.nextLong();
		while(connections.containsKey(key)){
			key = random.nextLong();
		}
		return key;
	}
}
