package com.jcaa.udec.bus.infrastructure.persistence;

import com.jcaa.udec.bus.domain.Bus;
import com.jcaa.udec.bus.domain.BusRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BusMemoryAdapter implements BusRepository {
    private final List<Bus> database = new ArrayList<>();

    @Override
    public void save(Bus bus) {
        database.add(bus);
    }

    @Override
    public Optional<Bus> findById(String id) {
        return database.stream()
                .filter(bus -> bus.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Bus> findAll() {
        return new ArrayList<>(database);
    }

    @Override
    public void update(Bus bus) {
        delete(bus.getId());
        database.add(bus);
    }

    @Override
    public void delete(String id) {
        database.removeIf(bus -> bus.getId().equals(id));
    }
}