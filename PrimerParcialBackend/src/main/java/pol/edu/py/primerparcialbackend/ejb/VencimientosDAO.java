package pol.edu.py.primerparcialbackend.ejb;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pol.edu.py.primerparcialbackend.model.Vencimientos;

@Stateless
public class VencimientosDAO extends AbstractDAO<Vencimientos> {

    private static final java.util.logging.Logger LOG = java.util.logging.Logger.getLogger(VencimientosDAO.class.getName());

    @PersistenceContext(unitName = "pol.edu.py_PrimerParcialBackend_war_1.0PU")
    private EntityManager em;

    public VencimientosDAO() {
        super(Vencimientos.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * Retorna el primer vencimento que la fecha si pertenece a si rango
     *
     * @param fecha Fecha que ser quiere meter en el rango
     * @return Objeto Vencimento
     */
    public Vencimientos traerVencimento(Date fecha) {
        List<Vencimientos> vencimientos = em.createQuery("select v from Vencimientos v "
                + "where v.fechaInicioValidez <= :fecha AND v.fechaFinValidez >= :fecha")
                .setParameter("fecha", fecha).getResultList();

        if (vencimientos != null && !vencimientos.isEmpty()) {
            return vencimientos.get(0);
        } else {
            return null;
        }
    }

}
