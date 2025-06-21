package com.stanreybackend.stanreyapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stanreybackend.stanreyapi.DTO.ProductoDTO;
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
                categoriaRepository.findByIdCategoria(productoDTO.getCategoriaId()).orElse(null));

        productoRepository.save(producto);

        return producto.getIdProducto().toString();

    };

    public Producto findByIdProducto(Long id_producto) {
        return productoRepository.findByIdProducto(id_producto).orElse(null);
    }

    public String bajaByIdproducto(Long id_producto) {
        Producto producto = findByIdProducto(id_producto);
        if (producto.getEstado() == 1) {
            producto.setEstado(0);
            productoRepository.save(producto);
        }

        return producto.getIdProducto().toString();
    }

    public String altaByIdproducto(Long id_producto) {
        Producto producto = findByIdProducto(id_producto);
        if (producto.getEstado() == 0) {
            producto.setEstado(1);
            productoRepository.save(producto);
        }

        return producto.getIdProducto().toString();
    }

    public String updateProducto(Long id_producto, ProductoDTO productoDTO) {
        Producto producto = productoRepository.findByIdProducto(id_producto).orElse(null);
        producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setStock(productoDTO.getStock());
        producto.setUrlImagen(productoDTO.getUrlImagen());
        producto.setColor(productoDTO.getColor());
        producto.setCategoria(categoriaRepository.findByIdCategoria(productoDTO.getCategoriaId()).orElse(null));

        productoRepository.save(producto);
        return producto.getIdProducto().toString();
    }

    public List<Producto> findAll() {
        List<Producto> productos = productoRepository.findAll();
        return productos;
    }

    public void updateStock(Long idProducto, Integer newStock) {
        Producto producto = findByIdProducto(idProducto);
        if (producto != null) {
            producto.setStock(newStock);
            productoRepository.save(producto);
        }
    }
}
