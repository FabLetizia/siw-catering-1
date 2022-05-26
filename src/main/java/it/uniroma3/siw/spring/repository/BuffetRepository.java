package it.uniroma3.siw.spring.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Buffet;
import it.uniroma3.siw.spring.model.Chef;

public interface BuffetRepository extends CrudRepository<Buffet, Long>{

	boolean existsByNomeAndDescrizioneAndChef(String nome, String descrizione, Chef chef);

}
