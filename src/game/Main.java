package game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("╔══════════════════════════════════╗");
        System.out.println("║      DUNGEON RPG TERMINAL        ║");
        System.out.println("║    La Búsqueda del Café Perdido  ║");
        System.out.println("╚══════════════════════════════════╝");
        System.out.println("\n¡Bienvenido, valiente programador!");
        System.out.println("El café de la oficina ha desaparecido...");
        System.out.println("¡Debes encontrarlo antes de que todos se duerman!");

        System.out.print("\nIngresa el nombre de tu personaje: ");
        String playerName = scanner.nextLine();

        Game game = new Game();
        game.startGame(playerName);
        scanner.close();
    }
}

