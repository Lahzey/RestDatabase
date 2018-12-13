package rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import user.User;

@Path("user")
public class UserResource {
	
	//Base User
	
	@POST
	@Path("register")
	@Produces(MediaType.APPLICATION_JSON)
	public UserResult register(@QueryParam("mail") String mail, @QueryParam("username") String username, @QueryParam("password") String password){
		System.out.println("request");
		return new User(mail, username, password).register();
	}
	
}