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
@Table(name = "recibo_compra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReciboCompra.findAll", query = "SELECT r FROM ReciboCompra r"),
    @NamedQuery(name = "ReciboCompra.findByIdRecibo", query = "SELECT r FROM ReciboCompra r WHERE r.idRecibo = :idRecibo"),
    @NamedQuery(name = "ReciboCompra.findByFecha", query = "SELECT r FROM ReciboCompra r WHERE r.fecha = :fecha"),
    @NamedQuery(name = "ReciboCompra.findByTotal", query = "SELECT r FROM ReciboCompra r WHERE r.total = :total")})
public class ReciboCompra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRecibo")
    private Integer idRecibo;
    @Basic(optional = false)
    @Column(name = "Fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "Total")
    private float total;
    @JoinColumn(name = "Proveedor_id", referencedColumnName = "idProveedor")
    @ManyToOne(optional = false)
    private Proveedor proveedorid;
    @JoinColumn(name = "Usuario_idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne(optional = false)
    private Usuario usuarioidUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reciboid")
    private List<DetalleCompra> detalleCompraList;

    public ReciboCompra() {
    }

    public ReciboCompra(Integer idRecibo) {
        this.idRecibo = idRecibo;
    }

    public ReciboCompra(Integer idRecibo, Date fecha, float total) {
        this.idRecibo = idRecibo;
        this.fecha = fecha;
        this.total = total;
    }

    public Integer getIdRecibo() {
        return idRecibo;
    }

    public void setIdRecibo(Integer idRecibo) {
        this.idRecibo = idRecibo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Proveedor getProveedorid() {
        return proveedorid;
    }

    public void setProveedorid(Proveedor proveedorid) {
        this.proveedorid = proveedorid;
    }

    public Usuario getUsuarioidUsuario() {
        return usuarioidUsuario;
    }

    public void setUsuarioidUsuario(Usuario usuarioidUsuario) {
        this.usuarioidUsuario = usuarioidUsuario;
    }

    @XmlTransient
    public List<DetalleCompra> getDetalleCompraList() {
        return detalleCompraList;
    }

    public void setDetalleCompraList(List<DetalleCompra> detalleCompraList) {
        this.detalleCompraList = detalleCompraList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRecibo != null ? idRecibo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReciboCompra)) {
            return false;
        }
        ReciboCompra other = (ReciboCompra) object;
        if ((this.idRecibo == null && other.idRecibo != null) || (this.idRecibo != null && !this.idRecibo.equals(other.idRecibo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPA.ReciboCompra[ idRecibo=" + idRecibo + " ]";
    }
    
}
