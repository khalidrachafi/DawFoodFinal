package models;

import controllers.DetalleticketJpaController;
import controllers.ProductosJpaController;
import controllers.TicketsJpaController;
import controllers.TipoproductoJpaController;
import controllers.TpvJpaController;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.Map;


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

    // Método para comprar los productos del carrito y generar el ticket
    public void comprar() throws Exception {
        
        
        Tickets ticket = new Tickets();
        ticket.setCodtpv(Metodos.EnconTpvPorId(1));
        ticket.setCodtransaccion(99);
        ticket.setFechahoraticket(new Date());
        double totalConIva = 0;

        for (Map.Entry<Productos, Integer> entry : carrito.entrySet()) {
            Productos producto = entry.getKey();
            int cantidad = entry.getValue();
            totalConIva += obtenerPrecioConIva(producto) * cantidad;
        }

        ticket.setPreciofinal(totalConIva);

        kc.create(ticket);
        

        for (Map.Entry<Productos, Integer> entry : carrito.entrySet()) {
            Productos producto = entry.getKey();
            int cantidad = entry.getValue();
             DetalleticketPK dk = new DetalleticketPK(producto.getIdproductos(),
            ticket.getIdtickets());
            Detalleticket detalle = new Detalleticket(dk, cantidad, producto, ticket);
//            detalle.setDetalleticketPK(dk);
//            detalle.setTickets(ticket);
//            detalle.setProductos(producto);
//            detalle.setCantidad(cantidad);        
            dc.create(detalle);
        }

        // Limpiar el carrito después de la compra
        carrito.clear();

    }

    // Método para obtener el precio con IVA de un producto
    public double obtenerPrecioConIva(Productos producto) {
        return producto.getPrecio() * (1 + producto.getIva());
    }
}
