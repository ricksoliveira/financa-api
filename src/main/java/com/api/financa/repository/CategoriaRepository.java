package com.api.financa.repository;

import com.api.financa.model.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query(value = "SELECT * FROM categoria WHERE categoria_nome = :categoria_nome", nativeQuery = true)
    Categoria findByCategoriaNome(
            @Param("categoria_nome") String categoria_nome
    );

    @Query(value = "SELECT * FROM categoria WHERE categoria_codigo = :categoria_codigo", nativeQuery = true)
    Categoria findByCategoriaCodigo(
            @Param("categoria_codigo") String categoria_codigo
    );

}
