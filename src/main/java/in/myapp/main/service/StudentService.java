package in.myapp.main.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import in.myapp.main.exception.StudentNotFoundException;
import in.myapp.main.model.Student;
import in.myapp.main.repository.StudentRepository;

/**
 * Service to process the API requests.
 * 
 * @author PranaySK
 */

@Service
public class StudentService {

	@Autowired
	private StudentRepository repository;

	public List<Student> getStudents() {
		return repository.findAll();
	}

	public Student getStudent(Long id) {
		Optional<Student> foundStudent = repository.findById(id);
		if (foundStudent.isPresent()) {
			return foundStudent.get();
		} else
			throw new StudentNotFoundException("Student does not exist with id: " + id);
	}

	public ResponseEntity<Object> addStudent(Student data) {
		Student savedStudent = repository.save(data);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedStudent.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	public ResponseEntity<Object> updateStudent(Long id, Student student) {
		Optional<Student> foundStudent = repository.findById(id);
		if (foundStudent.isPresent()) {
			student.setId(id);
			repository.save(student);
			return ResponseEntity.ok(student);
		} else
			return ResponseEntity.notFound().build();
	}

	public String removeStudent(Long id) {
		Optional<Student> foundStudent = repository.findById(id);
		if (foundStudent.isPresent()) {
			repository.deleteById(id);
			return "Student info deleted with id: " + id;
		} else
			return "Student does not exist with id: " + id;
	}

}
