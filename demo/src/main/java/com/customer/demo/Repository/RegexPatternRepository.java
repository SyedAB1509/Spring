package com.customer.demo.Repository;

import com.customer.demo.Entities.Patternreg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegexPatternRepository extends JpaRepository<Patternreg, Long> {
    Patternreg findByFieldname(String fieldname);
}
