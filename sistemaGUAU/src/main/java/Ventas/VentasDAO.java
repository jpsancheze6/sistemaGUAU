/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventas;

import JPA.Factura;
import JPA.FacturaJpaController;
import JPA.Producto;
import JPA.ProductoJpaController;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author izasj
 */
public class VentasDAO {
    private final FacturaJpaController factura_controller;
    private final EntityManagerFactory emf;
    
    public VentasDAO() {
        emf = Persistence.createEntityManagerFactory("com.mevecon_sistemaGUAU_jar_1.0PU");
        factura_controller = new FacturaJpaController(emf);
    }
    
    public void AgregarFactura(Factura factura) throws Exception {
        factura_controller.create(factura);
    }

    public void EditarFactura(Factura factura) throws Exception {
        factura_controller.edit(factura);
    }

    public void EliminarFactura(int FacturaID) throws Exception {
        factura_controller.destroy(FacturaID);
    }

    public List<Factura> getFacturas() {
        return factura_controller.findFacturaEntities();
    }

    public Factura getFacturaByID(int FacturaID) {
        return factura_controller.findFactura(FacturaID);
    }
}
