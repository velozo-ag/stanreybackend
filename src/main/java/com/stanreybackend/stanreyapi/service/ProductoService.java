package com.stanreybackend.stanreyapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stanreybackend.stanreyapi.DTO.CategoriaDTO;
import com.stanreybackend.stanreyapi.DTO.ProductoDTO;
import com.stanreybackend.stanreyapi.entity.Categoria;
import com.stanreybackend.stanreyapi.entity.Producto;
import com.stanreybackend.stanreyapi.repository.CategoriaRepository;
import com.stanreybackend.stanreyapi.repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    public String addProducto(ProductoDTO productoDTO) {

        Producto producto = new Producto(
            productoDTO.getIdProducto(),
            productoDTO.getNombre(),
            productoDTO.getDescripcion(),
            productoDTO.getPrecio(),
            productoDTO.getStock(),
            productoDTO.getUrlImagen(),
            productoDTO.getColor(),
            productoDTO.getEstado(),
            categoriaRepository.findByIdCategoria(productoDTO.getCategoriaId()).orElse(null)
        );

        productoRepository.save(producto);

        return producto.getIdProducto().toString();

    };

    public List<Producto> findAll() {
        List<Producto> productos = productoRepository.findAll();
        return productos;
    }
}
