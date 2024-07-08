package com.example.personmicroservice.Repository;

import com.example.personmicroservice.Model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person,Integer> {
}
