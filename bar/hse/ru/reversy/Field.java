package bar.hse.ru.reversy;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

public class Field {
    private final List<List<Cell>> gameField = new ArrayList<>();
    public Field() {
        for (int i = 0; i < 8; ++i) {
            gameField.add(new ArrayList<Cell>());
            for (int j = 0; j < 8; ++j) {
                gameField.get(i).add(new Cell());
            }
        }
        gameField.get(3).get(3).changeToWhite();
        gameField.get(4).get(4).changeToWhite();
        gameField.get(3).get(4).changeToBlack();
        gameField.get(4).get(3).changeToBlack();
    }
    private Pair runner(char colour, int a, int b, int i, int j) {
        int aCur = a;
        int bCur = b;
        while ((((i + aCur >= 0) && (i + aCur <= 7)) && ((j + bCur >= 0) && (j + bCur <= 7)))) {
            if ((gameField.get(i + aCur).get(j + bCur).getColour() == colour)) {
                aCur += a;
                bCur += b;
            } else {
                break;
            }
        }
        return new Pair(aCur, bCur);
    }
    public void outField() {
        System.out.println("  1_2_3_4_5_6_7_8 ");
        for (int i = 0; i < 8; ++i) {
            System.out.printf("%d|", i + 1);
            for (int j = 0; j < 8; ++j) {
                System.out.printf("%c ", gameField.get(i).get(j).getColour());
            }
            System.out.print("\n");
        }
    }
    public void outFieldBlack() {
        System.out.println("  1_2_3_4_5_6_7_8 ");
        for (int i = 0; i < 8; ++i) {
            System.out.printf("%d|", i + 1);
            for (int j = 0; j < 8; ++j) {
                if (gameField.get(i).get(j).getColour() == 'O') {
                    char curColour = gameField.get(i).get(j).getColour();
                    for (int a = -1; a <= 1; ++a) {
                        for (int b = -1; b <= 1; ++b) {
                            Pair cur = runner('W', a, b, i, j);
                            int aCur = cur.getFirst();
                            int bCur = cur.getSecond();
                            if (((i + aCur >= 0) && (i + aCur <= 7)) && ((j + bCur >= 0) && (j + bCur <= 7))) {
                                if ((gameField.get(i + aCur).get(j + bCur).getColour() == 'B') && ((abs(aCur) > abs(a)) || (abs(bCur) > abs(b)))) {
                                    curColour = 'P';
                                }
                            }
                        }
                    }
                    System.out.printf("%c ", curColour);
                } else {
                    System.out.printf("%c ", gameField.get(i).get(j).getColour());
                }
            }
            System.out.print("\n");
        }
    }
    public void outFieldWhite() {
        System.out.println("  1_2_3_4_5_6_7_8 ");
        for (int i = 0; i < 8; ++i) {
            System.out.printf("%d|", i + 1);
            for (int j = 0; j < 8; ++j) {
                if (gameField.get(i).get(j).getColour() == 'O') {
                    char curColour = gameField.get(i).get(j).getColour();
                    for (int a = -1; a <= 1; ++a) {
                        for (int b = -1; b <= 1; ++b) {
                            Pair cur = runner('B', a, b, i, j);
                            int aCur = cur.getFirst();
                            int bCur = cur.getSecond();
                            if (((i + aCur >= 0) && (i + aCur <= 7)) && ((j + bCur >= 0) && (j + bCur <= 7))) {
                                if ((gameField.get(i + aCur).get(j + bCur).getColour() == 'W') && ((abs(aCur) > abs(a)) || (abs(bCur) > abs(b)))) {
                                    curColour = 'P';
                                }
                            }
                        }
                    }
                    System.out.printf("%c ", curColour);
                } else {
                    System.out.printf("%c ", gameField.get(i).get(j).getColour());
                }
            }
            System.out.print("\n");
        }
    }
    public int blackCount() {
        int count = 0;
        for (int i = 0; i <= 7; ++i) {
            for (int j = 0; j <= 7; ++j) {
                if (gameField.get(i).get(j).getColour() == 'B') {
                    ++count;
                }
            }
        }
        return count;
    }
    public int whiteCount() {
        int count = 0;
        for (int i = 0; i <= 7; ++i) {
            for (int j = 0; j <= 7; ++j) {
                if (gameField.get(i).get(j).getColour() == 'W') {
                    ++count;
                }
            }
        }
        return count;
    }
    public int whitePossibleCount() {
        int count = 0;
        for (int i = 0; i <= 7; ++i) {
            for (int j = 0; j <= 7; ++j) {
                boolean flag = false;
                if (gameField.get(i).get(j).getColour() == 'O') {
                    for (int a = -1; a <= 1; ++a) {
                        for (int b = -1; b <= 1; ++b) {
                            Pair cur = runner('B', a, b, i, j);
                            int aCur = cur.getFirst();
                            int bCur = cur.getSecond();
                                if (((i + aCur >= 0) && (i + aCur <= 7)) && ((j + bCur >= 0) && (j + bCur <= 7))) {
                                    if ((gameField.get(i + aCur).get(j + bCur).getColour() == 'W') && ((abs(aCur) > abs(a)) || (abs(bCur) > abs(b)))) {
                                        flag = true;
                                    }
                                }
                        }
                    }
                }
                if (flag) {
                    ++count;
                }
            }
        }
        return count;
    }
    public int blackPossibleCount() {
        int count = 0;
        for (int i = 0; i <= 7; ++i) {
            for (int j = 0; j <= 7; ++j) {
                boolean flag = false;
                if (gameField.get(i).get(j).getColour() == 'O') {
                    for (int a = -1; a <= 1; ++a) {
                        for (int b = -1; b <= 1; ++b) {
                            Pair cur = runner('W', a, b, i, j);
                            int aCur = cur.getFirst();
                            int bCur = cur.getSecond();
                            if (((i + aCur >= 0) && (i + aCur <= 7)) && ((j + bCur >= 0) && (j + bCur <= 7))) {
                                if ((gameField.get(i + aCur).get(j + bCur).getColour() == 'B') && ((abs(aCur) > abs(a)) || (abs(bCur) > abs(b)))) {
                                    flag = true;
                                }
                            }
                        }
                    }
                }
                if (flag) {
                    ++count;
                }
            }
        }
        return count;
    }
    public boolean whiteMove(int i, int j) {
        --i;
        --j;
        boolean flag = false;
        if ((i >= 0) && (i <= 7) && (j >= 0) && (j <= 7)) {
            if (gameField.get(i).get(j).getColour() == 'O') {
                for (int a = -1; a <= 1; ++a) {
                    for (int b = -1; b <= 1; ++b) {
                        Pair cur = runner('B', a, b, i, j);
                        int aCur = cur.getFirst();
                        int bCur = cur.getSecond();
                        if (((i + aCur >= 0) && (i + aCur <= 7)) && ((j + bCur >= 0) && (j + bCur <= 7))) {
                            if ((gameField.get(i + aCur).get(j + bCur).getColour() == 'W') && ((abs(aCur) > abs(a)) || (abs(bCur) > abs(b)))) {
                                flag = true;
                                int n = i;
                                int m = j;
                                for (int x = 0; x < max(abs(bCur), abs(aCur)); ++x) {
                                    gameField.get(n).get(m).changeToWhite();
                                    n += a;
                                    m += b;
                                }
                            }
                        }
                    }
                }
            }
            if (!flag) {
                System.out.println("Wrong position");
            }
            return flag;
        } else {
            System.out.println("Wrong position");
            return flag;
        }
    }
    public boolean blackMove(int i, int j) {
        --i;
        --j;
        boolean flag = false;
        if ((i >= 0) && (i <= 7) && (j >= 0) && (j <= 7)) {
            if (gameField.get(i).get(j).getColour() == 'O') {
                for (int a = -1; a <= 1; ++a) {
                    for (int b = -1; b <= 1; ++b) {
                        Pair cur = runner('W', a, b, i, j);
                        int aCur = cur.getFirst();
                        int bCur = cur.getSecond();
                        if (((i + aCur >= 0) && (i + aCur <= 7)) && ((j + bCur >= 0) && (j + bCur <= 7))) {
                            if ((gameField.get(i + aCur).get(j + bCur).getColour() == 'B') && ((abs(aCur) > abs(a)) || (abs(bCur) > abs(b)))) {
                                flag = true;
                                int n = i;
                                int m = j;
                                for (int x = 0; x < max(abs(bCur), abs(aCur)); ++x) {
                                    gameField.get(n).get(m).changeToBlack();
                                    n += a;
                                    m += b;
                                }
                            }
                        }
                    }
                }
            }
            if (!flag) {
                System.out.println("Wrong position");
            }
            return flag;
        } else {
            System.out.println("Wrong position");
            return flag;
        }
    }
    public void blackComputerMove() {
        int maxScore = 0, maxi = 0, maxj = 0;
        for (int i = 0; i <= 7; ++i) {
            for (int j = 0; j <= 7; ++j) {
                int curScore;
                if ((i == 0) && (j == 0) || (i == 0) && (j == 7) || (i == 7) && (j == 0) || (i == 7) && (j == 7)){
                    curScore = 8;
                } else if (((i > 0) && (i < 7) && (j == 0 || j == 7)) || ((j > 0) && (j < 7) && (i == 0 || i == 7))) {
                    curScore = 4;
                } else {
                    curScore = 0;
                }
                if (gameField.get(i).get(j).getColour() == 'O') {
                    for (int a = -1; a <= 1; ++a) {
                        for (int b = -1; b <= 1; ++b) {
                            Pair cur = runner('W', a, b, i, j);
                            int aCur = cur.getFirst(), bCur = cur.getSecond();
                            if (((i + aCur >= 0) && (i + aCur <= 7)) && ((j + bCur >= 0) && (j + bCur <= 7))) {
                                if ((gameField.get(i + aCur).get(j + bCur).getColour() == 'B') && ((abs(aCur) > abs(a)) || (abs(bCur) > abs(b)))) {
                                    int n = i, m = j;
                                    for (int x = 0; x < max(abs(bCur), abs(aCur)); ++x) {
                                        if (((n > 0) && (n < 7) && (m == 0 || m == 7)) || ((m > 0) && (m < 7) && (n == 0 || n == 7))) {
                                            curScore += 20;
                                        } else {
                                            curScore += 10;
                                        }
                                        n += a;
                                        m += b;
                                    }
                                }
                            }
                        }
                    }
                }
               if (curScore > maxScore) {
                   maxScore = curScore;
                   maxi = i;
                   maxj = j;
               }
            }
        }
        blackMove(maxi + 1, maxj + 1);
    }
}

class Pair{
    private int first;
    private int second;
    public Pair(int f, int s) {
        first = f;
        second = s;
    }
    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }
}