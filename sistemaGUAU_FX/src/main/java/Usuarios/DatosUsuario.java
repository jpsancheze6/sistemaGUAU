/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuarios;

import Ventas.Factura;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author izasj
 */
public class DatosUsuario {
    private EntityManagerFactory emf;
    private EntityManager em;
    
    public DatosUsuario(){
        emf = Persistence.createEntityManagerFactory("STAppPU");
        em = emf.createEntityManager();
    }
    
    public List<Usuario> getUsuario(){
        return em.createQuery("Hola", Usuario.class).getResultList();
    }
}
