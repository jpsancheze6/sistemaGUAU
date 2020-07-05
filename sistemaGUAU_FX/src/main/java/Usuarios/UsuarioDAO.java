package Usuarios;

import Usuarios.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author José Sánchez
 */
public class UsuarioDAO {
    private final UsuarioJpaController controlador_usuario;
    private final EntityManagerFactory emf;

    public UsuarioDAO() {
        emf = Persistence.createEntityManagerFactory("com.mevecom_sistemaGUAU_FX_jar_1.0PU");
        this.controlador_usuario = new UsuarioJpaController(emf);
    }
    
    public void agregarUsuario(Usuario usuario){
        this.controlador_usuario.create(usuario);
    }
    
    public void editarUsuario(Usuario usuario) throws Exception{
        this.controlador_usuario.edit(usuario);
    }
    
    public void eliminarUsuario(int usuario) throws NonexistentEntityException{
        this.controlador_usuario.destroy(usuario);
    }
    
    public List<Usuario> obtenerUsuarios(){
        return this.controlador_usuario.findUsuarioEntities();
    }
    
    public Usuario obtenerUsuarioPorId(int id){
        return this.controlador_usuario.findUsuario(id);
    }
    
}
