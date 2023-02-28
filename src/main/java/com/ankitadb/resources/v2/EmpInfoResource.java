package com.ankitadb.resources.v2;

import com.ankitadb.api.EmployeeModel;
import com.ankitadb.dao.EmployeeDataEntityDao;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v2")
@Produces(MediaType.APPLICATION_JSON)
public class EmpInfoResource {

    private final EmployeeDataEntityDao employeeDataEntityDao;

    public EmpInfoResource(EmployeeDataEntityDao employeeDataEntityDao) {
        this.employeeDataEntityDao = employeeDataEntityDao;
    }

    @PUT
    @Path("{id}")
    @UnitOfWork
    public Response modifyV2(@PathParam("id") int id, @QueryParam("name") String name){
        return Response.ok(employeeDataEntityDao.updateV2(id,name)).build();
    }

    @POST
    @Path("/create")
    @UnitOfWork
    public Response addV2(EmployeeModel employeeModel){
        return Response.ok(employeeDataEntityDao.insertV2(employeeModel)).build();
    }

}
