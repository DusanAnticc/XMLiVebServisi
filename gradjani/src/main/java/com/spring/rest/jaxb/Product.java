package com.spring.rest.jaxb;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "product")
public class Product {
    
	private long id;
    
	private String description;
	
	//Obavezno imati prazan konstruktor
	public Product() {
		
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
}
