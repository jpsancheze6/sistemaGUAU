package sistemaguau;

import Proveedores.ProveedorDAO;
import Clientes.ClienteDAO;
import JPA.Cliente;
import JPA.Usuario;
import JPA.Proveedor;
import Usuarios.UsuarioDAO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author José Sánchez
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
       
        ProveedorDAO proveedor_dao = new ProveedorDAO();
        Proveedor a = new Proveedor();
        a.setNombre("Proveedor 2");
        a.setTelefono("98765432");
        a.setEmpresa("Areca");
        
        try {
            proveedor_dao.AgregarProveedor(a);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
////-----------------------------------------------------------------


//        byte[] contra = "Contra".getBytes();
//        UsuarioDAO usuario_dao = new UsuarioDAO();
//        Usuario a = new Usuario();
//        a.setNombre("Usuario1");
//        a.setNombreusuario("US1");
//        a.setContraseña(contra);
//
//
//        try {
//            usuario_dao.AgregarUsuario(a);
//            System.out.println("funciono");
//        } catch (Exception ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
}
