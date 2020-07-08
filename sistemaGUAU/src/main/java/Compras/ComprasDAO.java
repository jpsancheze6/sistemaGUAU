/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Compras;

import JPA.ReciboCompra;
import JPA.ReciboCompraJpaController;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author izasj
 */
public class ComprasDAO {
    private final ReciboCompraJpaController recibo_controller;
    private final EntityManagerFactory emf;
    
    public ComprasDAO() {
        emf = Persistence.createEntityManagerFactory("com.mevecon_sistemaGUAU_jar_1.0PU");
        recibo_controller = new ReciboCompraJpaController(emf);
    }
    
    public void AgregarCompra(ReciboCompra recibo) throws Exception {
        recibo_controller.create(recibo);
    }

    public void EditarCompra(ReciboCompra recibo) throws Exception {
        recibo_controller.edit(recibo);
    }

    public void EliminarCompra(int ReciboID) throws Exception {
        recibo_controller.destroy(ReciboID);
    }

    public List<ReciboCompra> getRecibos() {
        return recibo_controller.findReciboCompraEntities();
    }

    public ReciboCompra getReciboByID(int ReciboID) {
        return recibo_controller.findReciboCompra(ReciboID);
    }
}
