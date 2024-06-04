package models;

import controllers.DetalleticketJpaController;
import controllers.ProductosJpaController;
import controllers.TicketsJpaController;
import controllers.TipoproductoJpaController;
import controllers.TpvJpaController;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @autor krach
 */
public class FuncionesCarrito {

    private Map<Productos, Integer> carrito;
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("dawfoodbd");
    private static final DetalleticketJpaController dc = new DetalleticketJpaController(emf);
    private static final TicketsJpaController kc = new TicketsJpaController(emf);

    public FuncionesCarrito(Map<Productos, Integer> carrito) {
        this.carrito = carrito;
    }

    // Método para comprar los productos del carrito y generar el ticket y el detaleticket
    public void comprar() throws Exception {

        Tickets ticket = new Tickets();
        ticket.setCodtpv(Metodos.EnconTpvPorId(1)); //ponemos por defecto el 1
        ticket.setCodtransaccion(99); //ponemos por defecto 99
        ticket.setFechahoraticket(new Date());
        double totalConIva = 0;

        for (Map.Entry<Productos, Integer> entry : carrito.entrySet()) {
            Productos producto = entry.getKey();
            int cantidad = entry.getValue();
            totalConIva += obtenerPrecioConIva(producto) * cantidad;
        }

        ticket.setPreciofinal(totalConIva);
        
        

        kc.create(ticket);

        Collection<Detalleticket> detallesTicket = new ArrayList<>();

        ticket.setDetalleticketCollection(detallesTicket);

        for (Map.Entry<Productos, Integer> entry : carrito.entrySet()) {
            Productos producto = entry.getKey();
            int cantidad = entry.getValue();
            DetalleticketPK dk = new DetalleticketPK(producto.getIdproductos(),
                    ticket.getIdtickets());
            Detalleticket detalle = new Detalleticket(dk, cantidad, producto, ticket);
            
            dc.create(detalle);

            detallesTicket.add(detalle);
            
//            if ((producto.getStock() - cantidad) < 0) {
//                JOptionPane.showMessageDialog(null, "Error: no hay suficiente stock");
//            }
        }
        
        

        kc.edit(ticket);

        // Limpiar el carrito después de la compra
        carrito.clear();

    }

    // Método para obtener el precio con IVA de un producto
    public double obtenerPrecioConIva(Productos producto) {
        return producto.getPrecio() * (1 + producto.getIva());
    }
}
