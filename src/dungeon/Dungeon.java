package dungeon;

import entities.*;
import items.consumables.*;
import items.equipment.*;
import java.util.ArrayList;
import java.util.List;

public class Dungeon {
    private List<Floor> floors;

    public Dungeon() {
        this.floors = new ArrayList<>();
        initializeDungeon();
    }

    // ENCAPSULAMIENTO
    public List<Floor> getFloors() { return floors; }
    public Floor getFloor(int index) { return floors.get(index); }
    public int getTotalFloors() { return floors.size(); }

    private void initializeDungeon() {
        // Piso 1
        Floor floor1 = new Floor(1);
        floor1.addRoom(new Room("Sala de Descanso: Donde los programadores toman su café... o lo hacían.",
                new Enemy("Bug Critter", 20, 5, 2, 25),
                new Potion(), false));
        floor1.addRoom(new Room("Oficina Abandonada: Papeles y teclados por todas partes.",
                null, new EnergyCookie(), false));
        floor1.addRoom(new Room("Sala de Servidores: El zumbido es ensordecedor.",
                new Enemy("Error Elemental", 30, 8, 3, 35),
                null, false));
        floor1.addRoom(new Room("Cocina: Huele a café recién hecho... pero está vacío.",
                null, new Weapon("Espada de código", "Aumenta el ataque en 5", 5), false));
        floor1.addRoom(new Room("Jefatura: Aquí debería estar el café...",
                new Boss("Jefe de Proyecto Enojado", 50, 12, 5, 60),
                null, true));
        floors.add(floor1);

        // Piso 2
        Floor floor2 = new Floor(2);
        floor2.addRoom(new Room("Sótano de Archivos: Polvoriento y lleno de documentación antigua.",
                new Enemy("Documentación Viviente", 35, 10, 4, 40),
                null, false));
        floor2.addRoom(new Room("Sala de Reuniones: La pizarra tiene diagramas incomprensibles.",
                null, new Armor("Armadura de comentarios", "Aumenta la defensa en 3", 3), false));
        floor2.addRoom(new Room("Baño de Empleados: El lugar más misterioso de la oficina.",
                new Enemy("Cloaca Mutante", 40, 12, 6, 45),
                null, false));
        floor2.addRoom(new Room("Archivo de Código: Líneas y líneas de código legacy.",
                null, new Weapon("Báculo de refactorización", "Aumenta el ataque en 7", 7), false));
        floor2.addRoom(new Room("Sala del Servidor Principal: El corazón de la operación.",
                new Boss("Admin del Sistema", 70, 15, 8, 80),
                null, true));
        floors.add(floor2);

        // Piso 3
        Floor floor3 = new Floor(3);
        floor3.addRoom(new Room("Azotea: Una brisa fresca sopla... y el olor a café es más fuerte.",
                new Enemy("Guardián del Café", 45, 14, 7, 50),
                null, false));
        floor3.addRoom(new Room("Sala de Descanso Ejecutiva: Sofás de cuero y... ¡CAFÉ!",
                null, new MysteryCoffee(), false));
        floor3.addRoom(new Room("Sala de Máquinas: Donde la magia ocurre... o donde se rompe todo.",
                new Enemy("Máquina de Café Enfurecida", 55, 16, 9, 65),
                null, false));
        floor3.addRoom(new Room("Oficina del CEO: Lujosa pero... vacía.",
                null, new Armor("Túnica del arquitecto", "Aumenta la defensa en 5", 5), false));
        floor3.addRoom(new Room("Sala del Trono del Café: ¡EL ULTIMO OBSTACULO!",
                new Boss("BARISTA SUPREMO", 100, 20, 12, 150),
                null, true));
        floors.add(floor3);
    }
}