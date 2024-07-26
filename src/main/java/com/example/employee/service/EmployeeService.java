package com.example.employee.service;

import com.example.employee.payload.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    public EmployeeDto createEmployee(EmployeeDto employeeDto);
    public void deleteById(long id);
    public EmployeeDto updateEmployee(long id,EmployeeDto employeeDto);

    public List<EmployeeDto> getAllEmployees(int pageNo, int pageSize);
}
