package pol.edu.py.primerparcialbackend.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author heokim
 */
@Entity
@Table(name = "vencimientos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vencimientos.findAll", query = "SELECT v FROM Vencimientos v"),
    @NamedQuery(name = "Vencimientos.findByVencimientoId", query = "SELECT v FROM Vencimientos v WHERE v.vencimientoId = :vencimientoId"),
    @NamedQuery(name = "Vencimientos.findByFechaInicioValidez", query = "SELECT v FROM Vencimientos v WHERE v.fechaInicioValidez = :fechaInicioValidez"),
    @NamedQuery(name = "Vencimientos.findByFechaFinValidez", query = "SELECT v FROM Vencimientos v WHERE v.fechaFinValidez = :fechaFinValidez"),
    @NamedQuery(name = "Vencimientos.findByDiasRestantes", query = "SELECT v FROM Vencimientos v WHERE v.diasRestantes = :diasRestantes")})
public class Vencimientos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "vencimiento_id")
    private Integer vencimientoId;
    @Column(name = "fecha_inicio_validez")
    @Temporal(TemporalType.DATE)
    private Date fechaInicioValidez;
    @Column(name = "fecha_fin_validez")
    @Temporal(TemporalType.DATE)
    private Date fechaFinValidez;
    @Column(name = "dias_restantes")
    private Integer diasRestantes;

    public Vencimientos() {
    }

    public Vencimientos(Integer vencimientoId) {
        this.vencimientoId = vencimientoId;
    }

    public Integer getVencimientoId() {
        return vencimientoId;
    }

    public void setVencimientoId(Integer vencimientoId) {
        this.vencimientoId = vencimientoId;
    }

    public Date getFechaInicioValidez() {
        return fechaInicioValidez;
    }

    public void setFechaInicioValidez(Date fechaInicioValidez) {
        this.fechaInicioValidez = fechaInicioValidez;
    }

    public Date getFechaFinValidez() {
        return fechaFinValidez;
    }

    public void setFechaFinValidez(Date fechaFinValidez) {
        this.fechaFinValidez = fechaFinValidez;
    }

    public Integer getDiasRestantes() {
        return diasRestantes;
    }

    public void setDiasRestantes(Integer diasRestantes) {
        this.diasRestantes = diasRestantes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vencimientoId != null ? vencimientoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vencimientos)) {
            return false;
        }
        Vencimientos other = (Vencimientos) object;
        if ((this.vencimientoId == null && other.vencimientoId != null) || (this.vencimientoId != null && !this.vencimientoId.equals(other.vencimientoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pol.edu.py.primerparcialbackend.model.Vencimientos[ vencimientoId=" + vencimientoId + " ]";
    }

}
