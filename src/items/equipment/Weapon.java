package items.equipment;

import entities.Character;

public class Weapon extends Equipment {
    public Weapon(String name, String description, int bonus) {
        super(name, description, bonus, EquipmentType.WEAPON);
    }

    @Override
    public void use(Character character) {
        character.equipWeapon(this);
    }
}