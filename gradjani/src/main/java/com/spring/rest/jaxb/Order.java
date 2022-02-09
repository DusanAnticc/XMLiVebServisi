package com.spring.rest.jaxb;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "order")
public class Order {
    
	private long id;
    
    private String description;
    
    private Map<Long, Product> products = new HashMap<Long, Product>();
    
    // Obavezno imati prazan konstruktor
    public Order() {
        init();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String d) {
        this.description = d;
    }

    @GET
    @Path("/")
    @Produces("application/xml")
    public Order getThisOrder() {
    	System.out.println("Invoking getThisOrder");
        return this;
    }

    @GET
    @Path("products/{productId}/")
    @Produces("application/xml")
    public Product getProduct(@PathParam("productId")int productId) {
        System.out.println("Invoking getProduct, with a Product id: " + productId);
        Product p = products.get(new Long(productId));
        return p;
    }

    @GET
    @Path("products/")
    @Produces("application/xml")
    public Collection<Product> getProducts() {
    	System.out.println("Invoking getProducts");
        Collection<Product> c = products.values();
        return c; 
    }
    
    final void init() {
        Product p1 = new Product();
        p1.setId(234);
        p1.setDescription("HTC One");
        products.put(p1.getId(), p1);

        Product p2 = new Product();
        p2.setId(345);
        p2.setDescription("Ultra 32GB microSDHC Class 10 Memory Card");
        products.put(p2.getId(), p2);
    }
}
