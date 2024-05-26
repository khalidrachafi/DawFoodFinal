/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import controllers.exceptions.IllegalOrphanException;
import controllers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import models.Productos;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import models.Tipoproducto;

/**
 *
 * @author krach
 */
public class TipoproductoJpaController implements Serializable {

    public TipoproductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tipoproducto tipoproducto) {
        if (tipoproducto.getProductosCollection() == null) {
            tipoproducto.setProductosCollection(new ArrayList<Productos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Productos> attachedProductosCollection = new ArrayList<Productos>();
            for (Productos productosCollectionProductosToAttach : tipoproducto.getProductosCollection()) {
                productosCollectionProductosToAttach = em.getReference(productosCollectionProductosToAttach.getClass(), productosCollectionProductosToAttach.getIdproductos());
                attachedProductosCollection.add(productosCollectionProductosToAttach);
            }
            tipoproducto.setProductosCollection(attachedProductosCollection);
            em.persist(tipoproducto);
            for (Productos productosCollectionProductos : tipoproducto.getProductosCollection()) {
                Tipoproducto oldCodtipoproductoOfProductosCollectionProductos = productosCollectionProductos.getCodtipoproducto();
                productosCollectionProductos.setCodtipoproducto(tipoproducto);
                productosCollectionProductos = em.merge(productosCollectionProductos);
                if (oldCodtipoproductoOfProductosCollectionProductos != null) {
                    oldCodtipoproductoOfProductosCollectionProductos.getProductosCollection().remove(productosCollectionProductos);
                    oldCodtipoproductoOfProductosCollectionProductos = em.merge(oldCodtipoproductoOfProductosCollectionProductos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tipoproducto tipoproducto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipoproducto persistentTipoproducto = em.find(Tipoproducto.class, tipoproducto.getCodtipoproducto());
            Collection<Productos> productosCollectionOld = persistentTipoproducto.getProductosCollection();
            Collection<Productos> productosCollectionNew = tipoproducto.getProductosCollection();
            List<String> illegalOrphanMessages = null;
            for (Productos productosCollectionOldProductos : productosCollectionOld) {
                if (!productosCollectionNew.contains(productosCollectionOldProductos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Productos " + productosCollectionOldProductos + " since its codtipoproducto field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Productos> attachedProductosCollectionNew = new ArrayList<Productos>();
            for (Productos productosCollectionNewProductosToAttach : productosCollectionNew) {
                productosCollectionNewProductosToAttach = em.getReference(productosCollectionNewProductosToAttach.getClass(), productosCollectionNewProductosToAttach.getIdproductos());
                attachedProductosCollectionNew.add(productosCollectionNewProductosToAttach);
            }
            productosCollectionNew = attachedProductosCollectionNew;
            tipoproducto.setProductosCollection(productosCollectionNew);
            tipoproducto = em.merge(tipoproducto);
            for (Productos productosCollectionNewProductos : productosCollectionNew) {
                if (!productosCollectionOld.contains(productosCollectionNewProductos)) {
                    Tipoproducto oldCodtipoproductoOfProductosCollectionNewProductos = productosCollectionNewProductos.getCodtipoproducto();
                    productosCollectionNewProductos.setCodtipoproducto(tipoproducto);
                    productosCollectionNewProductos = em.merge(productosCollectionNewProductos);
                    if (oldCodtipoproductoOfProductosCollectionNewProductos != null && !oldCodtipoproductoOfProductosCollectionNewProductos.equals(tipoproducto)) {
                        oldCodtipoproductoOfProductosCollectionNewProductos.getProductosCollection().remove(productosCollectionNewProductos);
                        oldCodtipoproductoOfProductosCollectionNewProductos = em.merge(oldCodtipoproductoOfProductosCollectionNewProductos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = tipoproducto.getCodtipoproducto();
                if (findTipoproducto(id) == null) {
                    throw new NonexistentEntityException("The tipoproducto with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipoproducto tipoproducto;
            try {
                tipoproducto = em.getReference(Tipoproducto.class, id);
                tipoproducto.getCodtipoproducto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoproducto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Productos> productosCollectionOrphanCheck = tipoproducto.getProductosCollection();
            for (Productos productosCollectionOrphanCheckProductos : productosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tipoproducto (" + tipoproducto + ") cannot be destroyed since the Productos " + productosCollectionOrphanCheckProductos + " in its productosCollection field has a non-nullable codtipoproducto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipoproducto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tipoproducto> findTipoproductoEntities() {
        return findTipoproductoEntities(true, -1, -1);
    }

    public List<Tipoproducto> findTipoproductoEntities(int maxResults, int firstResult) {
        return findTipoproductoEntities(false, maxResults, firstResult);
    }

    private List<Tipoproducto> findTipoproductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tipoproducto.class));
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

    public Tipoproducto findTipoproducto(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tipoproducto.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoproductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tipoproducto> rt = cq.from(Tipoproducto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public Tipoproducto findByNombretipoprodcucto(String nombretipoprodcucto){
        EntityManager em = getEntityManager();
        // Se crea la query usando el nombre de la named query
        Query q = em.createNamedQuery("Tipoproducto.findByNombretipoprodcucto");
        // Se establece el parámetro de la consulta
        q.setParameter("nombretipoprodcucto", nombretipoprodcucto);
        return (Tipoproducto)q.getResultList();
    }
    
    
    public Tipoproducto findByCategoria(String categoria){
        EntityManager em = getEntityManager();
        // Se crea la query usando el nombre de la named query
        Query q = em.createNamedQuery("Tipoproducto.findByCategoria");
        // Se establece el parámetro de la consulta
        q.setParameter("categoria", categoria);
        return (Tipoproducto)q.getResultList();
    }
    
    
}
