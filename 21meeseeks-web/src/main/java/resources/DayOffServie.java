package resources;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import entities.DayOff;
import interfaces.DayOffServieLocal;

@Path("dayOff")
public class DayOffServie {
	@EJB
	DayOffServieLocal df;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addDayOff(DayOff d) {
		if (d != null) {
			int i = df.addDayOff(d);
		}
		return Response.status(Status.CREATED).entity("ok").build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllDayOff() {
		List<DayOff> list = new ArrayList<>();
		list = df.getAllDayOff();
		return Response.status(Status.FOUND).entity(list).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchDayOff(@PathParam(value = "id") int id) {
		DayOff d = df.findDayOff(id);
		return Response.status(Status.FOUND).entity(d).build();

	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteDayOff(@PathParam(value = "id") int id) {
		Boolean b = df.deleteDayOff(id);
		return Response.status(Status.OK).entity(b).build();

	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateDayOff(DayOff d) {
		df.updateDayOff(d);
		return Response.status(Status.OK).entity("update successful").build();
	}

}
