public enum GameStatus {

    X_WON("X wins"),
    O_WON("O wins"),
    DRAW("Draw"),
    IN_PROGRESS("Game not finished");

    final String message;

    GameStatus(String message) {
        this.message = message;
    }
}
