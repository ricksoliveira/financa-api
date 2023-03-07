package com.api.financa.service;

import com.api.financa.model.entity.Receita;
import com.api.financa.repository.ReceitaRepository;
import com.api.financa.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ReceitaService {

    @Autowired
    ReceitaRepository receitaRepository;

    private double getSumValorReceita(List<Receita> rece){
        log.debug("Calculando soma das receitas: [{}]", rece);
        double total = 0.0;
        for(Receita r : rece){
            total += r.getValor();
        }
        log.debug("Soma: [{}] das receitas: [{}]", total, rece);
        return total;
    }

    public Map<String, Object> findAll() {

        log.info("Procurando todas as Receitas");
        List<Receita> receitas = receitaRepository.findAll();
        if(receitas.isEmpty()){
            log.error("Nenhuma receita encontrada!");
            return null;
        }
        log.debug("Receitas encontradas: [{}]", receitas);

        log.info("Procurando Meses das receitas");
        List<String> meses = receitaRepository.getReceitaMeses();
        if(meses.isEmpty()){
            log.error("Nenhum mes encontrado!");
            return null;
        }
        log.debug("Meses encontrados: [{}]", meses);


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

        log.debug("Procurando as Receitas do mes atual: [{}]", queryString);
        List<Receita> receitas = receitaRepository.readReceitaByMonth(queryString);
        if(receitas.isEmpty()){
            log.error("Nenhuma receita encontrada!");
            return null;
        }
        log.debug("Receitas encontradas: [{}]", receitas);


        Map<String, Object> receitasResponse = new HashMap<>();
        receitasResponse.put("receitas", receitas);

        double total = this.getSumValorReceita((List<Receita>) receitasResponse.get("receitas"));

        receitasResponse.put("size", receitas.size());
        receitasResponse.put("total", total);

        return receitasResponse;
    }


    public Map<String, Object> readReceitaByMonth(String date){

        log.debug("Procurando as Receitas do Mes: [{}]", date);
        List<Receita> receitas = receitaRepository.readReceitaByMonth(date);
        if(receitas.isEmpty()){
            log.error("Nenhuma receita encontrada!");
            return null;
        }
        log.debug("Receitas encontradas: [{}]", receitas);


        Map<String, Object> receitasResponse = new HashMap<>();
        receitasResponse.put("receitas", receitas);

        double total = this.getSumValorReceita((List<Receita>) receitasResponse.get("receitas"));

        receitasResponse.put("size", receitas.size());
        receitasResponse.put("total", total);

        return receitasResponse;
    }

}
