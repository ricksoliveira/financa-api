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

        List<SomaMesesDto> somaMesesList = new ArrayList<>();
        List<String> meses = despesaRepository.getMesesList();

        for (String s : meses) {
            SomaMesesDto sM = new SomaMesesDto();
            sM.setMes(s);
            somaMesesList.add(sM);
        }

        List<String> receitaMeses = receitaRepository.getReceitaMeses();
        int receitaSize = receitaMeses.size();
        List<Double> receitaValores = receitaRepository.getReceitaValores();
        for(SomaMesesDto sm : somaMesesList){
            sm.setReceita(findCorrespondente(sm.getMes(), receitaSize, receitaMeses, receitaValores));
        }

        List<String> despesaMeses = despesaRepository.getDespesaMeses();
        int despesaSize = despesaMeses.size();
        List<Double> despesaValores = despesaRepository.getDespesaValores();
        for(SomaMesesDto sm : somaMesesList){
            sm.setDespesa(findCorrespondente(sm.getMes(), despesaSize, despesaMeses, despesaValores));
        }

        List<String> mantimentoMeses = despesaRepository.getMantimentoMeses();
        int mantimentoSize = mantimentoMeses.size();
        List<Double> mantimentoValores = despesaRepository.getMantimentoValores();
        for(SomaMesesDto sm : somaMesesList){
            sm.setMantimento(findCorrespondente(sm.getMes(), mantimentoSize, mantimentoMeses, mantimentoValores));
        }

        List<String> contaMeses = despesaRepository.getContaMeses();
        int contaSize = contaMeses.size();
        List<Double> contaValores = despesaRepository.getContaValores();
        for(SomaMesesDto sm : somaMesesList){
            sm.setConta(findCorrespondente(sm.getMes(), contaSize, contaMeses, contaValores));
        }

        List<String> outrosMeses = despesaRepository.getOutrosMeses();
        int outrosSize = outrosMeses.size();
        List<Double> outrosValores = despesaRepository.getOutrosValores();
        for(SomaMesesDto sm : somaMesesList){
            sm.setOutros(findCorrespondente(sm.getMes(), outrosSize, outrosMeses, outrosValores));
        }

        List<String> uberMeses = despesaRepository.getUberMeses();
        int uberSize = uberMeses.size();
        List<Double> uberValores = despesaRepository.getUberValores();

        for(SomaMesesDto sm : somaMesesList){
            sm.setUber(findCorrespondente(sm.getMes(), uberSize, uberMeses, uberValores));
        }


        for (SomaMesesDto sM : somaMesesList) {
            sM.setLucro(sM.getReceita()-sM.getDespesa());
        }

        return somaMesesList;
    }


}
