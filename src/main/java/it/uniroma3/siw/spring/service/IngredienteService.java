package it.uniroma3.siw.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.spring.model.Ingrediente;
import it.uniroma3.siw.spring.repository.IngredienteRepository;

@Service
public class IngredienteService {
	
	@Autowired
	private IngredienteRepository ingredienteRepository;
	
	@Transactional
	public void aggiungiIngrediente(Ingrediente i) {
		ingredienteRepository.save(i);
	}
	
	public void rimuoviIngrediente(Long id) {
		ingredienteRepository.deleteById(id);
	}
	
	public Ingrediente findById(Long id) {
		return ingredienteRepository.findById(id).get();
	}

	public List<Ingrediente> findAll() {
		List<Ingrediente> ingredienti = new ArrayList<Ingrediente>();
		for(Ingrediente i : ingredienteRepository.findAll()) {
			ingredienti.add(i);
		}
		return ingredienti;	
	}

	public void remove(Long id) {
		ingredienteRepository.deleteById(id);	
	}
	
	public boolean alreadyExists(Ingrediente ingrediente) {
		return ingredienteRepository.existsByNomeAndDescrizioneAndOrigine(ingrediente.getNome(), ingrediente.getDescrizione(), ingrediente.getOrigine());
	}

	public void updateIngrediente(Ingrediente ingrediente) {
			this.aggiungiIngrediente(ingrediente);	
	}

}
