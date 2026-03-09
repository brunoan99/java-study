package dev.brunoan.rest_with_spring_boot_and_java.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.brunoan.rest_with_spring_boot_and_java.exception.ResourceNotFoundException;
import dev.brunoan.rest_with_spring_boot_and_java.model.Person;
import dev.brunoan.rest_with_spring_boot_and_java.repository.PersonRepository;

@Service
public class PersonServices {

  private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

  @Autowired
  PersonRepository repository;

  public List<Person> findAll() {
    logger.info("Finding all people!");

    return repository.findAll();
  }

  public Person findById(Long id) {
    logger.info("Finding one person!");

    return repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
  }

  public Person create(Person person) {
    logger.info("Creating one person!");

    return repository.save(person);
  }

  public Person update(Person person) {
    logger.info("Updating one person!");
    Person entity = findById(person.getId());

    entity.setFirstName(person.getFirstName());
    entity.setLastName(person.getLastName());
    entity.setAddress(person.getAddress());
    entity.setGender(person.getGender());

    return repository.save(entity);
  }

  public void delete(Long id) {
    logger.info("Deleting one person!");
    findById(id);
    repository.deleteById(id);
  }
}
