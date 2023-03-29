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
import pol.edu.py.primerparcialbackend.ejb.ConceptosDAO;
import pol.edu.py.primerparcialbackend.model.Conceptos;

@RequestScoped
@Path("conceptos")
public class ConceptosRest {
    
    private static final java.util.logging.Logger LOG = java.util.logging.Logger.getLogger(ConceptosRest.class.getName());

    @Inject
    ConceptosDAO conceptosDAO;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response find() {
        return Response.ok(conceptosDAO.findAll()).build();
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findById(@PathParam("id") int id) {
        return Response.ok(conceptosDAO.find(id)).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response crear(Conceptos concepto) {
        conceptosDAO.create(concepto);
        return Response.ok(concepto).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response modificar(Conceptos concepto) {
        LOG.info("/api/conceptos  PUT");
        LOG.info("Conceptos Body:");
        LOG.info(concepto.toJson());

        Conceptos db = conceptosDAO.find(concepto.getConceptoId());
        LOG.info("Fetch in DB");
        LOG.info("db: " + db.toJson());

        if (db != null && Objects.equals(db.getConceptoId(), concepto.getConceptoId())) {
            if (concepto.getDescripcion() != null) {
                db.setDescripcion(concepto.getDescripcion());
            }
            if (concepto.getPuntosRequeridos() != null) {
                db.setPuntosRequeridos(concepto.getPuntosRequeridos());
            }
            LOG.info("db after updated fields: " + db.toJson());
            conceptosDAO.edit(db);
            return Response.ok(db).build();
        } else {
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") int id) {
        conceptosDAO.remove(new Conceptos(id));
        return Response.ok().build();
    }

}
