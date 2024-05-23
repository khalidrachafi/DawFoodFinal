/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import models.DetalleTicket;
import models.DetalleTicketPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import models.Productos;
import models.Tickets;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author khalid
 */
public class DetalleTicketJpaController implements Serializable {

    public DetalleTicketJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DetalleTicket detalleTicket) throws PreexistingEntityException, Exception {
        if (detalleTicket.getDetalleTicketPK() == null) {
            detalleTicket.setDetalleTicketPK(new DetalleTicketPK());
        }
        detalleTicket.getDetalleTicketPK().setIdTickets(detalleTicket.getTickets().getIdTickets());
        detalleTicket.getDetalleTicketPK().setIdProductos(detalleTicket.getProductos().getIdProductos());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Productos productos = detalleTicket.getProductos();
            if (productos != null) {
                productos = em.getReference(productos.getClass(), productos.getIdProductos());
                detalleTicket.setProductos(productos);
            }
            Tickets tickets = detalleTicket.getTickets();
            if (tickets != null) {
                tickets = em.getReference(tickets.getClass(), tickets.getIdTickets());
                detalleTicket.setTickets(tickets);
            }
            em.persist(detalleTicket);
            if (productos != null) {
                productos.getDetalleTicketCollection().add(detalleTicket);
                productos = em.merge(productos);
            }
            if (tickets != null) {
                tickets.getDetalleTicketCollection().add(detalleTicket);
                tickets = em.merge(tickets);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetalleTicket(detalleTicket.getDetalleTicketPK()) != null) {
                throw new PreexistingEntityException("DetalleTicket " + detalleTicket + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DetalleTicket detalleTicket) throws NonexistentEntityException, Exception {
        detalleTicket.getDetalleTicketPK().setIdTickets(detalleTicket.getTickets().getIdTickets());
        detalleTicket.getDetalleTicketPK().setIdProductos(detalleTicket.getProductos().getIdProductos());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetalleTicket persistentDetalleTicket = em.find(DetalleTicket.class, detalleTicket.getDetalleTicketPK());
            Productos productosOld = persistentDetalleTicket.getProductos();
            Productos productosNew = detalleTicket.getProductos();
            Tickets ticketsOld = persistentDetalleTicket.getTickets();
            Tickets ticketsNew = detalleTicket.getTickets();
            if (productosNew != null) {
                productosNew = em.getReference(productosNew.getClass(), productosNew.getIdProductos());
                detalleTicket.setProductos(productosNew);
            }
            if (ticketsNew != null) {
                ticketsNew = em.getReference(ticketsNew.getClass(), ticketsNew.getIdTickets());
                detalleTicket.setTickets(ticketsNew);
            }
            detalleTicket = em.merge(detalleTicket);
            if (productosOld != null && !productosOld.equals(productosNew)) {
                productosOld.getDetalleTicketCollection().remove(detalleTicket);
                productosOld = em.merge(productosOld);
            }
            if (productosNew != null && !productosNew.equals(productosOld)) {
                productosNew.getDetalleTicketCollection().add(detalleTicket);
                productosNew = em.merge(productosNew);
            }
            if (ticketsOld != null && !ticketsOld.equals(ticketsNew)) {
                ticketsOld.getDetalleTicketCollection().remove(detalleTicket);
                ticketsOld = em.merge(ticketsOld);
            }
            if (ticketsNew != null && !ticketsNew.equals(ticketsOld)) {
                ticketsNew.getDetalleTicketCollection().add(detalleTicket);
                ticketsNew = em.merge(ticketsNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DetalleTicketPK id = detalleTicket.getDetalleTicketPK();
                if (findDetalleTicket(id) == null) {
                    throw new NonexistentEntityException("The detalleTicket with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DetalleTicketPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetalleTicket detalleTicket;
            try {
                detalleTicket = em.getReference(DetalleTicket.class, id);
                detalleTicket.getDetalleTicketPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleTicket with id " + id + " no longer exists.", enfe);
            }
            Productos productos = detalleTicket.getProductos();
            if (productos != null) {
                productos.getDetalleTicketCollection().remove(detalleTicket);
                productos = em.merge(productos);
            }
            Tickets tickets = detalleTicket.getTickets();
            if (tickets != null) {
                tickets.getDetalleTicketCollection().remove(detalleTicket);
                tickets = em.merge(tickets);
            }
            em.remove(detalleTicket);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DetalleTicket> findDetalleTicketEntities() {
        return findDetalleTicketEntities(true, -1, -1);
    }

    public List<DetalleTicket> findDetalleTicketEntities(int maxResults, int firstResult) {
        return findDetalleTicketEntities(false, maxResults, firstResult);
    }

    private List<DetalleTicket> findDetalleTicketEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DetalleTicket.class));
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

    public DetalleTicket findDetalleTicket(DetalleTicketPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DetalleTicket.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleTicketCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DetalleTicket> rt = cq.from(DetalleTicket.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
