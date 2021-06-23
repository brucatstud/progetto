package it.uniroma3.siw.personal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.personal.model.Legna;


public interface LegnaRepository extends CrudRepository<Legna, Long> {

	public List<Legna> findByNome(String nome);

	public List<Legna> findByNomeOrProvenienza(String nome, String provenienza);

	public List<Legna> findByNomeAndProvenienza(String nome, String provenienza);

}
