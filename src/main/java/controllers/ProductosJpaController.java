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
import models.Tipoproducto;
import models.Detalleticket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.swing.JOptionPane;
import models.Productos;

/**
 *
 * @author krach
 */
public class ProductosJpaController implements Serializable {

    public ProductosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Productos productos) {
        if (productos.getDetalleticketCollection() == null) {
            productos.setDetalleticketCollection(new ArrayList<Detalleticket>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipoproducto codtipoproducto = productos.getCodtipoproducto();
            if (codtipoproducto != null) {
                codtipoproducto = em.getReference(codtipoproducto.getClass(), codtipoproducto.getCodtipoproducto());
                productos.setCodtipoproducto(codtipoproducto);
            }
            Collection<Detalleticket> attachedDetalleticketCollection = new ArrayList<Detalleticket>();
            for (Detalleticket detalleticketCollectionDetalleticketToAttach : productos.getDetalleticketCollection()) {
                detalleticketCollectionDetalleticketToAttach = em.getReference(detalleticketCollectionDetalleticketToAttach.getClass(), detalleticketCollectionDetalleticketToAttach.getDetalleticketPK());
                attachedDetalleticketCollection.add(detalleticketCollectionDetalleticketToAttach);
            }
            productos.setDetalleticketCollection(attachedDetalleticketCollection);
            em.persist(productos);
            if (codtipoproducto != null) {
                codtipoproducto.getProductosCollection().add(productos);
                codtipoproducto = em.merge(codtipoproducto);
            }
            for (Detalleticket detalleticketCollectionDetalleticket : productos.getDetalleticketCollection()) {
                Productos oldProductosOfDetalleticketCollectionDetalleticket = detalleticketCollectionDetalleticket.getProductos();
                detalleticketCollectionDetalleticket.setProductos(productos);
                detalleticketCollectionDetalleticket = em.merge(detalleticketCollectionDetalleticket);
                if (oldProductosOfDetalleticketCollectionDetalleticket != null) {
                    oldProductosOfDetalleticketCollectionDetalleticket.getDetalleticketCollection().remove(detalleticketCollectionDetalleticket);
                    oldProductosOfDetalleticketCollectionDetalleticket = em.merge(oldProductosOfDetalleticketCollectionDetalleticket);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Productos productos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Productos persistentProductos = em.find(Productos.class, productos.getIdproductos());
            Tipoproducto codtipoproductoOld = persistentProductos.getCodtipoproducto();
            Tipoproducto codtipoproductoNew = productos.getCodtipoproducto();
            Collection<Detalleticket> detalleticketCollectionOld = persistentProductos.getDetalleticketCollection();
            Collection<Detalleticket> detalleticketCollectionNew = productos.getDetalleticketCollection();
            List<String> illegalOrphanMessages = null;
            for (Detalleticket detalleticketCollectionOldDetalleticket : detalleticketCollectionOld) {
                if (!detalleticketCollectionNew.contains(detalleticketCollectionOldDetalleticket)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleticket " + detalleticketCollectionOldDetalleticket + " since its productos field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (codtipoproductoNew != null) {
                codtipoproductoNew = em.getReference(codtipoproductoNew.getClass(), codtipoproductoNew.getCodtipoproducto());
                productos.setCodtipoproducto(codtipoproductoNew);
            }
            Collection<Detalleticket> attachedDetalleticketCollectionNew = new ArrayList<Detalleticket>();
            for (Detalleticket detalleticketCollectionNewDetalleticketToAttach : detalleticketCollectionNew) {
                detalleticketCollectionNewDetalleticketToAttach = em.getReference(detalleticketCollectionNewDetalleticketToAttach.getClass(), detalleticketCollectionNewDetalleticketToAttach.getDetalleticketPK());
                attachedDetalleticketCollectionNew.add(detalleticketCollectionNewDetalleticketToAttach);
            }
            detalleticketCollectionNew = attachedDetalleticketCollectionNew;
            productos.setDetalleticketCollection(detalleticketCollectionNew);
            productos = em.merge(productos);
            if (codtipoproductoOld != null && !codtipoproductoOld.equals(codtipoproductoNew)) {
                codtipoproductoOld.getProductosCollection().remove(productos);
                codtipoproductoOld = em.merge(codtipoproductoOld);
            }
            if (codtipoproductoNew != null && !codtipoproductoNew.equals(codtipoproductoOld)) {
                codtipoproductoNew.getProductosCollection().add(productos);
                codtipoproductoNew = em.merge(codtipoproductoNew);
            }
            for (Detalleticket detalleticketCollectionNewDetalleticket : detalleticketCollectionNew) {
                if (!detalleticketCollectionOld.contains(detalleticketCollectionNewDetalleticket)) {
                    Productos oldProductosOfDetalleticketCollectionNewDetalleticket = detalleticketCollectionNewDetalleticket.getProductos();
                    detalleticketCollectionNewDetalleticket.setProductos(productos);
                    detalleticketCollectionNewDetalleticket = em.merge(detalleticketCollectionNewDetalleticket);
                    if (oldProductosOfDetalleticketCollectionNewDetalleticket != null && !oldProductosOfDetalleticketCollectionNewDetalleticket.equals(productos)) {
                        oldProductosOfDetalleticketCollectionNewDetalleticket.getDetalleticketCollection().remove(detalleticketCollectionNewDetalleticket);
                        oldProductosOfDetalleticketCollectionNewDetalleticket = em.merge(oldProductosOfDetalleticketCollectionNewDetalleticket);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = productos.getIdproductos();
                if (findProductos(id) == null) {
                    throw new NonexistentEntityException("The productos with id " + id + " no longer exists.");
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
            Productos productos;
            try {
                productos = em.getReference(Productos.class, id);
                productos.getIdproductos();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Detalleticket> detalleticketCollectionOrphanCheck = productos.getDetalleticketCollection();
            for (Detalleticket detalleticketCollectionOrphanCheckDetalleticket : detalleticketCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Productos (" + productos + ") cannot be destroyed since the Detalleticket " + detalleticketCollectionOrphanCheckDetalleticket + " in its detalleticketCollection field has a non-nullable productos field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Tipoproducto codtipoproducto = productos.getCodtipoproducto();
            if (codtipoproducto != null) {
                codtipoproducto.getProductosCollection().remove(productos);
                codtipoproducto = em.merge(codtipoproducto);
            }
            em.remove(productos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Productos> findProductosEntities() {
        return findProductosEntities(true, -1, -1);
    }

    public List<Productos> findProductosEntities(int maxResults, int firstResult) {
        return findProductosEntities(false, maxResults, firstResult);
    }

    private List<Productos> findProductosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Productos.class));
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

    public Productos findProductos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Productos.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Productos> rt = cq.from(Productos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Productos findByIdproductos(int idproductos) {
        EntityManager em = getEntityManager();
        // Se crea la query usando el nombre de la named query
        Query q = em.createNamedQuery("Productos.findByIdproductos");
        // Se establece el parámetro de la consulta
        q.setParameter("idproductos", idproductos);
        return (Productos) q.getSingleResult();        
    }

    public List<Productos> findByCategoria(String categoria) {
        EntityManager em = getEntityManager();

        // Create the query using the named query
        Query q = em.createNamedQuery("Productos.findByCategoria");
        // Set the parameter of the query
        q.setParameter("categoria", categoria);
        // Return the result list cast to the appropriate type
        return q.getResultList();
    }

    public List<Productos> findAll() {
        EntityManager em = getEntityManager();

        // Create the query using the named query
        Query q = em.createNamedQuery("Productos.findAll");

        // Return the result list cast to the appropriate type
        return q.getResultList();
    }

}
