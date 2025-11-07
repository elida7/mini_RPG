package items.equipment;

import entities.Character;

public class Armor extends Equipment {
    public Armor(String name, String description, int bonus) {
        super(name, description, bonus, EquipmentType.ARMOR);
    }

    @Override
    public void use(Character character) {
        character.equipArmor(this);
    }
}