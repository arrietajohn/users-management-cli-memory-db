package com.jcaa.udec.bus.domain;

public class Bus {
    private String id;
    private String plate;
    private int capacity; // Límite estricto de 10 niños del caso de estudio

    public Bus(String id, String plate, int capacity) {
        this.id = id;
        this.plate = plate;
        this.capacity = capacity;
    }

    public String getId() {
        return id;
    }

    public String getPlate() {
        return plate;
    }

    public int getCapacity() {
        return capacity;
    }
}