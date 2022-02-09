package com.spring.rest.xmlproj.ws;

import com.spring.rest.jaxb.Customer;
import com.spring.rest.xmlproj.obj.Izvestaj;
import com.spring.rest.xmlproj.obj.Saglasnost;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

public interface VebServis {
    @POST
    @Path("/izvestaji/upis/")
    public Response upisIzvestaja(Izvestaj izvestaj, @Context UriInfo uriInfo);

    @GET
    @Path("/izvestaji/{id}/")
    @Produces("application/xml")
    public Izvestaj dobaviJedanIzvestaj(@PathParam("id") String id);

    @GET
    @Path("/izvestaji/")
    @Produces("application/xml")
    public List<Izvestaj> dobaviSveIzvestaje();

    @POST
    @Path("/saglasnosti/upis/")
    public Response upisSaglasnosti(Saglasnost saglasnost, @Context UriInfo uriInfo);

    @GET
    @Path("/saglasnosti/{id}/")
    @Produces("application/xml")
    public Saglasnost dobaviJednuSaglasnost(@PathParam("id") String id);
}
