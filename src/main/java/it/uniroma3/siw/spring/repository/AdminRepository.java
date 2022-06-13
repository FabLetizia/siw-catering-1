package it.uniroma3.siw.spring.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Admin;

public interface AdminRepository extends CrudRepository<Admin, Long> {

}
