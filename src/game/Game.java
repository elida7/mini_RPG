package game;

import dungeon.Dungeon;
import dungeon.Floor;
import dungeon.Room;
import entities.Player;
import entities.Enemy;
import items.Item;
import items.equipment.Equipment;
import java.util.Scanner;

public class Game {
    private GameState gameState;
    private Dungeon dungeon;
    private Player player;
    private BattleSystem battleSystem;
    private Scanner scanner;

    public Game() {
        this.gameState = new GameState();
        this.dungeon = new Dungeon();
        this.scanner = new Scanner(System.in);
        this.battleSystem = new BattleSystem(scanner);
    }

    public void startGame(String playerName) {
        this.player = new Player(playerName);
        
        System.out.println("\n¡" + playerName + " comienza su aventura!");
        System.out.println("Tu misión: Encontrar el café perdido en las profundidades de la oficina.");
        
        // Bucle principal del juego
        while (!gameState.isGameOver() && !gameState.isGameWon()) {
            gameState.displayGameState();
            
            Floor currentFloor = dungeon.getFloor(gameState.getCurrentFloor());
            Room currentRoom = currentFloor.getRoom(gameState.getCurrentRoom());
            
            // Entrar a la sala
            Item collectedItem = currentRoom.enterRoom(player);
            
            // Si se recogió un Equipment, preguntar si quiere equiparlo
            if (collectedItem != null && collectedItem instanceof Equipment) {
                System.out.println("\n¿Quieres equipar " + collectedItem.getName() + "? (s/n)");
                String response = scanner.nextLine().toLowerCase();
                if (response.equals("s") || response.equals("si") || response.equals("sí")) {
                    ((Equipment) collectedItem).use(player);
                }
            }
            
            // Manejar enemigo si existe
            if (currentRoom.hasEnemy()) {
                Enemy enemy = currentRoom.getEnemy();
                boolean playerWon = battleSystem.startBattle(player, enemy);
                
                if (!playerWon) {
                    // El jugador perdió o huyó
                    if (!player.isAlive()) {
                        System.out.println("\n╔══════════════════════════════════╗");
                        System.out.println("║         GAME OVER                ║");
                        System.out.println("║    Has sido derrotado...         ║");
                        System.out.println("╚══════════════════════════════════╝");
                        gameState.setGameOver(true);
                        break;
                    }
                    // Si huyó, continúa en la misma sala
                    continue;
                } else {
                    // El jugador ganó
                    currentRoom.clearEnemy();
                    player.gainExperience(enemy.getExperienceReward());
                }
            }
            
            // Mostrar menú de acciones
            showActionMenu();
            int choice = getPlayerInput(1, 4);
            
            switch (choice) {
                case 1:
                    // Continuar a la siguiente sala
                    moveToNextRoom();
                    break;
                case 2:
                    player.displayStatus();
                    break;
                case 3:
                    player.displayInventory();
                    manageInventory();
                    break;
                case 4:
                    System.out.println("¿Estás seguro de que quieres salir? (s/n)");
                    String response = scanner.nextLine().toLowerCase();
                    if (response.equals("s") || response.equals("si") || response.equals("sí")) {
                        gameState.setGameOver(true);
                    }
                    break;
            }
            
            // Verificar si el jugador murió
            if (!player.isAlive()) {
                System.out.println("\n╔══════════════════════════════════╗");
                System.out.println("║         GAME OVER                ║");
                System.out.println("║    Has sido derrotado...         ║");
                System.out.println("╚══════════════════════════════════╝");
                gameState.setGameOver(true);
            }
        }
        
        if (gameState.isGameWon()) {
            System.out.println("\n╔══════════════════════════════════╗");
            System.out.println("║         ¡FELICIDADES!            ║");
            System.out.println("║    Has encontrado el café!       ║");
            System.out.println("║    ¡La oficina está salvada!     ║");
            System.out.println("╚══════════════════════════════════╝");
        }
        
        scanner.close();
    }
    
    private void showActionMenu() {
        System.out.println("\n¿Qué quieres hacer?");
        System.out.println("1. Continuar a la siguiente sala");
        System.out.println("2. Ver estado");
        System.out.println("3. Ver inventario");
        System.out.println("4. Salir del juego");
    }
    
    private void moveToNextRoom() {
        Floor currentFloor = dungeon.getFloor(gameState.getCurrentFloor());
        
        if (gameState.getCurrentRoom() < currentFloor.getTotalRooms() - 1) {
            gameState.moveToNextRoom();
        } else {
            // Avanzar al siguiente piso
            if (gameState.getCurrentFloor() < dungeon.getTotalFloors() - 1) {
                gameState.moveToNextFloor();
                System.out.println("\n¡Avanzas al siguiente piso!");
            } else {
                // Última sala del último piso - verificar si es el jefe final
                Room lastRoom = currentFloor.getRoom(gameState.getCurrentRoom());
                if (lastRoom.isBossRoom() && !lastRoom.hasEnemy()) {
                    gameState.setGameWon(true);
                } else {
                    System.out.println("No hay más salas. Debes derrotar al jefe final.");
                }
            }
        }
    }
    
    private void manageInventory() {
        if (player.getInventory().isEmpty()) {
            return;
        }
        
        System.out.println("\n¿Quieres usar algún objeto? (s/n)");
        String response = scanner.nextLine().toLowerCase();
        if (response.equals("s") || response.equals("si") || response.equals("sí")) {
            System.out.println("Selecciona un objeto del inventario:");
            for (int i = 0; i < player.getInventory().size(); i++) {
                System.out.println((i + 1) + ". " + player.getInventory().get(i).getName());
            }
            System.out.println((player.getInventory().size() + 1) + ". Cancelar");
            
            int choice = getPlayerInput(1, player.getInventory().size() + 1);
            if (choice <= player.getInventory().size()) {
                Item item = player.getInventory().get(choice - 1);
                if (item instanceof items.consumables.Consumable) {
                    player.useItem((items.consumables.Consumable) item);
                } else if (item instanceof Equipment) {
                    ((Equipment) item).use(player);
                }
            }
        }
    }
    
    private int getPlayerInput(int min, int max) {
        int input = -1;
        while (input < min || input > max) {
            System.out.print("Elige una opción (" + min + "-" + max + "): ");
            try {
                input = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingresa un número válido.");
            }
        }
        return input;
    }
}

