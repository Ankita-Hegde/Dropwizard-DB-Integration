package com.ankitadb.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "info")
@NamedQueries({ @NamedQuery(name = "com.ankitadb.entity.findAll",query = "select name,id from Info e"),
                @NamedQuery(name = "com.ankitadb.entity.findById",query = "select name,id from Info where id=:id"),
                @NamedQuery(name = "com.ankitadb.entity.update",query = "update Info set name=:name where id=:id"),
                @NamedQuery(name = "com.ankitadb.entity.delete", query = "delete from Info where id=:id")})

@NamedNativeQueries({@NamedNativeQuery(name = "com.ankitadb.entity.insert", query = "insert into Info values(:name,:id)")})

public class Info {

    @Column(name = "name")
    private String name;

    @Id
    @Column(name = "id")
    private int empId;

    public String getName() { return name; }
    public int getEmpId() { return empId; }

    public void setName(String name) { this.name = name; }
    public void setEmpId(int empId) { this.empId = empId; }

}
