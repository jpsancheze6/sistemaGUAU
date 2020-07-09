/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author José Sánchez
 */
@Entity
@Table(name = "producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p"),
    @NamedQuery(name = "Producto.findByIdProducto", query = "SELECT p FROM Producto p WHERE p.idProducto = :idProducto"),
    @NamedQuery(name = "Producto.findByNombre", query = "SELECT p FROM Producto p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Producto.findByDisponibilidad", query = "SELECT p FROM Producto p WHERE p.disponibilidad = :disponibilidad"),
    @NamedQuery(name = "Producto.findByExistencias", query = "SELECT p FROM Producto p WHERE p.existencias = :existencias"),
    @NamedQuery(name = "Producto.findByPrecio", query = "SELECT p FROM Producto p WHERE p.precio = :precio"),
    @NamedQuery(name = "Producto.findByTipoanimal", query = "SELECT p FROM Producto p WHERE p.tipoanimal = :tipoanimal"),
    @NamedQuery(name = "Producto.findByMarca", query = "SELECT p FROM Producto p WHERE p.marca = :marca"),
    @NamedQuery(name = "Producto.findByPeso", query = "SELECT p FROM Producto p WHERE p.peso = :peso"),
    @NamedQuery(name = "Producto.buscarPorNombre", query = "SELECT p FROM Producto P WHERE p.nombre LIKE :nombre"),
    @NamedQuery(name = "Producto.findByUnidadreferencia", query = "SELECT p FROM Producto p WHERE p.unidadreferencia = :unidadreferencia")})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProducto")
    private Integer idProducto;
    @Basic(optional = false)
    @Column(name = "Nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "Disponibilidad")
    private boolean disponibilidad;
    @Basic(optional = false)
    @Column(name = "Existencias")
    private float existencias;
    @Basic(optional = false)
    @Column(name = "Precio")
    private float precio;
    @Basic(optional = false)
    @Column(name = "Tipo_animal")
    private String tipoanimal;
    @Basic(optional = false)
    @Column(name = "Marca")
    private String marca;
    @Basic(optional = false)
    @Column(name = "Peso")
    private float peso;
    @Basic(optional = false)
    @Column(name = "Unidad_referencia")
    private String unidadreferencia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productoid")
    private List<DetalleFactura> detalleFacturaList;
    @JoinColumn(name = "Proveedor_id", referencedColumnName = "idProveedor")
    @ManyToOne(optional = false)
    private Proveedor proveedorid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productoid")
    private List<DetalleCompra> detalleCompraList;

    public Producto() {
    }

    public Producto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Producto(Integer idProducto, String nombre, boolean disponibilidad, float existencias, float precio, String tipoanimal, String marca, float peso, String unidadreferencia) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.disponibilidad = disponibilidad;
        this.existencias = existencias;
        this.precio = precio;
        this.tipoanimal = tipoanimal;
        this.marca = marca;
        this.peso = peso;
        this.unidadreferencia = unidadreferencia;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public float getExistencias() {
        return existencias;
    }

    public void setExistencias(float existencias) {
        this.existencias = existencias;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getTipoanimal() {
        return tipoanimal;
    }

    public void setTipoanimal(String tipoanimal) {
        this.tipoanimal = tipoanimal;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public String getUnidadreferencia() {
        return unidadreferencia;
    }

    public void setUnidadreferencia(String unidadreferencia) {
        this.unidadreferencia = unidadreferencia;
    }

    @XmlTransient
    public List<DetalleFactura> getDetalleFacturaList() {
        return detalleFacturaList;
    }

    public void setDetalleFacturaList(List<DetalleFactura> detalleFacturaList) {
        this.detalleFacturaList = detalleFacturaList;
    }

    public Proveedor getProveedorid() {
        return proveedorid;
    }

    public void setProveedorid(Proveedor proveedorid) {
        this.proveedorid = proveedorid;
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
        hash += (idProducto != null ? idProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.idProducto == null && other.idProducto != null) || (this.idProducto != null && !this.idProducto.equals(other.idProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPA.Producto[ idProducto=" + idProducto + " ]";
    }
    
}
