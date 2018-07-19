package com.mindty.api;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
//import javax.ws.rs.POST;
//import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.lang.JoseException;

import com.mindty.modelos.StatusMessage;
import com.mindty.modelos.Usuario;
import com.mindty.persistence.UsuarioEM;


@Path("/usuarios")
public class UsuariosAPI {

	public static List<Usuario> usuarios;

	static List<JsonWebKey> jwkList = null;
	private static Logger logger = Logger.getLogger("JSONService");

	static {
		logger.info("Inside static initializer...");
		jwkList = new LinkedList<JsonWebKey>();
		// Creating three keys, will use one now
		for (int kid = 1; kid <= 3; kid++) {
			JsonWebKey jwk = null;
			try {
				jwk = RsaJwkGenerator.generateJwk(2048);
				logger.info("PUBLIC KEY (" + kid + "): " + jwk.toJson(JsonWebKey.OutputControlLevel.PUBLIC_ONLY));
			} catch (JoseException e) {
				e.printStackTrace();
			}
			jwk.setKeyId(String.valueOf(kid));
			jwkList.add(jwk);
		}

	}

	// IVAN
	@Path("/authenticate")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	// Codigo get
	public Response getTokenUsuarios(@HeaderParam("email") String stremail,
			@HeaderParam("password") String password) {
		try {

			if (stremail== null) {
				StatusMessage statusMessage = new StatusMessage();
				statusMessage.setStatus(Status.PRECONDITION_FAILED.getStatusCode());
				statusMessage.setMessage("Username value is missing!!!");
				return Response.status(Status.PRECONDITION_FAILED.getStatusCode()).entity(statusMessage).build();
			}

			if (password == null) {
				StatusMessage statusMessage = new StatusMessage();
				statusMessage.setStatus(Status.PRECONDITION_FAILED.getStatusCode());
				statusMessage.setMessage("Password value is missing!!!");
				return Response.status(Status.PRECONDITION_FAILED.getStatusCode()).entity(statusMessage).build();
			}

			System.out.println("aqui llego");
			int nTipoUsuario = UsuarioEM.getInstance().getUsuario(stremail, password);
			System.out.println("aqui llego1");
			
			if (nTipoUsuario == 0) {
				StatusMessage statusMessage = new StatusMessage();
				statusMessage.setStatus(Status.FORBIDDEN.getStatusCode());
				statusMessage.setMessage("Access Denied for this functionality !!!");
				return Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMessage).build();
			}
			
			
			RsaJsonWebKey senderJwk = (RsaJsonWebKey) jwkList.get(0);

			senderJwk.setKeyId("1");
			logger.info("JWK (1) ===> " + senderJwk.toJson());

			// Create the Claims, which will be the content of the JWT
			JwtClaims claims = new JwtClaims();
			claims.setIssuer("netmind.com"); // who creates the token and signs it
			claims.setExpirationTimeMinutesInTheFuture(10); // token will expire (10
															// minutes from now)
			claims.setGeneratedJwtId(); // a unique identifier for the token
			claims.setIssuedAtToNow(); // when the token was issued/created (now)
			claims.setNotBeforeMinutesInThePast(2); // time before which the token
													// is not yet valid (2 minutes
			System.out.println("aqui llego2");										// ago)
			claims.setSubject(stremail); // the subject/principal is whom
												// the token is about
			claims.setStringListClaim("roles", "client"); //
			// multi-valued claims for roles
			JsonWebSignature jws = new JsonWebSignature();

			jws.setPayload(claims.toJson());

			jws.setKeyIdHeaderValue(senderJwk.getKeyId());
			jws.setKey(senderJwk.getPrivateKey());

			jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);

			String jwt = null;
			try {
				jwt = jws.getCompactSerialization();
			} catch (JoseException e) {
				e.printStackTrace();
			}

			return Response.status(200).entity(jwt).build();
			
			
			
			
			// return Response.status(202).entity(nTipo).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Ha habido un error al pedir los pedidos").build();
		}
	}
	
	
	@Path("")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Response getCursos() {
		try {
			return Response.status(202).entity(UsuarioEM.getInstance().getListaUsuarios()).build();
		} catch (Exception e) {
			return Response.status(500).entity("Internal Server Error").build();
		}
	}
	
	// IVAN
	@Path("")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	// Codigo POST
	public Response addUsuario(Usuario nuevoUsuario) {
		try {

			return Response.status(202).entity(UsuarioEM.getInstance().inserta(nuevoUsuario)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR.getStatusCode())
					.entity("Ha habido un error al guardar el pedido").build();
		}
	}

	@Path("/{idu}")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Response getUsuariosPorId(@PathParam(value = "idu") int idu) {
		try {
			return Response.status(202).entity(UsuarioEM.getInstance().getUsuarioPorId(idu)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Ha habido un error al pedir los pedidos").build();
		}
	}
	
	
	
	 @Path("/{idu}") //HTV
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.APPLICATION_JSON)
	 @PUT
		public Response updateModulo(@PathParam("idu") int idu, Usuario usuarioUpdate) {

			try {

				boolean bSalida = UsuarioEM.getInstance().actualizarUsuario(idu, usuarioUpdate);
				if (bSalida == true)
					return Response.status(202).entity(bSalida).build();
				else
					return Response.status(400).entity("El Usuario no existe en el sistema").build();

			} catch (Exception e) {
				e.printStackTrace();
				return Response.status(500).entity("Ha habido un error al pedir el modulo").build();
			}

		}
	
	 @Path("/{idu}")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
	 	@DELETE
		public Response deleteUsuario(@PathParam(value = "idu") int idu) {
			
			try {
				return Response.status(202).entity(UsuarioEM.getInstance().deleteUsuarioPorId(idu)).build();
			} catch (Exception e) {
				e.printStackTrace();
				return Response.status(Status.INTERNAL_SERVER_ERROR.getStatusCode()).entity("Ha habido un error al guardar el pedido").build();
			}
			
		}

}
