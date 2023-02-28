package com.ankitadb.dao;

import com.ankitadb.api.EmployeeModel;
import com.ankitadb.entity.Info;
import io.dropwizard.hibernate.AbstractDAO;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import java.security.Provider;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class EmployeeDataEntityDao extends AbstractDAO<Info> {


    public EmployeeDataEntityDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Info find(String empId){
        return get(empId);
    }

    public Info save(Info entity){
        return persist(entity);
    }


    public EmployeeModel[] findAll(){
        List<Info> empInfo = list((Query<Info>) namedQuery("com.ankitadb.entity.findAll"));
        EmployeeModel[] employeeData= parseEmpInfo(empInfo);
        return employeeData;
    }

    public EmployeeModel[] findById(int id){
        List<Info> empInfo = list((Query<Info>) namedQuery("com.ankitadb.entity.findById").setParameter("id",id));
        EmployeeModel[] employeeData= parseEmpInfo(empInfo);
        return employeeData;
    }

    public EmployeeModel[] update(int id,String name){
        System.out.println("id:"+id);
        namedQuery("com.ankitadb.entity.update").setParameter("id",id).setParameter("name",name).executeUpdate();
        EmployeeModel[] result = findById(id);
        return result;
    }

    public void delete(int id){
        namedQuery("com.ankitadb.entity.delete").setParameter("id",id).executeUpdate();
    }

    public EmployeeModel[] insert(EmployeeModel employeeModel){
        namedQuery("com.ankitadb.entity.insert").setParameter("id",employeeModel.getId()).setParameter("name",employeeModel.getName()).executeUpdate();
        System.out.println("id:"+employeeModel.getId());
        EmployeeModel[] result= findById(employeeModel.getId());
        return result;
    }

    EmployeeModel[] parseEmpInfo(List<Info> empInfo){
        Iterator it = empInfo.iterator();
        EmployeeModel[] empData = new EmployeeModel[empInfo.size()];
        int i=0;
        while (it.hasNext()){
            Object[] object = (Object[]) it.next();
            EmployeeModel employeeModel = new EmployeeModel();
            employeeModel.setName(String.valueOf(object[0]));
            employeeModel.setId(Integer.parseInt(String.valueOf(object[1])));
            empData[i]=employeeModel;
            i++;
        }
        return empData;
    }


    //Another way of executing queries

    public EmployeeModel[] updateV2(int id,String name){
        String queryString = "update Info set name=:name where id=:id";
        Query query = currentSession().createNativeQuery(queryString);
        query.setParameter("name",name);
        query.setParameter("id",id);
        query.executeUpdate();
        EmployeeModel[] empData= findById(id);
        return empData;
    }

    public EmployeeModel[] insertV2(EmployeeModel employeeModel){
        String queryString = "insert into Info values(:name,:id)";
        Query query = currentSession().createNativeQuery(queryString);
        query.setParameter("name",employeeModel.getName());
        query.setParameter("id",employeeModel.getId());
        query.executeUpdate();
        EmployeeModel[] empData = findById(employeeModel.getId());
        return empData;
    }


}
