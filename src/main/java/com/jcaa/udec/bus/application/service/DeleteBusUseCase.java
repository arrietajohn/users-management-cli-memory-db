package com.jcaa.udec.bus.application.service;

import com.jcaa.udec.bus.domain.BusRepository;

public class DeleteBusUseCase {
    private final BusRepository busRepository;

    public DeleteBusUseCase(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    public void run(String id) {
        busRepository.delete(id);
    }
}