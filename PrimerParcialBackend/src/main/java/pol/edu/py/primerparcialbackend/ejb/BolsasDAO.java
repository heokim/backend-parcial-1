package pol.edu.py.primerparcialbackend.ejb;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pol.edu.py.primerparcialbackend.model.Bolsas;
import pol.edu.py.primerparcialbackend.model.Vencimientos;
import pol.edu.py.primerparcialbackend.utils.DateUtils;

@Stateless
public class BolsasDAO extends AbstractDAO<Bolsas> {

    @Inject
    VencimientosDAO vencimientosDAO;

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

    public Bolsas create(int clienteId, int monto, int puntos) {
        Date today = new Date();

        Bolsas bolsa = new Bolsas();
        bolsa.setClienteId(clienteId);
        bolsa.setFechaDeAsignacionDePuntaje(today);
        bolsa.setPuntajeAsignado(puntos);
        bolsa.setPuntajeUtilizado(0);
        bolsa.setSaldoDePuntos(puntos);
        bolsa.setMontoDeLaOperacion(monto);
        bolsa.setEstado(true);

        // Buscar que vencimiento corresponde al dia de hoy
        Vencimientos vencimiento = vencimientosDAO.traerVencimento(today);
        if (vencimiento == null) {
            return null; // en caso que no tenga un vencimiento que setear
        }

        Date fechaVence = DateUtils.sumarFechaYDias(today, vencimiento.getDiasRestantes());
        bolsa.setFechaDeCaducidadDePuntaje(fechaVence);
        return this.create(bolsa);
    }

    public List<Bolsas> findOnlyActive() {
        return em.createQuery("SELECT b FROM Bolsas b WHERE b.estado = true").getResultList();
    }

}
