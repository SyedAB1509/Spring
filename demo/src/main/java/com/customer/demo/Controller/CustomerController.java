package com.customer.demo.Controller;

import com.customer.demo.Entities.Customer;
import com.customer.demo.Entities.Patternreg;
import com.customer.demo.Repository.RegexPatternRepository;
import com.customer.demo.Service.CustomerService;
import com.customer.demo.Service.Regservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

@RestController
@Validated
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
     private Regservice regservice;
    @Autowired
    private RegexPatternRepository regexPatternRepository;

    @GetMapping("Regex")
    public List<Patternreg> getpat(){
        return regservice.getpatt();
    }
//    @GetMapping("/patterns")
//    public List<String> getPatterns(@RequestParam String fieldname) {
//        return regservice.getPatternsByFieldname(fieldname);
//    }

    @GetMapping("/patt")
    public Pattern reg(){
        List<Patternreg> ent = regexPatternRepository.findAll();

        Pattern myentity = Pattern.compile(String.valueOf(ent.get(2)));
        return myentity;
    }


    @GetMapping("customers")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }


    @GetMapping("customer/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("customer")
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
        try {
            Customer createdCustomer = customerService.createCustomer(customer);
            return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



    @PutMapping("customer/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
        Customer updatedCustomer = customerService.updateCustomer(id, customerDetails);
        if (updatedCustomer != null) {
            return ResponseEntity.ok(updatedCustomer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("customer/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
