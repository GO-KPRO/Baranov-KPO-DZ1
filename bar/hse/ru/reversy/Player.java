package bar.hse.ru.reversy;

public class Player {
    private final String name;
    private String team;
    private int score = 0;
    Player(String Name) {
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
    public String getTeam() {
        return team;
    }
}
