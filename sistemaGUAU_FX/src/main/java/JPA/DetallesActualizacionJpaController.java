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
public class DetallesActualizacionJpaController implements Serializable {

    public DetallesActualizacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DetallesActualizacion detallesActualizacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ActualizacionInventario actualizacionid = detallesActualizacion.getActualizacionid();
            if (actualizacionid != null) {
                actualizacionid = em.getReference(actualizacionid.getClass(), actualizacionid.getIdActualizacion());
                detallesActualizacion.setActualizacionid(actualizacionid);
            }
            Producto productoid = detallesActualizacion.getProductoid();
            if (productoid != null) {
                productoid = em.getReference(productoid.getClass(), productoid.getIdProducto());
                detallesActualizacion.setProductoid(productoid);
            }
            em.persist(detallesActualizacion);
            if (actualizacionid != null) {
                actualizacionid.getDetallesActualizacionList().add(detallesActualizacion);
                actualizacionid = em.merge(actualizacionid);
            }
            if (productoid != null) {
                productoid.getDetallesActualizacionList().add(detallesActualizacion);
                productoid = em.merge(productoid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DetallesActualizacion detallesActualizacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetallesActualizacion persistentDetallesActualizacion = em.find(DetallesActualizacion.class, detallesActualizacion.getId());
            ActualizacionInventario actualizacionidOld = persistentDetallesActualizacion.getActualizacionid();
            ActualizacionInventario actualizacionidNew = detallesActualizacion.getActualizacionid();
            Producto productoidOld = persistentDetallesActualizacion.getProductoid();
            Producto productoidNew = detallesActualizacion.getProductoid();
            if (actualizacionidNew != null) {
                actualizacionidNew = em.getReference(actualizacionidNew.getClass(), actualizacionidNew.getIdActualizacion());
                detallesActualizacion.setActualizacionid(actualizacionidNew);
            }
            if (productoidNew != null) {
                productoidNew = em.getReference(productoidNew.getClass(), productoidNew.getIdProducto());
                detallesActualizacion.setProductoid(productoidNew);
            }
            detallesActualizacion = em.merge(detallesActualizacion);
            if (actualizacionidOld != null && !actualizacionidOld.equals(actualizacionidNew)) {
                actualizacionidOld.getDetallesActualizacionList().remove(detallesActualizacion);
                actualizacionidOld = em.merge(actualizacionidOld);
            }
            if (actualizacionidNew != null && !actualizacionidNew.equals(actualizacionidOld)) {
                actualizacionidNew.getDetallesActualizacionList().add(detallesActualizacion);
                actualizacionidNew = em.merge(actualizacionidNew);
            }
            if (productoidOld != null && !productoidOld.equals(productoidNew)) {
                productoidOld.getDetallesActualizacionList().remove(detallesActualizacion);
                productoidOld = em.merge(productoidOld);
            }
            if (productoidNew != null && !productoidNew.equals(productoidOld)) {
                productoidNew.getDetallesActualizacionList().add(detallesActualizacion);
                productoidNew = em.merge(productoidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = detallesActualizacion.getId();
                if (findDetallesActualizacion(id) == null) {
                    throw new NonexistentEntityException("The detallesActualizacion with id " + id + " no longer exists.");
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
            DetallesActualizacion detallesActualizacion;
            try {
                detallesActualizacion = em.getReference(DetallesActualizacion.class, id);
                detallesActualizacion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detallesActualizacion with id " + id + " no longer exists.", enfe);
            }
            ActualizacionInventario actualizacionid = detallesActualizacion.getActualizacionid();
            if (actualizacionid != null) {
                actualizacionid.getDetallesActualizacionList().remove(detallesActualizacion);
                actualizacionid = em.merge(actualizacionid);
            }
            Producto productoid = detallesActualizacion.getProductoid();
            if (productoid != null) {
                productoid.getDetallesActualizacionList().remove(detallesActualizacion);
                productoid = em.merge(productoid);
            }
            em.remove(detallesActualizacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DetallesActualizacion> findDetallesActualizacionEntities() {
        return findDetallesActualizacionEntities(true, -1, -1);
    }

    public List<DetallesActualizacion> findDetallesActualizacionEntities(int maxResults, int firstResult) {
        return findDetallesActualizacionEntities(false, maxResults, firstResult);
    }

    private List<DetallesActualizacion> findDetallesActualizacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DetallesActualizacion.class));
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

    public DetallesActualizacion findDetallesActualizacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DetallesActualizacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetallesActualizacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DetallesActualizacion> rt = cq.from(DetallesActualizacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
