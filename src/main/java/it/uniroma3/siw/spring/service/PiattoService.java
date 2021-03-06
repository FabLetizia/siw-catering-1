package it.uniroma3.siw.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.spring.model.Ingrediente;
import it.uniroma3.siw.spring.model.Piatto;
import it.uniroma3.siw.spring.repository.PiattoRepository;

@Service
public class PiattoService {

	@Autowired
	private PiattoRepository piattoRepository;

	@Transactional
	public void aggiungiPiatto(Piatto p) {
		piattoRepository.save(p);
	}

	public void rimuoviPiatto(Long id) {
		piattoRepository.deleteById(id);
	}

	public Piatto findById(Long id) {
		return piattoRepository.findById(id).get();
	}

	public List<Piatto> findAll() {
		List<Piatto> piatti = new ArrayList<Piatto>();
		for(Piatto p : piattoRepository.findAll()) {
			piatti.add(p);
		}
		return piatti;	
	}

	public void remove(Long id) {
		piattoRepository.deleteById(id);	
	}

	public boolean alredyExists(Piatto piatto) {
		return this.piattoRepository.existsByNomeAndDescrizione(piatto.getNome(), piatto.getDescrizione());

	}

	public void updatePiatto(Piatto piatto) {
		this.aggiungiPiatto(piatto);
	}

	public List<Piatto> findAllConIngrediente(Ingrediente ingrediente) {
		List<Piatto> piatti = new ArrayList<Piatto>();
		for(Piatto p : piattoRepository.findAll()) {
			if(p.getIngredienti().contains(ingrediente)) {
				piatti.add(p);
			}	
		}
		return piatti;
	}

	public void removeIngredienteDaPiatto(List<Piatto> piattiConIngrediente, Ingrediente ingrediente) {
		for(Piatto p : piattiConIngrediente) {
			p.getIngredienti().remove(ingrediente);
		}	
	}

}
