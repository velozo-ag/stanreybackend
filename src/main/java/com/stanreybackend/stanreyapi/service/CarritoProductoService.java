package com.stanreybackend.stanreyapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stanreybackend.stanreyapi.DTO.CarritoProductoDTO;
import com.stanreybackend.stanreyapi.entity.CarritoProducto;
import com.stanreybackend.stanreyapi.repository.CarritoProductoRepository;
import com.stanreybackend.stanreyapi.repository.CarritoRepository;
import com.stanreybackend.stanreyapi.repository.ProductoRepository;

@Service
public class CarritoProductoService {

    @Autowired
    private CarritoProductoRepository carritoProductoRepository;

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public String addCarritoProducto(CarritoProductoDTO carritoProductoDTO) {
        CarritoProducto carritoProducto = new CarritoProducto(
                carritoProductoDTO.getIdCarritoProducto(),
                carritoProductoDTO.getFechaCreacion(),
                carritoProductoDTO.getCantidad(),
                carritoProductoDTO.getPrecioUnitario(),
                carritoRepository.findByIdCarrito(carritoProductoDTO.getCarritoId()).orElse(null),
                productoRepository.findByIdProducto(carritoProductoDTO.getProductoId()).orElse(null)
        );

        carritoProductoRepository.save(carritoProducto);

        return carritoProducto.getIdCarritoProducto().toString();
    }

    public CarritoProducto findByIdCarritoProducto(Long idCarritoProducto) {
        return carritoProductoRepository.findByIdCarritoProducto(idCarritoProducto).orElse(null);
    }

    public List<CarritoProducto> findByCarritoId(Long carritoId) {
        return carritoProductoRepository.findByCarritoIdCarrito(carritoId);
    }

    public String updateCarritoProducto(Long idCarritoProducto, CarritoProductoDTO carritoProductoDTO) {
        CarritoProducto carritoProducto = carritoProductoRepository.findByIdCarritoProducto(idCarritoProducto).orElse(null);
        if (carritoProducto != null) {
            carritoProducto.setFechaCreacion(carritoProductoDTO.getFechaCreacion());
            carritoProducto.setCantidad(carritoProductoDTO.getCantidad());
            carritoProducto.setPrecioUnitario(carritoProductoDTO.getPrecioUnitario());
            carritoProducto.setCarrito(carritoRepository.findByIdCarrito(carritoProductoDTO.getCarritoId()).orElse(null));
            carritoProducto.setProducto(productoRepository.findByIdProducto(carritoProductoDTO.getProductoId()).orElse(null));
            carritoProductoRepository.save(carritoProducto);
            return carritoProducto.getIdCarritoProducto().toString();
        }
        return null;
    }

    public List<CarritoProducto> findAll() {
        return carritoProductoRepository.findAll();
    }
}