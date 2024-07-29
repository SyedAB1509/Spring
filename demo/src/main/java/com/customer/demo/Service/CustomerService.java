package com.customer.demo.Service;


import com.customer.demo.Entities.Patternreg;
import com.customer.demo.Repository.CustomerRepository;
import com.customer.demo.Entities.Customer;
import com.customer.demo.Repository.RegexPatternRepository;
import com.customer.demo.Validation.ValidationException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CustomerService {
    private RegexPatternRepository regexPatternRepository;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private Regservice regservice;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public Customer createCustomer(@NotNull Customer customer) {

            if(isValidName(customer.getName())){
        throw new RuntimeException("name is not valid ");
    }


        return customerRepository.save(customer);

    }

    public Customer updateCustomer(Long id, Customer customerDetails) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            customer.setName(customerDetails.getName());
            customer.setAddress(customerDetails.getAddress());
            customer.setAge(customerDetails.getAge());
            return customerRepository.save(customer);
        }
        return null;
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }


//    public boolean isValidName(String name){
//        String INTEGER_PATTERN = String.valueOf(regservice.getPatternsByFieldname(name));
//
//        Pattern pattern = Pattern.compile(INTEGER_PATTERN);
//
//        Matcher matcher = pattern.matcher(name);
//        return  matcher.matches();
////        return name != null && name.matches(regservice.getPatternsByFieldname(name));
//
//    }
public boolean isValidName(String name) {
    String patternString = regservice.getPatternsByFieldname("name");
    if (patternString != null) {
        Pattern pattern = Pattern.compile(patternString);

        return pattern.matcher(name).matches();
    }

    return false;
}

}
