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
@Table(name = "tickets")
@NamedQueries({
    @NamedQuery(name = "Tickets.findAll", query = "SELECT t FROM Tickets t"),
    @NamedQuery(name = "Tickets.findByIdtickets", query = "SELECT t FROM Tickets t WHERE t.idtickets = :idtickets"),
    @NamedQuery(name = "Tickets.findByCodtransaccion", query = "SELECT t FROM Tickets t WHERE t.codtransaccion = :codtransaccion"),
    @NamedQuery(name = "Tickets.findByPreciofinal", query = "SELECT t FROM Tickets t WHERE t.preciofinal = :preciofinal"),
    @NamedQuery(name = "Tickets.findByFechahoraticket", query = "SELECT t FROM Tickets t WHERE t.fechahoraticket = :fechahoraticket")})
public class Tickets implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtickets")
    private int idtickets;
    @Basic(optional = false)
    @Column(name = "codtransaccion")
    private int codtransaccion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "preciofinal")
    private double preciofinal;
    @Column(name = "fechahoraticket")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechahoraticket;
    @JoinColumn(name = "codtpv", referencedColumnName = "codtpv")
    @ManyToOne(optional = false)
    private Tpv codtpv;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tickets")
    private Collection<Detalleticket> detalleticketCollection;

    public Tickets() {
    }

    public Tickets(int idtickets) {
        this.idtickets = idtickets;
    }

    public Tickets(int idtickets, int codtransaccion) {
        this.idtickets = idtickets;
        this.codtransaccion = codtransaccion;
    }

    public int getIdtickets() {
        return idtickets;
    }

    public void setIdtickets(int idtickets) {
        this.idtickets = idtickets;
    }

    public int getCodtransaccion() {
        return codtransaccion;
    }

    public void setCodtransaccion(int codtransaccion) {
        this.codtransaccion = codtransaccion;
    }

    public double getPreciofinal() {
        return preciofinal;
    }

    public void setPreciofinal(double preciofinal) {
        this.preciofinal = preciofinal;
    }

    public Date getFechahoraticket() {
        return fechahoraticket;
    }

    public void setFechahoraticket(Date fechahoraticket) {
        this.fechahoraticket = fechahoraticket;
    }

    public Tpv getCodtpv() {
        return codtpv;
    }

    public void setCodtpv(Tpv codtpv) {
        this.codtpv = codtpv;
    }

    public Collection<Detalleticket> getDetalleticketCollection() {
        return detalleticketCollection;
    }

    public void setDetalleticketCollection(Collection<Detalleticket> detalleticketCollection) {
        this.detalleticketCollection = detalleticketCollection;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.idtickets;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tickets other = (Tickets) obj;
        return this.idtickets == other.idtickets;
    }

    

    @Override
    public String toString() {
        return "models.Tickets[ idtickets=" + idtickets + " ]";
    }
    
}
