package it.uniroma3.siw.personal.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.personal.model.Griglia;
import it.uniroma3.siw.personal.model.Pitmaster;
import it.uniroma3.siw.personal.repository.GrigliaRepository;
import it.uniroma3.siw.personal.repository.PitmasterRepository;


@Service
public class GrigliaService {
	
	@Autowired
	private GrigliaRepository grigliaRepository; 
	
	@Transactional
	public Griglia inserisci(Griglia griglia) {
		return grigliaRepository.save(griglia);
	}
	
	@Transactional
	public List<Griglia> grigliaPerModelloAndMarca(String modello, String marca) {
		return grigliaRepository.findByModelloAndMarca(modello, marca);
	}

	@Transactional
	public List<Griglia> tutti() {
		return (List<Griglia>) grigliaRepository.findAll();
	}

	@Transactional
	public Griglia grigliaPerId(Long id) {
		Optional<Griglia> optional = grigliaRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	@Transactional
	public boolean alreadyExists(Griglia griglia) {
		List<Griglia> artisti = this.grigliaRepository.findByModelloAndMarca(griglia.getModello(), griglia.getMarca());
		if (artisti.size() > 0)
			return true;
		else 
			return false;
	}



}
