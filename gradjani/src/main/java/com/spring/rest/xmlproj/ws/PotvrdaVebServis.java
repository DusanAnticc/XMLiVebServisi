package com.spring.rest.xmlproj.ws;

import com.spring.rest.xmlproj.bservisi.impl.PotvrdaServis;
import com.spring.rest.xmlproj.obj.potvrda.Potvrda;
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
public class PotvrdaVebServis {

    private final PotvrdaServis potvrdaServis;

    @Autowired
    public PotvrdaVebServis(PotvrdaServis potvrdaServis) {
        this.potvrdaServis = potvrdaServis;
    }

    @GET
    @Path("/potvrde/")
    @Produces("application/xml")
    public Response dobaviSvePotvrde() {
        List<Potvrda> svePotvrde = this.potvrdaServis.dobaviSve();
        Response r;

        if(svePotvrde.size() == 0){
            r = Response.noContent().build();
        }else{
            r = Response.ok(new GenericEntity<List<Potvrda>>(svePotvrde) {}).type("application/xml").build();
        }

        return r;
    }

    @GET
    @Path("/potvrde/{id}/")
    @Produces("application/xml")
    public Response dobaviJednuPotvrdu(@PathParam("id") String id) {
        Response r;
        Potvrda potvrda = this.potvrdaServis.dobaviPoId(id);

        if(potvrda != null){
            return Response.ok().type("application/xml").entity(potvrda).build();
        }else{
            return Response.noContent().build();
        }
    }

    @POST
    @Path("/sertifikati/upis/")
    public Response upisPotvrde(Potvrda potvrda, @Context UriInfo uriInfo) {
        Response r;
        if (potvrda != null) {
            this.potvrdaServis.upis(potvrda);
            r = Response.ok().type("application/xml").entity(potvrda).build();
        } else {
            r = Response.notModified().build();
        }

        return r;
    }
}
