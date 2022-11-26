package bar.hse.ru.reversy;

public class Cell {
    private String cellStatus = "void";
    public void changeToWhite() {
        cellStatus = "white";
    }
    public void changeToBlack() {
        cellStatus = "black";
    }
    public String getStatus() {
        return cellStatus;
    }
    public char getColour() {
        return switch (cellStatus) {
            case ("void") -> 'O';
            case ("black") -> 'B';
            case ("white") -> 'W';
            default -> ' ';
        };
    }
}
