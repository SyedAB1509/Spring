package com.customer.demo.Service;

import com.customer.demo.Entities.Patternreg;
import com.customer.demo.Repository.RegexPatternRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Regservice {
    @Autowired
    private RegexPatternRepository regexPatternRepository;

    public List<Patternreg> getpatt() {
        return regexPatternRepository.findAll();
    }
    public String getpattbyname(String fieldname) {
        return String.valueOf(regexPatternRepository.findByFieldname(fieldname));
    }

//    public List<String> getPatternsByFieldname(String fieldname) {
//        // Fetch patterns using projection
//        List<Patternreg> projections = regexPatternRepository.findByFieldname(fieldname);
//
//        // Extract pattern values from projections
//        return projections.stream()
//                .map(Patternreg::getPattern)
//                .toList();
//    }

    public String getPatternsByFieldname(String fieldname) {
        Patternreg patternRegex = regexPatternRepository.findByFieldname(fieldname);
        return patternRegex != null ? patternRegex.getPattern() : null;
    }


}
