/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventas;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author izasj
 */
public class DatosVentas {
    private EntityManagerFactory emf;
    private EntityManager em;
    
    public DatosVentas(){
        emf = Persistence.createEntityManagerFactory("STAppPU");
        em = emf.createEntityManager();
    }
    
    public List<Factura> getFactura(){
        TypedQuery<Factura> qry = em.createQuery("Hola", Factura.class);
        return qry.getResultList();
    }
    
}
