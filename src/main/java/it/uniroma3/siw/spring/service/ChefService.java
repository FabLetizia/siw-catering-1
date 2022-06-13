package it.uniroma3.siw.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.spring.model.Chef;
import it.uniroma3.siw.spring.repository.ChefRepository;

@Service
public class ChefService {
	
	@Autowired
	private ChefRepository chefRepository;
	
	@Transactional
	public void aggiungiChef(Chef c) {
		chefRepository.save(c);
	}
	
	public void rimuoviChef(Long id) {
		chefRepository.deleteById(id);
	}
	
	public Chef findById(Long id) {
		return chefRepository.findById(id).get();
	}

	public List<Chef> findAll() {
		List<Chef> chefs = new ArrayList<Chef>();
		for(Chef c : chefRepository.findAll()) {
			chefs.add(c);
		}
		return chefs;	
	}

	public void remove(Long id) {
		chefRepository.deleteById(id);	
	}
	
	public boolean alreadyExists(Chef chef) {
		return chefRepository.existsByNomeAndCognome(chef.getNome(), chef.getCognome());
	}
	
	public void updateChef(Chef chef) {
		this.aggiungiChef(chef);
	}

}
