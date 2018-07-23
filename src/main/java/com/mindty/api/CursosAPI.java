package com.mindty.api;

import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mindty.modelos.Curso;
import com.mindty.modelos.Modulo;
import com.mindty.modelos.StatusMessage;
import com.mindty.persistence.CursoEM;
import com.mindty.persistence.ModuloEM;
import com.sun.jersey.api.client.ClientResponse.Status;

@Path("/cursos")
public class CursosAPI {

	private static Logger logger = Logger.getLogger("cursos");

	// david

	// david

	/* curl http://localhost:8080/MindtyREST/api/cursos -v */
	@Path("")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Response getCursos() {
		try {
			return Response.status(202).entity(CursoEM.getInstance().getListaCursos()).build();
		} catch (Exception e) {
			return Response.status(500).entity("Internal Server Error").build();
		}
	}

	/*
	 * curl -H "Content-Type: application/json" -X POST -d
	 * '{"idCurso":0,"nombreCurso":"Prueba","codCurso":"ABC 12345","horasCurso":
	 * "210","formador":"5"}' http://localhost:8080/MindtyREST/api/cursos -v
	 */
	@Path("")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	public Response addCursos(Curso nuevoCurso) {
		try {
			return Response.status(202).entity(CursoEM.getInstance().addCurso(nuevoCurso)).build();
		} catch (Exception e) {
			return Response.status(500).entity("Internal Server Error").build();
		}
	}

	/* curl http://localhost:8080/MindtyREST/api/cursos/1 -v */
	@Path("/{idc}")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Response getCurso(@PathParam("idc") int idc) {
		try {
			return Response.status(202).entity(CursoEM.getInstance().getCurso(idc)).build();
		} catch (Exception e) {
			return Response.status(500).entity("Internal Server Error").build();
		}
	}

	@Path("/{idc}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@PUT
	public Response updateCurso(@PathParam("idc") int idc) {
		try {
			return Response.status(202).entity(CursoEM.getInstance()).build();
		} catch (Exception e) {
			return Response.status(500).entity("Internal Server Error").build();
		}
	}

	@Path("/{idc}")
	@Produces(MediaType.APPLICATION_JSON)
	@DELETE
	public Response deleteCurso(@PathParam("idc") int idc) {
		try {
			return Response.status(202).entity(CursoEM.getInstance()).build();
		} catch (Exception e) {
			return Response.status(500).entity("Internal Server Error").build();
		}
	}

	////////////////////////////////////////////////////////////////
	// raul
	// Curso/modulos

	@Path("{idc}/modulos")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	//public Response getModulos(@HeaderParam("token") String token, @PathParam("idc") int idCurso)
	public Response getModulos( @PathParam("idc") int idCurso) {
		
		Autentication aut=new Autentication();
		String userEmail = "";

		//userEmail = aut.getUserEmailFromToken(token);
		
	
		/*if (userEmail == null) {
			StatusMessage statusMessage = new StatusMessage();
			statusMessage.setStatus(Status.FORBIDDEN.getStatusCode());
			statusMessage.setMessage("Access Denied for this functionality !!!");
			return Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMessage).build();
		}*/
		try {

			return Response.status(202).entity(ModuloEM.getInstance().getListaModulos(idCurso)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Ha habido un error al pedir los modulos").build();
		}

	}
/////////////////////////////////////////////////////////////////////////////////////////////////
	@Path("{idc}/modulos/{idm}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Response getModulo(@PathParam("idc") int idCurso, @PathParam("idm") int idModulo) {
		logger.info("Estoy aqui ...");
		System.out.println("HOlaaa");
		try {
			Modulo mLeido=new Modulo();
			mLeido = ModuloEM.getInstance().getModulo(idCurso, idModulo);
			if (mLeido != null)
				return Response.status(202).entity(mLeido).build();
			else
				return Response.status(400).entity("El modulo no existe en el sistema").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Ha habido un error al pedir el modulo").build();
		}

	}
////////////////////////////////////////////////////////////////////////////////////////////////////
	@Path("/{idc}/modulos")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	public Response addModulo(@PathParam("idc") int idCurso,Modulo moduloInsertar) {

		try {

			return Response.status(202).entity(ModuloEM.getInstance().InsertarModulo(idCurso, moduloInsertar)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Ha habido un error al pedir los pedidos").build();
		}

	}
//////////////////////////////////////////////////////////////////////////////////////////////////
	@Path("/{idc}/modulos/{idm}")
	@Produces(MediaType.APPLICATION_JSON)
	@DELETE
	public Response deleteModulo(@PathParam("idc") int idCurso, @PathParam("idm") int idModulo) {

		try {

			boolean bSalida = ModuloEM.getInstance().eliminarModulo(idCurso, idModulo);
			if (bSalida == true)
				return Response.status(202).entity(bSalida).build();
			else
				return Response.status(400).entity("El modulo no existe en el sistema").build();

		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Ha habido un error al pedir el modulo").build();
		}

	}
/////////////////////////////////////////////////////////////////////////////////////////////////	
	@Path("/{idc}/modulos/{idm}")
	@Produces(MediaType.APPLICATION_JSON)
	@PUT
	public Response updateModulo(@PathParam("idc") int idCurso, @PathParam("idm") int idModulo,Modulo moduloUpdate) {

		try {

			boolean bSalida = ModuloEM.getInstance().actualizarModulo(idCurso, idModulo, moduloUpdate);
			if (bSalida == true)
				return Response.status(202).entity(bSalida).build();
			else
				return Response.status(400).entity("El modulo no existe en el sistema").build();

		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Ha habido un error al pedir el modulo").build();
		}

	}


	// Codigo POST
	/*
	 * @Path("/{idc}/modulos/{idm}")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON)
	 * 
	 * @GET
	 * 
	 * 
	 * 
	 * @Path("/{idc}/modulos/{idm}")
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON)
	 * 
	 * @Produces(MediaType.APPLICATION_JSON)
	 * 
	 * @PUT
	 * 
	 * 
	 * @Path("/{idc}/modulos/{idm}")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON)
	 * 
	 * @DELETE
	 * 
	 * /////////////////////////////////////////// //hector //
	 * cursos/modulos/nidades
	 * 
	 * 
	 * @Path("/{idc}/modulos/{idm}/unidades")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON)
	 * 
	 * @GET // Codigo get
	 * 
	 * 
	 * 
	 * @Path("/{idc}/modulos/{idm}/unidades")
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON)
	 * 
	 * @Produces(MediaType.APPLICATION_JSON)
	 * 
	 * @POST //Codigo POST
	 * 
	 * @Path("/{idc}/modulos/{idm}/unidades/{iduni}")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON)
	 * 
	 * @GET
	 * 
	 * 
	 * 
	 * @Path("/{idc}/modulos/{idm}/unidades/{iduni}")
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON)
	 * 
	 * @Produces(MediaType.APPLICATION_JSON)
	 * 
	 * @PUT
	 * 
	 * 
	 * @Path("/{idc}/modulos/{idm}/unidades/{iduni}")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON)
	 * 
	 * @DELETE
	 * 
	 */


}
