package Clientes;

import JPA.Cliente;
import JPA.ClienteJpaController;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author José Sánchez
 */
public class ClienteDAO {

    private final ClienteJpaController clientesController;

    private final EntityManagerFactory emf;

    public ClienteDAO() {
        emf = Persistence.createEntityManagerFactory("com.mevecon_sistemaGUAU_jar_1.0PU");
        clientesController = new ClienteJpaController(emf);
    }

    public void AgregarCliente(Cliente cliente) throws Exception {
        clientesController.create(cliente);
    }

    public void EditarCliente(Cliente cliente) throws Exception {
        clientesController.edit(cliente);
    }

    public void EliminarCliente(int ClienteID) throws Exception {
        clientesController.destroy(ClienteID);
    }

    public List<Cliente> getClientes() {
        return clientesController.findClienteEntities();
    }

    public Cliente getClienteByID(int ClienteID) {
        return clientesController.findCliente(ClienteID);
    }
}
