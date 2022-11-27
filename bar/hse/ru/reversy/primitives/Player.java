package bar.hse.ru.reversy.primitives;

public class Player implements Gamer {
    private final String name;
    private String team;
    private int score = 0;
    public Player(String Name) {
        name = Name;
    }
    public void setScore(int Score) {
        score = Score;
    }
    public void setWhiteTeam() {
        team = "White";
    }
    public void setBlackTeam() {
        team = "Black";
    }
    public String getName() {
        return name;
    }
    public int getScore() {
        return score;
    }
}
