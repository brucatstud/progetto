package it.uniroma3.siw.personal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Data;


@Entity
public @Data class Carne {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	 @Column(nullable = false)
	public String nome;
	 @Column(nullable = false)
	public String provenienza;
	 @Column(nullable = false)
	public String dataDiMacello;
	 @Column(nullable = false)
	public int eta;
	 @Column(nullable = false)
	public String periodoFrollatura;
	 
	 @ManyToOne
	 private Griglia griglia;
	 
	 @ManyToOne
	 private Legna legna;

}
