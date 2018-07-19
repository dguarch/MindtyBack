package com.mindty.modelos;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

@Entity
@Table(name = "curso")
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCurso;

	@Column(unique = true)
	private String codCurso;

	@Column
	private String nombreCurso;
	
	@Column
	private int horasCurso;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
        name = "curso_formador", 
        joinColumns = { @JoinColumn(name = "idCurso") }, 
        inverseJoinColumns = { @JoinColumn(name = "idFormador") }
    )
	private Usuario formador;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@OrderColumn(name="idu")
    @JoinTable(
        name = "curso_alumno", 
        joinColumns = { @JoinColumn(name = "idCurso") }, 
        inverseJoinColumns = { @JoinColumn(name = "idAlumno") }
    )
	private List<Usuario> alumnos;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
        name = "curso_modulo", 
        joinColumns = { @JoinColumn(name = "idCurso") }, 
        inverseJoinColumns = { @JoinColumn(name = "idModulo") }
    )
	private List<Modulo> modulo;
	
	
	/* CONSTRUCTORES */
	
	public Curso() {
	}

	public Curso(int idCurso, String codCurso, String nombreCurso, Usuario formador, List<Usuario> alumnos,	int horasCurso, List<Modulo> modulo) {
		this.idCurso = idCurso;
		this.codCurso = codCurso;
		this.nombreCurso = nombreCurso;
		this.formador = formador;
		this.alumnos = alumnos;
		this.horasCurso = horasCurso;
		this.modulo = modulo;
	}
	
	
	/* GETTERS & SETTERS */
	
	public int getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}

	public String getCodCurso() {
		return codCurso;
	}

	public void setCodCurso(String codCurso) {
		this.codCurso = codCurso;
	}

	public String getNombreCurso() {
		return nombreCurso;
	}

	public void setNombreCurso(String nombreCurso) {
		this.nombreCurso = nombreCurso;
	}

	public Usuario getFormador() {
		return formador;
	}

	public void setFormador(Usuario formador) {
		this.formador = formador;
	}

	public List<Usuario> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<Usuario> alumnos) {
		this.alumnos = alumnos;
	}

	public int getHorasCurso() {
		return horasCurso;
	}

	public void setHorasCurso(int horasCurso) {
		this.horasCurso = horasCurso;
	}

	public List<Modulo> getModulo() {
		return modulo;
	}

	public void setModulo(List<Modulo> modulo) {
		this.modulo = modulo;
	}
}