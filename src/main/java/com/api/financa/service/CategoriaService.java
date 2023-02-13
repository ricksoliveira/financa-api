package com.api.financa.service;

import com.api.financa.model.entity.Categoria;
import com.api.financa.repository.CategoriaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    @Transactional
    public Categoria saveCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public long getCount() {
        return categoriaRepository.count();
    }

    public Optional<Categoria> findById(Long id) {
        return categoriaRepository.findById(id);
    }

    public boolean existsByCategoriaNome(String categoria_nome) {
        if(categoriaRepository.findByCategoriaNome(categoria_nome) == null){
            return false;
        }

        return true;
    }


    public boolean existsByCategoriaCodigo(String categoria_codigo) {
        if(categoriaRepository.findByCategoriaCodigo(categoria_codigo) == null){
            return false;
        }

        return true;
    }
}
