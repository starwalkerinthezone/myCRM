package menu;

import Customers.Customer;
import Managers.CustomerManager;
import Managers.StorageManager;
import Products.Product;
import Storages.PointOfSale;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import Tools.ChoiceFrom;
import Tools.Counters;

public class MenuChoiceSecond {
    private final Scanner scanner = new Scanner(System.in);
    //второй выбор в меню
     public void menuChoice2(){
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
                System.out.println("Адрес?");
                String address = scanner.nextLine().trim();
                StorageManager.addStorage(new PointOfSale(address));
            }
            //закрыть пвз
            else if (choice == 2){
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
                //продать товар
            }
            else if (choice == 3){
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

            }
            //вернуть товар
            else if (choice == 4){
                System.out.println("Выберите у какого покупателя вернуть товар");
                List<Customer> customers = Counters.cusCounter();
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
                //инфо
            }
            else if (choice == 5){
                List<PointOfSale> pointList = Counters.posCounter();
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
            }
            //инфо о товарах
            else if (choice == 6){
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
            }
            //выйти
            else if (choice == 7) {
                return;
            }
            //доходность
            else {
                List<PointOfSale> pointOfSaleList = Counters.posCounter();
                if (pointOfSaleList.isEmpty()){
                    System.out.println("нет пвз");
                    return;
                }
                PointOfSale pos = pointOfSaleList.get(ChoiceFrom.choiceFromList(pointOfSaleList)-1);
                System.out.println(pos.getIncome());
            }
        }

    }
}
