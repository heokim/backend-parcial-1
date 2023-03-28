package pol.edu.py.primerparcialbackend.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author heokim
 */
@Entity
@Table(name = "uso_de_puntos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsoDePuntos.findAll", query = "SELECT u FROM UsoDePuntos u"),
    @NamedQuery(name = "UsoDePuntos.findByCabeceraId", query = "SELECT u FROM UsoDePuntos u WHERE u.cabeceraId = :cabeceraId"),
    @NamedQuery(name = "UsoDePuntos.findByFecha", query = "SELECT u FROM UsoDePuntos u WHERE u.fecha = :fecha"),
    @NamedQuery(name = "UsoDePuntos.findByPuntajeUtilizado", query = "SELECT u FROM UsoDePuntos u WHERE u.puntajeUtilizado = :puntajeUtilizado")})
public class UsoDePuntos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cabecera_id")
    private Integer cabeceraId;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "puntaje_utilizado")
    private Integer puntajeUtilizado;
    @JoinColumn(name = "cliente_id", referencedColumnName = "cliente_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Clientes clienteId;
    @JoinColumn(name = "concepto_id", referencedColumnName = "concepto_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Conceptos conceptoId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cabeceraId", fetch = FetchType.LAZY)
    private List<UsoDePuntosDetalles> usoDePuntosDetallesList;

    public UsoDePuntos() {
    }

    public UsoDePuntos(Date fecha, Integer puntajeUtilizado, Clientes clienteId, Conceptos conceptoId) {
        this.fecha = fecha;
        this.puntajeUtilizado = puntajeUtilizado;
        this.clienteId = clienteId;
        this.conceptoId = conceptoId;
    }

    public UsoDePuntos(Integer cabeceraId) {
        this.cabeceraId = cabeceraId;
    }

    public Integer getCabeceraId() {
        return cabeceraId;
    }

    public void setCabeceraId(Integer cabeceraId) {
        this.cabeceraId = cabeceraId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getPuntajeUtilizado() {
        return puntajeUtilizado;
    }

    public void setPuntajeUtilizado(Integer puntajeUtilizado) {
        this.puntajeUtilizado = puntajeUtilizado;
    }

    public Clientes getClienteId() {
        return clienteId;
    }

    public void setClienteId(Clientes clienteId) {
        this.clienteId = clienteId;
    }

    public Conceptos getConceptoId() {
        return conceptoId;
    }

    public void setConceptoId(Conceptos conceptoId) {
        this.conceptoId = conceptoId;
    }

    @XmlTransient
    public List<UsoDePuntosDetalles> getUsoDePuntosDetallesList() {
        return usoDePuntosDetallesList;
    }

    public void setUsoDePuntosDetallesList(List<UsoDePuntosDetalles> usoDePuntosDetallesList) {
        this.usoDePuntosDetallesList = usoDePuntosDetallesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cabeceraId != null ? cabeceraId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsoDePuntos)) {
            return false;
        }
        UsoDePuntos other = (UsoDePuntos) object;
        if ((this.cabeceraId == null && other.cabeceraId != null) || (this.cabeceraId != null && !this.cabeceraId.equals(other.cabeceraId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pol.edu.py.primerparcialbackend.model.UsoDePuntos[ cabeceraId=" + cabeceraId + " ]";
    }

}
