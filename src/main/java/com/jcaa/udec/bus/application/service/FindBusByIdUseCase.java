package com.jcaa.udec.bus.application.service;

import com.jcaa.udec.bus.domain.Bus;
import com.jcaa.udec.bus.domain.BusRepository;
import java.util.Optional;

public class FindBusByIdUseCase {
    private final BusRepository busRepository;

    public FindBusByIdUseCase(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    public Optional<Bus> run(String id) {
        return busRepository.findById(id);
    }
}