/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
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

/**
 *
 * @author khalid
 */
@Entity
@Table(name = "productos")
@NamedQueries({
    @NamedQuery(name = "Productos.findAll", query = "SELECT p FROM Productos p"),
    @NamedQuery(name = "Productos.findByIdproductos", query = "SELECT p FROM Productos p WHERE p.idproductos = :idproductos"),
    @NamedQuery(name = "Productos.findByIva", query = "SELECT p FROM Productos p WHERE p.iva = :iva"),
    @NamedQuery(name = "Productos.findByStock", query = "SELECT p FROM Productos p WHERE p.stock = :stock"),
    @NamedQuery(name = "Productos.findByDescripcion", query = "SELECT p FROM Productos p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "Productos.findByPrecio", query = "SELECT p FROM Productos p WHERE p.precio = :precio"),
    @NamedQuery(name = "Productos.findByNomproducto", query = "SELECT p FROM Productos p WHERE p.nomproducto = :nomproducto")})
public class Productos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idproductos")
    private int idproductos;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "iva")
    private double iva;
    @Column(name = "stock")
    private int stock;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "precio")
    private double precio;
    @Column(name = "nomproducto")
    private String nomproducto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productos")
    private Collection<Detalleticket> detalleticketCollection;
    @JoinColumn(name = "codtipoproducto", referencedColumnName = "codtipoproducto")
    @ManyToOne(optional = false)
    private Tipoproducto codtipoproducto;

    public Productos() {
    }

    public Productos(Integer idproductos) {
        this.idproductos = idproductos;
    }

    public Productos(Double iva, Integer stock, String descripcion, double precio, String nomproducto, Tipoproducto codtipoproducto) {
        this.iva = iva;
        this.stock = stock;
        this.descripcion = descripcion;
        this.precio = precio;
        this.nomproducto = nomproducto;
        this.codtipoproducto = codtipoproducto;
    }
    
    

    public int getIdproductos() {
        return idproductos;
    }

    public void setIdproductos(int idproductos) {
        this.idproductos = idproductos;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getNomproducto() {
        return nomproducto;
    }

    public void setNomproducto(String nomproducto) {
        this.nomproducto = nomproducto;
    }

    public Collection<Detalleticket> getDetalleticketCollection() {
        return detalleticketCollection;
    }

    public void setDetalleticketCollection(Collection<Detalleticket> detalleticketCollection) {
        this.detalleticketCollection = detalleticketCollection;
    }

    public Tipoproducto getCodtipoproducto() {
        return codtipoproducto;
    }

    public void setCodtipoproducto(Tipoproducto codtipoproducto) {
        this.codtipoproducto = codtipoproducto;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.idproductos;
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
        final Productos other = (Productos) obj;
        return this.idproductos == other.idproductos;
    }

    

    @Override
    public String toString() {
        return "models.Productos[ idproductos=" + idproductos + " ]";
    }
    
}
