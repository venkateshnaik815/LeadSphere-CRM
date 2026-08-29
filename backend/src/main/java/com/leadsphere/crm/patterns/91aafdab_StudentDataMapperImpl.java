package com.leadsphere.crm.patterns;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.Getter;

@Getter
public final class StudentDataMapperImpl implements StudentDataMapper {

  private final List<Student> students = new ArrayList<>();

  @Override
  public Optional<Student> find(int studentId) {
    return this.getStudents().stream().filter(x -> x.getStudentId() == studentId).findFirst();
  }

  @Override
  public void update(Student studentToBeUpdated) throws DataMapperException {
    String name = studentToBeUpdated.getName();
    Integer index =
        Optional.of(studentToBeUpdated)
            .map(Student::getStudentId)
            .flatMap(this::find)
            .map(students::indexOf)
            .orElseThrow(() -> new DataMapperException("Student [" + name + "] is not found"));
    students.set(index, studentToBeUpdated);
  }

  @Override
  public void insert(Student studentToBeInserted) throws DataMapperException {
    Optional<Student> student = find(studentToBeInserted.getStudentId());
    if (student.isPresent()) {
      String name = studentToBeInserted.getName();
      throw new DataMapperException("Student already [" + name + "] exists");
    }

    students.add(studentToBeInserted);
  }

  @Override
  public void delete(Student studentToBeDeleted) throws DataMapperException {
    if (!students.remove(studentToBeDeleted)) {
      String name = studentToBeDeleted.getName();
      throw new DataMapperException("Student [" + name + "] is not found");
    }
  }
}
