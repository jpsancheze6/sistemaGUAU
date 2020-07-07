/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA;

import JPA.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author José Sánchez
 */
public class DetalleFacturaJpaController implements Serializable {

    public DetalleFacturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DetalleFactura detalleFactura) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Factura facturaid = detalleFactura.getFacturaid();
            if (facturaid != null) {
                facturaid = em.getReference(facturaid.getClass(), facturaid.getIdFactura());
                detalleFactura.setFacturaid(facturaid);
            }
            Producto productoid = detalleFactura.getProductoid();
            if (productoid != null) {
                productoid = em.getReference(productoid.getClass(), productoid.getIdProducto());
                detalleFactura.setProductoid(productoid);
            }
            em.persist(detalleFactura);
            if (facturaid != null) {
                facturaid.getDetalleFacturaList().add(detalleFactura);
                facturaid = em.merge(facturaid);
            }
            if (productoid != null) {
                productoid.getDetalleFacturaList().add(detalleFactura);
                productoid = em.merge(productoid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DetalleFactura detalleFactura) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetalleFactura persistentDetalleFactura = em.find(DetalleFactura.class, detalleFactura.getId());
            Factura facturaidOld = persistentDetalleFactura.getFacturaid();
            Factura facturaidNew = detalleFactura.getFacturaid();
            Producto productoidOld = persistentDetalleFactura.getProductoid();
            Producto productoidNew = detalleFactura.getProductoid();
            if (facturaidNew != null) {
                facturaidNew = em.getReference(facturaidNew.getClass(), facturaidNew.getIdFactura());
                detalleFactura.setFacturaid(facturaidNew);
            }
            if (productoidNew != null) {
                productoidNew = em.getReference(productoidNew.getClass(), productoidNew.getIdProducto());
                detalleFactura.setProductoid(productoidNew);
            }
            detalleFactura = em.merge(detalleFactura);
            if (facturaidOld != null && !facturaidOld.equals(facturaidNew)) {
                facturaidOld.getDetalleFacturaList().remove(detalleFactura);
                facturaidOld = em.merge(facturaidOld);
            }
            if (facturaidNew != null && !facturaidNew.equals(facturaidOld)) {
                facturaidNew.getDetalleFacturaList().add(detalleFactura);
                facturaidNew = em.merge(facturaidNew);
            }
            if (productoidOld != null && !productoidOld.equals(productoidNew)) {
                productoidOld.getDetalleFacturaList().remove(detalleFactura);
                productoidOld = em.merge(productoidOld);
            }
            if (productoidNew != null && !productoidNew.equals(productoidOld)) {
                productoidNew.getDetalleFacturaList().add(detalleFactura);
                productoidNew = em.merge(productoidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = detalleFactura.getId();
                if (findDetalleFactura(id) == null) {
                    throw new NonexistentEntityException("The detalleFactura with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetalleFactura detalleFactura;
            try {
                detalleFactura = em.getReference(DetalleFactura.class, id);
                detalleFactura.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleFactura with id " + id + " no longer exists.", enfe);
            }
            Factura facturaid = detalleFactura.getFacturaid();
            if (facturaid != null) {
                facturaid.getDetalleFacturaList().remove(detalleFactura);
                facturaid = em.merge(facturaid);
            }
            Producto productoid = detalleFactura.getProductoid();
            if (productoid != null) {
                productoid.getDetalleFacturaList().remove(detalleFactura);
                productoid = em.merge(productoid);
            }
            em.remove(detalleFactura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DetalleFactura> findDetalleFacturaEntities() {
        return findDetalleFacturaEntities(true, -1, -1);
    }

    public List<DetalleFactura> findDetalleFacturaEntities(int maxResults, int firstResult) {
        return findDetalleFacturaEntities(false, maxResults, firstResult);
    }

    private List<DetalleFactura> findDetalleFacturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DetalleFactura.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public DetalleFactura findDetalleFactura(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DetalleFactura.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleFacturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DetalleFactura> rt = cq.from(DetalleFactura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
