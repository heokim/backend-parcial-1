package pol.edu.py.primerparcialbackend.ejb;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pol.edu.py.primerparcialbackend.model.UsoDePuntos;

@Stateless
public class UsoDePuntosDAO extends AbstractDAO<UsoDePuntos> {

    private static final java.util.logging.Logger LOG = java.util.logging.Logger.getLogger(UsoDePuntosDAO.class.getName());

    @PersistenceContext(unitName = "pol.edu.py_PrimerParcialBackend_war_1.0PU")
    private EntityManager em;

    public UsoDePuntosDAO() {
        super(UsoDePuntos.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<UsoDePuntos> findByClienteConceptoFechaUso(int clienteId, int conceptoId, Date fecha) {
        // extract_date(t.timestampField)
        return em.createQuery("SELECT u FROM UsoDePuntos u "
                + "WHERE u.clienteId = :clienteId AND "
                + "u.conceptoId = :conceptoId AND "
                + "DATE(u.fecha) = :fecha")
                .setParameter("clienteId", clienteId)
                .setParameter("conceptoId", conceptoId)
                .setParameter("fecha", fecha)
                .getResultList();
    }

}
