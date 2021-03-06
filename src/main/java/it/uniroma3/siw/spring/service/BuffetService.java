package it.uniroma3.siw.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.spring.model.Buffet;
import it.uniroma3.siw.spring.model.Chef;
import it.uniroma3.siw.spring.model.Piatto;
import it.uniroma3.siw.spring.repository.BuffetRepository;
import it.uniroma3.siw.spring.repository.ChefRepository;

@Service
public class BuffetService {
	@Autowired
	private BuffetRepository buffetRepository;
	@Autowired
	private ChefRepository chefRepository;

	@Transactional
	public void aggiungiBuffet(Buffet b) {
		buffetRepository.save(b);
		b.getChef().getBuffets().add(b);
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

	public void remove(Long id) {
		buffetRepository.deleteById(id);	
	}

	public boolean alreadyExistsChef(Buffet b) {
		if(chefRepository.findByNomeAndCognomeAndNazionalita(b.getChef().getNome(), b.getChef().getCognome(), b.getChef().getNazionalita()) != null )
			return true;
		else return false;
	}

	public Chef getChefById(Long id) {
		return chefRepository.findById(id).get();
	}

	public Chef getChefByNomeAndCognomeAndNazionalita(Buffet b) {
		return chefRepository.findByNomeAndCognomeAndNazionalita(b.getChef().getNome(), b.getChef().getCognome(), b.getChef().getNazionalita());
	}

	public void updateBuffet(Buffet buffet) {
		this.aggiungiBuffet(buffet);
	}

	public List<Buffet> findAllConPiatto(Piatto piatto) {
		List<Buffet> buffets = new ArrayList<Buffet>();
		for(Buffet buffet : buffetRepository.findAll()) {
			if(buffet.getPiatti().contains(piatto)) {
				buffets.add(buffet);
			}	
		}
		return buffets;
	}

	public void removePiattoDaBuffet(List<Buffet> buffetConPiatto, Piatto piatto) {
		for(Buffet b : buffetConPiatto) {
			b.getPiatti().remove(piatto);
		}
		
	} 

}
