package com.spring.rest.xmlproj.ws;

import com.spring.rest.xmlproj.bservisi.impl.SaglasnostServis;
import com.spring.rest.xmlproj.obj.saglasnost.Saglasnost;
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
public class SaglasnostVebServisImpl{

    private final SaglasnostServis saglasnostServis;

    @Autowired
    public SaglasnostVebServisImpl(SaglasnostServis saglasnostServis) {
        this.saglasnostServis = saglasnostServis;
    }

    @POST
    @Path("/saglasnosti/upis/")
    public Response upisSaglasnosti(Saglasnost saglasnost,@Context UriInfo uriInfo) {
        Response r;
        if (saglasnost != null) {
            this.saglasnostServis.upis(saglasnost);
            r = Response.ok().type("application/xml").entity(saglasnost).build();
        } else {
            r = Response.notModified().build();
        }

        return r;
    }

    @GET
    @Path("/saglasnosti/{id}/")
    @Produces("application/xml")
    public Response dobaviJednuSaglasnost(@PathParam("id") String id) {
        Response r;
        Saglasnost saglasnost = this.saglasnostServis.dobaviPoId(id);

        if(saglasnost != null){
            return Response.ok().type("application/xml").entity(saglasnost).build();
        }else{
            return Response.noContent().build();
        }
    }

    @GET
    @Path("/saglasnosti/")
    @Produces("application/xml")
    public Response dobaviSveSaglasnosti() {
        List<Saglasnost> sveSaglasnosti = this.saglasnostServis.dobaviSve();
        Response r;

        if(sveSaglasnosti.size() == 0){
            r = Response.noContent().build();
        }else{
            r = Response.ok(new GenericEntity<List<Saglasnost>>(sveSaglasnosti) {}).type("application/xml").build();
        }

        return r;
    }
}
