package pol.edu.py.primerparcialbackend.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import pol.edu.py.primerparcialbackend.ejb.BolsasDAO;
import pol.edu.py.primerparcialbackend.ejb.ClientesDAO;

@RequestScoped
@Path("consultas")
public class ConsultasRest {

    @Inject
    ClientesDAO clientesDAO;
    
    @Inject
    BolsasDAO bolsasDAO;

    @GET
    @Path("bolsa")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findById(@QueryParam("cliente_id") int id,
            @QueryParam("limite_inferior") int limiteInferior,
            @QueryParam("limite_superior") int limiteSuperior) {
        return Response.ok(bolsasDAO.findByClientIdAndRange(id, limiteInferior, limiteSuperior)).build();
    }
    
    @GET
    @Path("vencimientos")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findClientesPorVencer(@QueryParam("dias") int dias) {
        return Response.ok(clientesDAO.findByDiasRestantesVencimiento(dias)).build();
    }

}
