package com.api.financa.controller;

import com.api.financa.model.entity.Receita;
import com.api.financa.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/receita")
public class ReceitaController {

    @Autowired
    ReceitaService receitaService;

    @GetMapping("/all")
    public ResponseEntity<List<Receita>> readAllReceita(){
        return ResponseEntity.status(HttpStatus.OK).body(receitaService.findAll());
    }

    @GetMapping("/all/currentmonth")
    public ResponseEntity<List<Receita>> readReceitaCurrentMonth() {
        return ResponseEntity.status(HttpStatus.OK).body(receitaService.readReceitaCurrentMonth());
    }

    @GetMapping("/all/month")
    public ResponseEntity<List<Receita>> readReceitaByMonth(@RequestParam @DateTimeFormat(pattern="yyyy-MM") String data_ref) {
        return ResponseEntity.status(HttpStatus.OK).body(receitaService.readReceitaByMonth(data_ref));
    }


}
