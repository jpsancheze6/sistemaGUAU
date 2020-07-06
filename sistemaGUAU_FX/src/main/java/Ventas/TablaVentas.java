/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventas;

import Transaccion.DetalleFactura;
import static Transaccion.Factura_.detalleFacturaList;
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
public class TablaVentas {
    private SimpleIntegerProperty idFactura;
    private SimpleIntegerProperty Cliente_id;
    private ObjectProperty<Date> fecha = new SimpleObjectProperty<>(this, "fecha");
    private SimpleFloatProperty total;
    private List<DetalleFactura> detalleFacturaList;


    public TablaVentas(Integer idFactura, Integer Cliente_id, Date fecha, float total) {
        this.idFactura = new SimpleIntegerProperty(idFactura);
        this.Cliente_id = new SimpleIntegerProperty(Cliente_id);
        this.fecha = new SimpleObjectProperty<>(fecha);
        this.total = new SimpleFloatProperty(total);
    }

    public SimpleIntegerProperty getIdFacturaProperty() {
        return idFactura;
    }

    public SimpleIntegerProperty getCliente_idProperty() {
        return Cliente_id;
    }

    public ObjectProperty<Date> getFechaProperty() {
        return fecha;
    }

    public SimpleFloatProperty getTotalProperty() {
        return total;
    }
    
    //
    public Integer getIdFactura() {
        return idFactura.get();
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = new SimpleIntegerProperty(idFactura);
    }

    public Date getFecha() {
        return fecha.get();
    }

    public void setFecha(Date fecha) {
        this.fecha = new SimpleObjectProperty<>(fecha);
    }

    public Integer getCliente_id() {
        return Cliente_id.get();
    }

    public void setCliente_id(Integer Cliente_id) {
        this.Cliente_id = new SimpleIntegerProperty(Cliente_id);
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

    public List<DetalleFactura> getDetalleFacturaList() {
        return detalleFacturaList;
    }

    public void setDetalleFacturaList(List<DetalleFactura> detalleFacturaList) {
        this.detalleFacturaList = detalleFacturaList;
    }
}
