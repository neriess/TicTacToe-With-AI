public class HardAI {

    static int scoreOfField(String[][] field, String playerChar, String enemyChar) {

        boolean hasWinPlayer = Resolver.isWinner(playerChar, field);
        boolean hasWinEnemy = Resolver.isWinner(enemyChar, field);

        if (hasWinPlayer) {
            return 10;
        } else if (hasWinEnemy) {
            return -10;
        }
        return 0;
    }

    static int minimax(String[][] field, int depth, Boolean isMax,
                       String playerChar, String enemyChar, String emptyChar) {

        int score = scoreOfField(field, playerChar, enemyChar);
        boolean hasField = Resolver.hasField(field);

        if (score == 10) {
            return score;
        }

        if (score == -10) {
            return score;
        }

        if (!hasField) {
            return 0;
        }

        if (isMax) {
            int best = -1000;

            for (int i = 0; i < field.length; i++) {
                for (int j = 0; j < field.length; j++) {

                    if (field[i][j].equals(emptyChar)) {

                        field[i][j] = playerChar;

                        best = Math.max(best, minimax(field, depth + 1, !isMax,
                                playerChar, enemyChar, emptyChar));

                        field[i][j] = emptyChar;
                    }
                }
            }
            return best;

        } else {
            int best = 1000;

            for (int i = 0; i < field.length; i++) {
                for (int j = 0; j < field.length; j++) {

                    if (field[i][j].equals(emptyChar)) {
                        field[i][j] = enemyChar;
                        best = Math.min(best, minimax(field, depth + 1, !isMax,
                                playerChar, enemyChar, emptyChar));
                        field[i][j] = emptyChar;
                    }
                }
            }
            return best;
        }
    }

    public static int[] getHardCoords(String[][] field, String playerChar, String enemyChar, String emptyChar) {

        int bestVal = -1000;
        int[] coords = new int[2];


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j].equals(emptyChar)) {

                    field[i][j] = playerChar;

                    int move = minimax(field, 0, false, playerChar, enemyChar, emptyChar);

                    field[i][j] = emptyChar;

                    if (move > bestVal)
                    {
                        coords[0] = i;
                        coords[1] = j;
                        bestVal = move;
                    }
                }
            }
        }

        return coords;
    }
}
