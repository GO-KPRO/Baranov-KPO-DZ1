package bar.hse.ru.reversy;

import java.util.Objects;
import java.util.Scanner;

public class Game implements Playable{
    private Field gameField;
    private Player player1;
    private Player player2;
    private final ScoreBoard board;
    public Game() {
        board = new ScoreBoard();
    }
    public void showScoreBoard() {
        this.board.outBoard();
    }
    public void showMaxScore() {
        this.board.outBoardMax();
    }
    public void runPvPGame() {
        gameField = new Field();
        Scanner in = new Scanner(System.in);
        System.out.println("First player:");
        String Name1 = in.nextLine();
        System.out.println("Second player:");
        String Name2 = in.nextLine();
        player1 = new Player(Name1);
        player2 = new Player(Name2);
        player1.setWhiteTeam();
        player2.setBlackTeam();
        System.out.println("W - white cell");
        System.out.println("B - black cell");
        System.out.println("P - cell for possible move");
        System.out.println("O - empty cell");
        System.out.println("Position - a b");
        System.out.println("a - vertical coordinate");
        System.out.println("b - horizontal coordinate");
        while (true) {
            if ((gameField.blackCount() + gameField.whiteCount() < 64) && (gameField.blackCount() != 0) && (gameField.whiteCount() != 0) && (gameField.whitePossibleCount() != 0)) {
                System.out.println("White move");
                gameField.outFieldWhite();
                System.out.println("Enter the position");
                while (!gameField.whiteMove(in.nextInt(), in.nextInt())) ;
            } else if ((gameField.blackPossibleCount() == 0) && (gameField.whitePossibleCount() == 0)){
                break;
            }
            if ((gameField.blackCount() + gameField.whiteCount() < 64) && (gameField.blackCount() != 0) && (gameField.whiteCount() != 0) && (gameField.blackPossibleCount() != 0)) {
                System.out.println("Black move");
                gameField.outFieldBlack();
                System.out.println("Enter the position");
                while (!gameField.blackMove(in.nextInt(), in.nextInt())) ;
            } else if ((gameField.blackPossibleCount() != 0) && (gameField.whitePossibleCount() != 0)){
                break;
            }
        }
        gameField.outField();
        if (gameField.blackCount() > gameField.whiteCount()) {
            System.out.printf("%s wins\n", this.player2.getName());
            System.out.printf("%s score: %d\n", player2.getName(),gameField.blackCount());
            System.out.printf("%s score: %d\n", player1.getName(),gameField.whiteCount());
            this.player2.setScore(gameField.blackCount());
            this.board.addToBoard(this.player2);
        } else if (gameField.blackCount() < gameField.whiteCount()) {
            System.out.printf("%s wins\n", this.player1.getName());
            System.out.printf("%s score: %d\n", player1.getName(),gameField.whiteCount());
            System.out.printf("%s score: %d\n", player2.getName(),gameField.blackCount());
            this.player1.setScore(gameField.whiteCount());
            this.board.addToBoard(this.player1);
        } else {
            System.out.println("Draw");
        }
    }
    public void runPvEGame() {
        gameField = new Field();
        Scanner in = new Scanner(System.in);
        System.out.println("Player name:");
        String Name1 = in.nextLine();
        player1 = new Player(Name1);
        player1.setWhiteTeam();
        System.out.println("W - white cell");
        System.out.println("B - black cell");
        System.out.println("P - cell for possible move");
        System.out.println("O - empty cell");
        System.out.println("Position - a b");
        System.out.println("a - vertical coordinate");
        System.out.println("b - horizontal coordinate");
        while (true) {
            if ((gameField.blackCount() + gameField.whiteCount() < 64) && (gameField.blackCount() != 0) && (gameField.whiteCount() != 0) && (gameField.whitePossibleCount() != 0)) {
                System.out.println("Your move");
                gameField.outFieldWhite();
                System.out.println("Enter the position");
                while (!gameField.whiteMove(in.nextInt(), in.nextInt())) ;
            } else if ((gameField.blackPossibleCount() != 0) && (gameField.whitePossibleCount() != 0)){
                break;
            }
            if ((gameField.blackCount() + gameField.whiteCount() < 64) && (gameField.blackCount() != 0) && (gameField.whiteCount() != 0) && (gameField.blackPossibleCount() != 0)) {
                gameField.blackComputerMove();
            } else if ((gameField.blackPossibleCount() != 0) && (gameField.whitePossibleCount() != 0)){
                break;
            }
        }
        gameField.outField();
        if (gameField.blackCount() > gameField.whiteCount()) {
            System.out.println("Computer wins");
            System.out.printf("Computer score: %d\n", gameField.blackCount());
            System.out.printf("%s score: %d\n", player1.getName(),gameField.whiteCount());
        } else if (gameField.blackCount() < gameField.whiteCount()) {
            System.out.printf("%s wins\n", this.player1.getName());
            System.out.printf("%s score: %d\n", player1.getName(),gameField.whiteCount());
            System.out.printf("Computer score: %d\n", gameField.blackCount());
            this.player1.setScore(gameField.whiteCount());
            this.board.addToBoard(this.player1);
        } else {
            System.out.println("Draw");
        }
    }
    public void runPvhEGame() {
        System.out.println("Sorry, there is no realisation here");
    }
}
