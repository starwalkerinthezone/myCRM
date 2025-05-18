package menu;

import Managers.CustomerManager;
import Managers.EmployeeManager;
import Managers.StorageManager;
import Serialization.SerializerJson;

import java.io.IOException;
import java.util.Scanner;

public class MenuChoice5 {
    private final Scanner scanner = new Scanner(System.in);
    //меню выбор 5
    //сериализация
     public void menuChoice5() {
        String choiceable;
        try {
            System.out.println("1 - сохранить");
            System.out.println("2 - выйти");
            while (true) {
                choiceable = scanner.nextLine();
                int choice = Integer.parseInt(choiceable.trim());
                if (choice == 1) {
                    SerializerJson.serialize(EmployeeManager.getIdToEmployee(),
                            StorageManager.getIdToStorage(), CustomerManager.getIdToCustomer());
                    break;
                }
                if (choice == 2) {
                    break;
                }
            }
        }catch (IOException e){
            System.out.println("не удалось сохранить файлы");
        }
    }
}
