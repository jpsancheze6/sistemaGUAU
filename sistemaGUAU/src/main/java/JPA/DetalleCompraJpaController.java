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
public class DetalleCompraJpaController implements Serializable {

    public DetalleCompraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DetalleCompra detalleCompra) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto productoid = detalleCompra.getProductoid();
            if (productoid != null) {
                productoid = em.getReference(productoid.getClass(), productoid.getIdProducto());
                detalleCompra.setProductoid(productoid);
            }
            ReciboCompra reciboid = detalleCompra.getReciboid();
            if (reciboid != null) {
                reciboid = em.getReference(reciboid.getClass(), reciboid.getIdRecibo());
                detalleCompra.setReciboid(reciboid);
            }
            em.persist(detalleCompra);
            if (productoid != null) {
                productoid.getDetalleCompraList().add(detalleCompra);
                productoid = em.merge(productoid);
            }
            if (reciboid != null) {
                reciboid.getDetalleCompraList().add(detalleCompra);
                reciboid = em.merge(reciboid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DetalleCompra detalleCompra) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetalleCompra persistentDetalleCompra = em.find(DetalleCompra.class, detalleCompra.getId());
            Producto productoidOld = persistentDetalleCompra.getProductoid();
            Producto productoidNew = detalleCompra.getProductoid();
            ReciboCompra reciboidOld = persistentDetalleCompra.getReciboid();
            ReciboCompra reciboidNew = detalleCompra.getReciboid();
            if (productoidNew != null) {
                productoidNew = em.getReference(productoidNew.getClass(), productoidNew.getIdProducto());
                detalleCompra.setProductoid(productoidNew);
            }
            if (reciboidNew != null) {
                reciboidNew = em.getReference(reciboidNew.getClass(), reciboidNew.getIdRecibo());
                detalleCompra.setReciboid(reciboidNew);
            }
            detalleCompra = em.merge(detalleCompra);
            if (productoidOld != null && !productoidOld.equals(productoidNew)) {
                productoidOld.getDetalleCompraList().remove(detalleCompra);
                productoidOld = em.merge(productoidOld);
            }
            if (productoidNew != null && !productoidNew.equals(productoidOld)) {
                productoidNew.getDetalleCompraList().add(detalleCompra);
                productoidNew = em.merge(productoidNew);
            }
            if (reciboidOld != null && !reciboidOld.equals(reciboidNew)) {
                reciboidOld.getDetalleCompraList().remove(detalleCompra);
                reciboidOld = em.merge(reciboidOld);
            }
            if (reciboidNew != null && !reciboidNew.equals(reciboidOld)) {
                reciboidNew.getDetalleCompraList().add(detalleCompra);
                reciboidNew = em.merge(reciboidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = detalleCompra.getId();
                if (findDetalleCompra(id) == null) {
                    throw new NonexistentEntityException("The detalleCompra with id " + id + " no longer exists.");
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
            DetalleCompra detalleCompra;
            try {
                detalleCompra = em.getReference(DetalleCompra.class, id);
                detalleCompra.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleCompra with id " + id + " no longer exists.", enfe);
            }
            Producto productoid = detalleCompra.getProductoid();
            if (productoid != null) {
                productoid.getDetalleCompraList().remove(detalleCompra);
                productoid = em.merge(productoid);
            }
            ReciboCompra reciboid = detalleCompra.getReciboid();
            if (reciboid != null) {
                reciboid.getDetalleCompraList().remove(detalleCompra);
                reciboid = em.merge(reciboid);
            }
            em.remove(detalleCompra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DetalleCompra> findDetalleCompraEntities() {
        return findDetalleCompraEntities(true, -1, -1);
    }

    public List<DetalleCompra> findDetalleCompraEntities(int maxResults, int firstResult) {
        return findDetalleCompraEntities(false, maxResults, firstResult);
    }

    private List<DetalleCompra> findDetalleCompraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DetalleCompra.class));
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

    public DetalleCompra findDetalleCompra(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DetalleCompra.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleCompraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DetalleCompra> rt = cq.from(DetalleCompra.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
