package com.mindty.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mindty.modelos.Curso;
import com.mindty.modelos.Modulo;
import com.mindty.modelos.Usuario;

public class ModuloEM extends EntityManager {

	private static ModuloEM instance = null;

	public static final ModuloEM getInstance() {
		if (instance == null)
			instance = new ModuloEM();
		return instance;
	}

	protected ModuloEM() {
		super();
	}

	public List<Usuario> getListaUsuarios() {
		List<Usuario> usuarios = null;
		try {
			/* Hibernate */

			Session session = factory.openSession();

			Transaction t = session.beginTransaction();
			usuarios = session.createQuery("FROM Employee", Usuario.class).getResultList();

			t.commit();
			session.close();
			/* Hibernate */
		} catch (Exception e) {
			e.printStackTrace();
		}

		return usuarios;
	}

	public boolean save(Usuario usr) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		session.save(usr);

		t.commit();
		session.close();

		return true;

	}

	public List<Usuario> getListaAlumnos(int nIdModulo) {

		List<Curso> listaCurso = new ArrayList<Curso>();
		List<Modulo> listaCursoModulo = new ArrayList<Modulo>();
		List<Usuario> listaAlumnos = new ArrayList<Usuario>();
		try {
			/* Hibernate */

			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			listaCurso = session.createQuery("FROM Curso", Curso.class).getResultList();
			for (Curso curso : listaCurso) {

				if (curso.getIdCurso() == nIdModulo) {
					listaAlumnos = curso.getAlumnos();
					break;

				}
				break;
			}

			t.commit();
			session.close();
			/* Hibernate */
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaAlumnos;

	}

	public int idUsuario(String user) {

		int nId = 0;
		try {
			/* Hibernate */

			Session session = factory.openSession();

			Transaction t = session.beginTransaction();
			Usuario usr = (Usuario) session.createQuery("from Usuario us where us.email = :nombre")
					.setParameter("nombre", user).getSingleResult();

			nId = usr.getIdu();

			t.commit();
			session.close();
			/* Hibernate */
		} catch (Exception e) {
			e.printStackTrace();
		}

		return nId;
	}

	public List<Curso> getListaCurso(int nid) {
		List<Curso> listaCursoProfe = new ArrayList<Curso>();

		int nId = 0;
		try {
			/* Hibernate */

			Session session = factory.openSession();

			Transaction t = session.beginTransaction();
			listaCursoProfe = (List<Curso>) session.createQuery("from Curso WHERE formador.idu = :uid")
					.setParameter("uid", nid).getResultList();

			t.commit();
			session.close();
			/* Hibernate */
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaCursoProfe;
	}

	/////////////////////////////////////////////////////////////////////// API/////////////////////////////////////////////////////////////////
	public List<Modulo> getListaModulos(int nIdCurso) {

		Curso cursoBuscado = new Curso();
		try {
			/* Hibernate */

			Session session = factory.openSession();

			cursoBuscado = session.createQuery("FROM Curso WHERE idCurso=:idC", Curso.class).setParameter("idC", nIdCurso)
					.getSingleResult();
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return cursoBuscado.getModulo();
	}

	public Modulo getModulo(int nIdCurso, int nIdModulo) {

		List<Curso> listaCurso = new ArrayList<Curso>();
		List<Modulo> listaCursoModulo = new ArrayList<Modulo>();
		Modulo moduloEncontrado=new Modulo();
		String strNombreModulo = null;
		try {
			/* Hibernate */
			System.out.println("Hola");
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();

			listaCurso = session.createQuery("FROM Curso", Curso.class).getResultList();
			System.out.println("Aqui llego3" + listaCurso.toString());
			for (Curso curso : listaCurso) {

				if (curso.getIdCurso() == nIdCurso) {
					listaCursoModulo = curso.getModulo();
					System.out.println("Lo Encuentrooooo");

				}

				for (Modulo modulo : listaCursoModulo) {

					if (modulo.getIdm() == nIdModulo) {
						moduloEncontrado=modulo;
						System.out.println("Pillo el modulo : " + strNombreModulo);

					}
				}

			}

			t.commit();
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return moduloEncontrado;
	}

	public boolean eliminarModulo(int idCurso, int idModulo) {

		boolean isOk = false;

		try {
			/* Hibernate */
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();

			Modulo deleteModulo = (Modulo) session.createQuery("FROM Modulo WHERE idm=:id", Modulo.class)
					.setParameter("id", idModulo).getSingleResult();

			System.out.println(deleteModulo.getNombreModulo());
			session.delete(deleteModulo);
			isOk = true;
			t.commit();
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isOk;
	}

	public boolean actualizarModulo(int idCurso, int idModulo, Modulo moduloUpdate) {

		boolean isOk = false;

		try {
			/* Hibernate */
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();

			Modulo moduloLeido = (Modulo) session.createQuery("FROM Modulo WHERE idm=:id", Modulo.class)
					.setParameter("id", idModulo).getSingleResult();

			System.out.println("Mombre modulo enviado: " + moduloUpdate.getNombreModulo());
			System.out.println("Mombre modulo a modificar: " + moduloLeido.getNombreModulo());
			moduloLeido.setNombreModulo(moduloUpdate.getNombreModulo());

			session.update(moduloLeido);
			t.commit();

			System.out.println("Mombre modulo modificado:" + moduloLeido.getNombreModulo());
			isOk = true;

			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isOk;
	}

	public boolean InsertarModulo(int idCurso, Modulo moduloInsertar) {

		boolean isOk = false;

		Curso cursoBuscado = new Curso();
		List<Modulo> listaModulosCurso = new ArrayList<Modulo>();
		try {

			/* Hibernate */

			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			cursoBuscado = session.createQuery("FROM Curso WHERE idCurso=:id", Curso.class).setParameter("id", idCurso)
					.getSingleResult();
			listaModulosCurso = cursoBuscado.getModulo();
			System.out.println(cursoBuscado.getModulo().toString());
			
			listaModulosCurso.add(moduloInsertar);
			cursoBuscado.setModulo(listaModulosCurso);
			System.out.println(cursoBuscado.getModulo().toString());
			
			session.save(moduloInsertar);
			t.commit();

			isOk = true;

			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isOk;
	}

}