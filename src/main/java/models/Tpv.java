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
 * @author krach
 */
@Entity
@Table(name = "tpv")
@NamedQueries({
    @NamedQuery(name = "Tpv.findAll", query = "SELECT t FROM Tpv t"),
    @NamedQuery(name = "Tpv.findByCodtpv", query = "SELECT t FROM Tpv t WHERE t.codtpv = :codtpv"),
    @NamedQuery(name = "Tpv.findByPasswordadmin", query = "SELECT t FROM Tpv t WHERE t.passwordadmin = :passwordadmin"),
    @NamedQuery(name = "Tpv.findByUbicacion", query = "SELECT t FROM Tpv t WHERE t.ubicacion = :ubicacion"),
    @NamedQuery(name = "Tpv.findByFechahoraactual", query = "SELECT t FROM Tpv t WHERE t.fechahoraactual = :fechahoraactual")})
public class Tpv implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codtpv")
    private Integer codtpv;
    @Column(name = "passwordadmin")
    private String passwordadmin;
    @Column(name = "ubicacion")
    private String ubicacion;
    @Column(name = "fechahoraactual")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechahoraactual;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codtpv")
    private Collection<Tickets> ticketsCollection;

    public Tpv() {
    }

    public Tpv(Integer codtpv) {
        this.codtpv = codtpv;
    }

    public Integer getCodtpv() {
        return codtpv;
    }

    public void setCodtpv(Integer codtpv) {
        this.codtpv = codtpv;
    }

    public String getPasswordadmin() {
        return passwordadmin;
    }

    public void setPasswordadmin(String passwordadmin) {
        this.passwordadmin = passwordadmin;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Date getFechahoraactual() {
        return fechahoraactual;
    }

    public void setFechahoraactual(Date fechahoraactual) {
        this.fechahoraactual = fechahoraactual;
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
        hash += (codtpv != null ? codtpv.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tpv)) {
            return false;
        }
        Tpv other = (Tpv) object;
        if ((this.codtpv == null && other.codtpv != null) || (this.codtpv != null && !this.codtpv.equals(other.codtpv))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Tpv[ codtpv=" + codtpv + " ]";
    }
    
}
