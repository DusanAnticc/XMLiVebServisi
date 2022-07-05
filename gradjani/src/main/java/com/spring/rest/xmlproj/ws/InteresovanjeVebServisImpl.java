package com.spring.rest.xmlproj.ws;

import com.spring.rest.xmlproj.bservisi.impl.InteresovanjeServis;
import com.spring.rest.xmlproj.obj.interesovanje.Interesovanje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Service
@Path("gradjani/")
public class InteresovanjeVebServisImpl{

    private final InteresovanjeServis interesovanjeServis;

    @Autowired
    public InteresovanjeVebServisImpl(InteresovanjeServis interesovanjeServis) {
        this.interesovanjeServis = interesovanjeServis;
    }

    @GET
    @Path("/interesovanja/")
    @Produces("application/xml")
    public Response dobaviSvaInteresovanja() {
        List<Interesovanje> svaInteresovanja = this.interesovanjeServis.dobaviSve();
        Response r;

        if(svaInteresovanja.size() == 0){
            r = Response.noContent().build();
        }else{
            r = Response.ok(new GenericEntity<List<Interesovanje>>(svaInteresovanja) {}).type("application/xml").build();
        }

        return r;
    }

    @GET
    @Path("/interesovanja/{id}")
    @Produces("application/xml")
    public Response dobaviJednoInteresovanje(@PathParam("id") String id) {
        Response r;
        Interesovanje interesovanje = this.interesovanjeServis.dobaviPoId(id);
        if(interesovanje != null){
            return Response.ok().type("application/xml").entity(interesovanje).build();
        }else{
            return Response.noContent().build();
        }
    }

    @POST
    @Path("/interesovanja/upis/")
    public Response upisInteresovanja(Interesovanje interesovanje, @Context UriInfo uriInfo) {
        Response r;
        if (interesovanje != null) {
            this.interesovanjeServis.upis(interesovanje);
            r = Response.ok().type("application/xml").entity(interesovanje).build();
        } else {
            r = Response.notModified().build();
        }

        return r;
    }
}
