/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Compras;

import JPA.DetalleCompra;
import JPA.DetalleCompraJpaController;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author izasj
 */
public class DetalleCompraDAO {
    private final DetalleCompraJpaController detalle_controller;
    private final EntityManagerFactory emf;
    
    public DetalleCompraDAO() {
        emf = Persistence.createEntityManagerFactory("com.mevecon_sistemaGUAU_jar_1.0PU");
        detalle_controller = new DetalleCompraJpaController(emf);
    }
    
    public void AgregarDetalleCompra(DetalleCompra recibo) throws Exception {
        detalle_controller.create(recibo);
    }

    public void EditarDetalleCompra(DetalleCompra recibo) throws Exception {
        detalle_controller.edit(recibo);
    }

    public void EliminarDetalleCompra(int DetalleCompraID) throws Exception {
        detalle_controller.destroy(DetalleCompraID);
    }

    public List<DetalleCompra> getDetalleCompras() {
        return detalle_controller.findDetalleCompraEntities();
    }

    public DetalleCompra getDetalleCompraByID(int DetalleCompraID) {
        return detalle_controller.findDetalleCompra(DetalleCompraID);
    }
}
