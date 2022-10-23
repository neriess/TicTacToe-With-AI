import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Coords {

    private int row;
    private int column;

    Scanner scn = new Scanner(System.in);

    public void takeCoords(String[][] field, String emptyField) {

        boolean rightCoords = false;
        do {
            try {
                System.out.println("Enter the coordinates (ex.\"1 3\"): ");
                int x = scn.nextInt() - 1;
                int y = scn.nextInt() - 1;

                if (!isOccupied(field, x, y, emptyField)) {
                    throw new Exception("This cell is occupied! Choose another one!");
                }
                row = x; column = y;
                rightCoords = true;

            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
                scn.nextLine();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Coordinates should be from 1 to 3!");
                scn.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                scn.nextLine();
            }
        } while (!rightCoords);

    }

    private boolean isOccupied(String[][] field, int x, int y, String emptyField) {
        return Objects.equals(field[x][y], emptyField);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
