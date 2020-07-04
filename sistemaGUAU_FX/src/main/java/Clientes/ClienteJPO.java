/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clientes;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author GuillePC
 */
public class ClienteJPO {
    private final ClienteJpaController clientesController;

    private final  EntityManagerFactory emf ;

    public ClienteJPO() 
    {
        emf= Persistence.createEntityManagerFactory("FXProyecto");
        
        clientesController = new ClienteJpaController(emf);
    }
    
    public void AgregarCliente (Cliente cliente )throws Exception
    {
        clientesController.create(cliente);
    }
    
        public void EditarCliente (Cliente cliente )throws Exception
    {
        clientesController.edit(cliente);
    }
        
   public void EliminarCliente (int ClienteID )throws Exception
    {
        clientesController.destroy(ClienteID);
    }
    
   
   public List <Cliente> getClientes()
   {
       return clientesController.findClienteEntities();
   }
    
   public Cliente getClienteByID(int ClienteID)
   {
       return clientesController.findCliente(ClienteID);
   }
    
}
