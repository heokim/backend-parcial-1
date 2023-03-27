package pol.edu.py.primerparcialbackend.rest;

import java.util.Objects;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
    public Response find() {
        return Response.ok(clientesDAO.findAll()).build();
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findById(@PathParam("id") int id) {
        return Response.ok(clientesDAO.find(id)).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response crear(Clientes cliente) {
        clientesDAO.create(cliente);
        return Response.ok(cliente).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response modificar(Clientes cliente) {
        System.out.println("/api/clientes  POST");
        System.out.println("Clientes Body:");
        System.out.println(cliente.toJson());

        Clientes db = clientesDAO.find(cliente.getClienteId());
        System.out.println("Fetch in DB");
        System.out.println("db: " + db.toJson());

        if (db != null && Objects.equals(db.getClienteId(), cliente.getClienteId())) {
            if (cliente.getTipoDocumento() != null) {
                db.setTipoDocumento(cliente.getTipoDocumento());
            }
            if (cliente.getNumeroDocumento() != null) {
                db.setNumeroDocumento(cliente.getNumeroDocumento());
            }
            if (cliente.getNombre() != null) {
                db.setNombre(cliente.getNombre());
            }
            if (cliente.getApellido() != null) {
                db.setApellido(cliente.getApellido());
            }
            if (cliente.getFechaNacimiento() != null) {
                db.setFechaNacimiento(cliente.getFechaNacimiento());
            }
            if (cliente.getNacionalidad() != null) {
                db.setNacionalidad(cliente.getNacionalidad());
            }
            if (cliente.getMail() != null) {
                db.setMail(cliente.getMail());
            }
            if (cliente.getTelefono() != null) {
                db.setTelefono(cliente.getTelefono());
            }
            System.out.println("db after updated fields: " + db.toJson());
            clientesDAO.edit(db);
            return Response.ok(db).build();
        } else {
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response elimiar(@PathParam("id") int id) {
        clientesDAO.remove(new Clientes(id));
        return Response.ok().build();
    }

}
