package it.uniroma3.siw.personal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.personal.model.Pitmaster;


public interface PitmasterRepository extends CrudRepository<Pitmaster, Long> {

	public List<Pitmaster> findByMatricola(String matricola);

	public List<Pitmaster> findByMatricolaOrCognome(String matricola, String cognome);

	public List<Pitmaster> findByMatricolaAndCognome(String matricola, String cognome);

}
