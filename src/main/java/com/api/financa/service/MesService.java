package com.api.financa.service;

import com.api.financa.dto.MesDto;
import com.api.financa.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MesService {

    @Autowired
    DespesaRepository despesaRepository;

    public Map<String, Object> listGroupByMonths() {

        List<Integer> mesesNum = despesaRepository.getMesesMes();
        List<Integer> anosNum = despesaRepository.getMesesAno();
        List<String> status = despesaRepository.getMesesStatus();

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

        return mesesResponse;
    }
}
