package it.uniroma3.siw.personal.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.personal.model.Azienda;
import it.uniroma3.siw.personal.model.Carne;
import it.uniroma3.siw.personal.model.Griglia;
import it.uniroma3.siw.personal.model.Legna;
import it.uniroma3.siw.personal.model.Pitmaster;
import it.uniroma3.siw.personal.repository.AziendaRepository;
import it.uniroma3.siw.personal.repository.CarneRepository;
import it.uniroma3.siw.personal.repository.GrigliaRepository;
import it.uniroma3.siw.personal.repository.LegnaRepository;
import it.uniroma3.siw.personal.repository.PitmasterRepository;


@Service
public class AziendaService {
	
	@Autowired
	private AziendaRepository aziendaRepository; 
	
	@Transactional
	public Azienda inserisci(Azienda azienda) {
		return aziendaRepository.save(azienda);
	}
	
	@Transactional
	public List<Azienda> aziendaPerNomeAndPartitaIva(String partitaIva, String nome) {
		return aziendaRepository.findByPartitaIvaAndNome(partitaIva, nome);
	}

	@Transactional
	public List<Azienda> tutti() {
		return (List<Azienda>) aziendaRepository.findAll();
	}

	@Transactional
	public Azienda aziendaPerId(Long id) {
		Optional<Azienda> optional = aziendaRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	@Transactional
	public boolean alreadyExists(Azienda azienda) {
		List<Azienda> aziende = this.aziendaRepository.findByPartitaIvaAndNome(azienda.getPartitaIva(), azienda.getNome());
		if (aziende.size() > 0)
			return true;
		else 
			return false;
	}


}
