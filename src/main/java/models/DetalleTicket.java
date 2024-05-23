/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author khalid
 */
@Entity
@Table(name = "DetalleTicket")
@NamedQueries({
    @NamedQuery(name = "DetalleTicket.findAll", query = "SELECT d FROM DetalleTicket d"),
    @NamedQuery(name = "DetalleTicket.findByCantidad", query = "SELECT d FROM DetalleTicket d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "DetalleTicket.findByIdProductos", query = "SELECT d FROM DetalleTicket d WHERE d.detalleTicketPK.idProductos = :idProductos"),
    @NamedQuery(name = "DetalleTicket.findByIdTickets", query = "SELECT d FROM DetalleTicket d WHERE d.detalleTicketPK.idTickets = :idTickets")})
public class DetalleTicket implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetalleTicketPK detalleTicketPK;
    @Column(name = "cantidad")
    private Integer cantidad;
    @JoinColumn(name = "idProductos", referencedColumnName = "idProductos", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Productos productos;
    @JoinColumn(name = "idTickets", referencedColumnName = "idTickets", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Tickets tickets;

    public DetalleTicket() {
    }

    public DetalleTicket(DetalleTicketPK detalleTicketPK) {
        this.detalleTicketPK = detalleTicketPK;
    }

    public DetalleTicket(int idProductos, int idTickets) {
        this.detalleTicketPK = new DetalleTicketPK(idProductos, idTickets);
    }

    public DetalleTicketPK getDetalleTicketPK() {
        return detalleTicketPK;
    }

    public void setDetalleTicketPK(DetalleTicketPK detalleTicketPK) {
        this.detalleTicketPK = detalleTicketPK;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Productos getProductos() {
        return productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }

    public Tickets getTickets() {
        return tickets;
    }

    public void setTickets(Tickets tickets) {
        this.tickets = tickets;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleTicketPK != null ? detalleTicketPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleTicket)) {
            return false;
        }
        DetalleTicket other = (DetalleTicket) object;
        if ((this.detalleTicketPK == null && other.detalleTicketPK != null) || (this.detalleTicketPK != null && !this.detalleTicketPK.equals(other.detalleTicketPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.DetalleTicket[ detalleTicketPK=" + detalleTicketPK + " ]";
    }
    
}
