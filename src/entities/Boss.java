package entities;

public class Boss extends Enemy {
    private boolean specialAbilityUsed;

    public Boss(String name, int health, int attack, int defense, int expReward) {
        super(name, health, attack, defense, expReward);
        this.specialAbilityUsed = false;
    }

    // POLIMORFISMO: Sobrescritura del método de ataque
    @Override
    public void attack(Character target) {
        // Los jefes tienen un 30% de probabilidad de usar habilidad especial
        if (!specialAbilityUsed && Math.random() < 0.3) {
            specialAbility();
            specialAbilityUsed = true;
        } else {
            super.attack(target);
        }
    }

    // POLIMORFISMO: Implementación específica de habilidad especial
    @Override
    public void specialAbility() {
        System.out.println("¡" + getName() + " usa ATAQUE DE JEFE!");
        System.out.println("¡El daño y defensa aumentan temporalmente!");
        // Lógica de habilidad especial de jefe
    }
}