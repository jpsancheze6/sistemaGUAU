package Inventario;

import JPA.Producto;
import JPA.ProductoJpaController;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author José Sánchez
 */
public class ProductoDAO {

    private final ProductoJpaController producto_controller;
    private final EntityManagerFactory emf;

    public ProductoDAO() {
        emf = Persistence.createEntityManagerFactory("com.mevecon_sistemaGUAU_jar_1.0PU");
        producto_controller = new ProductoJpaController(emf);
    }

    public List<Producto> obtenerProductoPorNombre(String nombre){
        return producto_controller.findProductoPorNombre(nombre);
    }
    
    public void AgregarProducto(Producto producto) throws Exception {
        producto_controller.create(producto);
    }

    public void EditarProducto(Producto producto) throws Exception {
        producto_controller.edit(producto);
    }

    public void EliminarProducto(int ProductoID) throws Exception {
        producto_controller.destroy(ProductoID);
    }

    public List<Producto> getProductos() {
        return producto_controller.findProductoEntities();
    }

    public Producto getProductoByID(int ProductoID) {
        return producto_controller.findProducto(ProductoID);
    }

}
