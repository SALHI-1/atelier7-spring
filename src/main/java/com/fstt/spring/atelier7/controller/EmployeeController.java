package com.fstt.spring.atelier7.controller;

import com.fstt.spring.atelier7.entity.Employee;
import com.fstt.spring.atelier7.repository.EmployeeRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepository repository;

    @GetMapping
    public List<Employee> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Employee create(@Valid @RequestBody Employee employee) {
        return repository.save(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable Long id, @Valid @RequestBody Employee empDetails) {
        return repository.findById(id).map(employee -> {
            employee.setFirstName(empDetails.getFirstName());
            employee.setLastName(empDetails.getLastName());
            employee.setEmail(empDetails.getEmail());
            employee.setSalary(empDetails.getSalary());
            return ResponseEntity.ok(repository.save(employee));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return repository.findById(id).map(employee -> {
            repository.delete(employee);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}