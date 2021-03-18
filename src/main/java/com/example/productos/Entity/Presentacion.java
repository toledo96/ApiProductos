package com.example.productos.Entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import java.util.List;

@Entity
@Table
public class Presentacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "El campo no puede estár vacio")
    @Size(min = 3, max = 255, message = "El campo debe tener minimo 3 caracteres")
    private String nombre;

    @Size(max = 255, message = "El campo debe tener no mas de 255 caracteres")
    private String descripcion;

    
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.MERGE,mappedBy = "presentacion")
    private List<Producto> productos;


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

    
    
}
