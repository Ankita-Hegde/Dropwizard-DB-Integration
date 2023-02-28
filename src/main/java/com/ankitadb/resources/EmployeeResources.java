package com.ankitadb.resources;

import com.ankitadb.api.EmployeeModel;
import com.ankitadb.dao.EmployeeDataEntityDao;
import com.ankitadb.entity.Info;
import io.dropwizard.hibernate.UnitOfWork;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/emp")
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeResources {

    private final EmployeeDataEntityDao employeeDataEntityDao;

    public EmployeeResources(EmployeeDataEntityDao employeeDataEntityDao) {
        this.employeeDataEntityDao = employeeDataEntityDao;
    }


    @GET
    @Path("/getAll")
    @UnitOfWork
    public Response getAllInfo(){return Response.ok(employeeDataEntityDao.findAll()).build();}

    @GET
    @Path("{id}")
    @UnitOfWork
    public Response getById(@PathParam("id") int id){
        return Response.ok(employeeDataEntityDao.findById(id)).build();
    }

    @PUT
    @Path("{id}")
    @UnitOfWork
    public Response modify(@PathParam("id") int id, @QueryParam("name") String name){
        return Response.ok(employeeDataEntityDao.update(id,name)).build();
    }

    @DELETE
    @Path("{id}")
    @UnitOfWork
    public Response remove(@PathParam("id") int id){
        employeeDataEntityDao.delete(id);
        return Response.ok("Entry with id:"+ id +" deleted successfully.").build();
    }

    @POST
    @Path("/create")
    @UnitOfWork
    public Response add(EmployeeModel employeeModel){
        return Response.ok(employeeDataEntityDao.insert(employeeModel)).build();
    }

    @POST
    @Path("/empInfo")
    @UnitOfWork
    public int createEmp(@Valid Info employeeDataEntity){
        return employeeDataEntityDao.save(employeeDataEntity).getEmpId();
    }

}
