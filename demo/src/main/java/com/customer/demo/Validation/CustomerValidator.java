package com.customer.demo.Validation;

import com.customer.demo.Entities.Customer;
import com.customer.demo.Entities.Patternreg;
import com.customer.demo.Repository.CustomerRepository;
import com.customer.demo.Repository.RegexPatternRepository;
import com.customer.demo.Service.Regservice;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class CustomerValidator implements ConstraintValidator<ValidateCustomer,String> {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private Regservice regservice;
    @Autowired
    private RegexPatternRepository regexPatternRepository;


    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        List<Patternreg> ent = regexPatternRepository.findAll();

        String myentity = String.valueOf(ent.get(2));

        if (Pattern.matches(myentity,name)){
            return true;

        }
        return false;

    }


}