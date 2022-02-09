package com.spring.rest.client;


import java.io.InputStream;
import java.net.URL;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.io.IOUtils;

/**
 * Klijentska aplikacija koja demonstrira pozivanje različitih metoda 
 * HelloRESTService servisa.
 */
public final class HelloClient {

	public static String BASE_URL = "http://localhost:8080/spring";
	
	public static String URL_ENCODING = "UTF-8";
	
	public static String CONTENT_TYPE = "text/html";

    public static void main(String args[]) throws Exception {

    	/**
    	 *  HTTP GET http://localhost:8080/spring/greeting 
    	 */
    	
    	System.out.println("Sent HTTP GET request.");
        URL url = new URL(BASE_URL + "/greeting");
        InputStream in = url.openStream();
        System.out.println(getStringFromInputStream(in));
        
        System.out.println();
        
        /** 
         * HTTP POST http://localhost:8080/spring/greeting
         */
        
        System.out.println("Sent HTTP POST request.");
        PostMethod post = new PostMethod(BASE_URL + "/greeting");
        RequestEntity entity = new StringRequestEntity("HI REST!", CONTENT_TYPE, URL_ENCODING);
        post.setRequestEntity(entity);
        HttpClient httpclient = new HttpClient();
        httpclient.executeMethod(post);
        System.out.println(post.getResponseBodyAsString());

        System.exit(0);
    }

    /*
     * Convenience metoda za konverziju iz InputStream-a u String
     */ 
    public static String getStringFromInputStream(InputStream in) throws Exception {
    	return new String(IOUtils.toByteArray(in), URL_ENCODING);
    }

}
