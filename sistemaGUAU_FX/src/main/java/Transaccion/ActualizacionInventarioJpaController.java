/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transaccion;

import Transaccion.exceptions.IllegalOrphanException;
import Transaccion.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author José Sánchez
 */
public class ActualizacionInventarioJpaController implements Serializable {

    public ActualizacionInventarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ActualizacionInventario actualizacionInventario) {
        if (actualizacionInventario.getDetallesActualizacionList() == null) {
            actualizacionInventario.setDetallesActualizacionList(new ArrayList<DetallesActualizacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<DetallesActualizacion> attachedDetallesActualizacionList = new ArrayList<DetallesActualizacion>();
            for (DetallesActualizacion detallesActualizacionListDetallesActualizacionToAttach : actualizacionInventario.getDetallesActualizacionList()) {
                detallesActualizacionListDetallesActualizacionToAttach = em.getReference(detallesActualizacionListDetallesActualizacionToAttach.getClass(), detallesActualizacionListDetallesActualizacionToAttach.getId());
                attachedDetallesActualizacionList.add(detallesActualizacionListDetallesActualizacionToAttach);
            }
            actualizacionInventario.setDetallesActualizacionList(attachedDetallesActualizacionList);
            em.persist(actualizacionInventario);
            for (DetallesActualizacion detallesActualizacionListDetallesActualizacion : actualizacionInventario.getDetallesActualizacionList()) {
                ActualizacionInventario oldActualizacionidOfDetallesActualizacionListDetallesActualizacion = detallesActualizacionListDetallesActualizacion.getActualizacionid();
                detallesActualizacionListDetallesActualizacion.setActualizacionid(actualizacionInventario);
                detallesActualizacionListDetallesActualizacion = em.merge(detallesActualizacionListDetallesActualizacion);
                if (oldActualizacionidOfDetallesActualizacionListDetallesActualizacion != null) {
                    oldActualizacionidOfDetallesActualizacionListDetallesActualizacion.getDetallesActualizacionList().remove(detallesActualizacionListDetallesActualizacion);
                    oldActualizacionidOfDetallesActualizacionListDetallesActualizacion = em.merge(oldActualizacionidOfDetallesActualizacionListDetallesActualizacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ActualizacionInventario actualizacionInventario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ActualizacionInventario persistentActualizacionInventario = em.find(ActualizacionInventario.class, actualizacionInventario.getIdActualizacion());
            List<DetallesActualizacion> detallesActualizacionListOld = persistentActualizacionInventario.getDetallesActualizacionList();
            List<DetallesActualizacion> detallesActualizacionListNew = actualizacionInventario.getDetallesActualizacionList();
            List<String> illegalOrphanMessages = null;
            for (DetallesActualizacion detallesActualizacionListOldDetallesActualizacion : detallesActualizacionListOld) {
                if (!detallesActualizacionListNew.contains(detallesActualizacionListOldDetallesActualizacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DetallesActualizacion " + detallesActualizacionListOldDetallesActualizacion + " since its actualizacionid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<DetallesActualizacion> attachedDetallesActualizacionListNew = new ArrayList<DetallesActualizacion>();
            for (DetallesActualizacion detallesActualizacionListNewDetallesActualizacionToAttach : detallesActualizacionListNew) {
                detallesActualizacionListNewDetallesActualizacionToAttach = em.getReference(detallesActualizacionListNewDetallesActualizacionToAttach.getClass(), detallesActualizacionListNewDetallesActualizacionToAttach.getId());
                attachedDetallesActualizacionListNew.add(detallesActualizacionListNewDetallesActualizacionToAttach);
            }
            detallesActualizacionListNew = attachedDetallesActualizacionListNew;
            actualizacionInventario.setDetallesActualizacionList(detallesActualizacionListNew);
            actualizacionInventario = em.merge(actualizacionInventario);
            for (DetallesActualizacion detallesActualizacionListNewDetallesActualizacion : detallesActualizacionListNew) {
                if (!detallesActualizacionListOld.contains(detallesActualizacionListNewDetallesActualizacion)) {
                    ActualizacionInventario oldActualizacionidOfDetallesActualizacionListNewDetallesActualizacion = detallesActualizacionListNewDetallesActualizacion.getActualizacionid();
                    detallesActualizacionListNewDetallesActualizacion.setActualizacionid(actualizacionInventario);
                    detallesActualizacionListNewDetallesActualizacion = em.merge(detallesActualizacionListNewDetallesActualizacion);
                    if (oldActualizacionidOfDetallesActualizacionListNewDetallesActualizacion != null && !oldActualizacionidOfDetallesActualizacionListNewDetallesActualizacion.equals(actualizacionInventario)) {
                        oldActualizacionidOfDetallesActualizacionListNewDetallesActualizacion.getDetallesActualizacionList().remove(detallesActualizacionListNewDetallesActualizacion);
                        oldActualizacionidOfDetallesActualizacionListNewDetallesActualizacion = em.merge(oldActualizacionidOfDetallesActualizacionListNewDetallesActualizacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = actualizacionInventario.getIdActualizacion();
                if (findActualizacionInventario(id) == null) {
                    throw new NonexistentEntityException("The actualizacionInventario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ActualizacionInventario actualizacionInventario;
            try {
                actualizacionInventario = em.getReference(ActualizacionInventario.class, id);
                actualizacionInventario.getIdActualizacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The actualizacionInventario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<DetallesActualizacion> detallesActualizacionListOrphanCheck = actualizacionInventario.getDetallesActualizacionList();
            for (DetallesActualizacion detallesActualizacionListOrphanCheckDetallesActualizacion : detallesActualizacionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ActualizacionInventario (" + actualizacionInventario + ") cannot be destroyed since the DetallesActualizacion " + detallesActualizacionListOrphanCheckDetallesActualizacion + " in its detallesActualizacionList field has a non-nullable actualizacionid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(actualizacionInventario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ActualizacionInventario> findActualizacionInventarioEntities() {
        return findActualizacionInventarioEntities(true, -1, -1);
    }

    public List<ActualizacionInventario> findActualizacionInventarioEntities(int maxResults, int firstResult) {
        return findActualizacionInventarioEntities(false, maxResults, firstResult);
    }

    private List<ActualizacionInventario> findActualizacionInventarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ActualizacionInventario.class));
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

    public ActualizacionInventario findActualizacionInventario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ActualizacionInventario.class, id);
        } finally {
            em.close();
        }
    }

    public int getActualizacionInventarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ActualizacionInventario> rt = cq.from(ActualizacionInventario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
