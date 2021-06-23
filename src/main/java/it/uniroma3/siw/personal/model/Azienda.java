package it.uniroma3.siw.personal.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;


@Entity
public @Data class Azienda {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	 @Column(nullable = false)
	public String nome;
	 @Column(nullable = false)
	public String partitaIva;
	 @Column(nullable = false)
	public String telefono;
	 @Column(nullable = false)
	public String indirizzoSede;
	 
	 @OneToMany
	 private List<Griglia> griglie;

}
