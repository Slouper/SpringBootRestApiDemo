package cz.adastra.controller;

import static org.apache.logging.log4j.util.Strings.isBlank;

import cz.adastra.data.Person;
import cz.adastra.repository.PersonRepository;
import cz.adastra.service.PersonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private PersonService personService;

  @GetMapping(value = "/persons")
  public ResponseEntity<List<Person>> listPersons(@RequestParam(name = "name", required = false) String nameOrSurname) {
    return ResponseEntity.ok(
        isBlank(nameOrSurname)
            ? personRepository.findAll()
            : personService.findByAnyName(nameOrSurname));
  }

  @GetMapping(value = "/persons/{name}")
  public ResponseEntity<List<Person>> listByFirstName(@PathVariable String name) {
    return ResponseEntity.ok(personService.findByFirstName(name));
  }

  @PostMapping(value = "/persons/create")
  public ResponseEntity<String> create(@RequestBody Person person) {
    try {
      personRepository.save(person);
      return ResponseEntity.status(201).body("Entity created.");
    } catch (Exception exception) {
      return ResponseEntity.status(500).body("Could not create entity. " + exception);
    }
  }
}
