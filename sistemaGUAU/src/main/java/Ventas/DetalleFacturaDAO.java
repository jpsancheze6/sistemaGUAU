package Ventas;

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
    private final DetalleFacturaJpaController factura_controller;
    private final EntityManagerFactory emf;
    
    public DetalleFacturaDAO() {
        emf = Persistence.createEntityManagerFactory("com.mevecon_sistemaGUAU_jar_1.0PU");
        factura_controller = new DetalleFacturaJpaController(emf);
    }
    
    public void AgregarDetalleFactura(DetalleFactura detallefactura) throws Exception {
        factura_controller.create(detallefactura);
    }

    public void EditarDetalleFactura(DetalleFactura detallefactura) throws Exception {
        factura_controller.edit(detallefactura);
    }

    public void EliminarDetalleFactura(int detalleFacturaID) throws Exception {
        factura_controller.destroy(detalleFacturaID);
    }

    public List<DetalleFactura> getDetalleFacturas() {
        return factura_controller.findDetalleFacturaEntities();
    }

    public DetalleFactura getDetalleFacturaByID(int detalleFacturaID) {
        return factura_controller.findDetalleFactura(detalleFacturaID);
    }
    
}
