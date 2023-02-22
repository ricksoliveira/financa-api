package com.api.financa.service;

import com.api.financa.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MesService {

    @Autowired
    DespesaRepository despesaRepository;

    public Map<String, Object> listGroupByMonths() {

        List<String> meses = despesaRepository.getDespesaMeses();
        Map<String, Object> mesesResponse = new HashMap<>();

        for (int i = 0; i < meses.size(); i++) {
            switch (Integer.parseInt(meses.get(i).substring(0, meses.get(i).indexOf("/")))) {
                case 1 -> meses.set(i, "Janeiro" + meses.get(i).substring(meses.get(i).indexOf("/")));
                case 2 -> meses.set(i, "Fevereiro" + meses.get(i).substring(meses.get(i).indexOf("/")));
                case 3 -> meses.set(i, "Março" + meses.get(i).substring(meses.get(i).indexOf("/")));
                case 4 -> meses.set(i, "Abril" + meses.get(i).substring(meses.get(i).indexOf("/")));
                case 5 -> meses.set(i, "Maio" + meses.get(i).substring(meses.get(i).indexOf("/")));
                case 6 -> meses.set(i, "Junho" + meses.get(i).substring(meses.get(i).indexOf("/")));
                case 7 -> meses.set(i, "Julho" + meses.get(i).substring(meses.get(i).indexOf("/")));
                case 8 -> meses.set(i, "Agosto" + meses.get(i).substring(meses.get(i).indexOf("/")));
                case 9 -> meses.set(i, "Setembro" + meses.get(i).substring(meses.get(i).indexOf("/")));
                case 10 -> meses.set(i, "Outubro" + meses.get(i).substring(meses.get(i).indexOf("/")));
                case 11 -> meses.set(i, "Novembro" + meses.get(i).substring(meses.get(i).indexOf("/")));
                case 12 -> meses.set(i, "Dezembro" + meses.get(i).substring(meses.get(i).indexOf("/")));
            }
        }

        mesesResponse.put("meses", meses);
        mesesResponse.put("size", meses.size());

        return mesesResponse;
    }
}
