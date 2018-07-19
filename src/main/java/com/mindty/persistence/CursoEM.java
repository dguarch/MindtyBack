package com.mindty.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mindty.modelos.Curso;


public class CursoEM extends EntityManager {

	private static CursoEM instance = null;

	public static final CursoEM getInstance() {
		if (instance == null)
			instance = new CursoEM();
		return instance;
	}

	public List<Curso> getListaCursos() {
		List<Curso> cursoLista = new ArrayList<Curso>();
		Session session = factory.openSession();
		System.out.println("probando conexión");
		cursoLista = session.createQuery("FROM Curso", Curso.class).getResultList();
		session.close();
		return cursoLista;
	}

	public Curso getCurso(int idc) {

		Curso unCurso = new Curso();

		/* Hibernate */
		Session session = factory.openSession();
		System.out.println("probando conexión");
		unCurso = (Curso) session.createQuery("FROM Curso WHERE idCurso=:id", Curso.class).setInteger("id", idc)
				.getSingleResult();
		session.close();

		return unCurso;
	}

	public int addCurso(Curso nuevoCurso) {
		int newId = 0;

		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		newId = (Integer) session.save(nuevoCurso);

		t.commit();
		session.close();

		return newId;
	}
}
