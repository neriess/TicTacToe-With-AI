import java.util.Objects;
import java.util.Scanner;

public class Menu {

    Scanner scn = new Scanner(System.in);

    final String start = "start";
    final String exit = "exit";
    final String user = "user";
    final String easy = "easy";
    final String medium = "medium";
    final String hard = "hard";
    final String badParam = "Bad parameters!";

    int[] players = new int[2]; // 0 = user, 1 = easy, 2 = medium 3 = hard

    private boolean rightCommand = false;

    public boolean isExitGame() {
        return exitGame;
    }

    private boolean exitGame = false;

    private String[] input;
    private String player1;
    private String player2;

    public void takeInput() {

        System.out.println("""
                This Tic-Tac-Toe has three difficulties: easy, medium, hard.
                For start the game enter "start user hard" (start player X, player O).
                For exit the game enter "exit".
                Enjoy the game :)
                """);


        do {
            try {
                System.out.print("Input command: ");

                input = scn.nextLine().split(" ");

                String command = input[0];

                if (!Objects.equals(command, start) && !Objects.equals(command, exit)) {
                    System.out.println(badParam);
                } else if (command.equals(exit)) {
                    exitGame = true;
                    break;
                } else if (command.equals(start)) {
                    checkPlayers();
                }

            } catch (Exception e) {
                System.out.println(badParam);
            }

        } while (!exitGame && !rightCommand);
    }

    private void checkPlayers() {

        player1 = input[1];
        player2 = input[2];

        if (!Objects.equals(player1, user) && !Objects.equals(player1, easy) && !Objects.equals(player1, medium) &&
                !Objects.equals(player1, hard) || !Objects.equals(player2, user) && !Objects.equals(player2, easy) &&
                !Objects.equals(player2, medium) && !Objects.equals(player2, hard)) {

            System.out.println(badParam);
        } else {
            rightCommand = true;
        }
    }

    public int[] getPlayers() {

        players[0] = translatePlayer(player1);
        players[1] = translatePlayer(player2);

        return players;
    }

    private int translatePlayer(String player) {

        return switch (player) {
            case user -> 0;
            case easy -> 1;
            case medium -> 2;
            case hard -> 3;
            default -> -1;
        };
    }
}
