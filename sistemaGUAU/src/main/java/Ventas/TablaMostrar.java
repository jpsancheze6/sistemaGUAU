/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventas;

import java.util.Date;

/**
 *
 * @author izasj
 */
public class TablaMostrar {
    private int idFactura;
    private String nombreCliente;
    private Date Fecha;
    private float total;

    public TablaMostrar(int idFactura, String nombreCliente, Date Fecha, float total) {
        this.idFactura = idFactura;
        this.nombreCliente = nombreCliente;
        this.Fecha = Fecha;
        this.total = total;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
        
}
