package com.api.financa.service;

import com.api.financa.model.entity.Categoria;
import com.api.financa.repository.CategoriaRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    public List<Categoria> findAll() {

        log.info("Procurando todas as Categorias.");
        List<Categoria> categorias = categoriaRepository.findAll();

        if(categorias.isEmpty()){
            log.error("Nenhuma Categoria encontrada");
            return null;
        }

        log.debug("Categorias encontradas: [{}]", categorias);
        return categorias;
    }

    @Transactional
    public Categoria saveCategoria(Categoria categoria) {
        log.debug("Salvando nova Categoria: [{}]", categoria);
        return categoriaRepository.save(categoria);
    }

    public long getCount() {
        long count = categoriaRepository.count();
        log.debug("Categorias count: [{}]", count);
        return count;
    }

    public Optional<Categoria> findById(Long id) {
        log.debug("Procurando Categoria com o ID [{}]", id);
        Optional<Categoria> categoria = categoriaRepository.findById(id);

        if(categoria.isPresent()){
            log.debug("Categoria encontrada: [{}]", categoria);
        }
        return categoria;
    }

    public boolean existsByCategoriaNome(String categoria_nome) {
        if(categoriaRepository.findByCategoriaNome(categoria_nome) == null){
            log.warn("Categoria não salva: Já existe Caterogia com o nome [{}]", categoria_nome);
            return false;
        }

        return true;
    }


    public boolean existsByCategoriaCodigo(String categoria_codigo) {
        if(categoriaRepository.findByCategoriaCodigo(categoria_codigo) == null){
            log.warn("Categoria não salva: Já existe Caterogia com o código [{}]", categoria_codigo);
            return false;
        }

        return true;
    }

    public List<Categoria> readCategoriaDespesa() {

        log.info("Procurando Categorias das despesas");
        List<Categoria> categorias = categoriaRepository.getAllCategoriaDespesas();

        if(categorias.isEmpty()){
            log.error("Nenhuma Categoria encontrada");
            return null;
        }

        log.debug("Categorias encontradas: [{}]", categorias);
        return categorias;

    }
}
