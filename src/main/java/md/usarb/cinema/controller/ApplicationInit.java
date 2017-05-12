package md.usarb.cinema.controller;

import md.usarb.cinema.repository.PostgreSQLGenericDao;
import md.usarb.cinema.repository.SQLGenericDao;
import md.usarb.cinema.service.EmployeeServiceImpl;

import javax.persistence.Persistence;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Class to initialize app server without web.xml file
 */
@ApplicationPath("/cinema/")
public class ApplicationInit extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        PostgreSQLGenericDao.entityManagerFactory = Persistence.createEntityManagerFactory("PGSQLPU");
        SQLGenericDao.entityManagerFactory = Persistence.createEntityManagerFactory("MSSQLPU");
        Set<Class<?>> classes = new HashSet<>();
        classes.add(EmployeeServiceImpl.class);
        return classes;
    }
}