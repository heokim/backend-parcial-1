package pol.edu.py.primerparcialbackend.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pol.edu.py.primerparcialbackend.model.UsoDePuntosDetalles;

@Stateless
public class UsoDePuntosDetallesDAO extends AbstractDAO<UsoDePuntosDetalles> {

    private static final java.util.logging.Logger LOG = java.util.logging.Logger.getLogger(UsoDePuntosDetallesDAO.class.getName());

    @PersistenceContext(unitName = "pol.edu.py_PrimerParcialBackend_war_1.0PU")
    private EntityManager em;

    public UsoDePuntosDetallesDAO() {
        super(UsoDePuntosDetalles.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
