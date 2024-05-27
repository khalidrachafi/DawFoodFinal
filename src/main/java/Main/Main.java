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
import javax.swing.JOptionPane;
import models.Detalleticket;
import models.Productos;
import models.Tickets;
import models.Tipoproducto;
import views.ComprarMenu;
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
    
    public static void main(String[] args) {

        //new VentanaPrincipal().setVisible(true);
        
//        Productos p2 = new Productos(0.10, 50, "gg", 50.00, "pizza", new Tipoproducto(1));
//        pc.create(p2);
       // Tipoproducto pizza = new Tipoproducto(1);
       
        //System.out.println(pc.findByCategoria("postre"));
//        pc.findByCategoria("comida");

       ProductoCreado("coca", "cocafresca", 0.35, 40, 19.30, "bebida", "Refresco");
        
    }
    
    
    
    private static void crearProducto (Productos producto){
       pc.create(producto);
    }
    
    private static void crearTipo (Tipoproducto tipoproducto){
       tc.create(tipoproducto);
    }
    
    public static void ProductoCreado (String nombre,String descripcion, double iva, int stock, double precio, String categoria, String nombreTipo){
        
        
        if (categoria.equalsIgnoreCase("Comida")) {
          Tipoproducto t1 = new Tipoproducto(nombreTipo ,"Comida");  
        Productos p1 = new Productos(iva, stock, descripcion, precio, nombre, t1);
        if (tc.findByNombretipoprodcucto(nombreTipo) == null) {
            crearTipo(t1);
        }else{
        tc.findByNombretipoprodcucto(nombreTipo);
        }
        crearProducto(p1);
        }else if (categoria.equalsIgnoreCase("Bebida")) {
          Tipoproducto t1 = new Tipoproducto(nombreTipo, "Bebida"); 
        Productos p1 =  new Productos(iva, stock, descripcion, precio, nombre, t1);
         if (tc.findByNombretipoprodcucto(nombreTipo) == null) {
            crearTipo(t1);
        }else{
        tc.findByNombretipoprodcucto(nombreTipo);
        }
        crearProducto(p1);
        }else if (categoria.equalsIgnoreCase("Postre")) {
          Tipoproducto t1 = new Tipoproducto(nombreTipo, "Postre");  
        Productos p1 =  new Productos(iva, stock, descripcion, precio, nombre, t1);
         if (tc.findByNombretipoprodcucto(nombreTipo) == null) {
            crearTipo(t1);
        }else{
        tc.findByNombretipoprodcucto(nombreTipo);
        }
        crearProducto(p1);
        }else{
            JOptionPane.showMessageDialog(null, "Tienes que introducir una categoria válida");
        }
    }


    
    
}
