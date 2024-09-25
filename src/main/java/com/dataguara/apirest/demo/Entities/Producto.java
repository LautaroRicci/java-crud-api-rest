package com.dataguara.apirest.demo.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity  //con esto java sabe que es una clase de tipo entidad

public class Producto {

    // estos dos decoradores son propios del decorador entity (spring)
    @Id // identificador unico de cada producto que hagamos (primary key)
    @GeneratedValue(strategy = GenerationType.IDENTITY) /* genera automaticamente
    la clave primaria cada vez que se ingrese un nuevo registro
    */ 
    
    private Long id; // le pongo long porque puede ser que haya mucha cant de registros (puede ser int tambien)
    private String nombre;
    private double precio;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
