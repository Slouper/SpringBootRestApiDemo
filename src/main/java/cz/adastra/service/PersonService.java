package cz.adastra.service;

import cz.adastra.data.Person;
import cz.adastra.repository.PersonRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

  @Autowired
  private PersonRepository personRepository;

  public List<Person> findByFirstName(String name) {
    return personRepository
        .findAll()
        .stream()
        .filter(person -> person.getName().equals(name))
        .collect(Collectors.toList());
  }

  public List<Person> findByAnyName(String nameOrSurname) {
    return personRepository
        .findAll()
        .stream()
        .filter(person -> person.getName().equals(nameOrSurname) || person.getSurname().equals(nameOrSurname))
        .collect(Collectors.toList());
  }
}
