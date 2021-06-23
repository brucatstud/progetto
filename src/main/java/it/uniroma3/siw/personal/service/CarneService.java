package it.uniroma3.siw.personal.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.personal.model.Carne;
import it.uniroma3.siw.personal.model.Griglia;
import it.uniroma3.siw.personal.model.Pitmaster;
import it.uniroma3.siw.personal.repository.CarneRepository;
import it.uniroma3.siw.personal.repository.GrigliaRepository;
import it.uniroma3.siw.personal.repository.PitmasterRepository;


@Service
public class CarneService {
	
	@Autowired
	private CarneRepository carneRepository; 
	
	@Transactional
	public Carne inserisci(Carne carne) {
		return carneRepository.save(carne);
	}
	
	@Transactional
	public List<Carne> carnePerNomeAndProvenienza(String nome, String provenienza) {
		return carneRepository.findByNomeAndProvenienza(nome, provenienza);
	}

	@Transactional
	public List<Carne> tutti() {
		return (List<Carne>) carneRepository.findAll();
	}

	@Transactional
	public Carne carnePerId(Long id) {
		Optional<Carne> optional = carneRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	@Transactional
	public boolean alreadyExists(Carne carne) {
		List<Carne> carni = this.carneRepository.findByNomeAndProvenienza(carne.getNome(), carne.getProvenienza());
		if (carni.size() > 0)
			return true;
		else 
			return false;
	}


}
