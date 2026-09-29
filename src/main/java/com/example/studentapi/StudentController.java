package com.example.studentapi;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;


@RestController // return values are converted to JSON
@RequestMapping("/students") // base URL for this resource
public class StudentController {
    private final StudentService service;
    public StudentController(StudentService service) {
        this.service = service;
    }
    @GetMapping // GET /students
    public List<StudentResponse> findAll() {
        return service.findAll();
    }
    @GetMapping("/{id}") // GET /students/7
    public StudentResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping // POST /students
    @ResponseStatus(HttpStatus.CREATED) // return 201
    public StudentResponse create(
            @Valid @RequestBody StudentRequest request) {
        return service.create(request);
    }
    @PutMapping("/{id}") // PUT /students/7
    public StudentResponse update(@PathVariable Long id,
                                  @Valid @RequestBody StudentRequest request) {
        return service.update(id, request);
    }
    @DeleteMapping("/{id}") // DELETE /students/7
    @ResponseStatus(HttpStatus.NO_CONTENT) // return 204
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
