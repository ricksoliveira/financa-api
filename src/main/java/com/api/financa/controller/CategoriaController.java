package com.api.financa.controller;

import com.api.financa.dto.CategoriaDto;
import com.api.financa.model.entity.Categoria;
import com.api.financa.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/categoria")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;


    @PostMapping("/create")
    public ResponseEntity<Object> saveCategoria(@RequestBody @Valid CategoriaDto categoriaDto){

        if(categoriaService.existsByCategoriaNome(categoriaDto.getCategoria_nome())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro: Já existe caterogia com este Nome !");
        }

        if(categoriaService.existsByCategoriaCodigo(categoriaDto.getCategoria_codigo())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro: Já existe caterogia com este Codigo !");
        }

        Categoria categoria = new Categoria();
        BeanUtils.copyProperties(categoriaDto, categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.saveCategoria(categoria));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Categoria>> readAllCategoria(){
        return ResponseEntity.status(HttpStatus.OK).body(categoriaService.findAll());
    }

    @GetMapping("/despesas")
    public ResponseEntity<List<Categoria>> readCategoriaDespesa(){
        return ResponseEntity.status(HttpStatus.OK).body(categoriaService.readCategoriaDespesa());
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countCategoria(){
        return ResponseEntity.status(HttpStatus.OK).body(categoriaService.getCount());
    }

    @GetMapping("/{categoria_id}")
    public ResponseEntity<Object> readCategoriaById(@PathVariable(value = "categoria_id") Long categoria_id){
        Optional<Categoria> categoriaOptional = categoriaService.findById(categoria_id);

        if(!categoriaOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria não encontrada !");
        }

        return ResponseEntity.status(HttpStatus.OK).body(categoriaOptional.get());
    }



}
