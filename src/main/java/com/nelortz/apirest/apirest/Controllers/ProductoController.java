package com.nelortz.apirest.apirest.Controllers;

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

import com.nelortz.apirest.apirest.Entities.Producto;
import com.nelortz.apirest.apirest.Repositories.ProductoRepository;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Producto getProductoById(@PathVariable Long id) {
        return productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Producto no encontrado con el Id: " + id));
    }

    @PostMapping
    public Producto createProducto(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    @PutMapping("/{id}")
    public Producto updateProducto(@PathVariable long id , @RequestBody Producto detallesProducto) {
        Producto producto =  productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Producto no encontrado con el Id: " + id));

         producto.setNombre(detallesProducto.getNombre());
         producto.setPrecio(detallesProducto.getPrecio());

         return productoRepository.save(producto);
    }

    @DeleteMapping("/{id}")
    public String deleteProducto(@PathVariable long id ){
        Producto producto =  productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Producto no encontrado con el Id: " + id));

        productoRepository.delete(producto);
        return "Producto con Id: " + id + " ha sido eliminado con exito";
    }

}
