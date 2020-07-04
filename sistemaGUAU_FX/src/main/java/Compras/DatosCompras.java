/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Compras;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author izasj
 */
public class DatosCompras {
    private EntityManagerFactory emf;
    private EntityManager em;
    
    public DatosCompras(){
        emf = Persistence.createEntityManagerFactory("STAppPU");
        em = emf.createEntityManager();
    }
    
    public List<ReciboCompra> getCompra(){
        TypedQuery<ReciboCompra> qry = em.createQuery("Hola", ReciboCompra.class);
        return qry.getResultList();
    }
}
