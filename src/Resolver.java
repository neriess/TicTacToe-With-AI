public class Resolver {

    GameStatus gameStatus = GameStatus.IN_PROGRESS;
    public void resolve(String[][] field, String playerX, String playerO) {

        if (isWinner(playerX, field)) {
            gameStatus = GameStatus.X_WON;
        } else if (isWinner(playerO, field)) {
            gameStatus = GameStatus.O_WON;
        } else if (!hasField(field)) {
            gameStatus = GameStatus.DRAW;
        }

    }

    public static boolean isWinner(String player, String[][] field) {

        for (String[] strings : field) {
            if (strings[0].equals(player) && strings[1].equals(player) &&
                    strings[2].equals(player)) return true;
        }
        for (int i = 0; i < field.length; i++) {
            if (field[0][i].equals(player) && field[1][i].equals(player) &&
                    field[2][i].equals(player)) return true;
        }
        if (field[0][0].equals(player) && field[1][1].equals(player) &&
                field[2][2].equals(player)) return true;

        return field[2][0].equals(player) && field[1][1].equals(player) &&
                field[0][2].equals(player);
    }

    public static boolean hasField(String[][] field) {
        for (String[] strings : field) {
            for (String string : strings) {
                if (string.equals(" ")) return true;
            }
        }
        return false;
    }

    public void printResult() {

        switch (gameStatus) {
            case X_WON -> System.out.println(GameStatus.X_WON.message);
            case O_WON -> System.out.println(GameStatus.O_WON.message);
            case DRAW -> System.out.println(GameStatus.DRAW.message);
        }
    }
}
