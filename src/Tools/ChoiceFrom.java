package Tools;

import java.util.List;
import java.util.Scanner;

public class ChoiceFrom {
    //выбор из предложенного
    private static final Scanner scanner = new Scanner(System.in);
    public static int choiceFromList(List<?> list){
        int choice;
        while(true){
            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice < 1||choice>list.size()){
                continue;
            }
            break;
        }
        return choice;
    }
}
