package in.myapp.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.myapp.main.model.Student;

/**
 * Repository for handling DB operations.
 * 
 * @author PranaySK
 */

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
