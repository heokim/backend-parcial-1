package pol.edu.py.primerparcialbackend.rest;

import java.util.List;
import javax.ws.rs.DefaultValue;
import java.util.Objects;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import pol.edu.py.primerparcialbackend.ejb.ReglasDAO;
import pol.edu.py.primerparcialbackend.model.UsoDePuntos;
import pol.edu.py.primerparcialbackend.ejb.UsoDePuntosDAO;


@Path("consultas")
@Produces("application/json")
@Consumes("application/json")
public class ConsultasRest {

    @Inject
    UsoDePuntosDAO usoDePuntosDAO;
    
    @GET
    @Path("/puntos")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUsoDePuntos(
	@QueryParam("cliente_id") int cliente_id,
	@QueryParam("concepto_id") int concepto_id,
        @QueryParam("fecha_uso") String fecha_uso) {
        
	return Response.ok(usoDePuntosDAO.usoDePuntosQuery(cliente_id,concepto_id,fecha_uso)).status(200).build();
    }
    
}