package pol.edu.py.primerparcialbackend.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author heokim
 */
@Entity
@Table(name = "conceptos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conceptos.findAll", query = "SELECT c FROM Conceptos c"),
    @NamedQuery(name = "Conceptos.findByConceptoId", query = "SELECT c FROM Conceptos c WHERE c.conceptoId = :conceptoId"),
    @NamedQuery(name = "Conceptos.findByDescripcion", query = "SELECT c FROM Conceptos c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "Conceptos.findByPuntosRequeridos", query = "SELECT c FROM Conceptos c WHERE c.puntosRequeridos = :puntosRequeridos")})
public class Conceptos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "concepto_id")
    private Integer conceptoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "puntos_requeridos")
    private int puntosRequeridos;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conceptoId", fetch = FetchType.LAZY)
    private List<UsoDePuntos> usoDePuntosList;

    public Conceptos() {
    }

    public Conceptos(Integer conceptoId) {
        this.conceptoId = conceptoId;
    }

    public Conceptos(Integer conceptoId, String descripcion, int puntosRequeridos) {
        this.conceptoId = conceptoId;
        this.descripcion = descripcion;
        this.puntosRequeridos = puntosRequeridos;
    }

    public Integer getConceptoId() {
        return conceptoId;
    }

    public void setConceptoId(Integer conceptoId) {
        this.conceptoId = conceptoId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPuntosRequeridos() {
        return puntosRequeridos;
    }

    public void setPuntosRequeridos(int puntosRequeridos) {
        this.puntosRequeridos = puntosRequeridos;
    }

    @XmlTransient
    public List<UsoDePuntos> getUsoDePuntosList() {
        return usoDePuntosList;
    }

    public void setUsoDePuntosList(List<UsoDePuntos> usoDePuntosList) {
        this.usoDePuntosList = usoDePuntosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (conceptoId != null ? conceptoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conceptos)) {
            return false;
        }
        Conceptos other = (Conceptos) object;
        if ((this.conceptoId == null && other.conceptoId != null) || (this.conceptoId != null && !this.conceptoId.equals(other.conceptoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pol.edu.py.primerparcialbackend.model.Conceptos[ conceptoId=" + conceptoId + " ]";
    }

}
