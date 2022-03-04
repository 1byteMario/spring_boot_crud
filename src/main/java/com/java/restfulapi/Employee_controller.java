package com.java.restfulapi;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
/*
 * crud_demo
 * @author: Mario Hines
 * */

@SpringBootApplication
@RestController
public class Employee_controller {
	List<Employee> employeeList = employeeList();

	@PostMapping("/employee")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee emp){
		employeeList.add(emp);
		return new ResponseEntity<Employee>(emp, HttpStatus.CREATED);
	}
	
	@GetMapping("/employee")
	public List<Employee> getEmployee(){
		return employeeList;
	}
	
	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> getEmpById(@PathVariable String id){
		try {
			Employee empById = employeeList
					.stream()
					.filter(n -> n.getId().equals(id))
					.findAny().orElse(null);
			return new ResponseEntity<Employee>(empById, HttpStatus.OK);

		}catch(NullPointerException e){
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/employee/{id}")
	public ResponseEntity<Employee> updateEmpById(@PathVariable("id") String id, @RequestBody Employee emp){
		try {
			Employee empById = employeeList
					.stream()
					.filter(n -> n.getId().equals(id))
					.findAny().orElse(null);
			empById.setId(emp.id);
			return new ResponseEntity<Employee>(empById, HttpStatus.OK);
		}catch(NullPointerException e){
			e.printStackTrace();
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/employee")
	public ResponseEntity<Employee> delEmp(@RequestBody Employee emp){
		try {
			Employee empById = employeeList
					.stream().filter(n -> n.getId().equals(emp.id))
					.findAny().orElse(null);
			employeeList.remove(empById);
			return new ResponseEntity<Employee>(HttpStatus.OK);

		}catch(NullPointerException e){
			e.printStackTrace();
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
	}

	private List<Employee> employeeList(){
		List<Employee> list = new ArrayList<Employee>();
		Employee emp = new Employee();

		emp.setFirstName("");
		emp.setLastName("");
		emp.setDeptName("");
		emp.setId(" ");
		emp.setHireDate("");

		list.add(emp);

	    emp = new Employee();
		emp.setFirstName("Mario");
		emp.setLastName("Hines");
		emp.setDeptName("00");
		emp.setId("");
		emp.setHireDate("03-05-19");

		list.add(emp);
		return list;
	}
}
