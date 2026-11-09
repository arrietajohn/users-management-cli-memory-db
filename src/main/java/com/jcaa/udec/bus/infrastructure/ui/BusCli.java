package com.jcaa.udec.bus.infrastructure.ui;

import com.jcaa.udec.bus.application.service.*;
import com.jcaa.udec.bus.domain.Bus;
import com.jcaa.udec.bus.infrastructure.persistence.BusMemoryAdapter;

import java.util.Scanner;

public class BusCli {
    public static void start() {
        BusMemoryAdapter repository = new BusMemoryAdapter();
        
        CreateBusUseCase createBus = new CreateBusUseCase(repository);
        ListBusUseCase listBuses = new ListBusUseCase(repository);
        FindBusByIdUseCase findBus = new FindBusByIdUseCase(repository);
        UpdateBusUseCase updateBus = new UpdateBusUseCase(repository);
        DeleteBusUseCase deleteBus = new DeleteBusUseCase(repository);

        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n--- GESTIÓN DE BUSES - RUTA TÍO JUANITO ---");
            System.out.println("1. Registrar Bus");
            System.out.println("2. Listar Buses");
            System.out.println("3. Buscar Bus por ID");
            System.out.println("4. Actualizar Bus");
            System.out.println("5. Eliminar Bus");
            System.out.println("0. Salir");
            System.out.print("Elija una opción: ");
            
            option = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (option) {
                case 1:
                    System.out.print("Ingrese ID del bus: ");
                    String id = scanner.nextLine();
                    System.out.print("Ingrese placa: ");
                    String plate = scanner.nextLine();
                    System.out.print("Ingrese capacidad (máx 10 niños): ");
                    int capacity = scanner.nextInt();
                    try {
                        createBus.run(id, plate, capacity);
                        System.out.println("¡Bus registrado con éxito!");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("\n--- LISTA DE BUSES ---");
                    for (Bus b : listBuses.run()) {
                        System.out.println("ID: " + b.getId() + " | Placa: " + b.getPlate() + " | Capacidad: " + b.getCapacity());
                    }
                    break;
                case 3:
                    System.out.print("Ingrese ID a buscar: ");
                    String searchId = scanner.nextLine();
                    findBus.run(searchId).ifPresentOrElse(
                        b -> System.out.println("Encontrado -> Placa: " + b.getPlate() + " | Capacidad: " + b.getCapacity()),
                        () -> System.out.println("Bus no encontrado.")
                    );
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (option != 0);
    }
}