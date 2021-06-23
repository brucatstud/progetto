package it.uniroma3.siw.personal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.personal.model.Carne;


public interface CarneRepository extends CrudRepository<Carne, Long> {

	public List<Carne> findByNome(String nome);

	public List<Carne> findByNomeOrProvenienza(String nome, String provenienza);

	public List<Carne> findByNomeAndProvenienza(String nome, String provenienza);

}
