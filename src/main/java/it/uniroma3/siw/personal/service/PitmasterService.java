package it.uniroma3.siw.personal.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.personal.model.Pitmaster;
import it.uniroma3.siw.personal.repository.PitmasterRepository;


@Service
public class PitmasterService {
	
	@Autowired
	private PitmasterRepository pitmasterRepository; 
	
	@Transactional
	public Pitmaster inserisci(Pitmaster pitmaster) {
		return pitmasterRepository.save(pitmaster);
	}
	
	@Transactional
	public List<Pitmaster> artistaPerNomeAndCognome(String matricola, String cognome) {
		return pitmasterRepository.findByMatricolaAndCognome(matricola, cognome);
	}

	@Transactional
	public List<Pitmaster> tutti() {
		return (List<Pitmaster>) pitmasterRepository.findAll();
	}

	@Transactional
	public Pitmaster pitmasterPerId(Long id) {
		Optional<Pitmaster> optional = pitmasterRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	@Transactional
	public boolean alreadyExists(Pitmaster pitmaster) {
		List<Pitmaster> artisti = this.pitmasterRepository.findByMatricolaAndCognome(pitmaster.getNome(), pitmaster.getCognome());
		if (artisti.size() > 0)
			return true;
		else 
			return false;
	}


}
