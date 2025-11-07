package game;

public class GameState {
    private int currentFloor;
    private int currentRoom;
    private boolean gameOver;
    private boolean gameWon;

    public GameState() {
        this.currentFloor = 0;
        this.currentRoom = 0;
        this.gameOver = false;
        this.gameWon = false;
    }

    // ENCAPSULAMIENTO: Getters y Setters
    public int getCurrentFloor() { return currentFloor; }
    public int getCurrentRoom() { return currentRoom; }
    public boolean isGameOver() { return gameOver; }
    public boolean isGameWon() { return gameWon; }

    public void setCurrentFloor(int floor) { this.currentFloor = floor; }
    public void setCurrentRoom(int room) { this.currentRoom = room; }
    public void setGameOver(boolean gameOver) { this.gameOver = gameOver; }
    public void setGameWon(boolean gameWon) { this.gameWon = gameWon; }

    public void moveToNextRoom() {
        currentRoom++;
    }

    public void moveToNextFloor() {
        currentFloor++;
        currentRoom = 0;
    }

    public void displayGameState() {
        System.out.println("\n══════════════════════════════════");
        System.out.println("Piso: " + (currentFloor + 1) + " | Sala: " + (currentRoom + 1) + "/5");
        System.out.println("══════════════════════════════════");
    }
}