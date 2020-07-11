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
public class DetalleReciboDAO {
    private final DetalleCompraJpaController recibo_controller;
    private final EntityManagerFactory emf;
    
    public DetalleReciboDAO() {
        emf = Persistence.createEntityManagerFactory("com.mevecon_sistemaGUAU_jar_1.0PU");
        recibo_controller = new DetalleCompraJpaController(emf);
    }
    
    public void AgregarDetalleRecibo(DetalleCompra detallerecibo) throws Exception {
        recibo_controller.create(detallerecibo);
    }

    public void EditarDetalleRecibo(DetalleCompra detallerecibo) throws Exception {
        recibo_controller.edit(detallerecibo);
    }

    public void EliminarDetalleRecibo(int detalleReciboID) throws Exception {
        recibo_controller.destroy(detalleReciboID);
    }

    public List<DetalleCompra> getDetalleRecibo() {
        return recibo_controller.findDetalleCompraEntities();
    }

    public DetalleCompra getDetalleReciboByID(int detalleReciboID) {
        return recibo_controller.findDetalleCompra(detalleReciboID);
    }
}
