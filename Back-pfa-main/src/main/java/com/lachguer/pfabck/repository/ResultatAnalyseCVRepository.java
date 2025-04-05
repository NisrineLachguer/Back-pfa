package com.lachguer.pfabck.repository;

import com.lachguer.pfabck.model.ResultatAnalyseCV;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultatAnalyseCVRepository extends JpaRepository<ResultatAnalyseCV, Long> {
    ResultatAnalyseCV findByCandidatureId(Long candidatureId);
}
