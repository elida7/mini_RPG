package items.consumables;

import entities.Character;

public class MysteryCoffee extends Consumable {
    public MysteryCoffee() {
        super("Café misterioso", "Aumenta la salud máxima en 10 puntos", 0);
    }

    // POLIMORFISMO: Implementación específica
    @Override
    public void use(Character character) {
        character.setMaxHealth(character.getMaxHealth() + 10);
        character.heal(character.getMaxHealth());
        System.out.println("¡Usas Café misterioso! Salud máxima aumentada.");
    }
}