package bar.hse.ru.reversy.primitives;

public class Cell {
    private String cellStatus = "void";

    public Cell() {
    }

    public void changeToWhite() {
        cellStatus = "white";
    }

    public void changeToBlack() {
        cellStatus = "black";
    }

    public Cell copy() {
        switch (cellStatus) {
            case ("void") -> {
                return new Cell();
            }
            case ("black") -> {
                Cell res = new Cell();
                res.changeToBlack();
                return res;
            }
            case ("white") -> {
                Cell res = new Cell();
                res.changeToWhite();
                return res;
            }
        }
        return null;
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
