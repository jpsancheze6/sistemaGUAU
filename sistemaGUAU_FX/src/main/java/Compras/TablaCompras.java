/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Compras;

import Transaccion.DetalleCompra;
import java.util.Date;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 *
 * @author izasj
 */
public class TablaCompras {
    private SimpleIntegerProperty idRecibo;
    private SimpleIntegerProperty Proveedor_id;
    private ObjectProperty<Date> fecha = new SimpleObjectProperty<>(this, "fecha");
    private SimpleFloatProperty total;
    private List<DetalleCompra> detalleCompraList;


    public TablaCompras(Integer idRecibo, Integer Proveedor_id, Date fecha, float total) {
        this.idRecibo = new SimpleIntegerProperty(idRecibo);
        this.Proveedor_id = new SimpleIntegerProperty(Proveedor_id);
        this.fecha = new SimpleObjectProperty<>(fecha);
        this.total = new SimpleFloatProperty(total);
    }

    public SimpleIntegerProperty getIdReciboProperty() {
        return idRecibo;
    }

    public SimpleIntegerProperty getProveedor_idProperty() {
        return Proveedor_id;
    }

    public ObjectProperty<Date> getFechaProperty() {
        return fecha;
    }

    public SimpleFloatProperty getTotalProperty() {
        return total;
    }
    
    //
    public Integer getIdRecibo() {
        return idRecibo.get();
    }

    public void setIdRecibo(Integer idRecibo) {
        this.idRecibo = new SimpleIntegerProperty(idRecibo);
    }

    public Date getFecha() {
        return fecha.get();
    }

    public void setFecha(Date fecha) {
        this.fecha = new SimpleObjectProperty<>(fecha);
    }

    public Integer getProveedor_id() {
        return Proveedor_id.get();
    }

    public void setProveedor_id(Integer Proveedor_id) {
        this.Proveedor_id = new SimpleIntegerProperty(Proveedor_id);
    }

    public float getTotal() {
        return total.get();
    }

    public void setTotal(float total) {
        this.total = new SimpleFloatProperty(total);
    }

    public void setTotal(SimpleFloatProperty total) {
        this.total = total;
    }

    public List<DetalleCompra> getDetalleCompraList() {
        return detalleCompraList;
    }

    public void setDetalleCompraList(List<DetalleCompra> detalleCompraList) {
        this.detalleCompraList = detalleCompraList;
    }
}
