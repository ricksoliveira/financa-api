package com.api.financa.controller;

import com.api.financa.dto.ReceitaDto;
import com.api.financa.model.entity.Receita;
import com.api.financa.service.ReceitaService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/receita")
public class ReceitaController {

    @Autowired
    ReceitaService receitaService;

    @GetMapping("/all")
    public ResponseEntity<Object> readAllReceita(){
        return ResponseEntity.status(HttpStatus.OK).body(receitaService.findAll());
    }

    @GetMapping("/all/currentmonth")
    public ResponseEntity<Object> readReceitaCurrentMonth() {
        return ResponseEntity.status(HttpStatus.OK).body(receitaService.readReceitaCurrentMonth());
    }

    @GetMapping("/all/month")
    public ResponseEntity<Object> readReceitaByMonth(@RequestParam @DateTimeFormat(pattern="yyyy-MM") String data_ref) {
        return ResponseEntity.status(HttpStatus.OK).body(receitaService.readReceitaByMonth(data_ref));
    }


    @PostMapping("/create")
    public ResponseEntity<Object> saveReceita(@RequestBody @Valid ReceitaDto receitaDto){

        log.debug("Tentando salvar nova Receita: [{}] ", receitaDto);

        Receita receita = new Receita();
        BeanUtils.copyProperties(receitaDto, receita);
        return ResponseEntity.status(HttpStatus.CREATED).body(receitaService.saveReceita(receita));
    }


}
