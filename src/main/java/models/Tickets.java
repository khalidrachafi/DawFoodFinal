/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author khalid
 */
@Entity
@Table(name = "Tickets")
@NamedQueries({
    @NamedQuery(name = "Tickets.findAll", query = "SELECT t FROM Tickets t"),
    @NamedQuery(name = "Tickets.findByIdTickets", query = "SELECT t FROM Tickets t WHERE t.idTickets = :idTickets"),
    @NamedQuery(name = "Tickets.findByCodTransaccion", query = "SELECT t FROM Tickets t WHERE t.codTransaccion = :codTransaccion"),
    @NamedQuery(name = "Tickets.findByPrecioFinal", query = "SELECT t FROM Tickets t WHERE t.precioFinal = :precioFinal"),
    @NamedQuery(name = "Tickets.findByFechaHoraTicket", query = "SELECT t FROM Tickets t WHERE t.fechaHoraTicket = :fechaHoraTicket")})
public class Tickets implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTickets")
    private Integer idTickets;
    @Basic(optional = false)
    @Column(name = "codTransaccion")
    private int codTransaccion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precioFinal")
    private BigDecimal precioFinal;
    @Column(name = "fechaHoraTicket")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraTicket;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tickets")
    private Collection<DetalleTicket> detalleTicketCollection;
    @JoinColumn(name = "codTpv", referencedColumnName = "codTpv")
    @ManyToOne(optional = false)
    private Tpv codTpv;

    public Tickets() {
    }

    public Tickets(Integer idTickets) {
        this.idTickets = idTickets;
    }

    public Tickets(Integer idTickets, int codTransaccion) {
        this.idTickets = idTickets;
        this.codTransaccion = codTransaccion;
    }

    public Integer getIdTickets() {
        return idTickets;
    }

    public void setIdTickets(Integer idTickets) {
        this.idTickets = idTickets;
    }

    public int getCodTransaccion() {
        return codTransaccion;
    }

    public void setCodTransaccion(int codTransaccion) {
        this.codTransaccion = codTransaccion;
    }

    public BigDecimal getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(BigDecimal precioFinal) {
        this.precioFinal = precioFinal;
    }

    public Date getFechaHoraTicket() {
        return fechaHoraTicket;
    }

    public void setFechaHoraTicket(Date fechaHoraTicket) {
        this.fechaHoraTicket = fechaHoraTicket;
    }

    public Collection<DetalleTicket> getDetalleTicketCollection() {
        return detalleTicketCollection;
    }

    public void setDetalleTicketCollection(Collection<DetalleTicket> detalleTicketCollection) {
        this.detalleTicketCollection = detalleTicketCollection;
    }

    public Tpv getCodTpv() {
        return codTpv;
    }

    public void setCodTpv(Tpv codTpv) {
        this.codTpv = codTpv;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTickets != null ? idTickets.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tickets)) {
            return false;
        }
        Tickets other = (Tickets) object;
        if ((this.idTickets == null && other.idTickets != null) || (this.idTickets != null && !this.idTickets.equals(other.idTickets))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Tickets[ idTickets=" + idTickets + " ]";
    }
    
}
