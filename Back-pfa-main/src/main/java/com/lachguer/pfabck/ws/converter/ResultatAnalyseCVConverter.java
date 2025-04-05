package com.lachguer.pfabck.ws.converter;

import com.lachguer.pfabck.model.ResultatAnalyseCV;
import com.lachguer.pfabck.ws.dto.ResultatAnalyseCVDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ResultatAnalyseCVConverter {

    public ResultatAnalyseCVDto toDto(ResultatAnalyseCV item) {
        if (item == null) return null;

        ResultatAnalyseCVDto dto = new ResultatAnalyseCVDto();
        dto.setId(item.getId());
        dto.setCandidature(item.getCandidature());
        dto.setResultat(item.getResultat());
        dto.setCommentaire(item.getCommentaire());
        return dto;
    }

    public ResultatAnalyseCV toItem(ResultatAnalyseCVDto dto) {
        if (dto == null) return null;

        ResultatAnalyseCV item = new ResultatAnalyseCV();
        item.setId(dto.getId());
        item.setCandidature(dto.getCandidature());
        item.setResultat(dto.getResultat());
        item.setCommentaire(dto.getCommentaire());
        return item;
    }

    public List<ResultatAnalyseCVDto> toDtos(List<ResultatAnalyseCV> items) {
        List<ResultatAnalyseCVDto> dtos = new ArrayList<>();
        for (ResultatAnalyseCV item : items) {
            dtos.add(toDto(item));
        }
        return dtos;
    }

    public List<ResultatAnalyseCV> toItems(List<ResultatAnalyseCVDto> dtos) {
        List<ResultatAnalyseCV> items = new ArrayList<>();
        for (ResultatAnalyseCVDto dto : dtos) {
            items.add(toItem(dto));
        }
        return items;
    }
}
