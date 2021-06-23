package it.uniroma3.siw.personal.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.personal.model.Carne;
import it.uniroma3.siw.personal.model.Griglia;
import it.uniroma3.siw.personal.model.Legna;
import it.uniroma3.siw.personal.model.Pitmaster;
import it.uniroma3.siw.personal.repository.CarneRepository;
import it.uniroma3.siw.personal.repository.GrigliaRepository;
import it.uniroma3.siw.personal.repository.LegnaRepository;
import it.uniroma3.siw.personal.repository.PitmasterRepository;


@Service
public class LegnaService {
	
	@Autowired
	private LegnaRepository legnaRepository; 
	
	@Transactional
	public Legna inserisci(Legna legna) {
		return legnaRepository.save(legna);
	}
	
	@Transactional
	public List<Legna> carnePerNomeAndProvenienza(String nome, String provenienza) {
		return legnaRepository.findByNomeAndProvenienza(nome, provenienza);
	}

	@Transactional
	public List<Legna> tutti() {
		return (List<Legna>) legnaRepository.findAll();
	}

	@Transactional
	public Legna legnaPerId(Long id) {
		Optional<Legna> optional = legnaRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	@Transactional
	public boolean alreadyExists(Legna legna) {
		List<Legna> legni = this.legnaRepository.findByNomeAndProvenienza(legna.getNome(), legna.getProvenienza());
		if (legni.size() > 0)
			return true;
		else 
			return false;
	}


}
