package pol.edu.py.primerparcialbackend.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author heokim
 */
@Entity
@Table(name = "reglas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reglas.findAll", query = "SELECT r FROM Reglas r"),
    @NamedQuery(name = "Reglas.findByReglaId", query = "SELECT r FROM Reglas r WHERE r.reglaId = :reglaId"),
    @NamedQuery(name = "Reglas.findByLimiteInferior", query = "SELECT r FROM Reglas r WHERE r.limiteInferior = :limiteInferior"),
    @NamedQuery(name = "Reglas.findByLimiteSuperior", query = "SELECT r FROM Reglas r WHERE r.limiteSuperior = :limiteSuperior"),
    @NamedQuery(name = "Reglas.findByMontoEquivalenciaPorPunto", query = "SELECT r FROM Reglas r WHERE r.montoEquivalenciaPorPunto = :montoEquivalenciaPorPunto")})
public class Reglas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "regla_id")
    private Integer reglaId;
    @Column(name = "limite_inferior")
    private Integer limiteInferior;
    @Column(name = "limite_superior")
    private Integer limiteSuperior;
    @Basic(optional = false)
    @NotNull
    @Column(name = "monto_equivalencia_por_punto")
    private int montoEquivalenciaPorPunto;

    public Reglas() {
    }

    public Reglas(Integer reglaId) {
        this.reglaId = reglaId;
    }

    public Reglas(Integer reglaId, int montoEquivalenciaPorPunto) {
        this.reglaId = reglaId;
        this.montoEquivalenciaPorPunto = montoEquivalenciaPorPunto;
    }

    public Integer getReglaId() {
        return reglaId;
    }

    public void setReglaId(Integer reglaId) {
        this.reglaId = reglaId;
    }

    public Integer getLimiteInferior() {
        return limiteInferior;
    }

    public void setLimiteInferior(Integer limiteInferior) {
        this.limiteInferior = limiteInferior;
    }

    public Integer getLimiteSuperior() {
        return limiteSuperior;
    }

    public void setLimiteSuperior(Integer limiteSuperior) {
        this.limiteSuperior = limiteSuperior;
    }

    public int getMontoEquivalenciaPorPunto() {
        return montoEquivalenciaPorPunto;
    }

    public void setMontoEquivalenciaPorPunto(int montoEquivalenciaPorPunto) {
        this.montoEquivalenciaPorPunto = montoEquivalenciaPorPunto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reglaId != null ? reglaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reglas)) {
            return false;
        }
        Reglas other = (Reglas) object;
        if ((this.reglaId == null && other.reglaId != null) || (this.reglaId != null && !this.reglaId.equals(other.reglaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pol.edu.py.primerparcialbackend.model.Reglas[ reglaId=" + reglaId + " ]";
    }

}
