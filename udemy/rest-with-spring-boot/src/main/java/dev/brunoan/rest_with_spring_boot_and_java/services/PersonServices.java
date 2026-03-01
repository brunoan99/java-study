package dev.brunoan.rest_with_spring_boot_and_java.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import dev.brunoan.rest_with_spring_boot_and_java.model.Person;

@Service
public class PersonServices {

  private final AtomicLong counter = new AtomicLong(0);

  private Logger logger = Logger.getLogger(PersonServices.class.getName());

  public List<Person> findAll() {
    logger.info("Finding all people!");

    ArrayList<Person> persons = new ArrayList<>();
    for (int i = 0; i < 8; i++) {
      Person person = new Person();
      person.setId(counter.incrementAndGet());
      person.setFirstName("Person Name " + i);
      person.setLastName("Last Name " + i);
      person.setAddress("Some address in Brasil " + i);
      if (i % 2 == 0)
        person.setGender("Male");
      else
        person.setGender("Female");
      persons.add(person);
    }
    return persons;
  }

  public Person findById(String id) {
    logger.info("Finding one person!");

    Person person = new Person();
    person.setId(counter.incrementAndGet());
    person.setFirstName("Jorge");
    person.setLastName("Matias");
    person.setAddress("São Paulo - SP - Brasil");
    person.setGender("Male");
    return person;
  }

  public Person create(Person person) {
    logger.info("Creating one person!");

    Person newPerson = new Person();
    newPerson.setId(counter.incrementAndGet());
    newPerson.setFirstName(person.getFirstName());
    newPerson.setLastName(person.getLastName());
    newPerson.setAddress(person.getAddress());
    newPerson.setGender(person.getGender());
    return newPerson;
  }

  public Person update(Person person) {
    logger.info("Updating one person!");

    return person;
  }

  public void delete(String id) {
    logger.info("Deleting one person!");
  }
}
