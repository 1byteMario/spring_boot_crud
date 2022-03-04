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
import org.springframework.ui.Model;
/*
 * crud_demo
 * @author: Mario Hines
 * 
 * */


@SpringBootApplication
@RestController
public class employee_controller {
	List<employee> fetchEmp = getEmployees();

		
	@PostMapping("/employee")
	public ResponseEntity<employee> createEmp(@RequestBody employee emp){
		fetchEmp.add(emp);
		return new ResponseEntity<employee>(emp, HttpStatus.CREATED);
	}
	
	@GetMapping("/employee")
	public List<employee> rtnEmp(){
		return fetchEmp;
	}
	
	@GetMapping("/employee/{id}")
	public ResponseEntity<employee> getEmpById(@PathVariable String id){	
		employee empById=fetchEmp.stream().filter(n->n.getId().equals(id)).findAny().orElse(null);
		if(empById==null) {
			return new ResponseEntity<employee>(empById, HttpStatus.NOT_FOUND);
		}
		else
			return new ResponseEntity<employee>(empById, HttpStatus.OK);	
	}
	
	@PutMapping("/employee/{id}")
	public ResponseEntity<employee> updtEmpById(@PathVariable("id") String id, @RequestBody employee emp){
 	 employee empById=fetchEmp.stream().filter(n->n.getId().equals(id)).findAny().orElse(null);
     if(empById == null) {
			return new ResponseEntity<employee>(empById, HttpStatus.NOT_FOUND);	
     }
     else
    	 empById.setId(emp.id);
	     return new ResponseEntity<employee>(empById, HttpStatus.OK);
	}
	
	@DeleteMapping("/employee")
	public ResponseEntity<employee> delEmp(@RequestBody employee emp){
	 	employee empById=fetchEmp.stream().filter(n->n.getId().equals(emp.id)).findAny().orElse(null);
		fetchEmp.remove(empById);
	    return new ResponseEntity<employee>(HttpStatus.OK);
	}
	
	private List<employee> getEmployees(){
		List<employee> list=new ArrayList<employee>();
		employee emp=new employee();
		emp.setFirstName("");
		emp.setLastName("");
		emp.setDeptName("");
		emp.setId("");
		emp.setHireDate("");
		
		list.add(emp);
		
	    emp=new employee();
		emp.setFirstName("Mario");
		emp.setLastName("Hines");
		emp.setDeptName("00");
		emp.setId("");
		emp.setHireDate("03-05-19");
		
		list.add(emp);
		
		return list;
		
	}
	
	
	
	
	
	
	
	

}
