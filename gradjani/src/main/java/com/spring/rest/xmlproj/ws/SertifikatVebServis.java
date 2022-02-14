package com.spring.rest.xmlproj.ws;

import com.spring.rest.xmlproj.bservisi.impl.SertifikatServis;
import com.spring.rest.xmlproj.obj.Sertifikat;
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
public class SertifikatVebServis {

    private final SertifikatServis sertifikatServis;

    @Autowired
    public SertifikatVebServis(SertifikatServis sertifikatServis) {
        this.sertifikatServis = sertifikatServis;
    }


    @GET
    @Path("/sertifikati/")
    @Produces("application/xml")
    public Response dobaviSveSertifikate() {
        List<Sertifikat> sviSertifikati = this.sertifikatServis.dobaviSve();
        Response r;

        if(sviSertifikati.size() == 0){
            r = Response.noContent().build();
        }else{
            r = Response.ok(new GenericEntity<List<Sertifikat>>(sviSertifikati) {}).type("application/xml").build();
        }

        return r;
    }

    @GET
    @Path("/sertifikati/{id}/")
    @Produces("application/xml")
    public Response dobaviJedanSertifikat(@PathParam("id") String id) {
        Response r;
        Sertifikat sertifikat = this.sertifikatServis.dobaviPoId(id);

        if(sertifikat != null){
            return Response.ok().type("application/xml").entity(sertifikat).build();
        }else{
            return Response.noContent().build();
        }
    }

    @POST
    @Path("/sertifikati/upis/")
    public Response upisSertifikata(Sertifikat sertifikat, @Context UriInfo uriInfo) {
        Response r;
        if (sertifikat != null) {
            this.sertifikatServis.upis(sertifikat);
            r = Response.ok().type("application/xml").entity(sertifikat).build();
        } else {
            r = Response.notModified().build();
        }

        return r;
    }
}
