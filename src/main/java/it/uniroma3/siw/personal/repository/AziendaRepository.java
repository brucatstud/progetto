package it.uniroma3.siw.personal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.personal.model.Azienda;
import it.uniroma3.siw.personal.model.Carne;
import it.uniroma3.siw.personal.model.Griglia;


public interface AziendaRepository extends CrudRepository<Azienda, Long> {

	public List<Azienda> findByPartitaIva(String partitaIva);

	public List<Azienda> findByPartitaIvaOrNome(String partitaIva, String nome);

	public List<Azienda> findByPartitaIvaAndNome(String partitaIva, String nome);

}
