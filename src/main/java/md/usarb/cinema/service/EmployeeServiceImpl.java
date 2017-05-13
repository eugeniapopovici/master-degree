package md.usarb.cinema.service;

import flexjson.JSONSerializer;
import md.usarb.cinema.model.Employee;
import md.usarb.cinema.repository.PostgreSQLGenericDao;
import md.usarb.cinema.repository.SQLGenericDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Natalia Balan
 */
@Path("")
public class EmployeeServiceImpl {
    private SQLGenericDao<Employee> sqlDao = new SQLGenericDao<>();
    private PostgreSQLGenericDao<Employee> postgreSQLDao = new PostgreSQLGenericDao();

    @GET
    @Path("/cinemas/all")
    @Produces({MediaType.APPLICATION_JSON + "; charset=UTF-8"})
    public String loadAllMovies(){
        List<Employee> sqlEmployees = sqlDao.findAll(Employee.class);
        List<Employee> psEmployees = postgreSQLDao.findAll(Employee.class);

        List<Employee> result = new ArrayList<>();
        result.add(sqlEmployees.get(0));
        result.add(psEmployees.get(0));

        return new JSONSerializer().rootName("data").serialize(result);
    }
}
