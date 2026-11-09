package com.jcaa.udec.ports;

import com.jcaa.udec.domain.Cd;
import java.util.List;

public interface CdRepository {
    void create(Cd cd);      
    Cd read(String id);       
    void update(Cd cd);       
    void delete(String id);   
    List<Cd> listAll();      
}
