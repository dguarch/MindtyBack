package com.mindty.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mindty.modelos.Curso;
import com.mindty.modelos.Modulo;
import com.mindty.modelos.Usuario;


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

	public boolean putCurso(int idCurso,Curso cursoUpdater) {

		boolean isOk = false;

		try {
			/* Hibernate */
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();

			Curso cursoLeido = (Curso) session.createQuery("FROM Curso WHERE idCurso=:id", Curso.class)
					.setParameter("id", idCurso).getSingleResult();

			
			cursoLeido.setNombreCurso(cursoUpdater.getNombreCurso());

			session.update(cursoLeido);
			t.commit();

			
			isOk = true;

			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isOk;
	}

	public boolean deleteCurso(int idCurso) {
		boolean isOk = false;

		try {
			/* Hibernate */
			Session session = factory.openSession();
			Transaction tx = session.beginTransaction();
			
			Curso deleteCurso = getCurso(idCurso);
			
			session.delete(deleteCurso);
			tx.commit();
			session.close();
			/* Hibernate */
			
			isOk = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isOk;
	}
}
