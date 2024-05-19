package com.eduinx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.eduinx.entity.Address;
import com.eduinx.entity.Employee;
import com.eduinx.repo.EmployeeRepo;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeDetails(@PathVariable("id") int id) throws Exception {
    	
        String addressUrl = "http://localhost:8081/address/{id}";
        Address address = restTemplate.getForObject(addressUrl, Address.class, id);

        Employee employee = employeeRepo.findById(id).orElse(null);

        if (employee != null) {
            employee.setAddress(address);
            return ResponseEntity.ok(employee);
        } else {
            throw new Exception("ISSUE!!!");
        }
    }


}
