package models;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author krach
 */
public class FuncionesCarrito {
    private JList<Productos> listaCarrito;

    public FuncionesCarrito(JList<Productos> listaCarrito) {
        this.listaCarrito = listaCarrito;
    }

    // Método para comprar los productos del carrito y generar el ticket
    public Tickets comprar() {
        DefaultListModel<Productos> modeloCarrito = (DefaultListModel<Productos>) listaCarrito.getModel();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dawfoodbd");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        Tickets ticket = new Tickets();
        ticket.setCodtpv(Metodos.EnconTpvPorId(1));
        ticket.setCodtransaccion(99);
        ticket.setFechahoraticket(new Date());
        double totalConIva = 0;
        
        for (int i = 0; i < modeloCarrito.size(); i++) {
            Productos producto = modeloCarrito.getElementAt(i);
            totalConIva += obtenerPrecioConIva(producto);
        }
        
        ticket.setPreciofinal(totalConIva);
        
        
        em.persist(ticket);
        
        for (int i = 0; i < modeloCarrito.size(); i++) {
            Productos producto = modeloCarrito.getElementAt(i);
            Detalleticket detalle = new Detalleticket();
            detalle.setProductos(producto);
            detalle.setCantidad(1); // Hasta solucionarlo una unidad del producto
            detalle.setTickets(ticket);
            em.persist(detalle);
        }
        
        em.getTransaction().commit();
        em.close();
        emf.close();
        
        // Limpiar el carrito después de la compra
        modeloCarrito.clear();
        
        return ticket;
    }
    
    
    // Método para obtener el precio con IVA de un producto
    public double obtenerPrecioConIva(Productos producto) {
        return producto.getPrecio() * (1 + producto.getIva());
    }
//    public double obtenerPrecioConIva(Productos producto) {
//        double precioConIva = producto.getPrecio() * (1 + producto.getIva());
//        // Formatear el resultado a dos decimales
//        DecimalFormat df = new DecimalFormat("#.##");
//        return Double.parseDouble(df.format(precioConIva));
//    }

}
