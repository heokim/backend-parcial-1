package pol.edu.py.primerparcialbackend.rest;

import javax.ws.rs.DefaultValue;
import java.util.Objects;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createRegla(Reglas nuevaRegla) {
        return Response.ok(reglasDAO.create(nuevaRegla)).build();
    }
    
    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response removeId(@PathParam("id") int id) {
        reglasDAO.removeById(id);
        return Response.noContent().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response modificar(Reglas regla) {
        System.out.println("Regla Body:");
        System.out.println(regla.toJson());
        
        Reglas db = reglasDAO.find(regla.getReglaId());
        System.out.println("Fetch in DB");
        System.out.println("db: " + db.toJson());

        if (db != null && Objects.equals(db.getReglaId(), regla.getReglaId())) {
            if (regla.getReglaId() != null) {
                db.setReglaId(regla.getReglaId());
            }
            if (regla.getLimiteInferior() != null) {
                db.setLimiteInferior(regla.getLimiteInferior());
            }
            if (regla.getLimiteSuperior() != null) {
                db.setLimiteSuperior(regla.getLimiteSuperior());
            }
            if (regla.getMontoEquivalenciaPorPunto() != null) {
                db.setMontoEquivalenciaPorPunto(regla.getMontoEquivalenciaPorPunto());
            }
            System.out.println("db after updated fields: " + db.toJson());
            reglasDAO.edit(db);
            return Response.ok(db).build();
        } else {
            return Response.serverError().build();
        }
    }
}