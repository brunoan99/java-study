package dev.brunoan.rest_with_spring_boot_and_java.repository;

import dev.brunoan.rest_with_spring_boot_and_java.model.Person;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
