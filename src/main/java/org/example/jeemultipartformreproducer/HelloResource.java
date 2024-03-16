package org.example.jeemultipartformreproducer;

import static jakarta.ws.rs.core.MediaType.MULTIPART_FORM_DATA;

import java.io.IOException;
import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.EntityPart;

@Path("/")
public class HelloResource {
	@POST
	@Path("/multipart-list")
	@Produces("text/plain")
	@Consumes(MULTIPART_FORM_DATA)
	public String multipartFormList(List<EntityPart> parts) throws IOException {
		return "Hello " + firstName(parts);
	}

	private static String firstName(List<EntityPart> parts) throws IOException {
		return parts.stream()
				.filter(part -> "firstName".equals(part.getName()))
				.findFirst().orElseThrow()
				.getContent(String.class);
	}

	@POST
	@Path("/multipart-part")
	@Produces("text/plain")
	@Consumes(MULTIPART_FORM_DATA)
	public String multipartFormPart(@FormParam("firstName") String firstName) {
		return "Hello, " + firstName;
	}
}
