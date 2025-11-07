package items.consumables;

import items.Item;
import entities.Character;

public abstract class Consumable extends Item {
    protected int healingAmount;

    public Consumable(String name, String description, int healingAmount) {
        super(name, description);
        this.healingAmount = healingAmount;
    }

    // POLIMORFISMO: Implementación base que puede ser extendida
    @Override
    public void use(Character character) {
        character.heal(healingAmount);
        System.out.println("¡Usas " + name + "!");
    }
}