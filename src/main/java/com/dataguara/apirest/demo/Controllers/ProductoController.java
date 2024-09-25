package com.dataguara.apirest.demo.Controllers;

import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dataguara.apirest.demo.Repositories.ProductoRepositorie;
import com.dataguara.apirest.demo.Entities.Producto;

// con estas dos lineas "productos" pueda ser leida por un cliente
/*

@RestController: Define la clase como un controlador REST que maneja las solicitudes HTTP 
y devuelve respuestas en formato JSON/XML.

@RequestMapping("/productos"): Establece la ruta base para todos los métodos en la clase, 
lo que permite que los clientes accedan a los endpoints de la API a través de la URL 
/productos. */

@RestController
@RequestMapping("/productos")
public class ProductoController {

// aca pongo los metodos http mas comunes (explicado en obsidian)

    @Autowired
    private ProductoRepositorie productorepositorie;

    @GetMapping
    public List<Producto> getAllProductos(){
        return productorepositorie.findAll(); // con findAll devuelvo todos los 
                                              //productos que existen (funcion de Autowired)
    }

    @GetMapping("/{id}") // la notacion ("/{id}") se utiliza para identificar el recurso especifico que desea recibir
    public Producto getProductoByID(@PathVariable Long id){ //metodo para devolver un producto por la ID
        return productorepositorie.findById(id). // busca por id
        orElseThrow(() -> new RuntimeException("No se encontro el producto con el ID "+id)); // si no lo encuentra 
        //lanza error
    }


    @PostMapping
    public Producto createProducto(@RequestBody Producto producto){
        /* requestBody: extrae los atributos de una clase de 
        un objeto en Java, permitiendo que el controlador trabaje con objetos en lugar 
        de cadenas de texto. En este caso:
        ----- private Long id; 
        ----- private String nombre;
        ----- private double precio;
        */
        return productorepositorie.save(producto); // save actualiza la bdd
    }


    @PutMapping("/{id}")
    public Producto updateProducto(@PathVariable Long id, @RequestBody Producto detallesProducto){
        // el pathVariable: Extrae un valor de la URL y lo asigna al parámetro id del método
        // en nuestro caso vamos al registro con ID especifico y lo modificados
        // return productorepositorie.
        Producto producto = productorepositorie.findById(id). 
        orElseThrow(() -> new RuntimeException("No se encontro el producto con el ID "+id));

        producto.setNombre(producto.getNombre()); // actualizo el valor del nombre por lo que me mandaron en detallesProducto
        producto.setPrecio(producto.getPrecio()); // actualizo el valor precio por lo que me mandaron en detallesProducto
        
        return productorepositorie.save(producto);
    }

    @DeleteMapping("/{id}")
    public String deleteProducto(@PathVariable Long id){
        Producto producto = productorepositorie.findById(id). 
        orElseThrow(() -> new RuntimeException("No se encontro el producto con el ID "+id));

        productorepositorie.delete(producto);
        return "El producto fue eliminado con exito, ID: "+id;
    }

}

