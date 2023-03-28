package pol.edu.py.primerparcialbackend.rest;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import pol.edu.py.primerparcialbackend.ejb.BolsasDAO;
import pol.edu.py.primerparcialbackend.ejb.ClientesDAO;
import pol.edu.py.primerparcialbackend.ejb.ConceptosDAO;
import pol.edu.py.primerparcialbackend.ejb.ReglasDAO;
import pol.edu.py.primerparcialbackend.ejb.UsoDePuntosDAO;
import pol.edu.py.primerparcialbackend.ejb.UsoDePuntosDetallesDAO;
import pol.edu.py.primerparcialbackend.model.Bolsas;
import pol.edu.py.primerparcialbackend.model.Clientes;
import pol.edu.py.primerparcialbackend.model.Conceptos;
import pol.edu.py.primerparcialbackend.model.Reglas;
import pol.edu.py.primerparcialbackend.model.UsoDePuntos;
import pol.edu.py.primerparcialbackend.model.UsoDePuntosDetalles;

@RequestScoped
@Path("servicios")
public class ServiciosRest {
    
    private static final java.util.logging.Logger LOG = java.util.logging.Logger.getLogger(ServiciosRest.class.getName());
    
    @Inject
    ClientesDAO clientesDAO;
    @Inject
    ConceptosDAO conceptosDAO;
    @Inject
    BolsasDAO bolsasDAO;
    @Inject
    UsoDePuntosDAO usoDePuntosDAO;
    @Inject
    UsoDePuntosDetallesDAO usoDePuntosDetallesDAO;
    @Inject
    ReglasDAO reglasDAO;
    
    @POST
    @Path("utilizar_puntos")
    @Produces({MediaType.APPLICATION_JSON})
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Response utilizarPuntos(
            @QueryParam("cliente_id") int clienteId,
            @QueryParam("concepto_id") int conceptoId
    ) {
        LOG.info("/api/servicios/utilizar_puntos  POST");

        //variables
        int totalPuntosDisponibles = 0;
        int puntosNecesarios = 0;

        //Recuperar cliente
        Clientes cliente = clientesDAO.find(clienteId);
        if (cliente == null) {
            LOG.info("cliente_id invalido");
            return Response.ok("No existe cliente con id : " + clienteId).build();
        }

        //Recuperar concepto
        Conceptos concepto = conceptosDAO.find(conceptoId);
        if (concepto == null) {
            LOG.info("concepto_id invalido");
            return Response.ok("No existe concepto con id : " + conceptoId).build();
        }
        
        puntosNecesarios = concepto.getPuntosRequeridos();

        // verificar si el cliente tiene los puntos necesarios
        // obtener lista de bolsas disponibles para el cliente
        Map bolsasParametros = new HashMap<String, Object>();
        bolsasParametros.put("clienteId", clienteId);
        bolsasParametros.put("estado", true);
        List<Bolsas> listaBolsas = bolsasDAO.findByParametrosMap(bolsasParametros);/*OBS FALTA ORDENAR LA LISTA*/
        if (listaBolsas == null) {// si el cliente no tiene untos
            LOG.info("El cliente no tiene puntos");
            return Response.ok("El cliente no tiene puntos").build();
        } else {
            // obtener total de puntos disponibles
            totalPuntosDisponibles = listaBolsas.stream().mapToInt(i -> i.getSaldoDePuntos()).sum();
            LOG.info("Total de puntos del cliente : " + totalPuntosDisponibles);

            // verificar si tiene puntos para el concepto
            if (puntosNecesarios > totalPuntosDisponibles) {
                LOG.info("El cliente no tiene los puntos necesarios para " + concepto.getDescripcion());
                return Response.ok("El cliente no tiene los puntos necesarios para " + concepto.getDescripcion()).build();
            }

            // ordenando lista por fecha de vencimiento
            Collections.sort(listaBolsas, (Bolsas b1, Bolsas b2) -> b1.getFechaDeCaducidadDePuntaje().compareTo(b2.getFechaDeCaducidadDePuntaje()));
            
        }
        
        LOG.info("Generando UsoDePuntos");
        // Generar cabecera
        UsoDePuntos cabecera = new UsoDePuntos(new Date(), puntosNecesarios, cliente.getClienteId(), concepto.getConceptoId());
        usoDePuntosDAO.create(cabecera);
        
        LOG.info("Generando UsoDePuntosDetalles");
        //generar detalles
        //se va extraer los puntos de cada bolsa hasta completar los puntos necesarios
        for (Bolsas b : listaBolsas) {
            if (puntosNecesarios > 0) {
                int puntosUtilizados;
                if (b.getSaldoDePuntos() > puntosNecesarios) {
                    puntosUtilizados = puntosNecesarios;
                    b.setPuntajeUtilizado(b.getPuntajeUtilizado() + puntosNecesarios);
                    b.setSaldoDePuntos(b.getSaldoDePuntos() - puntosNecesarios);
                    puntosNecesarios = 0;
                } else {
                    puntosUtilizados = b.getSaldoDePuntos();
                    puntosNecesarios = puntosNecesarios - b.getSaldoDePuntos();
                    b.setPuntajeUtilizado(b.getPuntajeUtilizado() + b.getSaldoDePuntos());
                    b.setSaldoDePuntos(0);
                    b.setEstado(false);
                }
                LOG.info("puntos utilizados de bolsaId " + b.getBolsaId() + " : " + puntosUtilizados);
                // generar detalles
                UsoDePuntosDetalles detalle = new UsoDePuntosDetalles(puntosUtilizados, b.getBolsaId(), cabecera.getCabeceraId());
                usoDePuntosDetallesDAO.create(detalle);
                // actualizar bolsas
                bolsasDAO.edit(b);
            } else {
                break;
            }
        }
        
        LOG.info("Puntos usados correctamente");

        // Actualizar de la base de datos
        cabecera = usoDePuntosDAO.find(cabecera.getCabeceraId());
        return Response.ok(cabecera).build();
    }
    
    @GET
    @Path("consulta_puntos")
    public Response consultasPuntosPorMonto(@QueryParam("monto") int monto) {

        // traer todas las reglas
        List<Reglas> reglas = reglasDAO.findAll();
        
        if (reglas == null) {
            // si no tiene reglas parar
            return Response.ok("No exiten Reglas para realizar esta operacion").build();
        } else {
            // iterar las reglas
            for (Reglas regla : reglas) {
                // verificar que cumpla la regla
                if (reglasDAO.cumpleLaRegla(monto, regla)) {
                    // hacer calculo basado en la regla
                    LOG.info("--------- reglaid: " + regla.getReglaId());
                    LOG.info("--------- x punto: " + regla.getMontoEquivalenciaPorPunto());
                    LOG.info("--------- monto: " + monto);
                    int puntos = monto / regla.getMontoEquivalenciaPorPunto();
                    return Response.ok(puntos).build();
                }
            }
            return Response.ok("No existe regla que contenga al Monto especificado").build();
        }
    }
    
}
