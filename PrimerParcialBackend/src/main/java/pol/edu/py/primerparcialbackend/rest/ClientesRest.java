package pol.edu.py.primerparcialbackend.rest;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import pol.edu.py.primerparcialbackend.ejb.ClientesDAO;
import pol.edu.py.primerparcialbackend.model.Clientes;

@RequestScoped
@Path("clientes")
public class ClientesRest {

    @Inject
    ClientesDAO clientesDAO;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String find(@QueryParam("id") @DefaultValue("-1") Integer id) {
        return id.toString();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response crear(Clientes cliente) {
        System.out.println("/api/clientes  POST");
        System.out.println(cliente);
        clientesDAO.create(cliente);
        return Response.ok(cliente).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response modificar(Clientes cliente) {
        clientesDAO.edit(cliente);
        return Response.ok(cliente).build();
    }

    @DELETE
    @Path("/{id}")
    public Response elimiar(@PathParam("id") int id) {
        clientesDAO.remove(new Clientes(id));
        return Response.ok().build();
    }

}
