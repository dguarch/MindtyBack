package com.mindty.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int idu;
	
	@Column
	private String nombreUsuario;
	
	@Column
	private String password;
	
	@Column
	private String email;
	
	@Column
	private String telefono;
	
	@Column
	private int tipo;     // 1-Coordinador, 2-Profesor, 3-Alumno
	
	
	/* CONSTRUCTORES */
	
	public Usuario() {
	}
		
	public Usuario(int idu, String nombreUsuario, String password, String email, String telefono, int tipo) {
		this.idu = idu;
		this.nombreUsuario = nombreUsuario;
		this.password = password;
		this.email = email;
		this.telefono = telefono;
		this.tipo = tipo;
	}
	
	
	/* GETTERS & SETTERS */
	
	public int getIdu() {
		return idu;
	}

	public void setIdu(int idu) {
		this.idu = idu;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
}