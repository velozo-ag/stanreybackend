package com.stanreybackend.stanreyapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stanreybackend.stanreyapi.DTO.DetalleFacturaDTO;
import com.stanreybackend.stanreyapi.DTO.FacturaDTO;
import com.stanreybackend.stanreyapi.entity.Factura;
import com.stanreybackend.stanreyapi.entity.Producto;
import com.stanreybackend.stanreyapi.repository.FacturaRepository;
import com.stanreybackend.stanreyapi.repository.ProductoRepository;
import com.stanreybackend.stanreyapi.repository.UsuarioRepository;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private DetalleFacturaService detalleFacturaService;

    @Autowired
    private CarritoProductoService carritoProductoService;

    @Transactional
    public String addFactura(FacturaDTO facturaDTO, List<DetalleFacturaDTO> detallesDTO) {
        // Validar stock
        for (DetalleFacturaDTO detalle : detallesDTO) {
            Producto producto = productoRepository.findByIdProducto(detalle.getProductoId()).orElseThrow(
                    () -> new IllegalArgumentException("Producto no encontrado"));
            if (producto.getStock() < detalle.getCantidad()) {
                throw new IllegalArgumentException("Stock insuficiente para el producto: " + producto.getNombre());
            }
        }

        Factura factura = new Factura(
                facturaDTO.getIdFactura(),
                facturaDTO.getFecha(),
                facturaDTO.getImporteTotal(),
                facturaDTO.getFormaPago(),
                facturaDTO.getEstado(),
                usuarioRepository.findByIdUsuario(facturaDTO.getUsuarioId()).orElse(null),
                null);

        facturaRepository.save(factura);

        for (DetalleFacturaDTO detalleDTO : detallesDTO) {
            detalleDTO.setFacturaId(factura.getIdFactura());
            detalleFacturaService.addDetalleFactura(detalleDTO);
            Producto producto = productoRepository.findByIdProducto(detalleDTO.getProductoId()).orElse(null);
            if (producto != null) {
                producto.setStock(producto.getStock() - detalleDTO.getCantidad());
                productoRepository.save(producto);
            }
        }

        Long carritoId = facturaDTO.getCarritoId();
        if (carritoId != null) {
            carritoProductoService.deleteAllByCarritoId(carritoId);
        }

        return factura.getIdFactura().toString();
    }

    public Factura findByIdFactura(Long idFactura) {
        return facturaRepository.findByIdFactura(idFactura).orElse(null);
    }

    public List<Factura> findByUsuarioId(Long usuarioId) {
        return facturaRepository.findByUsuarioIdUsuario(usuarioId);
    }

    public String bajaByIdFactura(Long idFactura) {
        Factura factura = findByIdFactura(idFactura);
        if (factura != null && factura.getEstado().equals("1")) {
            factura.setEstado("0");
            facturaRepository.save(factura);
        }
        return factura != null ? factura.getIdFactura().toString() : null;
    }

    public String altaByIdFactura(Long idFactura) {
        Factura factura = findByIdFactura(idFactura);
        if (factura != null && factura.getEstado().equals("0")) {
            factura.setEstado("1");
            facturaRepository.save(factura);
        }
        return factura != null ? factura.getIdFactura().toString() : null;
    }

    public String updateFactura(Long idFactura, FacturaDTO facturaDTO) {
        Factura factura = facturaRepository.findByIdFactura(idFactura).orElse(null);
        if (factura != null) {
            factura.setFecha(facturaDTO.getFecha());
            factura.setImporteTotal(facturaDTO.getImporteTotal());
            factura.setFormaPago(facturaDTO.getFormaPago());
            factura.setEstado(facturaDTO.getEstado());
            factura.setUsuario(usuarioRepository.findByIdUsuario(facturaDTO.getUsuarioId()).orElse(null));
            facturaRepository.save(factura);
            return factura.getIdFactura().toString();
        }
        return null;
    }

    public List<Factura> findAll() {
        return facturaRepository.findAll();
    }
}