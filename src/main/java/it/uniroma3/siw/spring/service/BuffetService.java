package it.uniroma3.siw.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.spring.model.Buffet;
import it.uniroma3.siw.spring.repository.BuffetRepository;

@Service
public class BuffetService {
	@Autowired
	private BuffetRepository buffetRepository;

	@Transactional
	public void aggiungiBuffet(Buffet b) {
		buffetRepository.save(b);
	}

	public void rimuoviBuffet(Long id) {
		buffetRepository.deleteById(id);
	}

	public Buffet findById(Long id) {
		return buffetRepository.findById(id).get();
	}
	
	public boolean alreadyExists(Buffet buffet) {
		return buffetRepository.existsByNomeAndDescrizioneAndChef(buffet.getNome(), buffet.getDescrizione(), buffet.getChef());
	}

	public List<Buffet> findAll(){
		List<Buffet> buffets = new ArrayList<Buffet>();
		for(Buffet b : buffetRepository.findAll()) {
			buffets.add(b);
		}
		return buffets;
	}

	public boolean listaPiattiVuota(Buffet buffet) {
		if(buffetRepository.findById(buffet.getId()).get().getPiatti().isEmpty())
			return true;
		else
			return false;
			} 
	

}
