/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mentorandroid.employee.controller;


import com.mentorandroid.employee.exception.ResourceNotFoundException;
import com.mentorandroid.employee.model.Employee;
import com.mentorandroid.employee.model.Note;
import com.mentorandroid.employee.repository.EmployeeRepository;
import com.mentorandroid.employee.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    // Get All Notes
    // Get All Notes
    @GetMapping("/employee")
    public List<Employee> getAllNotes() {
        return employeeRepository.findAll();
    }

    // Create a new Note
    @PostMapping("/employee")
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }


    // Get a Single Note
    @GetMapping("/employee/{id}")
    public Employee getEmployeeById(@PathVariable(value = "id") Long employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
    }


    // Update a Note
    @PutMapping("/employee/{id}")
    public Employee updateEmployee(@PathVariable(value = "id") Long employeeId,
                                            @Valid @RequestBody Employee employeeDetails) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", employeeId));

        employee.setFirstName(employeeDetails.getFirstName());
        employee.setEmail(employeeDetails.getEmail());

        Employee updatedEmployee = employeeRepository.save(employee);
        return updatedEmployee;
    }


    // Delete a Note
    // Delete a Note
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(value = "id") Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", employeeId));

        employeeRepository.delete(employee);
        return ResponseEntity.ok().build();
    }

}
