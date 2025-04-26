package com.stanreybackend.stanreyapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stanreybackend.stanreyapi.DTO.CategoriaDTO;
import com.stanreybackend.stanreyapi.entity.Categoria;
import com.stanreybackend.stanreyapi.repository.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public String addCategoria(CategoriaDTO categoriaDTO) {

        Categoria categoria = new Categoria(
                categoriaDTO.getIdCategoria(),
                categoriaDTO.getDescripcion(),
                categoriaDTO.getEstado());

        categoriaRepository.save(categoria);

        return categoria.getIdCategoria().toString();

    };

    public List<Categoria> findAll() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias;
    }
}
