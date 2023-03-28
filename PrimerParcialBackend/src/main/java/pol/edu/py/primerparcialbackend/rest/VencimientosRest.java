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
import pol.edu.py.primerparcialbackend.ejb.VencimientosDAO;
import pol.edu.py.primerparcialbackend.model.Vencimientos;

@RequestScoped
@Path("vencimientos")
public class VencimientosRest {

    @Inject
    VencimientosDAO vencimientosDAO;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response find() {
        return Response.ok(vencimientosDAO.findAll()).build();
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findById(@PathParam("id") int id) {
        return Response.ok(vencimientosDAO.find(id)).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response crear(Vencimientos vencimiento) {
        vencimientosDAO.create(vencimiento);
        return Response.ok(vencimiento).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response modificar(Vencimientos vencimiento) {
        System.out.println("/api/vencimientos  POST");
        System.out.println("Body:");
        System.out.println(vencimiento.toJson());

        Vencimientos db = vencimientosDAO.find(vencimiento.getVencimientoId());
        System.out.println("Fetch in DB");
        System.out.println("db: " + db.toJson());

        if (db != null && Objects.equals(db.getVencimientoId(), vencimiento.getVencimientoId())) {
            if (vencimiento.getFechaInicioValidez() != null) {
                db.setFechaInicioValidez(vencimiento.getFechaInicioValidez());
            }
            if (vencimiento.getFechaFinValidez() != null) {
                db.setFechaFinValidez(vencimiento.getFechaFinValidez());
            }
            if (vencimiento.getDiasRestantes() != null) {
                db.setDiasRestantes(vencimiento.getDiasRestantes());
            }
            System.out.println("db after updated fields: " + db.toJson());
            vencimientosDAO.edit(db);
            return Response.ok(db).build();
        } else {
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response elimiar(@PathParam("id") int id) {
        vencimientosDAO.remove(new Vencimientos(id));
        return Response.ok().build();
    }

}
