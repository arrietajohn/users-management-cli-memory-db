package com.jcaa.udec.bus.application.service;

import com.jcaa.udec.bus.domain.Bus;
import com.jcaa.udec.bus.domain.BusRepository;
import java.util.List;

public class ListBusUseCase {
    private final BusRepository busRepository;

    public ListBusUseCase(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    public List<Bus> run() {
        return busRepository.findAll();
    }
}