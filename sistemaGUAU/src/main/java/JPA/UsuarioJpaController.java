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
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        if (usuario.getFacturaList() == null) {
            usuario.setFacturaList(new ArrayList<Factura>());
        }
        if (usuario.getReciboCompraList() == null) {
            usuario.setReciboCompraList(new ArrayList<ReciboCompra>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Factura> attachedFacturaList = new ArrayList<Factura>();
            for (Factura facturaListFacturaToAttach : usuario.getFacturaList()) {
                facturaListFacturaToAttach = em.getReference(facturaListFacturaToAttach.getClass(), facturaListFacturaToAttach.getIdFactura());
                attachedFacturaList.add(facturaListFacturaToAttach);
            }
            usuario.setFacturaList(attachedFacturaList);
            List<ReciboCompra> attachedReciboCompraList = new ArrayList<ReciboCompra>();
            for (ReciboCompra reciboCompraListReciboCompraToAttach : usuario.getReciboCompraList()) {
                reciboCompraListReciboCompraToAttach = em.getReference(reciboCompraListReciboCompraToAttach.getClass(), reciboCompraListReciboCompraToAttach.getIdRecibo());
                attachedReciboCompraList.add(reciboCompraListReciboCompraToAttach);
            }
            usuario.setReciboCompraList(attachedReciboCompraList);
            em.persist(usuario);
            for (Factura facturaListFactura : usuario.getFacturaList()) {
                Usuario oldUsuarioidUsuarioOfFacturaListFactura = facturaListFactura.getUsuarioidUsuario();
                facturaListFactura.setUsuarioidUsuario(usuario);
                facturaListFactura = em.merge(facturaListFactura);
                if (oldUsuarioidUsuarioOfFacturaListFactura != null) {
                    oldUsuarioidUsuarioOfFacturaListFactura.getFacturaList().remove(facturaListFactura);
                    oldUsuarioidUsuarioOfFacturaListFactura = em.merge(oldUsuarioidUsuarioOfFacturaListFactura);
                }
            }
            for (ReciboCompra reciboCompraListReciboCompra : usuario.getReciboCompraList()) {
                Usuario oldUsuarioidUsuarioOfReciboCompraListReciboCompra = reciboCompraListReciboCompra.getUsuarioidUsuario();
                reciboCompraListReciboCompra.setUsuarioidUsuario(usuario);
                reciboCompraListReciboCompra = em.merge(reciboCompraListReciboCompra);
                if (oldUsuarioidUsuarioOfReciboCompraListReciboCompra != null) {
                    oldUsuarioidUsuarioOfReciboCompraListReciboCompra.getReciboCompraList().remove(reciboCompraListReciboCompra);
                    oldUsuarioidUsuarioOfReciboCompraListReciboCompra = em.merge(oldUsuarioidUsuarioOfReciboCompraListReciboCompra);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getIdUsuario());
            List<Factura> facturaListOld = persistentUsuario.getFacturaList();
            List<Factura> facturaListNew = usuario.getFacturaList();
            List<ReciboCompra> reciboCompraListOld = persistentUsuario.getReciboCompraList();
            List<ReciboCompra> reciboCompraListNew = usuario.getReciboCompraList();
            List<String> illegalOrphanMessages = null;
            for (Factura facturaListOldFactura : facturaListOld) {
                if (!facturaListNew.contains(facturaListOldFactura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Factura " + facturaListOldFactura + " since its usuarioidUsuario field is not nullable.");
                }
            }
            for (ReciboCompra reciboCompraListOldReciboCompra : reciboCompraListOld) {
                if (!reciboCompraListNew.contains(reciboCompraListOldReciboCompra)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ReciboCompra " + reciboCompraListOldReciboCompra + " since its usuarioidUsuario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Factura> attachedFacturaListNew = new ArrayList<Factura>();
            for (Factura facturaListNewFacturaToAttach : facturaListNew) {
                facturaListNewFacturaToAttach = em.getReference(facturaListNewFacturaToAttach.getClass(), facturaListNewFacturaToAttach.getIdFactura());
                attachedFacturaListNew.add(facturaListNewFacturaToAttach);
            }
            facturaListNew = attachedFacturaListNew;
            usuario.setFacturaList(facturaListNew);
            List<ReciboCompra> attachedReciboCompraListNew = new ArrayList<ReciboCompra>();
            for (ReciboCompra reciboCompraListNewReciboCompraToAttach : reciboCompraListNew) {
                reciboCompraListNewReciboCompraToAttach = em.getReference(reciboCompraListNewReciboCompraToAttach.getClass(), reciboCompraListNewReciboCompraToAttach.getIdRecibo());
                attachedReciboCompraListNew.add(reciboCompraListNewReciboCompraToAttach);
            }
            reciboCompraListNew = attachedReciboCompraListNew;
            usuario.setReciboCompraList(reciboCompraListNew);
            usuario = em.merge(usuario);
            for (Factura facturaListNewFactura : facturaListNew) {
                if (!facturaListOld.contains(facturaListNewFactura)) {
                    Usuario oldUsuarioidUsuarioOfFacturaListNewFactura = facturaListNewFactura.getUsuarioidUsuario();
                    facturaListNewFactura.setUsuarioidUsuario(usuario);
                    facturaListNewFactura = em.merge(facturaListNewFactura);
                    if (oldUsuarioidUsuarioOfFacturaListNewFactura != null && !oldUsuarioidUsuarioOfFacturaListNewFactura.equals(usuario)) {
                        oldUsuarioidUsuarioOfFacturaListNewFactura.getFacturaList().remove(facturaListNewFactura);
                        oldUsuarioidUsuarioOfFacturaListNewFactura = em.merge(oldUsuarioidUsuarioOfFacturaListNewFactura);
                    }
                }
            }
            for (ReciboCompra reciboCompraListNewReciboCompra : reciboCompraListNew) {
                if (!reciboCompraListOld.contains(reciboCompraListNewReciboCompra)) {
                    Usuario oldUsuarioidUsuarioOfReciboCompraListNewReciboCompra = reciboCompraListNewReciboCompra.getUsuarioidUsuario();
                    reciboCompraListNewReciboCompra.setUsuarioidUsuario(usuario);
                    reciboCompraListNewReciboCompra = em.merge(reciboCompraListNewReciboCompra);
                    if (oldUsuarioidUsuarioOfReciboCompraListNewReciboCompra != null && !oldUsuarioidUsuarioOfReciboCompraListNewReciboCompra.equals(usuario)) {
                        oldUsuarioidUsuarioOfReciboCompraListNewReciboCompra.getReciboCompraList().remove(reciboCompraListNewReciboCompra);
                        oldUsuarioidUsuarioOfReciboCompraListNewReciboCompra = em.merge(oldUsuarioidUsuarioOfReciboCompraListNewReciboCompra);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getIdUsuario();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Factura> facturaListOrphanCheck = usuario.getFacturaList();
            for (Factura facturaListOrphanCheckFactura : facturaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Factura " + facturaListOrphanCheckFactura + " in its facturaList field has a non-nullable usuarioidUsuario field.");
            }
            List<ReciboCompra> reciboCompraListOrphanCheck = usuario.getReciboCompraList();
            for (ReciboCompra reciboCompraListOrphanCheckReciboCompra : reciboCompraListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the ReciboCompra " + reciboCompraListOrphanCheckReciboCompra + " in its reciboCompraList field has a non-nullable usuarioidUsuario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
