package pol.edu.py.primerparcialbackend.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import pol.edu.py.primerparcialbackend.ejb.ClientesDAO;

@RequestScoped
@Path("servicios")
public class ServiciosRest {

    @Inject
    ClientesDAO clientesDAO;

}
