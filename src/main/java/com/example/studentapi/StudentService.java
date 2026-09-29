package com.example.studentapi;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final StudentRepository repository;

    // Constructor injection makes the dependency explicit.
    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }


    public List<StudentResponse> findAll() {
        // Convert every Student into an API response DTO.
        return repository.findAll().stream()
                .map(this::toResponse).toList();
    }

    public StudentResponse findById(Long id) {
        return toResponse(getStudent(id));
    }

    private Student getStudent(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    public StudentResponse create(StudentRequest request) {
// ID is null because the repository generates it.
        Student student = new Student(
                null, request.name(), request.email(), request.course());
        return toResponse(repository.save(student));
    }
    public StudentResponse update(Long id, StudentRequest request) {
        getStudent(id); // fail with 404 before replacing data
        Student updated = new Student(
                id, request.name(), request.email(), request.course());
        return toResponse(repository.save(updated));
    }
    public void delete(Long id) {
        if (!repository.deleteById(id))
            throw new StudentNotFoundException(id);
    }
    private StudentResponse toResponse(Student s) {
        return new StudentResponse(s.id(), s.name(), s.email(), s.course());
    }

}


/*

package com.example.studentapi;
import org.springframework.transaction.annotation.Transactional;
public StudentResponse create(StudentRequest request) {
// PostgreSQL generates the ID when save inserts the row.
Student student = new Student(
request.name(), request.email(), request.course());
return toResponse(repository.save(student));
}
@Transactional
public StudentResponse update(Long id, StudentRequest request) {
Student student = getStudent(id); // 404 if it is missing
student.setName(request.name());
student.setEmail(request.email());
student.setCourse(request.course());
return toResponse(student); // Hibernate writes changes at commit
}

@Transactional
public void delete(Long id) {
// Read first so a missing ID still becomes HTTP 404.
Student student = getStudent(id);
repository.delete(student);
}
private StudentResponse toResponse(Student student) {
return new StudentResponse(
student.getId(),
student.getName(),
student.getEmail(),
student.getCourse());
}


 */
