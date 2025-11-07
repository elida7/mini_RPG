package dungeon;

import entities.Enemy;
import items.Item;

public class Room {
    private String description;
    private Enemy enemy;
    private Item item;
    private boolean hasItem;
    private boolean isBossRoom;
    private boolean visited;

    public Room(String description, Enemy enemy, Item item, boolean isBossRoom) {
        this.description = description;
        this.enemy = enemy;
        this.item = item;
        this.hasItem = item != null;
        this.isBossRoom = isBossRoom;
        this.visited = false;
    }

    // ENCAPSULAMIENTO: Getters
    public String getDescription() { return description; }
    public Enemy getEnemy() { return enemy; }
    public Item getItem() { return item; }
    public boolean hasEnemy() { return enemy != null && enemy.isAlive(); }
    public boolean hasItem() { return hasItem; }
    public boolean isBossRoom() { return isBossRoom; }
    public boolean isVisited() { return visited; }

    public Item enterRoom(entities.Player player) {
        System.out.println("\n" + description);

        Item collectedItem = null;
        if (!visited && hasItem) {
            player.addItem(item);
            collectedItem = item;
            hasItem = false;
        }
        visited = true;

        if (hasEnemy()) {
            System.out.println("¡Te enfrentas a " + enemy.getName() + "!");
        }
        
        return collectedItem;
    }

    public void clearEnemy() {
        this.enemy = null;
    }
}