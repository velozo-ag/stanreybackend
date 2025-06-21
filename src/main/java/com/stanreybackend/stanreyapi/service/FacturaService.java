package com.stanreybackend.stanreyapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stanreybackend.stanreyapi.DTO.FacturaDTO;
import com.stanreybackend.stanreyapi.entity.Factura;
import com.stanreybackend.stanreyapi.repository.FacturaRepository;
import com.stanreybackend.stanreyapi.repository.UsuarioRepository;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public String addFactura(FacturaDTO facturaDTO) {
        Factura factura = new Factura(
                facturaDTO.getIdFactura(),
                facturaDTO.getFecha(),
                facturaDTO.getImporteTotal(),
                facturaDTO.getFormaPago(),
                facturaDTO.getEstado(),
                usuarioRepository.findByIdUsuario(facturaDTO.getUsuarioId()).orElse(null),
                null 
        );

        facturaRepository.save(factura);

        return factura.getIdFactura().toString();
    }

    public Factura findByIdFactura(Long idFactura) {
        return facturaRepository.findByIdFactura(idFactura).orElse(null);
    }

    public List<Factura> findByUsuarioId(Long usuarioId) {
        return facturaRepository.findByUsuarioId(usuarioId);
    }

    public String bajaByIdFactura(Long idFactura) {
        Factura factura = findByIdFactura(idFactura);
        if (factura != null && "1".equals(factura.getEstado())) {
            factura.setEstado("0");
            facturaRepository.save(factura);
        }
        return factura != null ? factura.getIdFactura().toString() : null;
    }

    public String altaByIdFactura(Long idFactura) {
        Factura factura = findByIdFactura(idFactura);
        if (factura != null && "0".equals(factura.getEstado())) {
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