package pol.edu.py.primerparcialbackend.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

    /**
     * Verifica si una Regla de cambio a puntos es aceptado por el Monto
     * especificado
     *
     * @param monto Cantidad del monto que se quiere saber si cumple la regla
     * @param regla La regla de cambio
     * @return Monto de puntos a lo que equivale el monto segun la regla
     */
    public boolean cumpleLaRegla(int monto, Reglas regla) {
        Integer li = regla.getLimiteInferior();
        Integer ls = regla.getLimiteSuperior();

        if (li == null && ls == null) {
            return (monto >= 0);
        } else if (li == null && ls != null) {
            return (monto <= ls);
        } else if (li != null && ls == null) {
            return (monto >= li);
        } else {
            return (monto >= li && monto <= ls);
        }
    }
}
