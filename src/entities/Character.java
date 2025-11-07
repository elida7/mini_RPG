package entities;

import items.equipment.Equipment;

public abstract class Character {
    protected String name;
    protected int level;
    protected int health;
    protected int maxHealth;
    protected int attack;
    protected int defense;
    protected Equipment equippedWeapon;
    protected Equipment equippedArmor;

    public Character(String name, int health, int attack, int defense) {
        this.name = name;
        this.maxHealth = health;
        this.health = maxHealth;
        this.attack = attack;
        this.defense = defense;
        this.level = 1;
    }

    // ENCAPSULAMIENTO: Getters y Setters
    public String getName() { return name; }
    public int getLevel() { return level; }
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }

    public void setHealth(int health) { this.health = health; }
    public void setMaxHealth(int maxHealth) { this.maxHealth = maxHealth; }

    // ABSTRACCIÓN: Método abstracto que deben implementar las subclases
    public abstract void specialAbility();

    // POLIMORFISMO: Comportamiento que puede ser sobrescrito
    public void takeDamage(int damage) {
        int actualDamage = Math.max(1, damage - getTotalDefense());
        health -= actualDamage;
        System.out.println(name + " recibe " + actualDamage + " puntos de daño!");
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void heal(int amount) {
        health = Math.min(maxHealth, health + amount);
        System.out.println(name + " recupera " + amount + " puntos de salud!");
    }

    // ENCAPSULAMIENTO: Cálculos internos
    protected int getTotalAttack() {
        int totalAttack = attack;
        if (equippedWeapon != null) {
            totalAttack += equippedWeapon.getBonus();
        }
        return totalAttack;
    }

    protected int getTotalDefense() {
        int totalDefense = defense;
        if (equippedArmor != null) {
            totalDefense += equippedArmor.getBonus();
        }
        return totalDefense;
    }

    public void equipWeapon(Equipment weapon) {
        this.equippedWeapon = weapon;
        System.out.println(name + " equipa " + weapon.getName());
    }

    public void equipArmor(Equipment armor) {
        this.equippedArmor = armor;
        System.out.println(name + " equipa " + armor.getName());
    }

    public void displayStatus() {
        System.out.println("\n=== ESTADO DE " + name.toUpperCase() + " ===");
        System.out.println("Nivel: " + level);
        System.out.println("Salud: " + health + "/" + maxHealth);
        System.out.println("Ataque: " + getTotalAttack() + " (Base: " + attack + ")");
        System.out.println("Defensa: " + getTotalDefense() + " (Base: " + defense + ")");
        System.out.println("Arma: " + (equippedWeapon != null ? equippedWeapon.getName() : "Ninguna"));
        System.out.println("Armadura: " + (equippedArmor != null ? equippedArmor.getName() : "Ninguna"));
    }
}
