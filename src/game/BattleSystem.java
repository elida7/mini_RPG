package game;

import entities.Character;
import entities.Player;
import items.consumables.Consumable;
import java.util.Scanner;

public class BattleSystem {
    private Scanner scanner;

    public BattleSystem(Scanner scanner) {
        this.scanner = scanner;
    }

    public boolean startBattle(Player player, entities.Enemy enemy) {
        System.out.println("\n=== COMIENZA LA BATALLA ===");

        while (enemy.isAlive() && player.isAlive()) {
            displayBattleStatus(player, enemy);

            int choice = getBattleChoice(player);

            switch (choice) {
                case 1:
                    player.attack(enemy);
                    if (enemy.isAlive()) {
                        enemy.attack(player);
                    }
                    break;

                case 2:
                    if (useItemInBattle(player)) {
                        if (enemy.isAlive()) {
                            enemy.attack(player);
                        }
                    }
                    break;

                case 3:
                    if (attemptEscape()) {
                        System.out.println("¡Logras huir de la batalla!");
                        return false;
                    } else {
                        System.out.println("¡No puedes huir!");
                        enemy.attack(player);
                    }
                    break;
            }
        }

        return !enemy.isAlive();
    }

    private void displayBattleStatus(Player player, entities.Enemy enemy) {
        System.out.println("\n" + player.getName() + ": " + player.getHealth() + "/" + player.getMaxHealth() + " HP");
        System.out.println(enemy.getName() + ": " + enemy.getHealth() + "/" + enemy.getMaxHealth() + " HP");
    }

    private int getBattleChoice(Player player) {
        System.out.println("\n¿Qué quieres hacer?");
        System.out.println("1. Atacar");
        System.out.println("2. Usar objeto");
        System.out.println("3. Huir (50% de probabilidad)");

        return getPlayerInput(1, 3);
    }

    private boolean useItemInBattle(Player player) {
        if (player.getInventory().isEmpty()) {
            System.out.println("No tienes objetos en el inventario.");
            return false;
        }

        System.out.println("\nInventario:");
        int itemCount = 0;
        for (int i = 0; i < player.getInventory().size(); i++) {
            if (player.getInventory().get(i) instanceof Consumable) {
                System.out.println((itemCount + 1) + ". " + player.getInventory().get(i).getName());
                itemCount++;
            }
        }

        if (itemCount == 0) {
            System.out.println("No tienes objetos consumibles.");
            return false;
        }

        System.out.println((itemCount + 1) + ". Cancelar");
        int choice = getPlayerInput(1, itemCount + 1);

        if (choice <= itemCount) {
            // Encontrar el consumible seleccionado
            int consumableIndex = 0;
            for (int i = 0; i < player.getInventory().size(); i++) {
                if (player.getInventory().get(i) instanceof Consumable) {
                    consumableIndex++;
                    if (consumableIndex == choice) {
                        Consumable item = (Consumable) player.getInventory().get(i);
                        player.useItem(item);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean attemptEscape() {
        return Math.random() < 0.5;
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