package it.uniroma3.siw.personal.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.personal.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

}