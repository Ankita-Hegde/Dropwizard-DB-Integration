package com.ankitadb;

import com.ankitadb.dao.EmployeeDataEntityDao;
import com.ankitadb.entity.Info;
import com.ankitadb.resources.EmployeeResources;
import com.ankitadb.resources.v2.EmpInfoResource;
import io.dropwizard.Application;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class EmployeeDBApplication extends Application<EmployeeDBConfiguration> {

    public static void main(final String[] args) throws Exception {
        new EmployeeDBApplication().run(args);
    }

    @Override
    public String getName() {
        return "EmployeeDB";
    }

    @Override
    public void run(final EmployeeDBConfiguration configuration,
                    final Environment environment) {

        EmployeeDataEntityDao employeeDataEntityDao = new EmployeeDataEntityDao(hibernateBundle.getSessionFactory());
        environment.jersey().register(new EmployeeResources(employeeDataEntityDao));

        environment.jersey().register(new EmpInfoResource(employeeDataEntityDao));
    }

    HibernateBundle<EmployeeDBConfiguration> hibernateBundle = new HibernateBundle<EmployeeDBConfiguration>(Info.class) {
        @Override
        public PooledDataSourceFactory getDataSourceFactory(EmployeeDBConfiguration employeeDBConfiguration) {
            return employeeDBConfiguration.getDataSourceFactory();
        }
    };

    @Override
    public void initialize(final Bootstrap<EmployeeDBConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }

}
