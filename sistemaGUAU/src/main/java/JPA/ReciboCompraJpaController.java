/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA;

import JPA.exceptions.IllegalOrphanException;
import JPA.exceptions.NonexistentEntityException;
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
public class ReciboCompraJpaController implements Serializable {

    public ReciboCompraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ReciboCompra reciboCompra) {
        if (reciboCompra.getDetalleCompraList() == null) {
            reciboCompra.setDetalleCompraList(new ArrayList<DetalleCompra>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedor proveedorid = reciboCompra.getProveedorid();
            if (proveedorid != null) {
                proveedorid = em.getReference(proveedorid.getClass(), proveedorid.getIdProveedor());
                reciboCompra.setProveedorid(proveedorid);
            }
            Usuario usuarioidUsuario = reciboCompra.getUsuarioidUsuario();
            if (usuarioidUsuario != null) {
                usuarioidUsuario = em.getReference(usuarioidUsuario.getClass(), usuarioidUsuario.getIdUsuario());
                reciboCompra.setUsuarioidUsuario(usuarioidUsuario);
            }
            List<DetalleCompra> attachedDetalleCompraList = new ArrayList<DetalleCompra>();
            for (DetalleCompra detalleCompraListDetalleCompraToAttach : reciboCompra.getDetalleCompraList()) {
                detalleCompraListDetalleCompraToAttach = em.getReference(detalleCompraListDetalleCompraToAttach.getClass(), detalleCompraListDetalleCompraToAttach.getId());
                attachedDetalleCompraList.add(detalleCompraListDetalleCompraToAttach);
            }
            reciboCompra.setDetalleCompraList(attachedDetalleCompraList);
            em.persist(reciboCompra);
            if (proveedorid != null) {
                proveedorid.getReciboCompraList().add(reciboCompra);
                proveedorid = em.merge(proveedorid);
            }
            if (usuarioidUsuario != null) {
                usuarioidUsuario.getReciboCompraList().add(reciboCompra);
                usuarioidUsuario = em.merge(usuarioidUsuario);
            }
            for (DetalleCompra detalleCompraListDetalleCompra : reciboCompra.getDetalleCompraList()) {
                ReciboCompra oldReciboidOfDetalleCompraListDetalleCompra = detalleCompraListDetalleCompra.getReciboid();
                detalleCompraListDetalleCompra.setReciboid(reciboCompra);
                detalleCompraListDetalleCompra = em.merge(detalleCompraListDetalleCompra);
                if (oldReciboidOfDetalleCompraListDetalleCompra != null) {
                    oldReciboidOfDetalleCompraListDetalleCompra.getDetalleCompraList().remove(detalleCompraListDetalleCompra);
                    oldReciboidOfDetalleCompraListDetalleCompra = em.merge(oldReciboidOfDetalleCompraListDetalleCompra);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ReciboCompra reciboCompra) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ReciboCompra persistentReciboCompra = em.find(ReciboCompra.class, reciboCompra.getIdRecibo());
            Proveedor proveedoridOld = persistentReciboCompra.getProveedorid();
            Proveedor proveedoridNew = reciboCompra.getProveedorid();
            Usuario usuarioidUsuarioOld = persistentReciboCompra.getUsuarioidUsuario();
            Usuario usuarioidUsuarioNew = reciboCompra.getUsuarioidUsuario();
            List<DetalleCompra> detalleCompraListOld = persistentReciboCompra.getDetalleCompraList();
            List<DetalleCompra> detalleCompraListNew = reciboCompra.getDetalleCompraList();
            List<String> illegalOrphanMessages = null;
            for (DetalleCompra detalleCompraListOldDetalleCompra : detalleCompraListOld) {
                if (!detalleCompraListNew.contains(detalleCompraListOldDetalleCompra)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DetalleCompra " + detalleCompraListOldDetalleCompra + " since its reciboid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (proveedoridNew != null) {
                proveedoridNew = em.getReference(proveedoridNew.getClass(), proveedoridNew.getIdProveedor());
                reciboCompra.setProveedorid(proveedoridNew);
            }
            if (usuarioidUsuarioNew != null) {
                usuarioidUsuarioNew = em.getReference(usuarioidUsuarioNew.getClass(), usuarioidUsuarioNew.getIdUsuario());
                reciboCompra.setUsuarioidUsuario(usuarioidUsuarioNew);
            }
            List<DetalleCompra> attachedDetalleCompraListNew = new ArrayList<DetalleCompra>();
            for (DetalleCompra detalleCompraListNewDetalleCompraToAttach : detalleCompraListNew) {
                detalleCompraListNewDetalleCompraToAttach = em.getReference(detalleCompraListNewDetalleCompraToAttach.getClass(), detalleCompraListNewDetalleCompraToAttach.getId());
                attachedDetalleCompraListNew.add(detalleCompraListNewDetalleCompraToAttach);
            }
            detalleCompraListNew = attachedDetalleCompraListNew;
            reciboCompra.setDetalleCompraList(detalleCompraListNew);
            reciboCompra = em.merge(reciboCompra);
            if (proveedoridOld != null && !proveedoridOld.equals(proveedoridNew)) {
                proveedoridOld.getReciboCompraList().remove(reciboCompra);
                proveedoridOld = em.merge(proveedoridOld);
            }
            if (proveedoridNew != null && !proveedoridNew.equals(proveedoridOld)) {
                proveedoridNew.getReciboCompraList().add(reciboCompra);
                proveedoridNew = em.merge(proveedoridNew);
            }
            if (usuarioidUsuarioOld != null && !usuarioidUsuarioOld.equals(usuarioidUsuarioNew)) {
                usuarioidUsuarioOld.getReciboCompraList().remove(reciboCompra);
                usuarioidUsuarioOld = em.merge(usuarioidUsuarioOld);
            }
            if (usuarioidUsuarioNew != null && !usuarioidUsuarioNew.equals(usuarioidUsuarioOld)) {
                usuarioidUsuarioNew.getReciboCompraList().add(reciboCompra);
                usuarioidUsuarioNew = em.merge(usuarioidUsuarioNew);
            }
            for (DetalleCompra detalleCompraListNewDetalleCompra : detalleCompraListNew) {
                if (!detalleCompraListOld.contains(detalleCompraListNewDetalleCompra)) {
                    ReciboCompra oldReciboidOfDetalleCompraListNewDetalleCompra = detalleCompraListNewDetalleCompra.getReciboid();
                    detalleCompraListNewDetalleCompra.setReciboid(reciboCompra);
                    detalleCompraListNewDetalleCompra = em.merge(detalleCompraListNewDetalleCompra);
                    if (oldReciboidOfDetalleCompraListNewDetalleCompra != null && !oldReciboidOfDetalleCompraListNewDetalleCompra.equals(reciboCompra)) {
                        oldReciboidOfDetalleCompraListNewDetalleCompra.getDetalleCompraList().remove(detalleCompraListNewDetalleCompra);
                        oldReciboidOfDetalleCompraListNewDetalleCompra = em.merge(oldReciboidOfDetalleCompraListNewDetalleCompra);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = reciboCompra.getIdRecibo();
                if (findReciboCompra(id) == null) {
                    throw new NonexistentEntityException("The reciboCompra with id " + id + " no longer exists.");
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
            ReciboCompra reciboCompra;
            try {
                reciboCompra = em.getReference(ReciboCompra.class, id);
                reciboCompra.getIdRecibo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reciboCompra with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<DetalleCompra> detalleCompraListOrphanCheck = reciboCompra.getDetalleCompraList();
            for (DetalleCompra detalleCompraListOrphanCheckDetalleCompra : detalleCompraListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ReciboCompra (" + reciboCompra + ") cannot be destroyed since the DetalleCompra " + detalleCompraListOrphanCheckDetalleCompra + " in its detalleCompraList field has a non-nullable reciboid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Proveedor proveedorid = reciboCompra.getProveedorid();
            if (proveedorid != null) {
                proveedorid.getReciboCompraList().remove(reciboCompra);
                proveedorid = em.merge(proveedorid);
            }
            Usuario usuarioidUsuario = reciboCompra.getUsuarioidUsuario();
            if (usuarioidUsuario != null) {
                usuarioidUsuario.getReciboCompraList().remove(reciboCompra);
                usuarioidUsuario = em.merge(usuarioidUsuario);
            }
            em.remove(reciboCompra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ReciboCompra> findReciboCompraEntities() {
        return findReciboCompraEntities(true, -1, -1);
    }

    public List<ReciboCompra> findReciboCompraEntities(int maxResults, int firstResult) {
        return findReciboCompraEntities(false, maxResults, firstResult);
    }

    private List<ReciboCompra> findReciboCompraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ReciboCompra.class));
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

    public ReciboCompra findReciboCompra(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ReciboCompra.class, id);
        } finally {
            em.close();
        }
    }

    public int getReciboCompraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ReciboCompra> rt = cq.from(ReciboCompra.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
