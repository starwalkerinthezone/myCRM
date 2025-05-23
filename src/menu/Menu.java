package menu;

import java.util.Scanner;

public class Menu {
    //изначальное меню
    private final Scanner scanner = new Scanner(System.in);
    public int menu(){
        System.out.println("Команды:");
        System.out.println("1 - работа со складами");
        System.out.println("2 - работа с пунктами продаж");
        System.out.println("3 - работа с сотрудниками");
        System.out.println("4 - закрыть программу");
        while(true) {
            //проверка на правилльность вводимого значения

            String input = scanner.nextLine(); // Читаем строку
            try {
                int choice = Integer.parseInt(input.trim());
                if (choice >= 1 && choice <= 4) {
                    return choice;
                }
            } catch (NumberFormatException e) {
                // Не число — цикл продолжится
            }
            System.out.println("Ошибка: введите число от 1 до 4");
        }
    }
}
