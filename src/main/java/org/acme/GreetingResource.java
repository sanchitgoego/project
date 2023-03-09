package org.acme;
import org.acme.hibernate.bo.Employee1;
import org.acme.hibernate.bo.rep;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.Query;
@Path("/employee")
public class GreetingResource {
    @Inject
    EntityManager em;
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response hello1(Employee1 emp){
        Timestamp ts=new Timestamp(System.currentTimeMillis());
        emp.setUpdate_time(ts);
        em.persist(emp);
        return Response.status(201).entity(emp).build();
    }
    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") Long id,Employee1 emp){
        Employee1 emp1=em.find(Employee1.class,id);
        if (emp1!=null){
            if(emp.getFN()!=null)
                emp1.setFN(emp.getFN());
            if(emp.getLN()!=null)
                emp1.setLN(emp.getLN());
            if(emp.getSalary()!=0)
                emp1.setSalary(emp.getSalary());
            if(emp.getAge()!=0)
                emp1.setAge(emp.getAge());
            Timestamp ts=new Timestamp(System.currentTimeMillis());
            emp1.setUpdate_time(ts);
            em.persist(emp1);
            return Response.ok(emp1).build();
        }
        else{
            System.out.println("Not Found!!");
            return Response.status(404).build();
        }
    }
    @GET
    @Path("/{id}")
    public Employee1 getEmployeeById(@PathParam("id") Long id){
        Employee1 emp1=em.find(Employee1.class,id);
        return emp1;
    }
    @Inject
    rep re;
    @GET
    public List<Employee1> getAllEmployee(@QueryParam("limit") int limit,@QueryParam("offset") int offset){
        return re.find(limit,offset);
    }
}