package dev.brunoan.restwithspringbootandjava.repository;

import dev.brunoan.restwithspringbootandjava.model.Person;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
