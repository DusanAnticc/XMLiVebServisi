package com.spring.rest.inter;

import javax.ws.rs.GET;
import javax.ws.rs.POST;


public interface HelloService {
	
	@GET
	public String message();

	@POST
	public String lowerCase(final String message);
}
