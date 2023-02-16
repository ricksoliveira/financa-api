package com.api.financa.service;

import com.api.financa.model.entity.Receita;
import com.api.financa.repository.ReceitaRepository;
import com.api.financa.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReceitaService {

    @Autowired
    ReceitaRepository receitaRepository;

    private double getSumValorReceita(List<Receita> rece){
        double total = 0.0;
        for(Receita r : rece){
            total += r.getValor();
        }
        return total;
    }

    public Map<String, Object> findAll() {

        List<Receita> receitas = receitaRepository.findAll();
        List<String> meses = receitaRepository.getReceitaMeses();
        Map<String, Object> receitasResponse = new HashMap<>();

        receitasResponse.put("meses", meses);
        receitasResponse.put("receitas", receitas);

        double total = this.getSumValorReceita((List<Receita>) receitasResponse.get("receitas"));

        receitasResponse.put("size", receitas.size());
        receitasResponse.put("total", total);

        return receitasResponse;
    }

    public Map<String, Object> readReceitaCurrentMonth() {

        String queryString = Utils.DateUtils.getCurrentDateQueryString();
        List<Receita> receitas = receitaRepository.readReceitaByMonth(queryString);
        Map<String, Object> receitasResponse = new HashMap<>();

        receitasResponse.put("receitas", receitas);

        double total = this.getSumValorReceita((List<Receita>) receitasResponse.get("receitas"));

        receitasResponse.put("size", receitas.size());
        receitasResponse.put("total", total);

        return receitasResponse;
    }


    public Map<String, Object> readReceitaByMonth(String date){

        List<Receita> receitas = receitaRepository.readReceitaByMonth(date);
        Map<String, Object> receitasResponse = new HashMap<>();

        receitasResponse.put("receitas", receitas);

        double total = this.getSumValorReceita((List<Receita>) receitasResponse.get("receitas"));

        receitasResponse.put("size", receitas.size());
        receitasResponse.put("total", total);

        return receitasResponse;
    }

}
