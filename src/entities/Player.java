package entities;

import items.Item;
import items.consumables.Consumable;
import items.equipment.Equipment;
import java.util.ArrayList;

public class Player extends Character {
    private int experience;
    private int expToNextLevel;
    private ArrayList<Item> inventory;

    public Player(String name) {
        super(name, 50, 10, 5);
        this.experience = 0;
        this.expToNextLevel = 100;
        this.inventory = new ArrayList<>();
    }

    // ENCAPSULAMIENTO
    public int getExperience() { return experience; }
    public int getExpToNextLevel() { return expToNextLevel; }
    public ArrayList<Item> getInventory() { return inventory; }

    public void attack(Character target) {
        int damage = getTotalAttack() + (int)(Math.random() * 6) + 5;
        target.takeDamage(damage);
        System.out.println("¡Atacas a " + target.getName() + " y le infliges " + damage + " de daño!");
    }

    public void gainExperience(int exp) {
        experience += exp;
        System.out.println("¡Ganas " + exp + " puntos de experiencia!");

        if (experience >= expToNextLevel) {
            levelUp();
        }
    }

    private void levelUp() {
        level++;
        experience -= expToNextLevel;
        expToNextLevel = (int)(expToNextLevel * 1.5);

        maxHealth += 20;
        health = maxHealth;
        attack += 5;
        defense += 2;

        System.out.println("\n╔══════════════════════════════════╗");
        System.out.println("║          ¡SUBISTE DE NIVEL!      ║");
        System.out.println("║            Nivel " + level + " alcanzado       ║");
        System.out.println("╚══════════════════════════════════╝");
    }

    // POLIMORFISMO: Implementación del método abstracto
    @Override
    public void specialAbility() {
        System.out.println("¡Usas tu habilidad especial 'Debugging Rage'!");
        System.out.println("¡Ataque y defensa aumentados por 1 turno!");
        // Implementación de habilidad especial
    }

    public void useItem(Consumable item) {
        item.use(this);
        inventory.remove(item);
    }

    public void addItem(Item item) {
        inventory.add(item);
        System.out.println("¡Obtienes: " + item.getName() + "!");
    }

    public void displayInventory() {
        System.out.println("\n=== INVENTARIO ===");
        if (inventory.isEmpty()) {
            System.out.println("El inventario está vacío.");
        } else {
            for (int i = 0; i < inventory.size(); i++) {
                System.out.println((i + 1) + ". " + inventory.get(i).getName() + " - " + inventory.get(i).getDescription());
            }
        }
    }
}