package com.example.studentapi;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

@Repository // Spring creates and injects this object
public class StudentRepository {
    // LinkedHashMap keeps a predictable insertion order.
    private final Map<Long, Student> data = new LinkedHashMap<>();
    private final AtomicLong nextId = new AtomicLong(1);

    public List<Student> findAll() {
// Return a copy instead of exposing the Map directly.
        return new ArrayList<>(data.values());
    }

    public Optional<Student> findById(Long id) {
        return Optional.ofNullable(data.get(id));
    }

    public Student save(Student student) {
// Generate an ID for create; keep the ID for update.
        Long id = student.id() == null
                ? nextId.getAndIncrement()
                : student.id();
        Student saved = new Student(
                id, student.name(), student.email(), student.course());
        data.put(id, saved); // insert or replace by ID
        return saved;
    }

    public boolean deleteById(Long id) {
        return data.remove(id) != null; // true when a row existed
    }
}

/*

package com.example.student;
import org.springframework.data.jpa.repository.JpaRepository;
public interface StudentRepository
extends JpaRepository<Student, Long> {
// findAll, findById, save, and delete are already provided.
}

 */
