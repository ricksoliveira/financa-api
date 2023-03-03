package com.api.financa.service;

import com.api.financa.dto.MesDto;
import com.api.financa.repository.DespesaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class MesService {

    @Autowired
    DespesaRepository despesaRepository;

    public Map<String, Object> listGroupByMonths() {

        log.info("Agrupando meses diferentes");

        List<Integer> mesesNum = despesaRepository.getMesesMes();
        List<Integer> anosNum = despesaRepository.getMesesAno();
        List<String> status = despesaRepository.getMesesStatus();

        if(mesesNum.isEmpty() || anosNum.isEmpty() || status.isEmpty()){
            log.error("Nenhum mes foi encontrado e não puderam ser agrupados!");
            return null;
        }

        Map<String, Object> mesesResponse = new HashMap<>();
        List<MesDto> mesDtos = new ArrayList<>();

        for (int i = 0; i < mesesNum.size(); i++) {
            MesDto mesDto = new MesDto();
            mesDto.setMes(mesesNum.get(i));
            mesDto.setAno(anosNum.get(i));
            mesDto.setStatus(status.get(i));
            mesDtos.add(mesDto);
        }

        mesesResponse.put("meses", mesDtos);
        mesesResponse.put("size", mesesNum.size());

        log.debug("Total de [{}] meses agrupados: [{}]", mesesNum.size(), mesesResponse);

        return mesesResponse;
    }
}
