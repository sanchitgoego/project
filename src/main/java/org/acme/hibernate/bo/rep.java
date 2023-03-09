package org.acme.hibernate.bo;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
@ApplicationScoped
public class rep{
    public List<Employee1> find(int lim,int off){
        return Employee1.findAll(Sort.by("update_time")).page(off,lim).<Employee1>stream().toList();
    }
}
