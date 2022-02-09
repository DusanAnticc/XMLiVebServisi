package com.spring.rest;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;


/*
 * Glavna klasa Spring Boot aplikacije
 * Da pokrenete primer desni klik i run as java application 
 * Obavezno uraditi Maven Update (desni klik na projekat i maven update)
 * Podesavanja spring aplikacije se vrse u application.properties fajlu u src/main/resources
 * */

@SpringBootApplication
@ImportResource({ "classpath*:podaci/cxf-servlet.xml" })
public class RestApplication {

//	@Autowired
//	private Bus bus;

	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}

	/*Konfiguracija apache CXF JAX-RS servleta
	 *Moguce je konfigurasati endpoints dynamicki vezano za same klase
	 * */
	
	@Bean
	public ServletRegistrationBean<CXFServlet> cxfServlet() {
		CXFServlet cxfServlet = new CXFServlet();
		ServletRegistrationBean<CXFServlet> servletReg = new ServletRegistrationBean<CXFServlet>(cxfServlet, "/*");
		servletReg.setLoadOnStartup(1);
		return servletReg;
	}

}
