package menu;

import Customers.Customer;
import Managers.CustomerManager;
import Managers.EmployeeManager;
import Managers.StorageManager;
import Products.Product;
import Serialization.DeserializerJson;
import Serialization.JsonNames;
import Serialization.SerializerJson;
import Storages.PointOfSale;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import Tools.ChoiceFrom;
import Tools.Counters;

public class MenuChoiceSecond {
    private final Scanner scanner = new Scanner(System.in);
    private final DeserializerJson deserializerJson = new DeserializerJson();
    //второй выбор в меню
     public void menuChoice2() throws IOException {
        while (true) {
            System.out.println("1 - открыть пункт продаж");
            System.out.println("2 - закрыть пункт продаж");
            System.out.println("3 - продать товар");
            System.out.println("4 - вернуть товар");
            System.out.println("5 - информация о пункте");
            System.out.println("6 - информация о товарах");
            System.out.println("7 - назад");
            System.out.println("8 - доходность пункта продаж");
            String choiceable;
            int choice;
            while(true) {
                choiceable = scanner.nextLine();
                choice = Integer.parseInt(choiceable.trim());

                if (choice < 1 || choice > 8) {
                    System.out.println("неверная команда");
                    continue;
                }
                break;

            }
            //открыть пвз
            if (choice == 1){
                deserializerJson.deserialize(JsonNames.employees, JsonNames.customers, JsonNames.warehouse, JsonNames.pos);
                System.out.println("Адрес?");
                String address = scanner.nextLine().trim();
                StorageManager.addStorage(new PointOfSale(address));
                SerializerJson.serialize(EmployeeManager.getIdToEmployee(),
                            StorageManager.getIdToStorage(), CustomerManager.getIdToCustomer());

            }
            //закрыть пвз
            else if (choice == 2){

                deserializerJson.deserialize(JsonNames.employees, JsonNames.customers, JsonNames.warehouse, JsonNames.pos);
                List<PointOfSale> pointOfSaleList = Counters.posCounter();
                if (pointOfSaleList.isEmpty()){
                    System.out.println("нет пвз");
                    return;
                }
                int choicePos;
                while(true){
                    choiceable = scanner.nextLine();
                    choicePos = Integer.parseInt(choiceable.trim());

                    if (choicePos <  1 || choicePos > pointOfSaleList.size()){
                        continue;
                    }
                    break;
                }
                StorageManager.closeStorage(pointOfSaleList.get(choicePos-1).getId());
                SerializerJson.serialize(EmployeeManager.getIdToEmployee(),
                        StorageManager.getIdToStorage(), CustomerManager.getIdToCustomer());

            }
            //продать товар
            else if (choice == 3){
                deserializerJson.deserialize(JsonNames.employees, JsonNames.customers, JsonNames.warehouse, JsonNames.pos);
                List<PointOfSale> pointOfSaleList = Counters.posCounter();
                if (pointOfSaleList.isEmpty()){
                    System.out.println("нет пвз");
                    return;
                }
                int choicePos;
                while(true) {
                    choiceable = scanner.nextLine();
                    choicePos = Integer.parseInt(choiceable.trim());

                    if (choicePos < 1 || choicePos > pointOfSaleList.size()) {
                        System.out.println("неверная команда");
                        continue;
                    }
                    break;
                }
                System.out.println("введите имя покупателя");
                String name = scanner.nextLine().trim();
                Customer customer = new Customer(name);
                List<Product> products = new ArrayList<>();
                int counter = 0;
                for(Product product : pointOfSaleList.get(choicePos-1).getIdToProduct().values()){
                    System.out.println(++counter + " - " + product.getName());
                    products.add(product);
                }
                if (products.isEmpty()){
                    System.out.println("нет товару");
                    return;
                }
                int choiceProd;
                while(true) {
                    choiceable = scanner.nextLine();
                    choiceProd = Integer.parseInt(choiceable.trim());

                    if (choiceProd < 1 || choiceProd > products.size()) {
                        System.out.println("неверная команда");
                        continue;
                    }
                    break;
                }

                customer.buy(pointOfSaleList.get(choicePos-1).getId(), products.get(choiceProd-1).getId());
                CustomerManager.addCustomer(customer);
                SerializerJson.serialize(EmployeeManager.getIdToEmployee(),
                        StorageManager.getIdToStorage(), CustomerManager.getIdToCustomer());

            }
            //вернуть товар
            else if (choice == 4){
                deserializerJson.deserialize(JsonNames.employees, JsonNames.customers, JsonNames.warehouse, JsonNames.pos);
                List<Customer> customers = Counters.cusCounter();
                if (customers.isEmpty()){
                    System.out.println("нет продаж");
                    return;
                }
                System.out.println("Выберите у какого покупателя вернуть товар");
                int choiceCus;
                while(true) {
                    choiceable = scanner.nextLine();
                    choiceCus = Integer.parseInt(choiceable.trim());

                    if (choiceCus < 1 || choiceCus > customers.size()) {
                        System.out.println("неверная команда");
                        continue;
                    }
                    break;
                }
                int counter = 0;
                List<String> poses =  new ArrayList<>();
                for(String posId : customers.get(choiceCus-1).getPosToProdBought().keySet()){
                    System.out.println(++counter + " - " + posId);
                    poses.add(posId);
                }

                int choicePos;
                while(true) {
                    choiceable = scanner.nextLine();
                    choicePos = Integer.parseInt(choiceable.trim());

                    if (choicePos < 1 || choicePos > poses.size()) {
                        System.out.println("неверная команда");
                        continue;
                    }
                    break;
                }
                int counterProd = 0;
                List<String> products = new ArrayList<>();
                for(HashMap<String, Integer> hashMap : customers.get(choiceCus-1).getPosToProdBought().get(poses.get(choicePos-1))){
                    for (String key : hashMap.keySet()){
                        System.out.println(++counterProd + " - " + key + " " + hashMap.get(key));
                        products.add(key);
                    }

                }
                if (products.isEmpty()){
                    System.out.println("нет продуктов");
                    return;
                }
                int choiceProd;
                while(true) {
                    choiceable = scanner.nextLine();
                    choiceProd = Integer.parseInt(choiceable.trim());

                    if (choiceProd < 1 || choiceProd > products.size()) {
                        System.out.println("неверная команда");
                        continue;
                    }
                    break;
                }
                System.out.println("сколько вернуть?");
                choiceable = scanner.nextLine();
                int amount = Integer.parseInt(choiceable.trim());

                CustomerManager.getCustomer(customers.get(choiceCus-1).getId()).
                        returnProduct(poses.get(choicePos-1), products.get(choiceProd-1), amount);
                SerializerJson.serialize(EmployeeManager.getIdToEmployee(),
                        StorageManager.getIdToStorage(), CustomerManager.getIdToCustomer());
                //инфо
            }
            else if (choice == 5){
                deserializerJson.deserialize(JsonNames.employees, JsonNames.customers, JsonNames.warehouse, JsonNames.pos);
                List<PointOfSale> pointList = Counters.posCounter();
                if (pointList.isEmpty()){
                    System.out.println("нет пвз");
                    return;
                }
                while(true){
                    try{
                        choiceable = scanner.nextLine();
                        int choiceToClose = Integer.parseInt(choiceable.trim());

                        if (choiceToClose < 1 || choiceToClose > pointList.size()) {
                            System.out.println("неверная команда");
                            continue;
                        }
                        StorageManager.getStorage(pointList.get(choiceToClose-1).getId()).about();
                        break;
                    } catch (RuntimeException e) {
                        System.out.println("неверная команда");
                    }
                }
                SerializerJson.serialize(EmployeeManager.getIdToEmployee(),
                        StorageManager.getIdToStorage(), CustomerManager.getIdToCustomer());
            }
            //инфо о товарах
            else if (choice == 6){
                deserializerJson.deserialize(JsonNames.employees, JsonNames.customers, JsonNames.warehouse, JsonNames.pos);
                List<PointOfSale> pointOfSaleList = Counters.posCounter();
                if (pointOfSaleList.isEmpty()){
                    System.out.println("нет пвз");
                    return;
                }
                while(true){
                    try{
                        choiceable = scanner.nextLine();
                        int choiceToClose = Integer.parseInt(choiceable.trim());

                        if (choiceToClose < 1 || choiceToClose > pointOfSaleList.size()) {
                            System.out.println("неверная команда");
                            continue;
                        }
                        StorageManager.getStorage(pointOfSaleList.get(choiceToClose-1).getId()).aboutProducts();
                        break;
                    } catch (RuntimeException e) {

                        System.out.println("неверная команда");
                    }
                }
                SerializerJson.serialize(EmployeeManager.getIdToEmployee(),
                        StorageManager.getIdToStorage(), CustomerManager.getIdToCustomer());
            }
            //выйти
            else if (choice == 7) {
                return;
            }
            //доходность
            else {
                deserializerJson.deserialize(JsonNames.employees, JsonNames.customers, JsonNames.warehouse, JsonNames.pos);
                List<PointOfSale> pointOfSaleList = Counters.posCounter();
                if (pointOfSaleList.isEmpty()){
                    System.out.println("нет пвз");
                    return;
                }
                PointOfSale pos = pointOfSaleList.get(ChoiceFrom.choiceFromList(pointOfSaleList)-1);
                System.out.println(pos.getIncome());
                SerializerJson.serialize(EmployeeManager.getIdToEmployee(),
                        StorageManager.getIdToStorage(), CustomerManager.getIdToCustomer());
            }
        }

    }
}
