package pol.edu.py.primerparcialbackend.rest;

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


@Path("consultas")
@Produces("application/json")
@Consumes("application/json")
public class ConsultasRest {

    @GET
    @Path("/puntos")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUsoDePuntos(
	@QueryParam("cliente_id") int cliente_id,
	@QueryParam("fecha_uso") String fecha_uso,
	@QueryParam("concepto_id") int concepto_id) {

        //SELECT puntaje_utilizado FROM uso_de_puntos WHERE cliente_id = :cliente_id and concepto_id = :concepto_id and fecha = :fecha;
	return Response.status(200).build();
    }
    
}