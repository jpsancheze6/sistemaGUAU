/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventas;

import JPA.DetalleCompra;
import JPA.DetalleCompraJpaController;
import JPA.DetalleFactura;
import JPA.DetalleFacturaJpaController;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author izasj
 */
public class DetalleFacturaDAO {
    private final DetalleFacturaJpaController detalle_controller;
    private final EntityManagerFactory emf;
    
    public DetalleFacturaDAO() {
        emf = Persistence.createEntityManagerFactory("com.mevecon_sistemaGUAU_jar_1.0PU");
        detalle_controller = new DetalleFacturaJpaController(emf);
    }
    
    public void AgregarDetalleFactura(DetalleFactura factura) throws Exception {
        detalle_controller.create(factura);
    }

    public void EditarDetalleFactura(DetalleFactura factura) throws Exception {
        detalle_controller.edit(factura);
    }

    public void EliminarDetalleFactura(int DetalleFacturaID) throws Exception {
        detalle_controller.destroy(DetalleFacturaID);
    }

    public List<DetalleFactura> getDetalleFacturas() {
        return detalle_controller.findDetalleFacturaEntities();
    }

    public DetalleFactura getDetalleFacturaByID(int DetalleFacturaID) {
        return detalle_controller.findDetalleFactura(DetalleFacturaID);
    }
}
