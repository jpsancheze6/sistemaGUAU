/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proveedores;


import JPA.Proveedor;
import JPA.ProveedorJpaController;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author GuillePC
 */
public class ProveedorDAO {
      private final ProveedorJpaController proveedorController;

    private final EntityManagerFactory emf;

    public ProveedorDAO() {
        emf = Persistence.createEntityManagerFactory("com.mevecon_sistemaGUAU_jar_1.0PU");
        proveedorController = new ProveedorJpaController(emf);
    }

    public void AgregarProveedor(Proveedor proveedor) throws Exception {
        proveedorController.create(proveedor);
    }

    public void EditarProveedor(Proveedor proveedor) throws Exception {
        proveedorController.edit(proveedor);
    }

    public void EliminarProveedor(int ProveedorID) throws Exception {
        proveedorController.destroy(ProveedorID);
    }

    public List<Proveedor> getProveedor() {
        return proveedorController.findProveedorEntities();
    }

    public Proveedor getProveedorByID(int ProveedorID) {
        return proveedorController.findProveedor(ProveedorID);
    }
    
}
