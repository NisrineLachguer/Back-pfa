package com.lachguer.pfabck.service;

import com.lachguer.pfabck.model.ResultatAnalyseCV;
import com.lachguer.pfabck.repository.ResultatAnalyseCVRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultatAnalyseCVService {

    @Autowired
    private ResultatAnalyseCVRepository repository;

    public ResultatAnalyseCV save(ResultatAnalyseCV resultat) {
        return repository.save(resultat);
    }

    public ResultatAnalyseCV findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<ResultatAnalyseCV> findAll() {
        return repository.findAll();
    }

    public ResultatAnalyseCV update(ResultatAnalyseCV resultat) {
        if (repository.existsById(resultat.getId())) {
            return repository.save(resultat);
        }
        return null;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
