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
        
    }
    
    
    
   public void añadiorProducto (){
//       Productos p1 = new Productos(0.15, 50,"pizza rica mucho", 15.00, "pizza frembuesa", 1);
//      // pc.create(productos);



   } 
   
    
    
}
