/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @author José Sánchez
 */
@Entity
@Table(name = "actualizacion_inventario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActualizacionInventario.findAll", query = "SELECT a FROM ActualizacionInventario a"),
    @NamedQuery(name = "ActualizacionInventario.findByIdActualizacion", query = "SELECT a FROM ActualizacionInventario a WHERE a.idActualizacion = :idActualizacion"),
    @NamedQuery(name = "ActualizacionInventario.findByFecha", query = "SELECT a FROM ActualizacionInventario a WHERE a.fecha = :fecha"),
    @NamedQuery(name = "ActualizacionInventario.findByDescripccion", query = "SELECT a FROM ActualizacionInventario a WHERE a.descripccion = :descripccion")})
public class ActualizacionInventario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idActualizacion")
    private Integer idActualizacion;
    @Basic(optional = false)
    @Column(name = "Fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "Descripccion")
    private String descripccion;
    @JoinColumn(name = "Usuario_id", referencedColumnName = "idUsuario")
    @ManyToOne(optional = false)
    private Usuario usuarioid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "actualizacionid")
    private List<DetallesActualizacion> detallesActualizacionList;

    public ActualizacionInventario() {
    }

    public ActualizacionInventario(Integer idActualizacion) {
        this.idActualizacion = idActualizacion;
    }

    public ActualizacionInventario(Integer idActualizacion, Date fecha, String descripccion) {
        this.idActualizacion = idActualizacion;
        this.fecha = fecha;
        this.descripccion = descripccion;
    }

    public Integer getIdActualizacion() {
        return idActualizacion;
    }

    public void setIdActualizacion(Integer idActualizacion) {
        this.idActualizacion = idActualizacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripccion() {
        return descripccion;
    }

    public void setDescripccion(String descripccion) {
        this.descripccion = descripccion;
    }

    public Usuario getUsuarioid() {
        return usuarioid;
    }

    public void setUsuarioid(Usuario usuarioid) {
        this.usuarioid = usuarioid;
    }

    @XmlTransient
    public List<DetallesActualizacion> getDetallesActualizacionList() {
        return detallesActualizacionList;
    }

    public void setDetallesActualizacionList(List<DetallesActualizacion> detallesActualizacionList) {
        this.detallesActualizacionList = detallesActualizacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActualizacion != null ? idActualizacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActualizacionInventario)) {
            return false;
        }
        ActualizacionInventario other = (ActualizacionInventario) object;
        if ((this.idActualizacion == null && other.idActualizacion != null) || (this.idActualizacion != null && !this.idActualizacion.equals(other.idActualizacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPA.ActualizacionInventario[ idActualizacion=" + idActualizacion + " ]";
    }
    
}
