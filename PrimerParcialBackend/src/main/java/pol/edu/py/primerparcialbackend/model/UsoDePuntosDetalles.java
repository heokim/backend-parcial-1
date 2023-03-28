package pol.edu.py.primerparcialbackend.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "uso_de_puntos_detalles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsoDePuntosDetalles.findAll", query = "SELECT u FROM UsoDePuntosDetalles u"),
    @NamedQuery(name = "UsoDePuntosDetalles.findByDetalleId", query = "SELECT u FROM UsoDePuntosDetalles u WHERE u.detalleId = :detalleId"),
    @NamedQuery(name = "UsoDePuntosDetalles.findByPuntajeUtilizado", query = "SELECT u FROM UsoDePuntosDetalles u WHERE u.puntajeUtilizado = :puntajeUtilizado")})
public class UsoDePuntosDetalles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "detalle_id")
    private Integer detalleId;
    @Column(name = "cabecera_id")
    private UsoDePuntos cabeceraId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "puntaje_utilizado")
    private int puntajeUtilizado;
    @JoinColumn(name = "bolsa_id", referencedColumnName = "bolsa_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Bolsas bolsaId;
//    @JoinColumn(name = "cabecera_id", referencedColumnName = "cabecera_id")
//    @ManyToOne(optional = false, fetch = FetchType.LAZY)

    public UsoDePuntosDetalles() {
    }

    public UsoDePuntosDetalles(int puntajeUtilizado, Bolsas bolsaId, UsoDePuntos cabeceraId) {
        this.puntajeUtilizado = puntajeUtilizado;
        this.bolsaId = bolsaId;
        this.cabeceraId = cabeceraId;
    }

    public UsoDePuntosDetalles(Integer detalleId) {
        this.detalleId = detalleId;
    }

    public UsoDePuntosDetalles(Integer detalleId, int puntajeUtilizado) {
        this.detalleId = detalleId;
        this.puntajeUtilizado = puntajeUtilizado;
    }

    public Integer getDetalleId() {
        return detalleId;
    }

    public void setDetalleId(Integer detalleId) {
        this.detalleId = detalleId;
    }

    public int getPuntajeUtilizado() {
        return puntajeUtilizado;
    }

    public void setPuntajeUtilizado(int puntajeUtilizado) {
        this.puntajeUtilizado = puntajeUtilizado;
    }

    public Bolsas getBolsaId() {
        return bolsaId;
    }

    public void setBolsaId(Bolsas bolsaId) {
        this.bolsaId = bolsaId;
    }

    public UsoDePuntos getCabeceraId() {
        return cabeceraId;
    }

    public void setCabeceraId(UsoDePuntos cabeceraId) {
        this.cabeceraId = cabeceraId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleId != null ? detalleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsoDePuntosDetalles)) {
            return false;
        }
        UsoDePuntosDetalles other = (UsoDePuntosDetalles) object;
        if ((this.detalleId == null && other.detalleId != null) || (this.detalleId != null && !this.detalleId.equals(other.detalleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pol.edu.py.primerparcialbackend.model.UsoDePuntosDetalles[ detalleId=" + detalleId + " ]";
    }

}
