package pol.edu.py.primerparcialbackend.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pol.edu.py.primerparcialbackend.model.Reglas;

@Stateless
public class ReglasDAO extends AbstractDAO<Reglas> {

    private static final java.util.logging.Logger LOG = java.util.logging.Logger.getLogger(ReglasDAO.class.getName());

    @PersistenceContext(unitName = "pol.edu.py_PrimerParcialBackend_war_1.0PU")
    private EntityManager em;

    public ReglasDAO() {
        super(Reglas.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<Reglas> todos(){
        return this.em.createQuery("SELECT r FROM Reglas r").getResultList();
    }

}
