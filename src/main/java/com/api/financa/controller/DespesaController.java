package com.api.financa.controller;

import com.api.financa.service.DespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/despesa")
public class DespesaController {

    @Autowired
    DespesaService despesaService;



    @GetMapping("/test")
    public ResponseEntity<Object> readDespesaByCategoria() {
        return ResponseEntity.status(HttpStatus.OK).body(despesaService.test());
    }


    @GetMapping("/all")
    public ResponseEntity<Object> readAllDespesa(){
        return ResponseEntity.status(HttpStatus.OK).body(despesaService.findAll());
    }

    @GetMapping("/parcial/currentmonth")
    public ResponseEntity<Object> readDespesaByCategoriaCurrentMonth(@RequestParam int categoria_id) {
        return ResponseEntity.status(HttpStatus.OK).body(despesaService.readDespesaByCategoriaCurrentMonth(categoria_id));
    }

    @GetMapping("/all/currentmonth")
    public ResponseEntity<Object> readDespesaCurrentMonth() {
        return ResponseEntity.status(HttpStatus.OK).body(despesaService.readAllDespesaCurrentMonth());
    }

    @GetMapping("/parcial/month")
    public ResponseEntity<Object> readDespesaByMesAndCategoria(@RequestParam @DateTimeFormat(pattern="yyyy-MM") String data_ref,
                                                                      @RequestParam int categoria_id) {
        return ResponseEntity.status(HttpStatus.OK).body(despesaService.readDespesaByMesAndCategoria(data_ref, categoria_id));
    }

    @GetMapping("/all/month")
    public ResponseEntity<Object> readDespesaByCategoria(@RequestParam @DateTimeFormat(pattern="yyyy-MM") String data_ref) {
        return ResponseEntity.status(HttpStatus.OK).body(despesaService.readDespesaByMes(data_ref));
    }


}
