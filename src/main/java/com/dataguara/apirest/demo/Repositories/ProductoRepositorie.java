package com.dataguara.apirest.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dataguara.apirest.demo.Entities.Producto;

public interface ProductoRepositorie extends JpaRepository<Producto, Long>{

}
