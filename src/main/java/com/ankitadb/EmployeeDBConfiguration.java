package com.ankitadb;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.db.DataSourceFactory;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import java.util.Objects;

public class EmployeeDBConfiguration extends Configuration {

    private String url;

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }


    private DataSourceFactory dataSourceFactory;

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        if(Objects.isNull(dataSourceFactory))
            dataSourceFactory = new DataSourceFactory();
        return dataSourceFactory;
    }

    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory dataSourceFactory) {
        this.dataSourceFactory = dataSourceFactory;
    }




}
