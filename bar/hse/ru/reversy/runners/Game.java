package bar.hse.ru.reversy.runners;

import bar.hse.ru.reversy.parts.Field;
import bar.hse.ru.reversy.primitives.Gamer;
import bar.hse.ru.reversy.primitives.Player;
import bar.hse.ru.reversy.parts.ScoreBoard;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game implements Playable {
    private Field gameField;
    private Gamer player1;
    private Gamer player2;
    private final ScoreBoard board;
    private final List<Field> fieldHistory;
    private int curMove;

    public Game() {
        board = new ScoreBoard();
        fieldHistory = new ArrayList<>();
    }

    public void showScoreBoard() {
        this.board.outBoard();
    }

    public void showMaxScore() {
        this.board.outBoardMax();
    }

    public void runPvPGame() {
        gameField = new Field();
        fieldHistory.add(gameField.copy());
        curMove = 1;
        Scanner in = new Scanner(System.in);
        System.out.println("First player:");
        String Name1 = in.nextLine();
        System.out.println("Second player:");
        String Name2 = in.nextLine();
        player1 = new Player(Name1);
        player2 = new Player(Name2);
        System.out.println("W - white cell");
        System.out.println("B - black cell");
        System.out.println("P - cell for possible move");
        System.out.println("O - empty cell");
        System.out.println("Position - a b");
        System.out.println("a - vertical coordinate");
        System.out.println("b - horizontal coordinate");
        System.out.println("b - horizontal coordinate");
        System.out.println("If your move is out of the field, then you have the opportunity to cancel the previous move");
        while (true) {
            if (canWhiteMove() && (curMove % 2 == 1)) {
                System.out.println("White move");
                gameField.outFieldWhite();
                while (true) {
                    try {
                        System.out.println("Enter the position");
                        int curInA = in.nextInt();
                        int curInB = in.nextInt();
                        in.nextLine();
                        if (gameField.inField(curInA - 1, curInB - 1)) {
                            if (gameField.whiteMove(curInA, curInB)) {
                                ++curMove;
                                fieldHistory.add(gameField.copy());
                                break;
                            }
                        } else {
                            System.out.println("Wrong position");
                            System.out.println("Undo the previous move?(yes/no)");
                            String answer = in.nextLine();
                            if (answer.equals("yes")) {
                                cancelMove(1);
                                break;
                            } else {
                                System.out.println("Enter the position again");
                            }
                        }
                    } catch(Exception e) {
                        System.out.println("Wrong position");
                        in.nextLine();
                    }
                }
            } else if (canNobodyMove()) {
                break;
            } else if (!canWhiteMove()){
                ++curMove;
            }
            if (canBlackMove() && (curMove % 2 == 0)) {
                System.out.println("Black move");
                gameField.outFieldBlack();
                while (true) {
                    try {
                        System.out.println("Enter the position");
                        int curInA = in.nextInt();
                        int curInB = in.nextInt();
                        in.nextLine();
                        if (gameField.inField(curInA - 1, curInB - 1)) {
                            if (gameField.blackMove(curInA, curInB)) {
                                ++curMove;
                                fieldHistory.add(gameField.copy());
                                break;
                            }
                        } else {
                            System.out.println("Wrong position");
                            System.out.println("Undo the previous move?(yes/no)");
                            String answer = in.nextLine();

                            if (answer.equals("yes")) {
                                cancelMove(1);
                                break;
                            } else {
                                System.out.println("Enter the position again");
                            }
                        }
                    } catch(Exception e) {
                        System.out.println("Wrong position");
                        in.nextLine();
                    }
                }
            } else if (canNobodyMove()) {
                break;
            } else if (!canBlackMove()){
                ++curMove;
            }
        }
        gameField.outField();
        if (gameField.blackCount() > gameField.whiteCount()) {
            System.out.printf("%s wins\n", this.player2.getName());
            System.out.printf("%s score: %d\n", player2.getName(), gameField.blackCount());
            System.out.printf("%s score: %d\n", player1.getName(), gameField.whiteCount());
            this.player2.setScore(gameField.blackCount());
            this.board.addToBoard(this.player2);
        } else if (gameField.blackCount() < gameField.whiteCount()) {
            System.out.printf("%s wins\n", this.player1.getName());
            System.out.printf("%s score: %d\n", player1.getName(), gameField.whiteCount());
            System.out.printf("%s score: %d\n", player2.getName(), gameField.blackCount());
            this.player1.setScore(gameField.whiteCount());
            this.board.addToBoard(this.player1);
        } else {
            System.out.println("Draw");
        }
    }

    public void runPvEGame() {
        gameField = new Field();
        fieldHistory.add(gameField.copy());
        curMove = 1;
        Scanner in = new Scanner(System.in);
        System.out.println("Player name:");
        String Name1 = in.nextLine();
        player1 = new Player(Name1);
        System.out.println("W - white cell");
        System.out.println("B - black cell");
        System.out.println("P - cell for possible move");
        System.out.println("O - empty cell");
        System.out.println("Position - a b");
        System.out.println("a - vertical coordinate");
        System.out.println("b - horizontal coordinate");
        System.out.println("If your move is out of the field, then you have the opportunity to cancel the previous move");
        while (true) {
            if (canWhiteMove() && (curMove % 2 == 1)) {
                System.out.println("Your move");
                gameField.outFieldWhite();
                while (true) {
                    try {
                        System.out.println("Enter the position");
                        int curInA = in.nextInt();
                        int curInB = in.nextInt();
                        in.nextLine();
                        if (gameField.inField(curInA - 1, curInB - 1)) {
                            if (gameField.whiteMove(curInA, curInB)) {
                                ++curMove;
                                fieldHistory.add(gameField.copy());
                                break;
                            }
                        } else {
                            System.out.println("Wrong position");
                            System.out.println("Undo the previous move?(yes/no)");
                            String answer = in.nextLine();
                            if (answer.equals("yes")) {
                                cancelMove(2);
                                break;
                            } else {
                                System.out.println("Enter the position again");
                            }
                        }
                    } catch(Exception e) {
                        System.out.println("Wrong position");
                        in.nextLine();
                    }
                }
            } else if (canNobodyMove()) {
                break;
            } else if (!canWhiteMove()){
                ++curMove;
            }
            if (canBlackMove() && (curMove % 2 == 0)) {
                gameField.blackComputerMove();
                fieldHistory.add(gameField.copy());
                ++curMove;
            } else if (canNobodyMove()) {
                break;
            } else if (!canBlackMove()){
                ++curMove;
            }
        }
        gameField.outField();
        if (gameField.blackCount() > gameField.whiteCount()) {
            System.out.println("Computer wins");
            System.out.printf("Computer score: %d\n", gameField.blackCount());
            System.out.printf("%s score: %d\n", player1.getName(), gameField.whiteCount());
        } else if (gameField.blackCount() < gameField.whiteCount()) {
            System.out.printf("%s wins\n", this.player1.getName());
            System.out.printf("%s score: %d\n", player1.getName(), gameField.whiteCount());
            System.out.printf("Computer score: %d\n", gameField.blackCount());
            this.player1.setScore(gameField.whiteCount());
            this.board.addToBoard(this.player1);
        } else {
            System.out.println("Draw");
        }
    }

    public void runPvhEGame() {
        gameField = new Field();
        fieldHistory.add(gameField.copy());
        curMove = 1;
        Scanner in = new Scanner(System.in);
        System.out.println("Player name:");
        String Name1 = in.nextLine();
        player1 = new Player(Name1);
        System.out.println("W - white cell");
        System.out.println("B - black cell");
        System.out.println("P - cell for possible move");
        System.out.println("O - empty cell");
        System.out.println("Position - a b");
        System.out.println("a - vertical coordinate");
        System.out.println("b - horizontal coordinate");
        System.out.println("If your move is out of the field, then you have the opportunity to cancel the previous move");
        while (true) {
            if (canWhiteMove() && (curMove % 2 == 1)) {
                System.out.println("Your move");
                gameField.outFieldWhite();
                while (true) {
                    try {
                        System.out.println("Enter the position");
                        int curInA = in.nextInt();
                        int curInB = in.nextInt();
                        in.nextLine();
                        if (gameField.inField(curInA - 1, curInB - 1)) {
                            if (gameField.whiteMove(curInA, curInB)) {
                                ++curMove;
                                fieldHistory.add(gameField.copy());
                                break;
                            }
                        } else {
                            System.out.println("Wrong position");
                            System.out.println("Undo the previous move?(yes/no)");
                            String answer = in.nextLine();
                            if (answer.equals("yes")) {
                                cancelMove(2);
                                break;
                            } else {
                                System.out.println("Enter the position again");
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Wrong position");
                        in.nextLine();
                    }
                }
            } else if (canNobodyMove()) {
                break;
            } else if (!canWhiteMove()) {
                ++curMove;
            }
            if (canBlackMove() && (curMove % 2 == 0)) {
                if (curMove == 64) {
                    gameField.blackComputerMove();
                } else {
                    gameField.blackHardComputerMove();
                }
                fieldHistory.add(gameField.copy());
                ++curMove;
            } else if (canNobodyMove()) {
                break;
            } else if (!canBlackMove()){
                ++curMove;
            }
        }
        gameField.outField();
        if (gameField.blackCount() > gameField.whiteCount()) {
            System.out.println("Computer wins");
            System.out.printf("Computer score: %d\n", gameField.blackCount());
            System.out.printf("%s score: %d\n", player1.getName(), gameField.whiteCount());
        } else if (gameField.blackCount() < gameField.whiteCount()) {
            System.out.printf("%s wins\n", this.player1.getName());
            System.out.printf("%s score: %d\n", player1.getName(), gameField.whiteCount());
            System.out.printf("Computer score: %d\n", gameField.blackCount());
            this.player1.setScore(gameField.whiteCount());
            this.board.addToBoard(this.player1);
        } else {
            System.out.println("Draw");
        }
    }

    private void cancelMove(int num) {
        if (fieldHistory.size() >= 2) {
            gameField = fieldHistory.get(curMove - 1 - num).copy();
            int newSize = fieldHistory.size() - num;
            for (int i = fieldHistory.size() - 1; i >= newSize; --i) {
                fieldHistory.remove(i);
            }
            curMove -= num;
        } else {
            System.out.println("Sorry, this is first move");
        }
    }

    private boolean canWhiteMove() {
        return ((gameField.blackCount() + gameField.whiteCount() < 64) && (gameField.blackCount() != 0) && (gameField.whiteCount() != 0) && (gameField.whitePossibleCount() != 0));
    }

    private boolean canBlackMove() {
        return ((gameField.blackCount() + gameField.whiteCount() < 64) && (gameField.blackCount() != 0) && (gameField.whiteCount() != 0) && (gameField.blackPossibleCount() != 0));
    }

    private boolean canNobodyMove() {
        return ((gameField.blackPossibleCount() == 0) && (gameField.whitePossibleCount() == 0));
    }
}
