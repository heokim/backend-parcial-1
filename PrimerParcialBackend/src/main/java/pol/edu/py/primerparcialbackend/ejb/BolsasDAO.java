package pol.edu.py.primerparcialbackend.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pol.edu.py.primerparcialbackend.model.Bolsas;

@Stateless
public class BolsasDAO extends AbstractDAO<Bolsas> {

    private static final java.util.logging.Logger LOG = java.util.logging.Logger.getLogger(BolsasDAO.class.getName());

    @PersistenceContext(unitName = "pol.edu.py_PrimerParcialBackend_war_1.0PU")
    private EntityManager em;

    public BolsasDAO() {
        super(Bolsas.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Bolsas> findByClientIdAndRange(int id, int limiteInferior, int limiteSuperior) {
        return em.createQuery(
                "SELECT b FROM Bolsas b WHERE b.saldoDePuntos >= :li "
                + "and b.saldoDePuntos <= :ls "
                + "and estado = true "
                + "and b.clienteId = :id")
                .setParameter("id", id)
                .setParameter("li", limiteInferior)
                .setParameter("ls", limiteSuperior)
                .getResultList();
    }

}
