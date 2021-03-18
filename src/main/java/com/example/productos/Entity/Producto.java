package com.example.productos.Entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import net.bytebuddy.dynamic.TypeResolutionStrategy.Lazy;

@Entity
@Table
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "El campo no puede estár vacio")
    @Size(min = 3, max = 255, message = "El campo debe tener minimo 3 caracteres")
    private String nombre;

    @Size(max = 255, message = "El campo debe tener no mas de 255 caracteres")
    private String descripcion;

    @Min(value = 0, message = "El campo debe ser mayor o igual a 0")
    private double precio;

    @Min(value = 0,message = "El campo debe ser mayor o igual a 0")
    private long stock;

    //muchos productos van a estár asociados a una misma presentación
    
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @NotNull(message = "El campo presentacion no debe ser nulo")
    private Presentacion presentacion;
    




    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

    public Presentacion getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(Presentacion presentacion) {
        this.presentacion = presentacion;
    }


    
    
}
