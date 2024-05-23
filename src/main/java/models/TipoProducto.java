/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import java.util.Collection;
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

/**
 *
 * @author khalid
 */
@Entity
@Table(name = "TipoProducto")
@NamedQueries({
    @NamedQuery(name = "TipoProducto.findAll", query = "SELECT t FROM TipoProducto t"),
    @NamedQuery(name = "TipoProducto.findByNombreTipoProdcucto", query = "SELECT t FROM TipoProducto t WHERE t.nombreTipoProdcucto = :nombreTipoProdcucto"),
    @NamedQuery(name = "TipoProducto.findByCodTipoProducto", query = "SELECT t FROM TipoProducto t WHERE t.codTipoProducto = :codTipoProducto"),
    @NamedQuery(name = "TipoProducto.findByCategoria", query = "SELECT t FROM TipoProducto t WHERE t.categoria = :categoria")})
public class TipoProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "nombreTipoProdcucto")
    private String nombreTipoProdcucto;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codTipoProducto")
    private Integer codTipoProducto;
    @Column(name = "categoria")
    private String categoria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codTipoProducto")
    private Collection<Productos> productosCollection;

    public TipoProducto() {
    }

    public TipoProducto(Integer codTipoProducto) {
        this.codTipoProducto = codTipoProducto;
    }

    public String getNombreTipoProdcucto() {
        return nombreTipoProdcucto;
    }

    public void setNombreTipoProdcucto(String nombreTipoProdcucto) {
        this.nombreTipoProdcucto = nombreTipoProdcucto;
    }

    public Integer getCodTipoProducto() {
        return codTipoProducto;
    }

    public void setCodTipoProducto(Integer codTipoProducto) {
        this.codTipoProducto = codTipoProducto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Collection<Productos> getProductosCollection() {
        return productosCollection;
    }

    public void setProductosCollection(Collection<Productos> productosCollection) {
        this.productosCollection = productosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codTipoProducto != null ? codTipoProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoProducto)) {
            return false;
        }
        TipoProducto other = (TipoProducto) object;
        if ((this.codTipoProducto == null && other.codTipoProducto != null) || (this.codTipoProducto != null && !this.codTipoProducto.equals(other.codTipoProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TipoProducto[ codTipoProducto=" + codTipoProducto + " ]";
    }
    
}
