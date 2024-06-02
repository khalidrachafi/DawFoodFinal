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
import controllers.exceptions.IllegalOrphanException;
import controllers.exceptions.NonexistentEntityException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import models.Detalleticket;
import models.Productos;
import models.Tickets;
import models.Tipoproducto;
import views.VentanaPrincipal;

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

    public static void main(String[] args) throws IllegalOrphanException, NonexistentEntityException {

        new VentanaPrincipal().setVisible(true);

//        Productos p2 = new Productos(0.10, 50, "gg", 50.00, "pizza", new Tipoproducto(1));
//        pc.create(p2);
        // Tipoproducto pizza = new Tipoproducto(1);
//        System.out.println(pc.findByCategoria("postre"));
////        pc.findByCategoria("comida");
//    //    System.out.println(pc.findByCategoria("Postre"));
//        if (dc.findByIdproductos(6).isEmpty() || dc.findByIdproductos(6) == null) {
//            System.out.println("Hola bb");
//        }else{
//            System.out.println("no funciona");
//        }
//        System.out.println(  dc.findByIdproductos(1));
    }

}
