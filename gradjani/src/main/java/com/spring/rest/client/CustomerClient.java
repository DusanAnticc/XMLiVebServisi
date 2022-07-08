package com.spring.rest.client;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.io.IOUtils;

/*
 * Ako hocete da implementirate testove u okviru vase aplikacije, necete raditi ovako!
 * Pogledajte kako bi se koristili Maven i JUnit4 sa springom
 * Primer:
 * @RunWith(SpringRunner.class)
 * @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
 * public class ApplicationTests {
 * 	@Autowired
 *  private TestRestTemplate restTemplate; //Olaksava komunikaciju
 *  
 *  @Test
 *  * vase metode za testiranje * 
 * }
 * 
 * */


/**
 * Klijentska aplikacija koja demonstrira upotrebu REST web servisa.
 */
public final class CustomerClient {

	public static String BASE_URL = "http://localhost:8080/xmlproj";

	public static String URL_ENCODING = "UTF-8";
	

    public static void main(String args[]) throws Exception {

        System.out.println("\n");
        System.out.println("Sent HTTP POST request to add interesovanje");
        String inputFile = ClassLoader.getSystemResource("podaci/interesovanje1.xml").getFile();
        File input = new File(inputFile);
        PostMethod post = new PostMethod(BASE_URL + "/gradjani/interesovanja/upis/");
        post.addRequestHeader("Accept" , "text/xml");
        RequestEntity entity = new FileRequestEntity(input, "text/xml");
        post.setRequestEntity(entity);
        HttpClient httpclient = new HttpClient();

        try {
            int result = httpclient.executeMethod(post);
            System.out.println("Response status code: " + result);
            System.out.println("Response body: ");
            System.out.println(post.getResponseBodyAsString());
        } finally {
            /*
             * Oslobodi konekciju...
             */
            post.releaseConnection();
        }

        System.out.println("Sent HTTP GET request to query interesovanje info");
        GetMethod get = new GetMethod(BASE_URL+"/gradjani/interesovanja/5AA6VDA8");

        get.addRequestHeader("Content-type" , "text/xml");

        try {
            int result = httpclient.executeMethod(get);
            System.out.println("Response status code: " + result);
            System.out.println("Response body: ");
            System.out.println(get.getResponseBodyAsString());
        } finally {
            /*
             * Oslobodi konekciju...
             */
            get.releaseConnection();
        }

        System.out.println("\n");

        System.out.println("\n");
        System.out.println("Sent HTTP POST request to add saglasnost");
        inputFile = ClassLoader.getSystemResource("podaci/saglasnost2.xml").getFile();
        input = new File(inputFile);
        post = new PostMethod(BASE_URL + "/gradjani/saglasnosti/upis/");
        post.addRequestHeader("Accept" , "text/xml");
        entity = new FileRequestEntity(input, "text/xml");
        post.setRequestEntity(entity);

        try {
            int result = httpclient.executeMethod(post);
            System.out.println("Response status code: " + result);
            System.out.println("Response body: ");
            System.out.println(post.getResponseBodyAsString());
        } finally {
            /*
             * Oslobodi konekciju...
             */
            post.releaseConnection();
        }


        System.out.println("Sent HTTP GET request to query every saglasnost info");
        get = new GetMethod(BASE_URL+"/gradjani/saglasnosti/");

        get.addRequestHeader("Content-type" , "text/xml");

        try {
            int result = httpclient.executeMethod(get);
            System.out.println("Response status code: " + result);
            System.out.println("Response body: ");
            System.out.println(get.getResponseBodyAsString());
        } finally {
            /*
             * Oslobodi konekciju...
             */
            get.releaseConnection();
        }

        System.out.println("\n");



        System.out.println("\n");
        System.out.println("Sent HTTP POST request to add zahtev");
        inputFile = ClassLoader.getSystemResource("podaci/zahtev1.xml").getFile();
        input = new File(inputFile);
        post = new PostMethod(BASE_URL + "/gradjani/zahtevi/upis/");
        post.addRequestHeader("Accept" , "text/xml");
        entity = new FileRequestEntity(input, "text/xml");
        post.setRequestEntity(entity);

        try {
            int result = httpclient.executeMethod(post);
            System.out.println("Response status code: " + result);
            System.out.println("Response body: ");
            System.out.println(post.getResponseBodyAsString());
        } finally {
            /*
             * Oslobodi konekciju...
             */
            post.releaseConnection();
        }

        System.out.println("Sent HTTP GET request to query zahtev info");
        get = new GetMethod(BASE_URL+"/gradjani/zahtevi/");

        get.addRequestHeader("Content-type" , "text/xml");

        try {
            int result = httpclient.executeMethod(get);
            System.out.println("Response status code: " + result);
            System.out.println("Response body: ");
            System.out.println(get.getResponseBodyAsString());
        } finally {
            /*
             * Oslobodi konekciju...
             */
            get.releaseConnection();
        }

        System.out.println("\n");

        System.exit(0);
    }

    /*
     * Convenience metoda za konverziju iz InputStream-a u String
     */ 
    public static String getStringFromInputStream(InputStream in) throws Exception {
    	return new String(IOUtils.toByteArray(in), URL_ENCODING);
    }

}
