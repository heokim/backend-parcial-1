package pol.edu.py.primerparcialbackend.rest;

import java.util.Date;
import java.util.HashMap;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import pol.edu.py.primerparcialbackend.ejb.BolsasDAO;
import pol.edu.py.primerparcialbackend.ejb.ClientesDAO;
import pol.edu.py.primerparcialbackend.ejb.UsoDePuntosDAO;
import pol.edu.py.primerparcialbackend.utils.DateUtils;

@RequestScoped
@Path("consultas")
public class ConsultasRest {

    @Inject
    ClientesDAO clientesDAO;

    @Inject
    BolsasDAO bolsasDAO;

    @Inject
    UsoDePuntosDAO usoDePuntosDAO;

    @GET
    @Path("puntos")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUsoDePuntos(
            @QueryParam("cliente_id") int clienteId,
            @QueryParam("concepto_id") int conceptoId,
            @QueryParam("fecha_uso") String fechaUso
    ) {
        Date fecha = DateUtils.stringToDate(fechaUso, "yyyy-MM-dd");
        return Response.ok(usoDePuntosDAO.findByClienteConceptoFechaUso(clienteId, conceptoId, fecha)).build();
    }

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

    @GET
    @Path("clientes")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getClientePorNombreApellidoYCumple(
            @QueryParam("nombre") String nombre,
            @QueryParam("apellido") String apellido,
            @QueryParam("cumpleanhos") String fechaString
    ) {
        System.out.println("/api/consultas/clientes  GET");
        System.out.println("cumpleanhos: " + fechaString);
        Date fecha = DateUtils.stringToDate(fechaString, "yyyy-MM-dd");

        return Response.ok(clientesDAO.getClienteByNombreApellidoAndCumple(nombre, apellido, fecha)).build();
    }

}
