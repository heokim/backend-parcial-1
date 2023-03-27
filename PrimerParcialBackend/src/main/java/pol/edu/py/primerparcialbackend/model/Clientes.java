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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author heokim
 */
@Entity
@Table(name = "clientes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clientes.findAll", query = "SELECT c FROM Clientes c"),
    @NamedQuery(name = "Clientes.findByClienteId", query = "SELECT c FROM Clientes c WHERE c.clienteId = :clienteId"),
    @NamedQuery(name = "Clientes.findByTipoDocumento", query = "SELECT c FROM Clientes c WHERE c.tipoDocumento = :tipoDocumento"),
    @NamedQuery(name = "Clientes.findByNumeroDocumento", query = "SELECT c FROM Clientes c WHERE c.numeroDocumento = :numeroDocumento"),
    @NamedQuery(name = "Clientes.findByNombre", query = "SELECT c FROM Clientes c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Clientes.findByApellido", query = "SELECT c FROM Clientes c WHERE c.apellido = :apellido"),
    @NamedQuery(name = "Clientes.findByFechaNacimiento", query = "SELECT c FROM Clientes c WHERE c.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "Clientes.findByNacionalidad", query = "SELECT c FROM Clientes c WHERE c.nacionalidad = :nacionalidad"),
    @NamedQuery(name = "Clientes.findByMail", query = "SELECT c FROM Clientes c WHERE c.mail = :mail"),
    @NamedQuery(name = "Clientes.findByTelefono", query = "SELECT c FROM Clientes c WHERE c.telefono = :telefono")})
public class Clientes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cliente_id")
    private Integer clienteId;
    @Size(max = 100)
    @Column(name = "tipo_documento")
    private String tipoDocumento;
    @Size(max = 100)
    @Column(name = "numero_documento")
    private String numeroDocumento;
    @Size(max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 100)
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Size(max = 100)
    @Column(name = "nacionalidad")
    private String nacionalidad;
    @Size(max = 100)
    @Column(name = "mail")
    private String mail;
    @Size(max = 100)
    @Column(name = "telefono")
    private String telefono;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clienteId", fetch = FetchType.LAZY)
    private List<UsoDePuntos> usoDePuntosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clienteId", fetch = FetchType.LAZY)
    private List<Bolsas> bolsasList;

    public Clientes() {
    }

    public Clientes(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @XmlTransient
    public List<UsoDePuntos> getUsoDePuntosList() {
        return usoDePuntosList;
    }

    public void setUsoDePuntosList(List<UsoDePuntos> usoDePuntosList) {
        this.usoDePuntosList = usoDePuntosList;
    }

    @XmlTransient
    public List<Bolsas> getBolsasList() {
        return bolsasList;
    }

    public void setBolsasList(List<Bolsas> bolsasList) {
        this.bolsasList = bolsasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clienteId != null ? clienteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clientes)) {
            return false;
        }
        Clientes other = (Clientes) object;
        if ((this.clienteId == null && other.clienteId != null) || (this.clienteId != null && !this.clienteId.equals(other.clienteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pol.edu.py.primerparcialbackend.model.Clientes[ clienteId=" + clienteId + " ]";
    }

}
