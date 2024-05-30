/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import models.Detalleticket;
import models.DetalleticketPK;
import models.Productos;
import models.Tickets;

/**
 *
 * @author krach
 */
public class DetalleticketJpaController implements Serializable {

    public DetalleticketJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detalleticket detalleticket) throws PreexistingEntityException, Exception {
        if (detalleticket.getDetalleticketPK() == null) {
            detalleticket.setDetalleticketPK(new DetalleticketPK());
        }
        detalleticket.getDetalleticketPK().setIdproductos(detalleticket.getProductos().getIdproductos());
        detalleticket.getDetalleticketPK().setIdtickets(detalleticket.getTickets().getIdtickets());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Productos productos = detalleticket.getProductos();
            if (productos != null) {
                productos = em.getReference(productos.getClass(), productos.getIdproductos());
                detalleticket.setProductos(productos);
            }
            Tickets tickets = detalleticket.getTickets();
            if (tickets != null) {
                tickets = em.getReference(tickets.getClass(), tickets.getIdtickets());
                detalleticket.setTickets(tickets);
            }
            em.persist(detalleticket);
            if (productos != null) {
                productos.getDetalleticketCollection().add(detalleticket);
                productos = em.merge(productos);
            }
            if (tickets != null) {
                tickets.getDetalleticketCollection().add(detalleticket);
                tickets = em.merge(tickets);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetalleticket(detalleticket.getDetalleticketPK()) != null) {
                throw new PreexistingEntityException("Detalleticket " + detalleticket + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detalleticket detalleticket) throws NonexistentEntityException, Exception {
        detalleticket.getDetalleticketPK().setIdproductos(detalleticket.getProductos().getIdproductos());
        detalleticket.getDetalleticketPK().setIdtickets(detalleticket.getTickets().getIdtickets());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleticket persistentDetalleticket = em.find(Detalleticket.class, detalleticket.getDetalleticketPK());
            Productos productosOld = persistentDetalleticket.getProductos();
            Productos productosNew = detalleticket.getProductos();
            Tickets ticketsOld = persistentDetalleticket.getTickets();
            Tickets ticketsNew = detalleticket.getTickets();
            if (productosNew != null) {
                productosNew = em.getReference(productosNew.getClass(), productosNew.getIdproductos());
                detalleticket.setProductos(productosNew);
            }
            if (ticketsNew != null) {
                ticketsNew = em.getReference(ticketsNew.getClass(), ticketsNew.getIdtickets());
                detalleticket.setTickets(ticketsNew);
            }
            detalleticket = em.merge(detalleticket);
            if (productosOld != null && !productosOld.equals(productosNew)) {
                productosOld.getDetalleticketCollection().remove(detalleticket);
                productosOld = em.merge(productosOld);
            }
            if (productosNew != null && !productosNew.equals(productosOld)) {
                productosNew.getDetalleticketCollection().add(detalleticket);
                productosNew = em.merge(productosNew);
            }
            if (ticketsOld != null && !ticketsOld.equals(ticketsNew)) {
                ticketsOld.getDetalleticketCollection().remove(detalleticket);
                ticketsOld = em.merge(ticketsOld);
            }
            if (ticketsNew != null && !ticketsNew.equals(ticketsOld)) {
                ticketsNew.getDetalleticketCollection().add(detalleticket);
                ticketsNew = em.merge(ticketsNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DetalleticketPK id = detalleticket.getDetalleticketPK();
                if (findDetalleticket(id) == null) {
                    throw new NonexistentEntityException("The detalleticket with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DetalleticketPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleticket detalleticket;
            try {
                detalleticket = em.getReference(Detalleticket.class, id);
                detalleticket.getDetalleticketPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleticket with id " + id + " no longer exists.", enfe);
            }
            Productos productos = detalleticket.getProductos();
            if (productos != null) {
                productos.getDetalleticketCollection().remove(detalleticket);
                productos = em.merge(productos);
            }
            Tickets tickets = detalleticket.getTickets();
            if (tickets != null) {
                tickets.getDetalleticketCollection().remove(detalleticket);
                tickets = em.merge(tickets);
            }
            em.remove(detalleticket);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detalleticket> findDetalleticketEntities() {
        return findDetalleticketEntities(true, -1, -1);
    }

    public List<Detalleticket> findDetalleticketEntities(int maxResults, int firstResult) {
        return findDetalleticketEntities(false, maxResults, firstResult);
    }

    private List<Detalleticket> findDetalleticketEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detalleticket.class));
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

    public Detalleticket findDetalleticket(DetalleticketPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detalleticket.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleticketCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detalleticket> rt = cq.from(Detalleticket.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    
    public Detalleticket findAll(){
        EntityManager em = getEntityManager();
        // Se crea la query usando el nombre de la named query
        Query q = em.createNamedQuery("Detalleticket.findAll");

        return (Detalleticket)q.getResultList();
    }
    
    
    public Detalleticket findByIdproductos(String idproductos){
        EntityManager em = getEntityManager();
        // Se crea la query usando el nombre de la named query
        Query q = em.createNamedQuery("Detalleticket.findByIdproductos");
        // Se establece el parámetro de la consulta
        q.setParameter("idproductos", idproductos);
        return (Detalleticket)q.getResultList();
    }
    
    
    public List<Detalleticket> findByIdtickets(int idtickets){
        EntityManager em = getEntityManager();
        // Se crea la query usando el nombre de la named query
        Query q = em.createNamedQuery("Detalleticket.findByIdtickets");
        // Se establece el parámetro de la consulta
        q.setParameter("idtickets", idtickets);
        return q.getResultList();
    }
    
    
}
