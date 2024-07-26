package com.example.employee.service;

import com.example.employee.entity.Employee;
import com.example.employee.payload.EmployeeDto;
import com.example.employee.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeDto createEmployee(EmployeeDto employeeDto){
        Employee employee=mapToEntity(employeeDto);
        Employee savedEntity= employeeRepository.save(employee);
        EmployeeDto empDto= mapToDto(savedEntity);
        empDto.setMessage("Record saved");
        return empDto;
    }

    @Override
    public void deleteById(long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public EmployeeDto updateEmployee(long id, EmployeeDto employeeDto) {
        Optional<Employee> opEmp = employeeRepository.findById(id);
        Employee employee = opEmp.get();
        employee.setName(employeeDto.getName());
        employee.setEmail(employeeDto.getEmail());
        employee.setMobile(employeeDto.getMobile());
        employee.setSalary(employeeDto.getSalary());
        Employee savedEntity = employeeRepository.save(employee);
        EmployeeDto dto = mapToDto(employee);
        return dto;


    }

    @Override
    public List<EmployeeDto> getAllEmployees(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Employee> all = employeeRepository.findAll(pageable);
        List<Employee> employees = all.getContent();
        //List<Employee> employee = employeeRepository.findAll();
        List<EmployeeDto> collect = employees.stream().map(e -> mapToDto(e)).collect(Collectors.toList());
        return collect;
    }


    Employee mapToEntity(EmployeeDto employeeDto){
        Employee emp=new Employee();
        emp.setName(employeeDto.getName());
        emp.setEmail(employeeDto.getEmail());
        emp.setMobile(employeeDto.getMobile());
        emp.setSalary(employeeDto.getSalary());
        return emp;
    }

    EmployeeDto mapToDto(Employee employee){
        EmployeeDto empDto=new EmployeeDto();
        empDto.setId(employee.getId());
        empDto.setName(employee.getName());
        empDto.setEmail(employee.getEmail());
        empDto.setMobile(employee.getMobile());
        empDto.setSalary(employee.getSalary());
        return empDto;
    }
    

}
