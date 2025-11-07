package items.consumables;

import entities.Character;
import entities.Player;

public class EnergyCookie extends Consumable {
    public EnergyCookie() {
        super("Galleta energética", "Restaura 15 puntos de salud y aumenta el ataque", 15);
    }

    // POLIMORFISMO: Sobrescritura del método use
    @Override
    public void use(Character character) {
        super.use(character);
        if (character instanceof Player) {
            // Efecto adicional específico para EnergyCookie
            System.out.println("¡Tu ataque aumenta temporalmente!");
        }
    }
}