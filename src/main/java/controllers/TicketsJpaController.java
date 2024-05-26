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
import models.Tpv;
import models.Detalleticket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import models.Tickets;

/**
 *
 * @author krach
 */
public class TicketsJpaController implements Serializable {

    public TicketsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tickets tickets) {
        if (tickets.getDetalleticketCollection() == null) {
            tickets.setDetalleticketCollection(new ArrayList<Detalleticket>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tpv codtpv = tickets.getCodtpv();
            if (codtpv != null) {
                codtpv = em.getReference(codtpv.getClass(), codtpv.getCodtpv());
                tickets.setCodtpv(codtpv);
            }
            Collection<Detalleticket> attachedDetalleticketCollection = new ArrayList<Detalleticket>();
            for (Detalleticket detalleticketCollectionDetalleticketToAttach : tickets.getDetalleticketCollection()) {
                detalleticketCollectionDetalleticketToAttach = em.getReference(detalleticketCollectionDetalleticketToAttach.getClass(), detalleticketCollectionDetalleticketToAttach.getDetalleticketPK());
                attachedDetalleticketCollection.add(detalleticketCollectionDetalleticketToAttach);
            }
            tickets.setDetalleticketCollection(attachedDetalleticketCollection);
            em.persist(tickets);
            if (codtpv != null) {
                codtpv.getTicketsCollection().add(tickets);
                codtpv = em.merge(codtpv);
            }
            for (Detalleticket detalleticketCollectionDetalleticket : tickets.getDetalleticketCollection()) {
                Tickets oldTicketsOfDetalleticketCollectionDetalleticket = detalleticketCollectionDetalleticket.getTickets();
                detalleticketCollectionDetalleticket.setTickets(tickets);
                detalleticketCollectionDetalleticket = em.merge(detalleticketCollectionDetalleticket);
                if (oldTicketsOfDetalleticketCollectionDetalleticket != null) {
                    oldTicketsOfDetalleticketCollectionDetalleticket.getDetalleticketCollection().remove(detalleticketCollectionDetalleticket);
                    oldTicketsOfDetalleticketCollectionDetalleticket = em.merge(oldTicketsOfDetalleticketCollectionDetalleticket);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tickets tickets) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tickets persistentTickets = em.find(Tickets.class, tickets.getIdtickets());
            Tpv codtpvOld = persistentTickets.getCodtpv();
            Tpv codtpvNew = tickets.getCodtpv();
            Collection<Detalleticket> detalleticketCollectionOld = persistentTickets.getDetalleticketCollection();
            Collection<Detalleticket> detalleticketCollectionNew = tickets.getDetalleticketCollection();
            List<String> illegalOrphanMessages = null;
            for (Detalleticket detalleticketCollectionOldDetalleticket : detalleticketCollectionOld) {
                if (!detalleticketCollectionNew.contains(detalleticketCollectionOldDetalleticket)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleticket " + detalleticketCollectionOldDetalleticket + " since its tickets field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (codtpvNew != null) {
                codtpvNew = em.getReference(codtpvNew.getClass(), codtpvNew.getCodtpv());
                tickets.setCodtpv(codtpvNew);
            }
            Collection<Detalleticket> attachedDetalleticketCollectionNew = new ArrayList<Detalleticket>();
            for (Detalleticket detalleticketCollectionNewDetalleticketToAttach : detalleticketCollectionNew) {
                detalleticketCollectionNewDetalleticketToAttach = em.getReference(detalleticketCollectionNewDetalleticketToAttach.getClass(), detalleticketCollectionNewDetalleticketToAttach.getDetalleticketPK());
                attachedDetalleticketCollectionNew.add(detalleticketCollectionNewDetalleticketToAttach);
            }
            detalleticketCollectionNew = attachedDetalleticketCollectionNew;
            tickets.setDetalleticketCollection(detalleticketCollectionNew);
            tickets = em.merge(tickets);
            if (codtpvOld != null && !codtpvOld.equals(codtpvNew)) {
                codtpvOld.getTicketsCollection().remove(tickets);
                codtpvOld = em.merge(codtpvOld);
            }
            if (codtpvNew != null && !codtpvNew.equals(codtpvOld)) {
                codtpvNew.getTicketsCollection().add(tickets);
                codtpvNew = em.merge(codtpvNew);
            }
            for (Detalleticket detalleticketCollectionNewDetalleticket : detalleticketCollectionNew) {
                if (!detalleticketCollectionOld.contains(detalleticketCollectionNewDetalleticket)) {
                    Tickets oldTicketsOfDetalleticketCollectionNewDetalleticket = detalleticketCollectionNewDetalleticket.getTickets();
                    detalleticketCollectionNewDetalleticket.setTickets(tickets);
                    detalleticketCollectionNewDetalleticket = em.merge(detalleticketCollectionNewDetalleticket);
                    if (oldTicketsOfDetalleticketCollectionNewDetalleticket != null && !oldTicketsOfDetalleticketCollectionNewDetalleticket.equals(tickets)) {
                        oldTicketsOfDetalleticketCollectionNewDetalleticket.getDetalleticketCollection().remove(detalleticketCollectionNewDetalleticket);
                        oldTicketsOfDetalleticketCollectionNewDetalleticket = em.merge(oldTicketsOfDetalleticketCollectionNewDetalleticket);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tickets.getIdtickets();
                if (findTickets(id) == null) {
                    throw new NonexistentEntityException("The tickets with id " + id + " no longer exists.");
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
            Tickets tickets;
            try {
                tickets = em.getReference(Tickets.class, id);
                tickets.getIdtickets();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tickets with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Detalleticket> detalleticketCollectionOrphanCheck = tickets.getDetalleticketCollection();
            for (Detalleticket detalleticketCollectionOrphanCheckDetalleticket : detalleticketCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tickets (" + tickets + ") cannot be destroyed since the Detalleticket " + detalleticketCollectionOrphanCheckDetalleticket + " in its detalleticketCollection field has a non-nullable tickets field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Tpv codtpv = tickets.getCodtpv();
            if (codtpv != null) {
                codtpv.getTicketsCollection().remove(tickets);
                codtpv = em.merge(codtpv);
            }
            em.remove(tickets);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tickets> findTicketsEntities() {
        return findTicketsEntities(true, -1, -1);
    }

    public List<Tickets> findTicketsEntities(int maxResults, int firstResult) {
        return findTicketsEntities(false, maxResults, firstResult);
    }

    private List<Tickets> findTicketsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tickets.class));
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

    public Tickets findTickets(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tickets.class, id);
        } finally {
            em.close();
        }
    }

    public int getTicketsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tickets> rt = cq.from(Tickets.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
