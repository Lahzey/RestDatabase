package main;

import java.net.URI;

import javax.swing.JLabel;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import com.creditsuisse.graphics.swing.TestFrame;
import com.sun.net.httpserver.HttpServer;

import rest.CORSFilter;
import rest.UserResource;

public class RestDatabaseApplication {
	

	private final static int PORT = 9998;
	private final static String HOST="http://localhost/";

	
	public static void main(String[] args) {
		System.out.println("starting server...");
		URI baseUri = UriBuilder.fromUri(HOST).port(PORT).build();
		ResourceConfig config = new ResourceConfig(UserResource.class);
		config.register(JacksonFeature.class);
		config.register(new CORSFilter());
		HttpServer server = JdkHttpServerFactory.createHttpServer(baseUri, config);
		System.out.println("server started");
		
		TestFrame frame = new TestFrame(new JLabel(server.getAddress().toString()));
		Runtime.getRuntime().addShutdownHook(new Thread(){
			public void run(){
				System.out.println("stopping server");
				server.stop(0);
				System.out.println("server stopped");
			}
		});
	}
}
