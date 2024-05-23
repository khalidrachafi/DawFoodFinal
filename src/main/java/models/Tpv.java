/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "Tpv")
@NamedQueries({
    @NamedQuery(name = "Tpv.findAll", query = "SELECT t FROM Tpv t"),
    @NamedQuery(name = "Tpv.findByCodTpv", query = "SELECT t FROM Tpv t WHERE t.codTpv = :codTpv"),
    @NamedQuery(name = "Tpv.findByPasswordAdmin", query = "SELECT t FROM Tpv t WHERE t.passwordAdmin = :passwordAdmin"),
    @NamedQuery(name = "Tpv.findByUbicacion", query = "SELECT t FROM Tpv t WHERE t.ubicacion = :ubicacion"),
    @NamedQuery(name = "Tpv.findByFechaHoraActual", query = "SELECT t FROM Tpv t WHERE t.fechaHoraActual = :fechaHoraActual")})
public class Tpv implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codTpv")
    private Integer codTpv;
    @Column(name = "passwordAdmin")
    private String passwordAdmin;
    @Column(name = "ubicacion")
    private String ubicacion;
    @Column(name = "fechaHoraActual")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraActual;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codTpv")
    private Collection<Tickets> ticketsCollection;

    public Tpv() {
    }

    public Tpv(Integer codTpv) {
        this.codTpv = codTpv;
    }

    public Integer getCodTpv() {
        return codTpv;
    }

    public void setCodTpv(Integer codTpv) {
        this.codTpv = codTpv;
    }

    public String getPasswordAdmin() {
        return passwordAdmin;
    }

    public void setPasswordAdmin(String passwordAdmin) {
        this.passwordAdmin = passwordAdmin;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Date getFechaHoraActual() {
        return fechaHoraActual;
    }

    public void setFechaHoraActual(Date fechaHoraActual) {
        this.fechaHoraActual = fechaHoraActual;
    }

    public Collection<Tickets> getTicketsCollection() {
        return ticketsCollection;
    }

    public void setTicketsCollection(Collection<Tickets> ticketsCollection) {
        this.ticketsCollection = ticketsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codTpv != null ? codTpv.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tpv)) {
            return false;
        }
        Tpv other = (Tpv) object;
        if ((this.codTpv == null && other.codTpv != null) || (this.codTpv != null && !this.codTpv.equals(other.codTpv))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Tpv[ codTpv=" + codTpv + " ]";
    }
    
}
