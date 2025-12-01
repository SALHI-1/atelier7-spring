package com.fstt.spring.atelier7.repository;

import com.fstt.spring.atelier7.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Méthodes CRUD incluses par défaut
}