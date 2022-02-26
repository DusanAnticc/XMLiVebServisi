package com.spring.rest.xmlproj.ws;

import com.spring.rest.xmlproj.bservisi.impl.ZahtevServis;
import com.spring.rest.xmlproj.obj.zahtev.Zahtev;
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
public class ZahtevVebServis {

    private final ZahtevServis zahtevServis;

    @Autowired
    public ZahtevVebServis(ZahtevServis zahtevServis) {
        this.zahtevServis = zahtevServis;
    }

    @GET
    @Path("/zahtevi/")
    @Produces("application/xml")
    public Response dobaviSveZahteve() {
        List<Zahtev> sviZahtevi = this.zahtevServis.dobaviSve();
        Response r;

        if(sviZahtevi.size() == 0){
            r = Response.noContent().build();
        }else{
            r = Response.ok(new GenericEntity<List<Zahtev>>(sviZahtevi) {}).type("application/xml").build();
        }

        return r;
    }

    @GET
    @Path("/zahtevi/{id}/")
    @Produces("application/xml")
    public Response dobaviJedanZahtev(@PathParam("id") String id) {
        Response r;
        Zahtev zahtev = this.zahtevServis.dobaviPoId(id);

        if(zahtev != null){
            return Response.ok().type("application/xml").entity(zahtev).build();
        }else{
            return Response.noContent().build();
        }
    }

    @POST
    @Path("/zahtevi/upis/")
    public Response upisZahtev(Zahtev zahtev, @Context UriInfo uriInfo) {
        Response r;
        if (zahtev != null) {
            this.zahtevServis.upis(zahtev);
            r = Response.ok().type("application/xml").entity(zahtev).build();
        } else {
            r = Response.notModified().build();
        }

        return r;
    }
}
