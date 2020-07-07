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
public class FacturaJpaController implements Serializable {

    public FacturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Factura factura) {
        if (factura.getDetalleFacturaList() == null) {
            factura.setDetalleFacturaList(new ArrayList<DetalleFactura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente clienteid = factura.getClienteid();
            if (clienteid != null) {
                clienteid = em.getReference(clienteid.getClass(), clienteid.getIdCliente());
                factura.setClienteid(clienteid);
            }
            Usuario usuarioidUsuario = factura.getUsuarioidUsuario();
            if (usuarioidUsuario != null) {
                usuarioidUsuario = em.getReference(usuarioidUsuario.getClass(), usuarioidUsuario.getIdUsuario());
                factura.setUsuarioidUsuario(usuarioidUsuario);
            }
            List<DetalleFactura> attachedDetalleFacturaList = new ArrayList<DetalleFactura>();
            for (DetalleFactura detalleFacturaListDetalleFacturaToAttach : factura.getDetalleFacturaList()) {
                detalleFacturaListDetalleFacturaToAttach = em.getReference(detalleFacturaListDetalleFacturaToAttach.getClass(), detalleFacturaListDetalleFacturaToAttach.getId());
                attachedDetalleFacturaList.add(detalleFacturaListDetalleFacturaToAttach);
            }
            factura.setDetalleFacturaList(attachedDetalleFacturaList);
            em.persist(factura);
            if (clienteid != null) {
                clienteid.getFacturaList().add(factura);
                clienteid = em.merge(clienteid);
            }
            if (usuarioidUsuario != null) {
                usuarioidUsuario.getFacturaList().add(factura);
                usuarioidUsuario = em.merge(usuarioidUsuario);
            }
            for (DetalleFactura detalleFacturaListDetalleFactura : factura.getDetalleFacturaList()) {
                Factura oldFacturaidOfDetalleFacturaListDetalleFactura = detalleFacturaListDetalleFactura.getFacturaid();
                detalleFacturaListDetalleFactura.setFacturaid(factura);
                detalleFacturaListDetalleFactura = em.merge(detalleFacturaListDetalleFactura);
                if (oldFacturaidOfDetalleFacturaListDetalleFactura != null) {
                    oldFacturaidOfDetalleFacturaListDetalleFactura.getDetalleFacturaList().remove(detalleFacturaListDetalleFactura);
                    oldFacturaidOfDetalleFacturaListDetalleFactura = em.merge(oldFacturaidOfDetalleFacturaListDetalleFactura);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Factura factura) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Factura persistentFactura = em.find(Factura.class, factura.getIdFactura());
            Cliente clienteidOld = persistentFactura.getClienteid();
            Cliente clienteidNew = factura.getClienteid();
            Usuario usuarioidUsuarioOld = persistentFactura.getUsuarioidUsuario();
            Usuario usuarioidUsuarioNew = factura.getUsuarioidUsuario();
            List<DetalleFactura> detalleFacturaListOld = persistentFactura.getDetalleFacturaList();
            List<DetalleFactura> detalleFacturaListNew = factura.getDetalleFacturaList();
            List<String> illegalOrphanMessages = null;
            for (DetalleFactura detalleFacturaListOldDetalleFactura : detalleFacturaListOld) {
                if (!detalleFacturaListNew.contains(detalleFacturaListOldDetalleFactura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DetalleFactura " + detalleFacturaListOldDetalleFactura + " since its facturaid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (clienteidNew != null) {
                clienteidNew = em.getReference(clienteidNew.getClass(), clienteidNew.getIdCliente());
                factura.setClienteid(clienteidNew);
            }
            if (usuarioidUsuarioNew != null) {
                usuarioidUsuarioNew = em.getReference(usuarioidUsuarioNew.getClass(), usuarioidUsuarioNew.getIdUsuario());
                factura.setUsuarioidUsuario(usuarioidUsuarioNew);
            }
            List<DetalleFactura> attachedDetalleFacturaListNew = new ArrayList<DetalleFactura>();
            for (DetalleFactura detalleFacturaListNewDetalleFacturaToAttach : detalleFacturaListNew) {
                detalleFacturaListNewDetalleFacturaToAttach = em.getReference(detalleFacturaListNewDetalleFacturaToAttach.getClass(), detalleFacturaListNewDetalleFacturaToAttach.getId());
                attachedDetalleFacturaListNew.add(detalleFacturaListNewDetalleFacturaToAttach);
            }
            detalleFacturaListNew = attachedDetalleFacturaListNew;
            factura.setDetalleFacturaList(detalleFacturaListNew);
            factura = em.merge(factura);
            if (clienteidOld != null && !clienteidOld.equals(clienteidNew)) {
                clienteidOld.getFacturaList().remove(factura);
                clienteidOld = em.merge(clienteidOld);
            }
            if (clienteidNew != null && !clienteidNew.equals(clienteidOld)) {
                clienteidNew.getFacturaList().add(factura);
                clienteidNew = em.merge(clienteidNew);
            }
            if (usuarioidUsuarioOld != null && !usuarioidUsuarioOld.equals(usuarioidUsuarioNew)) {
                usuarioidUsuarioOld.getFacturaList().remove(factura);
                usuarioidUsuarioOld = em.merge(usuarioidUsuarioOld);
            }
            if (usuarioidUsuarioNew != null && !usuarioidUsuarioNew.equals(usuarioidUsuarioOld)) {
                usuarioidUsuarioNew.getFacturaList().add(factura);
                usuarioidUsuarioNew = em.merge(usuarioidUsuarioNew);
            }
            for (DetalleFactura detalleFacturaListNewDetalleFactura : detalleFacturaListNew) {
                if (!detalleFacturaListOld.contains(detalleFacturaListNewDetalleFactura)) {
                    Factura oldFacturaidOfDetalleFacturaListNewDetalleFactura = detalleFacturaListNewDetalleFactura.getFacturaid();
                    detalleFacturaListNewDetalleFactura.setFacturaid(factura);
                    detalleFacturaListNewDetalleFactura = em.merge(detalleFacturaListNewDetalleFactura);
                    if (oldFacturaidOfDetalleFacturaListNewDetalleFactura != null && !oldFacturaidOfDetalleFacturaListNewDetalleFactura.equals(factura)) {
                        oldFacturaidOfDetalleFacturaListNewDetalleFactura.getDetalleFacturaList().remove(detalleFacturaListNewDetalleFactura);
                        oldFacturaidOfDetalleFacturaListNewDetalleFactura = em.merge(oldFacturaidOfDetalleFacturaListNewDetalleFactura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = factura.getIdFactura();
                if (findFactura(id) == null) {
                    throw new NonexistentEntityException("The factura with id " + id + " no longer exists.");
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
            Factura factura;
            try {
                factura = em.getReference(Factura.class, id);
                factura.getIdFactura();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The factura with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<DetalleFactura> detalleFacturaListOrphanCheck = factura.getDetalleFacturaList();
            for (DetalleFactura detalleFacturaListOrphanCheckDetalleFactura : detalleFacturaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Factura (" + factura + ") cannot be destroyed since the DetalleFactura " + detalleFacturaListOrphanCheckDetalleFactura + " in its detalleFacturaList field has a non-nullable facturaid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Cliente clienteid = factura.getClienteid();
            if (clienteid != null) {
                clienteid.getFacturaList().remove(factura);
                clienteid = em.merge(clienteid);
            }
            Usuario usuarioidUsuario = factura.getUsuarioidUsuario();
            if (usuarioidUsuario != null) {
                usuarioidUsuario.getFacturaList().remove(factura);
                usuarioidUsuario = em.merge(usuarioidUsuario);
            }
            em.remove(factura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Factura> findFacturaEntities() {
        return findFacturaEntities(true, -1, -1);
    }

    public List<Factura> findFacturaEntities(int maxResults, int firstResult) {
        return findFacturaEntities(false, maxResults, firstResult);
    }

    private List<Factura> findFacturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Factura.class));
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

    public Factura findFactura(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Factura.class, id);
        } finally {
            em.close();
        }
    }

    public int getFacturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Factura> rt = cq.from(Factura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
