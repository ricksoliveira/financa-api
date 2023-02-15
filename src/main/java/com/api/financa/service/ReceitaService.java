package com.api.financa.service;

import com.api.financa.model.entity.Receita;
import com.api.financa.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReceitaService {

    @Autowired
    ReceitaRepository receitaRepository;

    public List<Receita> findAll() {
        return receitaRepository.findAll();
    }

    public List<Receita> readReceitaCurrentMonth() {

        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        String queryString;

        if (month < 10){
            queryString = year + "-0" + month;
        }
        else{
            queryString = year + "-" + month;
        }

        List<Receita> receitas = receitaRepository.readReceitaByMonth(queryString);

        double total = 0;
        for(Receita r : receitas){
            total += r.getValor();
        }

        return receitas;

    }


    public List<Receita> readReceitaByMonth(String date){
        List<Receita> receitas = receitaRepository.readReceitaByMonth(date);

        double total = 0;
        for(Receita r : receitas){
            total += r.getValor();
        }

        return receitas;
    }

}
