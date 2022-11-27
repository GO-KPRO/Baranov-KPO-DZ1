package bar.hse.ru.reversy.parts;
import bar.hse.ru.reversy.primitives.Player;

import java.util.List;
import java.util.ArrayList;

public class ScoreBoard {
    private final List<Player> guys = new ArrayList<>();
    public void addToBoard(Player man) {
        guys.add(man);
    }
    public void outBoard() {
        for (int i = 0; i < guys.size(); ++i) {
            System.out.printf("%s %d\n", this.guys.get(i).getName(), this.guys.get(i).getScore());
        }
        System.out.println("");
    }
    public void outBoardMax() {
        int maxScore = 0;
        for (int i = 0; i < guys.size(); ++i) {
            if (maxScore < this.guys.get(i).getScore()) {
                maxScore = this.guys.get(i).getScore();
            };
        }
        for (int i = 0; i < guys.size(); ++i) {
            if (this.guys.get(i).getScore() == maxScore) {
                System.out.printf("%s %d\n", this.guys.get(i).getName(), this.guys.get(i).getScore());
            }
        }
        System.out.println();
    }
}
