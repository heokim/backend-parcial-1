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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author heokim
 */
@Entity
@Table(name = "bolsas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bolsas.findAll", query = "SELECT b FROM Bolsas b"),
    @NamedQuery(name = "Bolsas.findByBolsaId", query = "SELECT b FROM Bolsas b WHERE b.bolsaId = :bolsaId"),
    @NamedQuery(name = "Bolsas.findByFechaDeAsignacionDePuntaje", query = "SELECT b FROM Bolsas b WHERE b.fechaDeAsignacionDePuntaje = :fechaDeAsignacionDePuntaje"),
    @NamedQuery(name = "Bolsas.findByFechaDeCaducidadDePuntaje", query = "SELECT b FROM Bolsas b WHERE b.fechaDeCaducidadDePuntaje = :fechaDeCaducidadDePuntaje"),
    @NamedQuery(name = "Bolsas.findByPuntajeAsignado", query = "SELECT b FROM Bolsas b WHERE b.puntajeAsignado = :puntajeAsignado"),
    @NamedQuery(name = "Bolsas.findByPuntajeUtilizado", query = "SELECT b FROM Bolsas b WHERE b.puntajeUtilizado = :puntajeUtilizado"),
    @NamedQuery(name = "Bolsas.findBySaldoDePuntos", query = "SELECT b FROM Bolsas b WHERE b.saldoDePuntos = :saldoDePuntos"),
    @NamedQuery(name = "Bolsas.findByMontoDeLaOperacion", query = "SELECT b FROM Bolsas b WHERE b.montoDeLaOperacion = :montoDeLaOperacion"),
    @NamedQuery(name = "Bolsas.findByEstado", query = "SELECT b FROM Bolsas b WHERE b.estado = :estado")})
public class Bolsas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "bolsa_id")
    private Integer bolsaId;
    @Column(name = "fecha_de_asignacion_de_puntaje")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeAsignacionDePuntaje;
    @Column(name = "fecha_de_caducidad_de_puntaje")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeCaducidadDePuntaje;
    @Basic(optional = false)
    @NotNull
    @Column(name = "puntaje_asignado")
    private int puntajeAsignado;
    @Column(name = "puntaje_utilizado")
    private Integer puntajeUtilizado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "saldo_de_puntos")
    private int saldoDePuntos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "monto_de_la_operacion")
    private int montoDeLaOperacion;
    @Column(name = "estado")
    private Boolean estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bolsaId", fetch = FetchType.EAGER)
    private List<UsoDePuntosDetalles> usoDePuntosDetallesList;
    @JoinColumn(name = "cliente_id", referencedColumnName = "cliente_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Clientes clienteId;

    public Bolsas() {
    }

    public Bolsas(Integer bolsaId) {
        this.bolsaId = bolsaId;
    }

    public Bolsas(Integer bolsaId, int puntajeAsignado, int saldoDePuntos, int montoDeLaOperacion) {
        this.bolsaId = bolsaId;
        this.puntajeAsignado = puntajeAsignado;
        this.saldoDePuntos = saldoDePuntos;
        this.montoDeLaOperacion = montoDeLaOperacion;
    }

    public Integer getBolsaId() {
        return bolsaId;
    }

    public void setBolsaId(Integer bolsaId) {
        this.bolsaId = bolsaId;
    }

    public Date getFechaDeAsignacionDePuntaje() {
        return fechaDeAsignacionDePuntaje;
    }

    public void setFechaDeAsignacionDePuntaje(Date fechaDeAsignacionDePuntaje) {
        this.fechaDeAsignacionDePuntaje = fechaDeAsignacionDePuntaje;
    }

    public Date getFechaDeCaducidadDePuntaje() {
        return fechaDeCaducidadDePuntaje;
    }

    public void setFechaDeCaducidadDePuntaje(Date fechaDeCaducidadDePuntaje) {
        this.fechaDeCaducidadDePuntaje = fechaDeCaducidadDePuntaje;
    }

    public int getPuntajeAsignado() {
        return puntajeAsignado;
    }

    public void setPuntajeAsignado(int puntajeAsignado) {
        this.puntajeAsignado = puntajeAsignado;
    }

    public Integer getPuntajeUtilizado() {
        return puntajeUtilizado;
    }

    public void setPuntajeUtilizado(Integer puntajeUtilizado) {
        this.puntajeUtilizado = puntajeUtilizado;
    }

    public int getSaldoDePuntos() {
        return saldoDePuntos;
    }

    public void setSaldoDePuntos(int saldoDePuntos) {
        this.saldoDePuntos = saldoDePuntos;
    }

    public int getMontoDeLaOperacion() {
        return montoDeLaOperacion;
    }

    public void setMontoDeLaOperacion(int montoDeLaOperacion) {
        this.montoDeLaOperacion = montoDeLaOperacion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<UsoDePuntosDetalles> getUsoDePuntosDetallesList() {
        return usoDePuntosDetallesList;
    }

    public void setUsoDePuntosDetallesList(List<UsoDePuntosDetalles> usoDePuntosDetallesList) {
        this.usoDePuntosDetallesList = usoDePuntosDetallesList;
    }

    public Clientes getClienteId() {
        return clienteId;
    }

    public void setClienteId(Clientes clienteId) {
        this.clienteId = clienteId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bolsaId != null ? bolsaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bolsas)) {
            return false;
        }
        Bolsas other = (Bolsas) object;
        if ((this.bolsaId == null && other.bolsaId != null) || (this.bolsaId != null && !this.bolsaId.equals(other.bolsaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pol.edu.py.primerparcialbackend.model.Bolsas[ bolsaId=" + bolsaId + " ]";
    }

}
