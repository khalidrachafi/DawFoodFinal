/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author khalid
 */
@Embeddable
public class DetalleTicketPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idProductos")
    private int idProductos;
    @Basic(optional = false)
    @Column(name = "idTickets")
    private int idTickets;

    public DetalleTicketPK() {
    }

    public DetalleTicketPK(int idProductos, int idTickets) {
        this.idProductos = idProductos;
        this.idTickets = idTickets;
    }

    public int getIdProductos() {
        return idProductos;
    }

    public void setIdProductos(int idProductos) {
        this.idProductos = idProductos;
    }

    public int getIdTickets() {
        return idTickets;
    }

    public void setIdTickets(int idTickets) {
        this.idTickets = idTickets;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idProductos;
        hash += (int) idTickets;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleTicketPK)) {
            return false;
        }
        DetalleTicketPK other = (DetalleTicketPK) object;
        if (this.idProductos != other.idProductos) {
            return false;
        }
        if (this.idTickets != other.idTickets) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.DetalleTicketPK[ idProductos=" + idProductos + ", idTickets=" + idTickets + " ]";
    }
    
}
