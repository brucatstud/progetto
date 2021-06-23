package it.uniroma3.siw.personal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
@Entity
public @Data class Pitmaster {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	 @Column(nullable = false)
		public String matricola;
	 @Column(nullable = false)
		public String nome;
	 @Column(nullable = false)
	public String cognome;
	 @Column(nullable = false)
		public String email;
	 @Column(nullable = false)
		public String nazionalita;
	 @Column(nullable = false)
	public String numeroDiTelefono;
	 
	 @ManyToOne
	 private Griglia griglia;

}
