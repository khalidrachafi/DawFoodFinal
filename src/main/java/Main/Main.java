/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import controllers.DetalleticketJpaController;
import controllers.ProductosJpaController;
import controllers.TicketsJpaController;
import controllers.TipoproductoJpaController;
import controllers.TpvJpaController;
import java.math.BigDecimal;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import models.Detalleticket;
import models.Productos;
import models.Tickets;
import models.Tipoproducto;

/**
 *
 * @author krach
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("dawfoodbd");
    private static final DetalleticketJpaController dc = new DetalleticketJpaController(emf);
    private static final TpvJpaController vc = new TpvJpaController(emf);
    private static final TipoproductoJpaController tc = new TipoproductoJpaController(emf);
    private static final ProductosJpaController pc = new ProductosJpaController(emf);
    private static final TicketsJpaController kc = new TicketsJpaController(emf);
    
    public static void main(String[] args) {
        // TODO code application logic here
//        Productos p1 = new Productos(0.15, 50, "hola mi pizza", 12.50, "pizza fria", 1);
//        pc.create(p1);
        
        
        /*----
         */       
                
//        public Detalleticket findAll(){
//        EntityManager em = getEntityManager();
//        // Se crea la query usando el nombre de la named query
//        Query q = em.createNamedQuery("Detalleticket.findAll");
//
//        return (Detalleticket)q.getResultList();
//    }
//    
//    
//    public Detalleticket findByIdproductos(String idproductos){
//        EntityManager em = getEntityManager();
//        // Se crea la query usando el nombre de la named query
//        Query q = em.createNamedQuery("Detalleticket.findByIdproductos");
//        // Se establece el parámetro de la consulta
//        q.setParameter("idproductos", idproductos);
//        return (Detalleticket)q.getResultList();
//    }
//    
//    
//    public Detalleticket findByIdtickets(String idtickets){
//        EntityManager em = getEntityManager();
//        // Se crea la query usando el nombre de la named query
//        Query q = em.createNamedQuery("Detalleticket.findByIdtickets");
//        // Se establece el parámetro de la consulta
//        q.setParameter("idtickets", idtickets);
//        return (Detalleticket)q.getResultList();
//    }
//    
//    public Productos findByIdproductos(int idproductos){
//        EntityManager em = getEntityManager();
//        // Se crea la query usando el nombre de la named query
//        Query q = em.createNamedQuery("Productos.findByIdproductos");
//        // Se establece el parámetro de la consulta
//        q.setParameter("idproductos", idproductos);
//        return (Productos) q.getSingleResult();
//    }
//    
//    public Tickets findAll(){
//        EntityManager em = getEntityManager();
//        // Se crea la query usando el nombre de la named query
//        Query q = em.createNamedQuery("Tickets.findAll");
//
//        return (Tickets)q.getResultList();
//    }
//    
//    public Tipoproducto findByCodtipoproducto(int codtipoproducto){
//        EntityManager em = getEntityManager();
//        // Se crea la query usando el nombre de la named query
//        Query q = em.createNamedQuery("Tipoproducto.findByCodtipoproducto");
//        // Se establece el parámetro de la consulta
//        q.setParameter("codtipoproducto", codtipoproducto);
//        return (Tipoproducto) q.getResultList();
//    }
//    
//    
//    public Tipoproducto findByCategoria(String categoria){
//        EntityManager em = getEntityManager();
//        // Se crea la query usando el nombre de la named query
//        Query q = em.createNamedQuery("Tipoproducto.findByCategoria");
//        // Se establece el parámetro de la consulta
//        q.setParameter("categoria", categoria);
//        return (Tipoproducto)q.getResultList();
//    }
//    
//    public Tipoproducto findByNombretipoprodcucto(String nombretipoprodcucto){
//        EntityManager em = getEntityManager();
//        // Se crea la query usando el nombre de la named query
//        Query q = em.createNamedQuery("Tipoproducto.findByNombretipoprodcucto");
//        // Se establece el parámetro de la consulta
//        q.setParameter("nombretipoprodcucto", nombretipoprodcucto);
//        return (Tipoproducto)q.getResultList();
//    }
                
                
        /*----
        */
    }
    
    
    
   
    
    
}
