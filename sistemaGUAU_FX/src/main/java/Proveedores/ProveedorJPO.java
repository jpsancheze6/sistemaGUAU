/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proveedores;


import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author GuillePC
 */
public class ProveedorJPO {
    
     private final ProveedorJpaController proveedorcontroller;

    private final  EntityManagerFactory emf ;

    public ProveedorJPO() 
    {
        emf= Persistence.createEntityManagerFactory("FXProyecto");
        
        proveedorcontroller = new ProveedorJpaController(emf);
    }
    
    public void AgregarProveedor(Proveedor proveedor )throws Exception
    {
        proveedorcontroller.create(proveedor);
    }
    
        public void EditarProveedor (Proveedor proveedor )throws Exception
    {
        proveedorcontroller.edit(proveedor);
    }
        
   public void EliminarProveedor (int ProveedorID )throws Exception
    {
        proveedorcontroller.destroy(ProveedorID);
    }
    
   
   public List <Proveedor> Proveedores()
   {
       return proveedorcontroller.findProveedorEntities();
   }
    
   public Proveedor getProveedorByID(int ProveedorID)
   {
       return proveedorcontroller.findProveedor(ProveedorID);
   }
    
    
}
