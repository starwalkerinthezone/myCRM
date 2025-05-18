package menu;

import Serialization.DeserializerJson;
import Serialization.JsonNames;
import Serialization.JsonNames.*;
import java.io.IOException;
import java.util.Scanner;

public class MenuChoiceThird {
    private final Scanner scanner = new Scanner(System.in);
    //выбор в меню 3
     public void menuChoice3() {
            try{
                DeserializerJson DJ = new DeserializerJson();
                DJ.deserialize(JsonNames.employees, JsonNames.customers, JsonNames.warehouse, JsonNames.pos);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("неверно введен путь");
            }
    }
}
