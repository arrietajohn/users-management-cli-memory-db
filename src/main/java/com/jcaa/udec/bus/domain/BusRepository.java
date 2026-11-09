package com.jcaa.udec.bus.domain;

import java.util.List;
import java.util.Optional;

public interface BusRepository {
    void save(Bus bus);
    Optional<Bus> findById(String id);
    List<Bus> findAll();
    void update(Bus bus);
    void delete(String id);
}