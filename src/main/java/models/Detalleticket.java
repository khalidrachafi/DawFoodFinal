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
@Table(name = "detalleticket")
@NamedQueries({
    @NamedQuery(name = "Detalleticket.findAll", query = "SELECT d FROM Detalleticket d"),
    @NamedQuery(name = "Detalleticket.findByCantidad", query = "SELECT d FROM Detalleticket d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = " ", query = "SELECT d FROM Detalleticket d WHERE d.detalleticketPK.idproductos = :idproductos"),
    @NamedQuery(name = "Detalleticket.findByIdtickets", query = "SELECT d FROM Detalleticket d WHERE d.detalleticketPK.idtickets = :idtickets")})
public class Detalleticket implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetalleticketPK detalleticketPK;
    @Column(name = "cantidad")
    private int cantidad;
    @JoinColumn(name = "idproductos", referencedColumnName = "idproductos", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Productos productos;
    @JoinColumn(name = "idtickets", referencedColumnName = "idtickets", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Tickets tickets;

    public Detalleticket() {
    }

    public Detalleticket(DetalleticketPK detalleticketPK) {
        this.detalleticketPK = detalleticketPK;
    }

    public Detalleticket(int idproductos, int idtickets) {
        this.detalleticketPK = new DetalleticketPK(idproductos, idtickets);
    }

    public DetalleticketPK getDetalleticketPK() {
        return detalleticketPK;
    }

    public void setDetalleticketPK(DetalleticketPK detalleticketPK) {
        this.detalleticketPK = detalleticketPK;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
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
        hash += (detalleticketPK != null ? detalleticketPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalleticket)) {
            return false;
        }
        Detalleticket other = (Detalleticket) object;
        if ((this.detalleticketPK == null && other.detalleticketPK != null) || (this.detalleticketPK != null && !this.detalleticketPK.equals(other.detalleticketPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Detalleticket[ detalleticketPK=" + detalleticketPK + " ]";
    }
    
}
