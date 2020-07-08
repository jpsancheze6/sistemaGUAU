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
public class ProveedorJpaController implements Serializable {

    public ProveedorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Proveedor proveedor) {
        if (proveedor.getReciboCompraList() == null) {
            proveedor.setReciboCompraList(new ArrayList<ReciboCompra>());
        }
        if (proveedor.getProductoList() == null) {
            proveedor.setProductoList(new ArrayList<Producto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<ReciboCompra> attachedReciboCompraList = new ArrayList<ReciboCompra>();
            for (ReciboCompra reciboCompraListReciboCompraToAttach : proveedor.getReciboCompraList()) {
                reciboCompraListReciboCompraToAttach = em.getReference(reciboCompraListReciboCompraToAttach.getClass(), reciboCompraListReciboCompraToAttach.getIdRecibo());
                attachedReciboCompraList.add(reciboCompraListReciboCompraToAttach);
            }
            proveedor.setReciboCompraList(attachedReciboCompraList);
            List<Producto> attachedProductoList = new ArrayList<Producto>();
            for (Producto productoListProductoToAttach : proveedor.getProductoList()) {
                productoListProductoToAttach = em.getReference(productoListProductoToAttach.getClass(), productoListProductoToAttach.getIdProducto());
                attachedProductoList.add(productoListProductoToAttach);
            }
            proveedor.setProductoList(attachedProductoList);
            em.persist(proveedor);
            for (ReciboCompra reciboCompraListReciboCompra : proveedor.getReciboCompraList()) {
                Proveedor oldProveedoridOfReciboCompraListReciboCompra = reciboCompraListReciboCompra.getProveedorid();
                reciboCompraListReciboCompra.setProveedorid(proveedor);
                reciboCompraListReciboCompra = em.merge(reciboCompraListReciboCompra);
                if (oldProveedoridOfReciboCompraListReciboCompra != null) {
                    oldProveedoridOfReciboCompraListReciboCompra.getReciboCompraList().remove(reciboCompraListReciboCompra);
                    oldProveedoridOfReciboCompraListReciboCompra = em.merge(oldProveedoridOfReciboCompraListReciboCompra);
                }
            }
            for (Producto productoListProducto : proveedor.getProductoList()) {
                Proveedor oldProveedoridOfProductoListProducto = productoListProducto.getProveedorid();
                productoListProducto.setProveedorid(proveedor);
                productoListProducto = em.merge(productoListProducto);
                if (oldProveedoridOfProductoListProducto != null) {
                    oldProveedoridOfProductoListProducto.getProductoList().remove(productoListProducto);
                    oldProveedoridOfProductoListProducto = em.merge(oldProveedoridOfProductoListProducto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Proveedor proveedor) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedor persistentProveedor = em.find(Proveedor.class, proveedor.getIdProveedor());
            List<ReciboCompra> reciboCompraListOld = persistentProveedor.getReciboCompraList();
            List<ReciboCompra> reciboCompraListNew = proveedor.getReciboCompraList();
            List<Producto> productoListOld = persistentProveedor.getProductoList();
            List<Producto> productoListNew = proveedor.getProductoList();
            List<String> illegalOrphanMessages = null;
            for (ReciboCompra reciboCompraListOldReciboCompra : reciboCompraListOld) {
                if (!reciboCompraListNew.contains(reciboCompraListOldReciboCompra)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ReciboCompra " + reciboCompraListOldReciboCompra + " since its proveedorid field is not nullable.");
                }
            }
            for (Producto productoListOldProducto : productoListOld) {
                if (!productoListNew.contains(productoListOldProducto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Producto " + productoListOldProducto + " since its proveedorid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<ReciboCompra> attachedReciboCompraListNew = new ArrayList<ReciboCompra>();
            for (ReciboCompra reciboCompraListNewReciboCompraToAttach : reciboCompraListNew) {
                reciboCompraListNewReciboCompraToAttach = em.getReference(reciboCompraListNewReciboCompraToAttach.getClass(), reciboCompraListNewReciboCompraToAttach.getIdRecibo());
                attachedReciboCompraListNew.add(reciboCompraListNewReciboCompraToAttach);
            }
            reciboCompraListNew = attachedReciboCompraListNew;
            proveedor.setReciboCompraList(reciboCompraListNew);
            List<Producto> attachedProductoListNew = new ArrayList<Producto>();
            for (Producto productoListNewProductoToAttach : productoListNew) {
                productoListNewProductoToAttach = em.getReference(productoListNewProductoToAttach.getClass(), productoListNewProductoToAttach.getIdProducto());
                attachedProductoListNew.add(productoListNewProductoToAttach);
            }
            productoListNew = attachedProductoListNew;
            proveedor.setProductoList(productoListNew);
            proveedor = em.merge(proveedor);
            for (ReciboCompra reciboCompraListNewReciboCompra : reciboCompraListNew) {
                if (!reciboCompraListOld.contains(reciboCompraListNewReciboCompra)) {
                    Proveedor oldProveedoridOfReciboCompraListNewReciboCompra = reciboCompraListNewReciboCompra.getProveedorid();
                    reciboCompraListNewReciboCompra.setProveedorid(proveedor);
                    reciboCompraListNewReciboCompra = em.merge(reciboCompraListNewReciboCompra);
                    if (oldProveedoridOfReciboCompraListNewReciboCompra != null && !oldProveedoridOfReciboCompraListNewReciboCompra.equals(proveedor)) {
                        oldProveedoridOfReciboCompraListNewReciboCompra.getReciboCompraList().remove(reciboCompraListNewReciboCompra);
                        oldProveedoridOfReciboCompraListNewReciboCompra = em.merge(oldProveedoridOfReciboCompraListNewReciboCompra);
                    }
                }
            }
            for (Producto productoListNewProducto : productoListNew) {
                if (!productoListOld.contains(productoListNewProducto)) {
                    Proveedor oldProveedoridOfProductoListNewProducto = productoListNewProducto.getProveedorid();
                    productoListNewProducto.setProveedorid(proveedor);
                    productoListNewProducto = em.merge(productoListNewProducto);
                    if (oldProveedoridOfProductoListNewProducto != null && !oldProveedoridOfProductoListNewProducto.equals(proveedor)) {
                        oldProveedoridOfProductoListNewProducto.getProductoList().remove(productoListNewProducto);
                        oldProveedoridOfProductoListNewProducto = em.merge(oldProveedoridOfProductoListNewProducto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = proveedor.getIdProveedor();
                if (findProveedor(id) == null) {
                    throw new NonexistentEntityException("The proveedor with id " + id + " no longer exists.");
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
            Proveedor proveedor;
            try {
                proveedor = em.getReference(Proveedor.class, id);
                proveedor.getIdProveedor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proveedor with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<ReciboCompra> reciboCompraListOrphanCheck = proveedor.getReciboCompraList();
            for (ReciboCompra reciboCompraListOrphanCheckReciboCompra : reciboCompraListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Proveedor (" + proveedor + ") cannot be destroyed since the ReciboCompra " + reciboCompraListOrphanCheckReciboCompra + " in its reciboCompraList field has a non-nullable proveedorid field.");
            }
            List<Producto> productoListOrphanCheck = proveedor.getProductoList();
            for (Producto productoListOrphanCheckProducto : productoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Proveedor (" + proveedor + ") cannot be destroyed since the Producto " + productoListOrphanCheckProducto + " in its productoList field has a non-nullable proveedorid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(proveedor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Proveedor> findProveedorEntities() {
        return findProveedorEntities(true, -1, -1);
    }

    public List<Proveedor> findProveedorEntities(int maxResults, int firstResult) {
        return findProveedorEntities(false, maxResults, firstResult);
    }

    private List<Proveedor> findProveedorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Proveedor.class));
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

    public Proveedor findProveedor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Proveedor.class, id);
        } finally {
            em.close();
        }
    }

    public int getProveedorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Proveedor> rt = cq.from(Proveedor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
