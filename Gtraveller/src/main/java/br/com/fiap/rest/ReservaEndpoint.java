package br.com.fiap.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.fiap.dao.ReservaDAO;
import br.com.fiap.model.Reserva;

@Path("/reservas")
@Produces(MediaType.APPLICATION_JSON)
public class ReservaEndpoint {
	
	private ReservaDAO dao = new ReservaDAO();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Reserva> index() {
		return dao.getAll();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Reserva reserva) {
		if (reserva == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		try {
			dao.save(reserva);
			return Response.status(Response.Status.CREATED).entity(reserva).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();

		}
	}
	
	@GET
	@Path("{id}")
	public Response show(@PathParam("id") Long id) {
		if (id == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		Reserva reserva = dao.findById(id);
		if (reserva == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.status(Response.Status.OK).entity(reserva).build();

	}
	
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") Long id, Reserva reserva) {
		if (id == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		if (reserva == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		if (dao.findById(id) == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		reserva.setId(id);
		try {
			dao.update(reserva);
			return Response.status(Response.Status.OK).entity(reserva).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
		

	}

	
}
