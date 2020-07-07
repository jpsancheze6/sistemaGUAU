/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author José Sánchez
 */
@Entity
@Table(name = "detalles_actualizacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetallesActualizacion.findAll", query = "SELECT d FROM DetallesActualizacion d"),
    @NamedQuery(name = "DetallesActualizacion.findById", query = "SELECT d FROM DetallesActualizacion d WHERE d.id = :id"),
    @NamedQuery(name = "DetallesActualizacion.findByCantidad", query = "SELECT d FROM DetallesActualizacion d WHERE d.cantidad = :cantidad")})
public class DetallesActualizacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Cantidad")
    private int cantidad;
    @JoinColumn(name = "Actualizacion_id", referencedColumnName = "idActualizacion")
    @ManyToOne(optional = false)
    private ActualizacionInventario actualizacionid;
    @JoinColumn(name = "Producto_id", referencedColumnName = "idProducto")
    @ManyToOne(optional = false)
    private Producto productoid;

    public DetallesActualizacion() {
    }

    public DetallesActualizacion(Integer id) {
        this.id = id;
    }

    public DetallesActualizacion(Integer id, int cantidad) {
        this.id = id;
        this.cantidad = cantidad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public ActualizacionInventario getActualizacionid() {
        return actualizacionid;
    }

    public void setActualizacionid(ActualizacionInventario actualizacionid) {
        this.actualizacionid = actualizacionid;
    }

    public Producto getProductoid() {
        return productoid;
    }

    public void setProductoid(Producto productoid) {
        this.productoid = productoid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallesActualizacion)) {
            return false;
        }
        DetallesActualizacion other = (DetallesActualizacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPA.DetallesActualizacion[ id=" + id + " ]";
    }
    
}
