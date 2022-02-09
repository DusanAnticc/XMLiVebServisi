package com.spring.rest.xmlproj.ws.impl;

import com.spring.rest.xmlproj.bservisi.impl.IzvestajServis;
import com.spring.rest.xmlproj.bservisi.impl.SaglasnostServis;
import com.spring.rest.xmlproj.obj.Izvestaj;
import com.spring.rest.xmlproj.obj.Saglasnost;
import com.spring.rest.xmlproj.ws.VebServis;
import org.apache.jena.base.Sys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Service
@Path("/gradjani")
public class VebServisImpl implements VebServis {

    private final IzvestajServis izvestajServis;
    private final SaglasnostServis saglasnostServis;

    @Autowired
    public VebServisImpl(IzvestajServis izvestajServis, SaglasnostServis saglasnostServis) {
        this.izvestajServis = izvestajServis;
        this.saglasnostServis = saglasnostServis;
    }

    @Override
    @POST
    @Path("/izvestaji/upis/")
    public Response upisIzvestaja(Izvestaj izvestaj, @Context UriInfo uriInfo) {
        Response r;
        if (izvestaj != null) {
            this.izvestajServis.upisIzvestaja(izvestaj);
            r = Response.ok().type("application/xml").entity(izvestaj).build();
        } else {
            r = Response.notModified().build();
        }

        return r;
    }

    @Override
    @GET
    @Path("/izvestaji/{id}/")
    @Produces("application/xml")
    public Izvestaj dobaviJedanIzvestaj(@PathParam("id") String id) {
        return this.izvestajServis.dobaviIzvestajPoId(id);
    }

    @Override
    @GET
    @Path("/izvestaji/")
    @Produces("application/xml")
    public List<Izvestaj> dobaviSveIzvestaje() {
        return this.izvestajServis.dobaviSveIzvestaje();
    }

    @Override
    @POST
    @Path("/saglasnosti/upis/")
    public Response upisSaglasnosti(Saglasnost saglasnost,@Context UriInfo uriInfo) {
        Response r;
        if (saglasnost != null) {
            this.saglasnostServis.upisSaglasnosti(saglasnost);
            r = Response.ok().type("application/xml").entity(saglasnost).build();
        } else {
            r = Response.notModified().build();
        }

        return r;
    }



    @Override
    @GET
    @Path("/saglasnosti/{id}/")
    @Produces("application/xml")
    public Saglasnost dobaviJednuSaglasnost(@PathParam("id") String id) {
        return this.saglasnostServis.dobaviSaglasnostPoId(id);
    }
}
