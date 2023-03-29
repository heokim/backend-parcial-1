package pol.edu.py.primerparcialbackend.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import pol.edu.py.primerparcialbackend.model.Reglas;
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
           
    public List<Integer> usoDePuntosQuery(int cliente_id, int concepto_id, String fecha_uso) {
        //String sql = "SELECT s FROM " + entityClass.getName() + " s ORDER BY s.id DESC";
        String sql = "SELECT puntaje_utilizado FROM uso_de_puntos WHERE cliente_id = " + cliente_id + " and concepto_id = " + concepto_id + " and fecha = " + fecha_uso;
        Query query = getEntityManager().createQuery(sql);
        List<Integer> resultList = query.getResultList();
        return resultList;
    }
}
