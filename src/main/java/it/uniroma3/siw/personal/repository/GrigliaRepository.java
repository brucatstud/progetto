package it.uniroma3.siw.personal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.personal.model.Carne;
import it.uniroma3.siw.personal.model.Griglia;


public interface GrigliaRepository extends CrudRepository<Griglia, Long> {

	public List<Griglia> findByModello(String modello);

	public List<Griglia> findByModelloOrMarca(String modello, String marca);

	public List<Griglia> findByModelloAndMarca(String modello, String marca);

}
