
package pol.edu.py.primerparcialbackend.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.DELETE;
import static javax.ws.rs.HttpMethod.POST;
import static javax.ws.rs.HttpMethod.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import pol.edu.py.primerparcialbackend.ejb.VencimientosDAO;
import pol.edu.py.primerparcialbackend.model.Vencimientos;

@RequestScoped
@Path("/vencimientos")
public class VencimientosRest {
    
    @Inject
    VencimientosDAO vencimientosDAO;
    
    @GET
    @Path("/")
    @Produces({MediaType.APPLICATION_JSON})
    public Response lista() {
        return Response.ok(vencimientosDAO.findAll()).build();
    }
    
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getId(@PathParam("id") int id) {
        return Response.ok(vencimientosDAO.find(id)).build();
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response crear(Vencimientos vencimiento) {
        System.out.println("/api/vencimientos  POST");
        System.out.println(vencimiento);
        vencimientosDAO.create(vencimiento);
        return Response.ok(vencimiento).build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editar(Vencimientos vencimiento) {
        vencimientosDAO.edit(vencimiento);
        return Response.ok(vencimiento).build();
    }
    
    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") int id) {
        vencimientosDAO.remove(new Vencimientos(id));
        return Response.ok().build();
    }
     
    
}
