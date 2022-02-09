package com.spring.rest.ws;

import javax.ws.rs.Path;

import org.springframework.stereotype.Service;

import com.spring.rest.inter.HelloService;

/*
 * Primer implementacije sa nasledjivanje anotacija iz interface-a (annotation inheritance)
 * Sama specifikacija (section 3.6 of JSR-339) predlaze da stavite sve anotacija i u implementacionoj klasi
 * Pogledati kako je odradjeno u CustomerServiceImpl
 * */

@Service
@Path("/greeting") 
public class HelloServiceImpl implements HelloService {
	
	
	public String message() {
		System.out.println("Invoking HTTP GET method");
		return "Hi REST!";
	}

	
	public String lowerCase(final String message) {
		System.out.println("Invoking HTTP POST method, message: " + message);
		return message.toLowerCase();
	}
}
