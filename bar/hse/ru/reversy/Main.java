package bar.hse.ru.reversy;

import bar.hse.ru.reversy.runners.Menu;

public class Main {
    public static void main(String[] args) {
        Menu program = Menu.getOneMenu();
        program.runMenu();
    }
}