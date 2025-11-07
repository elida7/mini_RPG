package items.equipment;

import items.Item;
import entities.Character;

public abstract class Equipment extends Item {
    protected int bonus;
    protected EquipmentType type;

    public Equipment(String name, String description, int bonus, EquipmentType type) {
        super(name, description);
        this.bonus = bonus;
        this.type = type;
    }

    // ENCAPSULAMIENTO
    public int getBonus() { return bonus; }
    public EquipmentType getType() { return type; }

    // ABSTRACCIÓN
    @Override
    public abstract void use(Character character);
}

enum EquipmentType {
    WEAPON, ARMOR
}