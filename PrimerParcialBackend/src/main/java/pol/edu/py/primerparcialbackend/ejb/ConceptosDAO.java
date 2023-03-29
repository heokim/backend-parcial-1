package pol.edu.py.primerparcialbackend.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pol.edu.py.primerparcialbackend.model.Conceptos;

@Stateless
public class ConceptosDAO extends AbstractDAO<Conceptos> {

    private static final java.util.logging.Logger LOG = java.util.logging.Logger.getLogger(ConceptosDAO.class.getName());

    @PersistenceContext(unitName = "pol.edu.py_PrimerParcialBackend_war_1.0PU")
    private EntityManager em;

    public ConceptosDAO() {
        super(Conceptos.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
