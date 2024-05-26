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
 * @author krach
 */
@Embeddable
public class DetalleticketPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idproductos")
    private int idproductos;
    @Basic(optional = false)
    @Column(name = "idtickets")
    private int idtickets;

    public DetalleticketPK() {
    }

    public DetalleticketPK(int idproductos, int idtickets) {
        this.idproductos = idproductos;
        this.idtickets = idtickets;
    }

    public int getIdproductos() {
        return idproductos;
    }

    public void setIdproductos(int idproductos) {
        this.idproductos = idproductos;
    }

    public int getIdtickets() {
        return idtickets;
    }

    public void setIdtickets(int idtickets) {
        this.idtickets = idtickets;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idproductos;
        hash += (int) idtickets;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleticketPK)) {
            return false;
        }
        DetalleticketPK other = (DetalleticketPK) object;
        if (this.idproductos != other.idproductos) {
            return false;
        }
        if (this.idtickets != other.idtickets) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.DetalleticketPK[ idproductos=" + idproductos + ", idtickets=" + idtickets + " ]";
    }
    
}
