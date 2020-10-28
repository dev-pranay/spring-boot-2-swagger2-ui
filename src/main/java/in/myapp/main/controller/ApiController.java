package in.myapp.main.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import in.myapp.main.exception.StudentNotFoundException;
import in.myapp.main.model.Student;
import in.myapp.main.service.StudentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

/**
 * Controller to handle API requests.
 * 
 * @author PranaySK
 */

@RestController
public class ApiController {

	@Autowired
	private StudentService service;

	@GetMapping("/students")
	@ApiOperation(value = "Find all students info")
	public List<Student> getAllStudents() {
		return service.getStudents();
	}

	@GetMapping("/students/{id}")
	@ApiOperation(value = "Find student info by id")
	public Student getStudent(@PathVariable(name = "id") Long sid) {
		if (sid == null)
			throw new StudentNotFoundException("Student does not exist with id: " + sid);

		return service.getStudent(sid);
	}

	@PostMapping("/students")
	@ApiOperation(value = "Add new student info")
	public ResponseEntity<Object> addStudent(@RequestBody Student data) {
		if (Objects.isNull(data))
			throw new StudentNotFoundException("Student data can not be null or empty!");

		return service.addStudent(data);
	}

	@PutMapping("/students/{id}")
	@ApiOperation(value = "Update existing student info")
	public ResponseEntity<Object> updateStudent(@PathVariable(name = "id") Long sid, @RequestBody Student data) {
		if (sid == null)
			throw new StudentNotFoundException("Student not present with id: " + sid);

		if (Objects.isNull(data))
			throw new StudentNotFoundException("Student data can not be null or empty!");

		return service.updateStudent(sid, data);
	}

	@DeleteMapping("/students/{id}")
	@ApiOperation(value = "Remove existing student info")
	public String removeStudent(@PathVariable(name = "id") Long sid) {
		if (sid == null)
			throw new StudentNotFoundException("Student not present with id: " + sid);

		return service.removeStudent(sid);
	}

}
