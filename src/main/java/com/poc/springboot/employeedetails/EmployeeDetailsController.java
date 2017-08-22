package com.poc.springboot.employeedetails;

import java.util.Collection;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeDetailsController {
	
	@RequestMapping(value = "/employees", method = RequestMethod.GET)
    public Collection getEmployeeNames() {
        return EmployeeSource.getEmployees();
    }
}