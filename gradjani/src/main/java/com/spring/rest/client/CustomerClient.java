package com.spring.rest.client;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.FileRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
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


		/**
		 * HTTP GET http://localhost:8080/spring/webshop/customers/123
		 *
		 * Vraća Customer-a sa id-jem 123.
		 *
		 * HTTP zahtev se mapira na poziv getCustomer() metode.
		 *
		 */
        System.out.println("Sent HTTP GET request to query customer info");
        URL url = new URL(BASE_URL + "/webshop/customers/123");
        InputStream in = url.openStream();
        System.out.println(getStringFromInputStream(in));

        /**
		 * HTTP GET http://localhost:8080/spring/webshop/orders/543/products/234
		 *
		 * Vraća Product sa id-jem 234 unutar Order-a sa id-jem 543.
		 *
		 * HTTP zahtev se mapira na poziv getProduct() metode (iz JAXB bean-a Order).
		 *
		 */
        System.out.println("\n");
        System.out.println("Sent HTTP GET request to query sub resource product info");
        url = new URL(BASE_URL + "/webshop/orders/543/products/234");
        in = url.openStream();
        System.out.println(getStringFromInputStream(in));

		/**
		 * HTTP PUT http://localhost:8080/spring/webshop/customers
		 *
		 * Ažurira kolekciju postojećih Customer-a na osnovu XML-a prosleđenog u telu zahteva.
		 *
		 * Vraća ažuriranog Customer-a.
		 *
		 * HTTP zahtev se mapira na poziv updateCustomer() metode.
		 *
		 */
        System.out.println("\n");
        System.out.println("Sent HTTP PUT request to update customer info");
        String inputFile = ClassLoader.getSystemResource("podaci/update_customer.xml").getFile();
        File input = new File(inputFile);
        PutMethod put = new PutMethod(BASE_URL + "/webshop/customers");
        RequestEntity entity = new FileRequestEntity(input, "text/xml; charset=" + URL_ENCODING);
        put.setRequestEntity(entity);
        HttpClient httpclient = new HttpClient();

        try {
            int result = httpclient.executeMethod(put);
            System.out.println("Response status code: " + result);
            System.out.println("Response body: ");
            System.out.println(put.getResponseBodyAsString());
        } finally {
        	/*
        	 * Oslobodi konekciju...
        	 */
            put.releaseConnection();
        }

        /**
		 * HTTP POST http://localhost:8080/spring/webshop/customers
		 *
		 * Dodaje novog Customer-a na osnovu XML-a prosleđenog u telu zahteva.
		 *
		 * Vraća XML reprezent novododatog Customer-a.
		 *
		 * HTTP zahtev se mapira na poziv addCustomer() metode.
		 *
		 */

        System.out.println("\n");
        System.out.println("Sent HTTP POST request to add customer");
        inputFile = ClassLoader.getSystemResource("podaci/add_customer.xml").getFile();
        input = new File(inputFile);
        PostMethod post = new PostMethod(BASE_URL + "/webshop/customers");
        post.addRequestHeader("Accept" , "text/xml");
        entity = new FileRequestEntity(input, "text/xml; charset=" + URL_ENCODING);
        post.setRequestEntity(entity);
        httpclient = new HttpClient();

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

        System.out.println("\n");
        System.out.println("Sent HTTP POST request to add izvestaj");
        inputFile = ClassLoader.getSystemResource("podaci/izvestaj5.xml").getFile();
        input = new File(inputFile);
        post = new PostMethod(BASE_URL + "/gradjani/izvestaji/upis/");
        post.addRequestHeader("Accept" , "text/xml");
        entity = new FileRequestEntity(input, "text/xml");
        post.setRequestEntity(entity);
        httpclient = new HttpClient();

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

        System.out.println("Sent HTTP GET request to query every izvestaj info");
        url = new URL(BASE_URL + "/gradjani/izvestaji");
        in = url.openStream();
        System.out.println(getStringFromInputStream(in));

        System.out.println("\n");


        System.out.println("\n");
        System.out.println("Sent HTTP POST request to add saglasnost");
        inputFile = ClassLoader.getSystemResource("podaci/saglasnost1.xml").getFile();
        input = new File(inputFile);
        post = new PostMethod(BASE_URL + "/gradjani/saglasnosti/upis/");
        post.addRequestHeader("Accept" , "text/xml");
        entity = new FileRequestEntity(input, "text/xml");
        post.setRequestEntity(entity);
        httpclient = new HttpClient();

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

        System.out.println("Sent HTTP GET request to query saglasnost info");
        url = new URL(BASE_URL + "/gradjani/saglasnosti/1227AB5916");
        in = url.openStream();
        System.out.println(getStringFromInputStream(in));

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
