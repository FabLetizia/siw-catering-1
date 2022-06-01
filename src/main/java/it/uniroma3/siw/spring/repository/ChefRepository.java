package it.uniroma3.siw.spring.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Chef;

public interface ChefRepository extends CrudRepository<Chef, Long>{

	Chef findByNomeAndCognomeAndNazionalita(String nome, String cognome, String nazionalita);



}
