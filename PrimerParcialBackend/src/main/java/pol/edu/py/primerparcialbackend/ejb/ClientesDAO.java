package pol.edu.py.primerparcialbackend.ejb;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pol.edu.py.primerparcialbackend.model.Clientes;

@Stateless
public class ClientesDAO extends AbstractDAO<Clientes> {

    private static final java.util.logging.Logger LOG = java.util.logging.Logger.getLogger(ClientesDAO.class.getName());

    @PersistenceContext(unitName = "pol.edu.py_PrimerParcialBackend_war_1.0PU")
    private EntityManager em;

    public ClientesDAO() {
        super(Clientes.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
     public List<Clientes> findByDiasRestantesVencimiento(int dias) {
        // calcular max fecha de vencimiento (actual + dias)
        Date fechaActual = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaActual);
        calendar.add(Calendar.DAY_OF_YEAR, dias);
        
        
        return em.createQuery(
                "SELECT c FROM Clientes c, Bolsas b "
                        + "WHERE b.clienteId = c.clienteId "
                        + "AND b.fechaDeCaducidadDePuntaje BETWEEN :fecha_actual AND :fecha_vencimiento "
                        + "AND b.estado = true")
                .setParameter("fecha_actual", fechaActual)
                .setParameter("fecha_vencimiento", calendar.getTime())
                .getResultList();
    }

}
