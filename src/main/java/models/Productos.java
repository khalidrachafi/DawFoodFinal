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
 * @author krach
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
    @NamedQuery(name = "Productos.findByCategoria", query = "SELECT p FROM Productos p JOIN p.codtipoproducto t WHERE t.categoria = :categoria"),
    @NamedQuery(name = "Productos.findByNomproducto", query = "SELECT p FROM Productos p WHERE p.nomproducto = :nomproducto")})
public class Productos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idproductos")
    private Integer idproductos;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "iva")
    private Double iva;
    @Column(name = "stock")
    private Integer stock;
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

    public Integer getIdproductos() {
        return idproductos;
    }

    public void setIdproductos(Integer idproductos) {
        this.idproductos = idproductos;
    }

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
        this.iva = iva;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
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
        int hash = 0;
        hash += (idproductos != null ? idproductos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productos)) {
            return false;
        }
        Productos other = (Productos) object;
        if ((this.idproductos == null && other.idproductos != null) || (this.idproductos != null && !this.idproductos.equals(other.idproductos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Productos{");
        sb.append("idproductos=").append(idproductos);
        sb.append(", nomproducto=").append(nomproducto);
        sb.append(", precio=").append(precio);
        sb.append(", codtipoproducto=").append(codtipoproducto);
        sb.append('}');
        return sb.toString();
    }

}
