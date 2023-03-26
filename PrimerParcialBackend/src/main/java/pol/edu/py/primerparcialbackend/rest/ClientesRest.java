package pol.edu.py.primerparcialbackend.rest;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/clientes")
public class ClientesRest {

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String find(@QueryParam("id") @DefaultValue("-1") Integer id) {
        return id.toString();
    }
}
