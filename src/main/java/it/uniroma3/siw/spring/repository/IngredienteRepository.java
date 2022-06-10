package it.uniroma3.siw.spring.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Ingrediente;

public interface IngredienteRepository extends CrudRepository<Ingrediente, Long>{

	boolean existsByNomeAndDescrizioneAndOrigine(String nome, String descrizione, String origine);

}
