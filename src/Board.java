import java.util.Arrays;

public class Board {

    final String empty = " ";
    final String playerX = "X";
    final String playerO = "O";

    Resolver resolver = new Resolver();
    Menu menu = new Menu();
    AI ai = new AI();

    String[][] field = new String[3][3];

    public void createBoard(String[][] field, String empty) {

        for (String[] strings : field) {
            Arrays.fill(strings, empty);
        }
    }

    public void printBoard(String[][] field) {
        System.out.println("---------");
        for (String[] strings : field) {
            System.out.print("| ");
            for (String string : strings) {
                System.out.print(string + " ");
            }
            System.out.print("|\n");
        }
        System.out.println("---------");
    }


    public void placeMove(String[][] gameField, int row, int column, String playerChar) {

        try {
            gameField[row][column] = playerChar;
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
    public void start() {

        createBoard(field, empty);
        menu.takeInput();
        printBoard(field);

        boolean inProgress = true;
        boolean exitGame = menu.isExitGame();


        while (inProgress && !exitGame) {

            int[] player = menu.getPlayers();

            if (resolver.gameStatus == GameStatus.IN_PROGRESS) {
                makeMove(playerX, player[0], playerO);
            } else {
                inProgress = false;
            }

            if (resolver.gameStatus == GameStatus.IN_PROGRESS) {
                makeMove(playerO, player[1], playerX);
            } else {
                inProgress = false;
            }

        }
        resolver.printResult();
    }

    public void userMove(String playerChar) {

        Coords coord = new Coords();

        coord.takeCoords(field, empty);
        placeMove(field, coord.getRow(), coord.getColumn(), playerChar);
        resolver.resolve(field, playerX, playerO);
        printBoard(field);
    }

    public void easyMove(String playerChar) {

        int[] coords = ai.getEasyCoords(field, empty);

        System.out.println("Making move level \"easy\"");
        placeMove(field, coords[0], coords[1], playerChar);
        resolver.resolve(field, playerX, playerO);
        printBoard(field);
    }

    public void makeMove(String playerChar, int player, String enemyChar) {

        switch (player) {
            case 0 -> userMove(playerChar);
            case 1 -> easyMove(playerChar);
            case 2 -> mediumMove(playerChar, enemyChar);
            case 3 -> hardMove(playerChar, enemyChar);
        }
    }

    public void mediumMove(String playerChar, String enemyChar) {

        int[] coords = ai.getMediumCoords(field, empty, playerChar, enemyChar);

        System.out.println("Making move level \"medium\"");
        placeMove(field, coords[0], coords[1], playerChar);
        resolver.resolve(field, playerX, playerO);
        printBoard(field);
    }

    public void hardMove(String playerChar, String enemyChar) {

        int[] coords = HardAI.getHardCoords(field, playerChar, enemyChar, empty);

        System.out.println("Making move level \"hard\"");
        placeMove(field, coords[0], coords[1], playerChar);
        resolver.resolve(field, playerX, playerO);
        printBoard(field);
    }
}
