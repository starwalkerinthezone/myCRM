import menu.*;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        //цикл работы программы
        while (true){
            Menu menu = new Menu();
            int choice = menu.menu();
            if (choice == 1) {
                MenuChoiceFirst menuChoiceFirst = new MenuChoiceFirst();
                menuChoiceFirst.menuChoice1();
            }
            else if (choice == 2){
                MenuChoiceSecond menuChoiceSecond = new MenuChoiceSecond();
                menuChoiceSecond.menuChoice2();
            }
            else if (choice == 3){
                MenuChoice4 menuChoice4 = new MenuChoice4();
                menuChoice4.menuChoice4();
            }

            else if(choice == 4){
                System.exit(0);
            }
        }
    }


}
