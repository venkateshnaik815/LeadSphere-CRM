package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class App {

  private static final String STUDENT_STRING = "App.main(), student : ";

  public static void main(final String... args) {

    final var mapper = new StudentDataMapperImpl();

    var student = new Student(1, "Adam", 'A');

    mapper.insert(student);

    LOGGER.debug(STUDENT_STRING + student + ", is inserted");

    final var studentToBeFound = mapper.find(student.getStudentId());

    LOGGER.debug(STUDENT_STRING + studentToBeFound + ", is searched");

    student = new Student(student.getStudentId(), "AdamUpdated", 'A');

    mapper.update(student);

    LOGGER.debug(STUDENT_STRING + student + ", is updated");
    LOGGER.debug(STUDENT_STRING + student + ", is going to be deleted");

    mapper.delete(student);
  }

  private App() {}
}
