package com.leadsphere.crm.patterns;

import com.iluwatar.onion.application.dto.PersonResponse;
import com.iluwatar.onion.application.dto.SavePersonCommand;
import com.iluwatar.onion.application.usecase.GetPersonUseCase;
import com.iluwatar.onion.application.usecase.SavePersonUseCase;
import com.iluwatar.onion.domain.exception.DomainException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PersonController {

  private final SavePersonUseCase savePersonUseCase;
  private final GetPersonUseCase getPersonUseCase;

  public PersonController(SavePersonUseCase savePersonUseCase, GetPersonUseCase getPersonUseCase) {
    this.savePersonUseCase = savePersonUseCase;
    this.getPersonUseCase = getPersonUseCase;
  }

  @GetMapping("/persons/{id}")
  public ResponseEntity<PersonResponse> getPerson(@PathVariable("id") Long id) {
    var person = getPersonUseCase.execute(id);
    return ResponseEntity.ok(person);
  }

  @GetMapping("/persons")
  public ResponseEntity<List<PersonResponse>> getAllPersons() {
    var persons = getPersonUseCase.executeAll();
    return ResponseEntity.ok(persons);
  }

  @PostMapping("/persons")
  public ResponseEntity<PersonResponse> savePerson(@RequestBody SavePersonCommand command) {
    try {
      var savedPerson = savePersonUseCase.execute(command);
      return ResponseEntity.status(HttpStatus.OK).body(savedPerson);
    } catch (DomainException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }
}
