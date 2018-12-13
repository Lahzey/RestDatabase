package rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("database")
public class DatabaseResource {

	
	
	@POST
	@Path("create")
	@Produces(MediaType.APPLICATION_JSON)
	public String createDatabase(@QueryParam("username") String username, @QueryParam("password") String password, @QueryParam("dbName") String dbName){
		return ""; //success?
	}
	
	@POST
	@Path("{database}/drop")
	@Produces(MediaType.APPLICATION_JSON)
	public String dropDatabase(@QueryParam("username") String username, @QueryParam("password") String password, @QueryParam("dbName") String dbName){
		return ""; //success?
	}
	
	@POST
	@Path("{database}/createConnection")
	@Produces(MediaType.APPLICATION_JSON)
	public String createConnection(@QueryParam("username") String username, @QueryParam("password") String password){
		return ""; //result
	}
	
	@POST
	@Path("{database}/execute")
	@Produces(MediaType.APPLICATION_JSON)
	public String execute(@QueryParam("username") String username, @QueryParam("password") String password, @QueryParam("connectionId") Long connectionId, @QueryParam("sql") String sql, @QueryParam("params") Object[] params){
		return "";
	}
	
	@POST
	@Path("{database}/commit")
	@Produces(MediaType.APPLICATION_JSON)
	public String commit(@QueryParam("connectionId") long connectionId){
		return "";
	}
	
	@POST
	@Path("{database}/rollback")
	@Produces(MediaType.APPLICATION_JSON)
	public String rollback(@QueryParam("connectionId") long connectionId){
		return "";
	}
	
	
}
