package dev.brunoan.rest_with_spring_boot_and_java.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.brunoan.rest_with_spring_boot_and_java.model.Person;
import dev.brunoan.rest_with_spring_boot_and_java.services.PersonServices;

@RestController
@RequestMapping("/person")
public class PersonController {
  @Autowired
  private PersonServices service;

  @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public Person findById(@PathVariable("id") Long id) {
    return service.findById(id);
  }

  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Person> findAll() {
    return service.findAll();
  }

  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Person create(@RequestBody Person person) {
    return service.create(person);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Person update(@PathVariable("id") Long id, @RequestBody Person person) {
    person.setId(id);
    return service.update(person);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public void delete(@PathVariable("id") Long id) {
    service.delete(id);
  }
}
