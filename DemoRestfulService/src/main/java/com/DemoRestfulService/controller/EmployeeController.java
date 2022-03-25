package com.DemoRestfulService.controller;



import java.util.HashMap;
import java.util.Map;





import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



import com.DemoRestfulService.model.employee;





@RestController
public class EmployeeController {





private static Map<Integer, employee> employeeRepo = new HashMap<Integer, employee>();
static {
com.DemoRestfulService.model.employee emp1=new com.DemoRestfulService.model.employee();
emp1.setEmpid(201);
emp1.setEmpname("SRIDAR");
emp1.setEmpsal(2600);
employeeRepo.put(emp1.getEmpid(), emp1);



employee emp2=new employee();
emp2.setEmpid(202);
emp2.setEmpname("MYILI");
emp2.setEmpsal(2500);
employeeRepo.put(emp2.getEmpid(), emp2);
}



@GetMapping(value = "employees")
public ResponseEntity<Object> getEmployee() {
System.out.println("response came");
return new ResponseEntity<>(employeeRepo.values(), HttpStatus.OK);
}
@GetMapping(value = "employees/{empid}")
public ResponseEntity<Object> getOneEmployee(@PathVariable("empid") Integer empid) {
if(employeeRepo.containsKey(empid))
return new ResponseEntity<>((employee)employeeRepo.get(empid), HttpStatus.OK);
else
return new ResponseEntity<>("Employee Not Found", HttpStatus.OK);
}



@PostMapping(value = "employees")
public ResponseEntity<Object> createEmployee(@RequestBody employee employee) {
employeeRepo.put(employee.getEmpid(), employee);
return new ResponseEntity<>("Employee created successfully", HttpStatus.CREATED);
}



@DeleteMapping(value = "/employees/{empid}")
public ResponseEntity<Object> delete(@PathVariable("empid") Integer empid) {
employeeRepo.remove(empid);
return new ResponseEntity<>("Employee deleted successsfully", HttpStatus.OK);
}





@PutMapping(value = "/employees/{empid}")
public ResponseEntity<Object> updateEmployee(@PathVariable("empid") Integer empid, @RequestBody employee employee) {
employeeRepo.remove(empid);
employee.setEmpid(empid);
employeeRepo.put(empid, employee);
return new ResponseEntity<>("Employee updated successsfully", HttpStatus.OK);
}
}