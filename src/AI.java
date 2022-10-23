import java.util.Objects;
import java.util.Random;

public class AI {

    Random random = new Random();

    public int[] getEasyCoords(String[][] field, String emptyField) {

        int[] coords = new int[2];
        boolean isItFound = false;

        while(!isItFound) {
            int row = random.nextInt(3);
            int column = random.nextInt(3);

            boolean found = Objects.equals(field[row][column], emptyField);

            if (found) {
                coords[0] = row;
                coords[1] = column;
                isItFound = true;
            }
        }
        return coords;
    }

    private int mediumRow;
    private int mediumColumn;

    public int[] getMediumCoords(String[][] field, String emptyField, String playerChar, String enemyChar) {

        int[] coords = new int[2];

        if (mediumCoordsInRow(field, emptyField, playerChar) ||
                mediumCoordsInColumn(field, emptyField, playerChar) ||
                mediumCoordsInDiagonal(field, emptyField, playerChar)) {
            coords[0] = mediumRow;
            coords[1] = mediumColumn;
        } else if (mediumCoordsInRow(field, emptyField, enemyChar) ||
                mediumCoordsInColumn(field, emptyField, enemyChar) ||
                mediumCoordsInDiagonal(field, emptyField, enemyChar)) {
            coords[0] = mediumRow;
            coords[1] = mediumColumn;
        } else {
            coords = getEasyCoords(field, emptyField);
        }


        return coords;
    }

    private boolean mediumCoordsInRow(String[][] field, String emptyField, String findingChar) {

        int findingCount;
        int emptyCount;

        outerloop:
        for (int i = 0; i < field.length; i++) {
            findingCount = 0;
            emptyCount = 0;
            for (int j = 0; j < field.length; j++) {
                if (field[i][j].equals(findingChar)) {
                    findingCount += 1;
                } else if (field[i][j].equals(emptyField)) {
                    emptyCount += 1;
                }
                if (findingCount == 2 && emptyCount == 1) {
                    mediumRow = i;
                    for (int k = 0; k < field.length; k++) {
                        if (field[mediumRow][k].equals(emptyField)) {
                            mediumColumn = k;
                            return true;
                        }
                    }
                    break outerloop;
                }
            }
        }
        return false;
    }

    private boolean mediumCoordsInColumn(String[][] field, String emptyField, String findingChar) {

        int findingCount;
        int emptyCount;

        outerloop:
        for (int i = 0; i < field.length; i++) {
            findingCount = 0;
            emptyCount = 0;
            for (String[] strings : field) {
                if (strings[i].equals(findingChar)) {
                    findingCount += 1;
                } else if (strings[i].equals(emptyField)) {
                    emptyCount += 1;
                }
                if (findingCount == 2 && emptyCount == 1) {
                    mediumColumn = i;
                    for (int k = 0; k < field.length; k++) {
                        if (field[k][mediumColumn].equals(emptyField)) {
                            mediumRow = k;
                            return true;
                        }
                    }
                    break outerloop;
                }
            }
        }
        return false;
    }

    private boolean mediumCoordsInDiagonal(String[][] field, String emptyField, String findingChar) {

        int findingCount = 0;
        int emptyCount = 0;

        for (int i = 0; i < field.length; i++) {
            if (field[i][i].equals(findingChar)) {
                findingCount += 1;
            } else if (field[i][i].equals(emptyField)) {
                emptyCount += 1;
            }
            if (findingCount == 2 && emptyCount == 1) {
                for (int j = 0; j < field.length; j++) {
                    if (field[j][j].equals(emptyField)) {
                        mediumRow = j;
                        mediumColumn = j;
                        return true;
                    }
                }
            }
        }

        findingCount = 0;
        emptyCount = 0;

        for (int i = 0; i < field.length; i++) {
            int column = Math.abs(i - 2);
            if (field[i][column].equals(findingChar)) {
                findingCount += 1;
            } else if (field[i][column].equals(emptyField)) {
                emptyCount += 1;
            }
            if (findingCount == 2 && emptyCount == 1) {
                for (int j = 0; j < field.length; j++) {
                    int column2 = Math.abs(j - 2);
                    if (field[j][column2].equals(emptyField)) {
                        mediumRow = j;
                        mediumColumn = column2;
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
