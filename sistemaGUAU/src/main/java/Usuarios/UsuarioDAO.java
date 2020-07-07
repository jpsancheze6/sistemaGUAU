/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuarios;

import JPA.Usuario;
import JPA.UsuarioJpaController;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author GuillePC
 */
public class UsuarioDAO {
    private final UsuarioJpaController usuarioController;

    private final EntityManagerFactory emf;

    public UsuarioDAO() {
        emf = Persistence.createEntityManagerFactory("com.mevecon_sistemaGUAU_jar_1.0PU");
        usuarioController = new UsuarioJpaController(emf);
    }

    public void AgregarUsuario(Usuario usuario) throws Exception {
        usuarioController.create(usuario);
    }

    public void EditarUsuario(Usuario usuario) throws Exception {
        usuarioController.edit(usuario);
    }

    public void EliminarUsuario(int UsuarioID) throws Exception {
        usuarioController.destroy(UsuarioID);
    }

    public List<Usuario> getUsuarios() {
        return usuarioController.findUsuarioEntities();
    }

    public Usuario getUsuarioByID(int UsuarioID) {
        return usuarioController.findUsuario(UsuarioID);
    }
    
}
