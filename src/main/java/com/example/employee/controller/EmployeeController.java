package com.example.employee.controller;

import com.example.employee.entity.Employee;
import com.example.employee.payload.EmployeeDto;
import com.example.employee.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //http://localhost:8080/api/v1/employee
    //http://localhost:8080/api/v1/employee?id=
    @PostMapping
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto empDto = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(empDto, HttpStatus.CREATED);

    }
    //http://localhost:8080/api/v1/employee?id=
    @DeleteMapping
    public ResponseEntity<String> deleteData(@RequestParam long id){
        employeeService.deleteById(id);
        return new ResponseEntity<>("Record Deleted", HttpStatus.OK);

    }
    @PutMapping
    public ResponseEntity<EmployeeDto> updateEmployee(
            @RequestParam long id,
            @RequestBody EmployeeDto employeeDto ){
        EmployeeDto dto= employeeService.updateEmployee(id, employeeDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployee(
            @RequestParam(name = "pageNo",defaultValue = "0",required = false)int pageNo,
            @RequestParam(name = "pageSize",defaultValue = "4",required = false) int pageSize,
            @RequestParam(name = "sortBy",defaultValue = "name",required = false) String sortBy,
            @RequestParam(name = "sortDir",defaultValue = "asc",required = false) String sortDir
    ){
        List<EmployeeDto> dtos = employeeService.getAllEmployees(pageNo, pageSize);
        return new ResponseEntity<>(dtos,HttpStatus.OK);

    }
}
