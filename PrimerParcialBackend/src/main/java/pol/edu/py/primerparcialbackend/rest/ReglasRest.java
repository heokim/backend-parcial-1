package pol.edu.py.primerparcialbackend.rest;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import pol.edu.py.primerparcialbackend.model.Reglas;
import pol.edu.py.primerparcialbackend.ejb.ReglasDAO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;


@Path("reglas")
@Produces("application/json")
@Consumes("application/json")
public class ReglasRest {

    @Inject
    ReglasDAO reglasDAO;
    
    @GET
    @Path("saludo")
    public Response hola() {
        return Response.ok("hola").build();
    }
    
    @GET
    @Path("/")
    @Produces({MediaType.APPLICATION_JSON})
    public Response lista() {
        return Response.ok(reglasDAO.findAll()).build();
    }
    
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getId(@PathParam("id") int id) {
        return Response.ok(reglasDAO.find(id)).build();
    }
}