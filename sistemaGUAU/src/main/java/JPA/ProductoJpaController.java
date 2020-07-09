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
public class ProductoJpaController implements Serializable {

    public ProductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Producto producto) {
        if (producto.getDetalleFacturaList() == null) {
            producto.setDetalleFacturaList(new ArrayList<DetalleFactura>());
        }
        if (producto.getDetalleCompraList() == null) {
            producto.setDetalleCompraList(new ArrayList<DetalleCompra>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedor proveedorid = producto.getProveedorid();
            if (proveedorid != null) {
                proveedorid = em.getReference(proveedorid.getClass(), proveedorid.getIdProveedor());
                producto.setProveedorid(proveedorid);
            }
            List<DetalleFactura> attachedDetalleFacturaList = new ArrayList<DetalleFactura>();
            for (DetalleFactura detalleFacturaListDetalleFacturaToAttach : producto.getDetalleFacturaList()) {
                detalleFacturaListDetalleFacturaToAttach = em.getReference(detalleFacturaListDetalleFacturaToAttach.getClass(), detalleFacturaListDetalleFacturaToAttach.getId());
                attachedDetalleFacturaList.add(detalleFacturaListDetalleFacturaToAttach);
            }
            producto.setDetalleFacturaList(attachedDetalleFacturaList);
            List<DetalleCompra> attachedDetalleCompraList = new ArrayList<DetalleCompra>();
            for (DetalleCompra detalleCompraListDetalleCompraToAttach : producto.getDetalleCompraList()) {
                detalleCompraListDetalleCompraToAttach = em.getReference(detalleCompraListDetalleCompraToAttach.getClass(), detalleCompraListDetalleCompraToAttach.getId());
                attachedDetalleCompraList.add(detalleCompraListDetalleCompraToAttach);
            }
            producto.setDetalleCompraList(attachedDetalleCompraList);
            em.persist(producto);
            if (proveedorid != null) {
                proveedorid.getProductoList().add(producto);
                proveedorid = em.merge(proveedorid);
            }
            for (DetalleFactura detalleFacturaListDetalleFactura : producto.getDetalleFacturaList()) {
                Producto oldProductoidOfDetalleFacturaListDetalleFactura = detalleFacturaListDetalleFactura.getProductoid();
                detalleFacturaListDetalleFactura.setProductoid(producto);
                detalleFacturaListDetalleFactura = em.merge(detalleFacturaListDetalleFactura);
                if (oldProductoidOfDetalleFacturaListDetalleFactura != null) {
                    oldProductoidOfDetalleFacturaListDetalleFactura.getDetalleFacturaList().remove(detalleFacturaListDetalleFactura);
                    oldProductoidOfDetalleFacturaListDetalleFactura = em.merge(oldProductoidOfDetalleFacturaListDetalleFactura);
                }
            }
            for (DetalleCompra detalleCompraListDetalleCompra : producto.getDetalleCompraList()) {
                Producto oldProductoidOfDetalleCompraListDetalleCompra = detalleCompraListDetalleCompra.getProductoid();
                detalleCompraListDetalleCompra.setProductoid(producto);
                detalleCompraListDetalleCompra = em.merge(detalleCompraListDetalleCompra);
                if (oldProductoidOfDetalleCompraListDetalleCompra != null) {
                    oldProductoidOfDetalleCompraListDetalleCompra.getDetalleCompraList().remove(detalleCompraListDetalleCompra);
                    oldProductoidOfDetalleCompraListDetalleCompra = em.merge(oldProductoidOfDetalleCompraListDetalleCompra);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Producto> findProductoPorNombre(String nombre) {
        EntityManager em = null;
        em = getEntityManager();
        em.getTransaction().begin();
        return (List<Producto>) em.createNamedQuery("Producto.buscarPorNombre").setParameter("nombre", nombre).getResultList();
    }

    public void edit(Producto producto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto persistentProducto = em.find(Producto.class, producto.getIdProducto());
            Proveedor proveedoridOld = persistentProducto.getProveedorid();
            Proveedor proveedoridNew = producto.getProveedorid();
            List<DetalleFactura> detalleFacturaListOld = persistentProducto.getDetalleFacturaList();
            List<DetalleFactura> detalleFacturaListNew = producto.getDetalleFacturaList();
            List<DetalleCompra> detalleCompraListOld = persistentProducto.getDetalleCompraList();
            List<DetalleCompra> detalleCompraListNew = producto.getDetalleCompraList();
            List<String> illegalOrphanMessages = null;
            for (DetalleFactura detalleFacturaListOldDetalleFactura : detalleFacturaListOld) {
                if (!detalleFacturaListNew.contains(detalleFacturaListOldDetalleFactura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DetalleFactura " + detalleFacturaListOldDetalleFactura + " since its productoid field is not nullable.");
                }
            }
            for (DetalleCompra detalleCompraListOldDetalleCompra : detalleCompraListOld) {
                if (!detalleCompraListNew.contains(detalleCompraListOldDetalleCompra)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DetalleCompra " + detalleCompraListOldDetalleCompra + " since its productoid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (proveedoridNew != null) {
                proveedoridNew = em.getReference(proveedoridNew.getClass(), proveedoridNew.getIdProveedor());
                producto.setProveedorid(proveedoridNew);
            }
            List<DetalleFactura> attachedDetalleFacturaListNew = new ArrayList<DetalleFactura>();
            for (DetalleFactura detalleFacturaListNewDetalleFacturaToAttach : detalleFacturaListNew) {
                detalleFacturaListNewDetalleFacturaToAttach = em.getReference(detalleFacturaListNewDetalleFacturaToAttach.getClass(), detalleFacturaListNewDetalleFacturaToAttach.getId());
                attachedDetalleFacturaListNew.add(detalleFacturaListNewDetalleFacturaToAttach);
            }
            detalleFacturaListNew = attachedDetalleFacturaListNew;
            producto.setDetalleFacturaList(detalleFacturaListNew);
            List<DetalleCompra> attachedDetalleCompraListNew = new ArrayList<DetalleCompra>();
            for (DetalleCompra detalleCompraListNewDetalleCompraToAttach : detalleCompraListNew) {
                detalleCompraListNewDetalleCompraToAttach = em.getReference(detalleCompraListNewDetalleCompraToAttach.getClass(), detalleCompraListNewDetalleCompraToAttach.getId());
                attachedDetalleCompraListNew.add(detalleCompraListNewDetalleCompraToAttach);
            }
            detalleCompraListNew = attachedDetalleCompraListNew;
            producto.setDetalleCompraList(detalleCompraListNew);
            producto = em.merge(producto);
            if (proveedoridOld != null && !proveedoridOld.equals(proveedoridNew)) {
                proveedoridOld.getProductoList().remove(producto);
                proveedoridOld = em.merge(proveedoridOld);
            }
            if (proveedoridNew != null && !proveedoridNew.equals(proveedoridOld)) {
                proveedoridNew.getProductoList().add(producto);
                proveedoridNew = em.merge(proveedoridNew);
            }
            for (DetalleFactura detalleFacturaListNewDetalleFactura : detalleFacturaListNew) {
                if (!detalleFacturaListOld.contains(detalleFacturaListNewDetalleFactura)) {
                    Producto oldProductoidOfDetalleFacturaListNewDetalleFactura = detalleFacturaListNewDetalleFactura.getProductoid();
                    detalleFacturaListNewDetalleFactura.setProductoid(producto);
                    detalleFacturaListNewDetalleFactura = em.merge(detalleFacturaListNewDetalleFactura);
                    if (oldProductoidOfDetalleFacturaListNewDetalleFactura != null && !oldProductoidOfDetalleFacturaListNewDetalleFactura.equals(producto)) {
                        oldProductoidOfDetalleFacturaListNewDetalleFactura.getDetalleFacturaList().remove(detalleFacturaListNewDetalleFactura);
                        oldProductoidOfDetalleFacturaListNewDetalleFactura = em.merge(oldProductoidOfDetalleFacturaListNewDetalleFactura);
                    }
                }
            }
            for (DetalleCompra detalleCompraListNewDetalleCompra : detalleCompraListNew) {
                if (!detalleCompraListOld.contains(detalleCompraListNewDetalleCompra)) {
                    Producto oldProductoidOfDetalleCompraListNewDetalleCompra = detalleCompraListNewDetalleCompra.getProductoid();
                    detalleCompraListNewDetalleCompra.setProductoid(producto);
                    detalleCompraListNewDetalleCompra = em.merge(detalleCompraListNewDetalleCompra);
                    if (oldProductoidOfDetalleCompraListNewDetalleCompra != null && !oldProductoidOfDetalleCompraListNewDetalleCompra.equals(producto)) {
                        oldProductoidOfDetalleCompraListNewDetalleCompra.getDetalleCompraList().remove(detalleCompraListNewDetalleCompra);
                        oldProductoidOfDetalleCompraListNewDetalleCompra = em.merge(oldProductoidOfDetalleCompraListNewDetalleCompra);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = producto.getIdProducto();
                if (findProducto(id) == null) {
                    throw new NonexistentEntityException("The producto with id " + id + " no longer exists.");
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
            Producto producto;
            try {
                producto = em.getReference(Producto.class, id);
                producto.getIdProducto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The producto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<DetalleFactura> detalleFacturaListOrphanCheck = producto.getDetalleFacturaList();
            for (DetalleFactura detalleFacturaListOrphanCheckDetalleFactura : detalleFacturaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Producto (" + producto + ") cannot be destroyed since the DetalleFactura " + detalleFacturaListOrphanCheckDetalleFactura + " in its detalleFacturaList field has a non-nullable productoid field.");
            }
            List<DetalleCompra> detalleCompraListOrphanCheck = producto.getDetalleCompraList();
            for (DetalleCompra detalleCompraListOrphanCheckDetalleCompra : detalleCompraListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Producto (" + producto + ") cannot be destroyed since the DetalleCompra " + detalleCompraListOrphanCheckDetalleCompra + " in its detalleCompraList field has a non-nullable productoid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Proveedor proveedorid = producto.getProveedorid();
            if (proveedorid != null) {
                proveedorid.getProductoList().remove(producto);
                proveedorid = em.merge(proveedorid);
            }
            em.remove(producto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Producto> findProductoEntities() {
        return findProductoEntities(true, -1, -1);
    }

    public List<Producto> findProductoEntities(int maxResults, int firstResult) {
        return findProductoEntities(false, maxResults, firstResult);
    }

    private List<Producto> findProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Producto.class));
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

    public Producto findProducto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Producto.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Producto> rt = cq.from(Producto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
