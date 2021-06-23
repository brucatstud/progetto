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
public @Data class Griglia {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	 @Column(nullable = false)
		public String modello;
	 @Column(nullable = false)
		public String marca;
	 @Column(nullable = false)
	public String capacita;
	 @Column(nullable = false)
		public String tempMassima;
	 @Column(nullable = false)
		public String descrizione;
	 
	 @OneToMany
	 private List<Pitmaster> Pitmasters;
	 
	 @OneToMany
	 private List<Carne> carne;
	 
	 @ManyToOne
	 private Azienda azienda;
	 
	 

}
