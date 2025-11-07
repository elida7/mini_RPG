package entities;

public class Enemy extends Character {
    private int experienceReward;

    public Enemy(String name, int health, int attack, int defense, int expReward) {
        super(name, health, attack, defense);
        this.experienceReward = expReward;
    }

    // ENCAPSULAMIENTO
    public int getExperienceReward() { return experienceReward; }

    public void attack(Character target) {
        int damage = getTotalAttack() + (int)(Math.random() * 4) + 1;
        target.takeDamage(damage);
        System.out.println(name + " te ataca y te inflige " + damage + " de daño!");
    }

    // POLIMORFISMO: Implementación del método abstracto
    @Override
    public void specialAbility() {
        System.out.println("¡" + name + " usa su habilidad especial!");
        // Habilidades especiales específicas de cada enemigo
    }
}