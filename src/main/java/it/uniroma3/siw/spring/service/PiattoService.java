package it.uniroma3.siw.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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

}
