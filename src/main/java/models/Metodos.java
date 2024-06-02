/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import controllers.DetalleticketJpaController;
import controllers.ProductosJpaController;
import controllers.TicketsJpaController;
import controllers.TipoproductoJpaController;
import controllers.TpvJpaController;
import controllers.exceptions.IllegalOrphanException;
import controllers.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

/**
 *
 * @author khalid
 */
public class Metodos {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("dawfoodbd");
    private static final DetalleticketJpaController dc = new DetalleticketJpaController(emf);
    private static final TpvJpaController vc = new TpvJpaController(emf);
    private static final TipoproductoJpaController tc = new TipoproductoJpaController(emf);
    private static final ProductosJpaController pc = new ProductosJpaController(emf);
    private static final TicketsJpaController kc = new TicketsJpaController(emf);

    private static void crearProducto(Productos producto) {
        pc.create(producto);
    }

    private static void crearTipo(Tipoproducto tipoproducto) {
        tc.create(tipoproducto);
    }

    public static void ProductoCreado(String nombre, String descripcion, double iva, int stock, double precio, String categoria, String nombreTipo) {

        if (categoria.equalsIgnoreCase("Comida")) {
            Tipoproducto t1 = new Tipoproducto(nombreTipo, "Comida");
            Productos p1 = new Productos(iva, stock, descripcion, precio, nombre, t1);

            crearTipo(t1);

            crearProducto(p1);
        } else if (categoria.equalsIgnoreCase("Bebida")) {
            Tipoproducto t1 = new Tipoproducto(nombreTipo, "Bebida");
            Productos p1 = new Productos(iva, stock, descripcion, precio, nombre, t1);

            crearTipo(t1);

            crearProducto(p1);
        } else if (categoria.equalsIgnoreCase("Postre")) {
            Tipoproducto t1 = new Tipoproducto(nombreTipo, "Postre");
            Productos p1 = new Productos(iva, stock, descripcion, precio, nombre, t1);

            crearTipo(t1);

            crearProducto(p1);
        } else {
            JOptionPane.showMessageDialog(null, "Tienes que introducir una categoria válida");
        }
    }

    public static void EliminarProd(int idProducto) throws IllegalOrphanException, NonexistentEntityException {
        pc.destroy(idProducto);
    }

    public static List<Productos> RecibirListaProd(String categoria) {
        return pc.findByCategoria(categoria);
    }

    public static void EditarProd(Productos producto) throws NonexistentEntityException, Exception {
        pc.edit(producto);
    }

    public static Productos EnconProductosPorId(int Idproducto) {
        return pc.findByIdproductos(Idproducto);
    }

    public static Tpv EnconTpvPorId(int codtpv) {
        return vc.findByIdproductos(codtpv);
    }

    public static List<Tickets> RecibirListaTick() {
        return kc.findAll();
    }

    public static List<Detalleticket> RecibirListaDetalle(int idTicket) {
        return dc.findByIdtickets(idTicket);
    }

    public static List<Productos> TodaListaProductos() {
        return pc.findAll();
    }

}
