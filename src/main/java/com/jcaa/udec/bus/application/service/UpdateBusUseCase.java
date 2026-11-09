package com.jcaa.udec.bus.application.service;

import com.jcaa.udec.bus.domain.Bus;
import com.jcaa.udec.bus.domain.BusRepository;

public class UpdateBusUseCase {
    private final BusRepository busRepository;

    public UpdateBusUseCase(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    public void run(String id, String plate, int capacity) {
        if (capacity > 10) {
            throw new IllegalArgumentException("La capacidad máxima permitida por minibús es de 10 niños.");
        }
        Bus bus = new Bus(id, plate, capacity);
        busRepository.update(bus);
    }
}