package com.eduinx.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduinx.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
}
