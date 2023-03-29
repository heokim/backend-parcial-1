package pol.edu.py.primerparcialbackend.rest;

import java.util.List;
import javax.ws.rs.DefaultValue;
import java.util.Objects;
import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import pol.edu.py.primerparcialbackend.ejb.ReglasDAO;
import pol.edu.py.primerparcialbackend.model.UsoDePuntos;
import pol.edu.py.primerparcialbackend.ejb.UsoDePuntosDAO;
import pol.edu.py.primerparcialbackend.ejb.BolsasDAO;
import pol.edu.py.primerparcialbackend.ejb.ClientesDAO;
import pol.edu.py.primerparcialbackend.utils.DateUtils;


@RequestScoped
@Path("consultas")
public class ConsultasRest {

    @Inject
    UsoDePuntosDAO usoDePuntosDAO;
    
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

    @GET
    @Path("/puntos")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUsoDePuntos(
	@QueryParam("cliente_id") int cliente_id,
	@QueryParam("concepto_id") int concepto_id,
        @QueryParam("fecha_uso") String fecha_uso) {
        
	return Response.ok(usoDePuntosDAO.usoDePuntosQuery(cliente_id,concepto_id,fecha_uso)).status(200).build();

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
