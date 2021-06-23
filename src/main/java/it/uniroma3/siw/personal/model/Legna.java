package it.uniroma3.siw.personal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
@Entity
public @Data class Legna {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	 @Column(nullable = false)
	public String nome;
	 @Column(nullable = false)
		public String provenienza;
	 @Column(nullable = false)
	public String umidita;
	 @Column(nullable = false)
	public String aroma;
	 
	 

}
