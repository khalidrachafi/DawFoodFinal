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
 * @author krach
 */
@Entity
@Table(name = "tipoproducto")
@NamedQueries({
    @NamedQuery(name = "Tipoproducto.findAll", query = "SELECT t FROM Tipoproducto t"),
    @NamedQuery(name = "Tipoproducto.findByNombretipoprodcucto", query = "SELECT t FROM Tipoproducto t WHERE t.nombretipoprodcucto = :nombretipoprodcucto"),
    @NamedQuery(name = "Tipoproducto.findByCodtipoproducto", query = "SELECT t FROM Tipoproducto t WHERE t.codtipoproducto = :codtipoproducto"),
    @NamedQuery(name = "Tipoproducto.findByCategoria", query = "SELECT t FROM Tipoproducto t WHERE t.categoria = :categoria")})
public class Tipoproducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "nombretipoprodcucto")
    private String nombretipoprodcucto;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codtipoproducto")
    private Integer codtipoproducto;
    @Column(name = "categoria")
    private String categoria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codtipoproducto")
    private Collection<Productos> productosCollection;

    public Tipoproducto() {
    }

    public Tipoproducto(Integer codtipoproducto) {
        this.codtipoproducto = codtipoproducto;
    }

    public String getNombretipoprodcucto() {
        return nombretipoprodcucto;
    }

    public void setNombretipoprodcucto(String nombretipoprodcucto) {
        this.nombretipoprodcucto = nombretipoprodcucto;
    }

    public Integer getCodtipoproducto() {
        return codtipoproducto;
    }

    public void setCodtipoproducto(Integer codtipoproducto) {
        this.codtipoproducto = codtipoproducto;
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
        hash += (codtipoproducto != null ? codtipoproducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipoproducto)) {
            return false;
        }
        Tipoproducto other = (Tipoproducto) object;
        if ((this.codtipoproducto == null && other.codtipoproducto != null) || (this.codtipoproducto != null && !this.codtipoproducto.equals(other.codtipoproducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Tipoproducto[ codtipoproducto=" + codtipoproducto + " ]";
    }
    
}
