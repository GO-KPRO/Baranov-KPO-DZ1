package bar.hse.ru.reversy.runners;

import java.util.Scanner;

public class Menu {
    private final Game curGame = new Game();



    private static Menu oneMenu;
    private Menu() {
    }
    public static Menu getOneMenu() {
        if (oneMenu == null) {
            oneMenu = new Menu();
        }
        return oneMenu;
    }
    public void runMenu(){
        while(true) {
        System.out.println("Which mode do you want to run?");
        System.out.println("PvP - 2 human players");
        System.out.println("PvE - Human vs computer");
        System.out.println("PvhE - Human vs hard computer");
        System.out.println("SB - Show score board");
        System.out.println("MS - Show max score");
        System.out.println("exit - exit and stop the program");
        Scanner in = new Scanner(System.in);
            switch (in.nextLine()) {
                case ("PvP") -> curGame.runPvPGame();
                case ("PvE") -> curGame.runPvEGame();
                case ("PvhE") -> curGame.runPvhEGame();
                case ("SB") -> curGame.showScoreBoard();
                case ("MS") -> curGame.showMaxScore();
                case ("exit") -> {
                    return;
                }
                default -> System.out.println("Wrong input");
            }
        }
    }
}
