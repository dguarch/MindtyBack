package com.mindty.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "modulo")
public class Modulo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idm;

	@Column
	private String nombreModulo;
	
	
	/* CONSTRUCTORES */
	
	public Modulo() {
	}
	
	public Modulo(int idm, String nombreModulo) {
		this.idm = idm;
		this.nombreModulo = nombreModulo;
	}

	
	/* GETTERS & SETTERS */
	
	public int getIdm() {
		return idm;
	}

	public void setIdm(int idm) {
		this.idm = idm;
	}

	public String getNombreModulo() {
		return nombreModulo;
	}

	public void setNombreModulo(String nombreModulo) {
		this.nombreModulo = nombreModulo;
	}
}