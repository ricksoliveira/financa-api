package com.api.financa.service;

import com.api.financa.dto.SomaMesesDto;
import com.api.financa.repository.DespesaRepository;
import com.api.financa.repository.ReceitaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class SomaMesesService {

    @Autowired
    DespesaRepository despesaRepository;

    @Autowired
    ReceitaRepository receitaRepository;

    private double findCorrespondente(String mes, int catSize, List<String> catMeses, List<Double> catValores){
        for (int j = 0; j < catSize; j++) {
            if(mes.equals(catMeses.get(j))){
                return catValores.get(j);
            }
        }
        return 0.0;
    }

    public List<SomaMesesDto> findAll() {

        log.info("Calculando Dados totais de todos os meses");

        List<SomaMesesDto> somaMesesList = new ArrayList<>();

        List<String> meses = despesaRepository.getMesesList();
        if(meses.isEmpty()){
            log.error("Nenhum mes encontrado!");
            return null;
        }
        log.debug("Meses encontrados: [{}]", meses);

        log.info("Inserindo meses na Lista SomaMesesDto");
        for (String s : meses) {
            SomaMesesDto sM = new SomaMesesDto();
            sM.setMes(s);
            somaMesesList.add(sM);
        }


        log.info("Procurando Soma das Receitas");
        List<String> receitaMeses = receitaRepository.getReceitaMeses();
        int receitaSize = receitaMeses.size();
        log.debug("Soma das Receitas size: [{}]", receitaSize);
        List<Double> receitaValores = receitaRepository.getReceitaValores();
        if(receitaValores.isEmpty()){
            log.error("Nenhuma Soma das Receitas encontrada!");
            return null;
        }
        log.debug("Somas das Receitas encontradas: [{}]", receitaValores);
        log.info("Inserindo Soma das Receitas na Lista SomaMesesDto");
        for(SomaMesesDto sm : somaMesesList){
            sm.setReceita(findCorrespondente(sm.getMes(), receitaSize, receitaMeses, receitaValores));
        }
        log.debug("Soma dos Meses com Receitas: [{}]", somaMesesList);



        log.info("Procurando Soma das Despesas");
        List<String> despesaMeses = despesaRepository.getDespesaMeses();
        int despesaSize = despesaMeses.size();
        log.debug("Soma das Despesas size: [{}]", despesaSize);
        List<Double> despesaValores = despesaRepository.getDespesaValores();
        if(despesaValores.isEmpty()){
            log.error("Nenhuma Soma das Despesas encontrada!");
            return null;
        }
        log.debug("Somas das Despesas encontradas: [{}]", despesaValores);
        log.info("Inserindo Soma das Despesas na Lista SomaMesesDto");
        for(SomaMesesDto sm : somaMesesList){
            sm.setDespesa(findCorrespondente(sm.getMes(), despesaSize, despesaMeses, despesaValores));
        }
        log.debug("Soma dos Meses com Despesas: [{}]", somaMesesList);



        log.info("Procurando Soma dos Mantimentos");
        List<String> mantimentoMeses = despesaRepository.getMantimentoMeses();
        int mantimentoSize = mantimentoMeses.size();
        log.debug("Soma dos Mantimentos size: [{}]", mantimentoSize);
        List<Double> mantimentoValores = despesaRepository.getMantimentoValores();
        if(mantimentoValores.isEmpty()){
            log.error("Nenhuma Soma dos Mantimentos encontrada!");
            return null;
        }
        log.debug("Somas dos Mantimentos encontradas: [{}]", mantimentoValores);
        log.info("Inserindo Soma dos Mantimentos na Lista SomaMesesDto");
        for(SomaMesesDto sm : somaMesesList){
            sm.setMantimento(findCorrespondente(sm.getMes(), mantimentoSize, mantimentoMeses, mantimentoValores));
        }
        log.debug("Soma dos Meses com Mantimentos: [{}]", somaMesesList);


        log.info("Procurando Soma dos Mantimentos");
        List<String> contaMeses = despesaRepository.getContaMeses();
        int contaSize = contaMeses.size();
        log.debug("Soma das Contas size: [{}]", contaSize);
        List<Double> contaValores = despesaRepository.getContaValores();
        if(contaValores.isEmpty()){
            log.error("Nenhuma Soma das Contas encontrada!");
            return null;
        }
        log.debug("Soma das Contas encontradas: [{}]", contaValores);
        log.info("Inserindo Soma das Contas na Lista SomaMesesDto");
        for(SomaMesesDto sm : somaMesesList){
            sm.setConta(findCorrespondente(sm.getMes(), contaSize, contaMeses, contaValores));
        }
        log.debug("Soma dos Meses com Contas: [{}]", somaMesesList);


        log.info("Procurando Soma dos Outros");
        List<String> outrosMeses = despesaRepository.getOutrosMeses();
        int outrosSize = outrosMeses.size();
        log.debug("Soma dos Outros size: [{}]", outrosSize);
        List<Double> outrosValores = despesaRepository.getOutrosValores();
        if(outrosValores.isEmpty()){
            log.error("Nenhuma Soma dos Outros encontrada!");
            return null;
        }
        log.debug("Soma dos Outros encontradas: [{}]", outrosValores);
        log.info("Inserindo Soma dos Outros na Lista SomaMesesDto");
        for(SomaMesesDto sm : somaMesesList){
            sm.setOutros(findCorrespondente(sm.getMes(), outrosSize, outrosMeses, outrosValores));
        }
        log.debug("Soma dos Meses com Outros: [{}]", somaMesesList);


        log.info("Procurando Soma dos Ubers");
        List<String> uberMeses = despesaRepository.getUberMeses();
        int uberSize = uberMeses.size();
        log.debug("Soma dos Ubers size: [{}]", uberSize);
        List<Double> uberValores = despesaRepository.getUberValores();
        if(uberValores.isEmpty()){
            log.error("Nenhuma Soma dos Ubers encontrada!");
            return null;
        }
        log.debug("Soma dos Ubers encontradas: [{}]", uberValores);
        log.info("Inserindo Soma dos Ubers na Lista SomaMesesDto");
        for(SomaMesesDto sm : somaMesesList){
            sm.setUber(findCorrespondente(sm.getMes(), uberSize, uberMeses, uberValores));
        }
        log.debug("Soma dos Meses com Ubers: [{}]", somaMesesList);


        log.info("Calculando Lucro");
        for (SomaMesesDto sM : somaMesesList) {
            sM.setLucro(sM.getReceita()-sM.getDespesa());
        }

        log.debug("Soma dos Meses finalizada: [{}]", somaMesesList);

        return somaMesesList;
    }


}
