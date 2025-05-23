package menu;

import Managers.CustomerManager;
import Managers.EmployeeManager;
import Managers.StorageManager;
import Products.Product;
import Serialization.DeserializerJson;
import Serialization.JsonNames;
import Serialization.SerializerJson;
import Storages.PointOfSale;
import Storages.Warehouse;
import Tools.ChoiceFrom;
import Tools.Counters;
import Tools.FailIn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuChoiceFirst {
    private final Scanner scanner = new Scanner(System.in);
    private final DeserializerJson deserializerJson = new DeserializerJson();
    //первый выбор в меню
     public void menuChoice1(){
        while(true) {
            System.out.println("1 - открытие нового склада");
            System.out.println("2 - закрытие склада");
            System.out.println("3 - информация о складе");
            System.out.println("4 - информация о товарах на складе");
            System.out.println("5 - перемещение товара по складу");
            System.out.println("6 - перемещение товара на пункт продажи");
            System.out.println("7 - назад");
            System.out.println("8 - доходность склада");
            System.out.println(" 9 - Закупка товара");
            System.out.println("10 - добавить ячейку склада");



            try{
                String choiceable;
                int choice;
                while(true) {
                    choiceable = scanner.nextLine();
                    choice = Integer.parseInt(choiceable.trim());
                    if (choice < 1 || choice > 10) {
                        System.out.println("неверная команда");
                        continue;
                    }
                    break;
                }
                //выборы в подменю
                //открыть склад
                if (choice == 1){
                    deserializerJson.deserialize(JsonNames.employees, JsonNames.customers, JsonNames.warehouse, JsonNames.pos);
                    System.out.println("Введите адрес склада:");
                    String address = scanner.nextLine().trim();
                    StorageManager.addStorage(new Warehouse(address));
                    SerializerJson.serialize(EmployeeManager.getIdToEmployee(),
                            StorageManager.getIdToStorage(), CustomerManager.getIdToCustomer());
                }
                //закрыть склад
                if(choice == 2){
                    deserializerJson.deserialize(JsonNames.employees, JsonNames.customers, JsonNames.warehouse, JsonNames.pos);
                    System.out.println("Выберите какой склад закрыть:");
                    List<Warehouse> warehouseList = Counters.warehouseCounter();
                    if (warehouseList.isEmpty()){
                        System.out.println("нет складов");
                        return;
                    }
                    while(true){
                        try{
                            choiceable = scanner.nextLine();
                            int choiceToClose = Integer.parseInt(choiceable.trim());
                            if (choiceToClose < 1 || choiceToClose > warehouseList.size()) {
                                System.out.println("неверная команда");
                                continue;
                            }
                            StorageManager.closeStorage(warehouseList.get(choiceToClose-1).getId());
                            break;
                        } catch (RuntimeException e) {
                            System.out.println("неверная команда");
                        }
                    }
                    SerializerJson.serialize(EmployeeManager.getIdToEmployee(),
                            StorageManager.getIdToStorage(), CustomerManager.getIdToCustomer());
                }
                //инфо о складе
                if (choice == 3){
                    deserializerJson.deserialize(JsonNames.employees, JsonNames.customers, JsonNames.warehouse, JsonNames.pos);
                    List<Warehouse> warehouseList = Counters.warehouseCounter();
                    if (warehouseList.isEmpty()){
                        System.out.println("нет складов");
                        return;
                    }
                    while(true){
                        try{
                            choiceable = scanner.nextLine();
                            int choiceToClose = Integer.parseInt(choiceable.trim());
                            if (choiceToClose == 0){
                                return;
                            }
                            else if (choiceToClose < 1 || choiceToClose > warehouseList.size()) {
                                System.out.println("неверная команда");
                                continue;
                            }

                            StorageManager.getStorage(warehouseList.get(choiceToClose-1).getId()).about();
                            break;
                        } catch (RuntimeException e) {
                            System.out.println("неверная команда");
                        }
                    }
                    SerializerJson.serialize(EmployeeManager.getIdToEmployee(),
                            StorageManager.getIdToStorage(), CustomerManager.getIdToCustomer());
                }
                //инфо о товарах на складе
                if (choice == 4){
                    deserializerJson.deserialize(JsonNames.employees, JsonNames.customers, JsonNames.warehouse, JsonNames.pos);
                    List<Warehouse> warehouseList = Counters.warehouseCounter();
                    if (warehouseList.isEmpty()){
                        System.out.println("нет складов");
                        return;
                    }
                    while(true){
                        try{
                            choiceable = scanner.nextLine();
                            int choiceToClose = Integer.parseInt(choiceable.trim());
                            if (choiceToClose < 1 || choiceToClose > warehouseList.size()) {
                                System.out.println("неверная команда");
                                continue;
                            }
                            StorageManager.getStorage(warehouseList.get(choiceToClose-1).getId()).aboutProducts();

                            break;
                        } catch (RuntimeException e) {
                            e.printStackTrace();

                        }
                    }
                    SerializerJson.serialize(EmployeeManager.getIdToEmployee(),
                            StorageManager.getIdToStorage(), CustomerManager.getIdToCustomer());
                }
                //перемещение товара по складу
                if (choice == 5){
                    deserializerJson.deserialize(JsonNames.employees, JsonNames.customers, JsonNames.warehouse, JsonNames.pos);
                    System.out.println("Выберите товар и ячейку");
                    List<Warehouse> warehouseList = Counters.warehouseCounter();
                    if (warehouseList.isEmpty()){
                        System.out.println("нет складов");
                        return;
                    }
                    while(true){
                        try{
                            choiceable = scanner.nextLine();
                            int choiceToClose = Integer.parseInt(choiceable.trim());

                            if (choiceToClose < 1 || choiceToClose > warehouseList.size()) {
                                System.out.println("неверная команда");
                                continue;
                            }
                            Warehouse warehouse = (Warehouse) StorageManager.getStorage(warehouseList.get(choiceToClose-1).getId());
                            List<Product> products = new ArrayList<>();
                            int counter = 0;
                            for(Product product : warehouse.getIdToProduct().values()){
                                System.out.println(++counter + " - " + product.getName());
                                products.add(product);
                            }
                            if (products.isEmpty()){
                                System.out.println("нет продуктов");
                                return;
                            }
                            int choiceProd;
                            while(true) {
                                choiceable = scanner.nextLine();
                                choiceProd = Integer.parseInt(choiceable.trim());

                                if (choiceProd < 1 || choiceProd > counter){
                                    continue;
                                }
                                break;
                            }
                            int counter1 = 0;
                            List<Warehouse.WarehouseCell> warehouseCells = new ArrayList<>();
                            for(Warehouse.WarehouseCell warehouseCell : warehouse.getIdToWarehouseCell().values()){
                                System.out.println(++counter1 + " - " + warehouseCell.getId());
                                warehouseCells.add(warehouseCell);
                            }
                            if (warehouseCells.isEmpty()){
                                System.out.println("нет ячеек");
                                return;
                            }
                            int choiceCell;
                            while(true) {
                                choiceable = scanner.nextLine();
                                choiceCell = Integer.parseInt(choiceable.trim());

                                if (choiceCell < 1 || choiceCell > counter1){
                                    continue;
                                }
                                break;
                            }
                            warehouse.moveProductInWarehouse(products.get(choiceProd-1).getId(), warehouseCells.get(choiceCell-1).getId());

                            break;
                        } catch (RuntimeException e) {
                            System.out.println("неверная команда");
                        }
                    }
                    SerializerJson.serialize(EmployeeManager.getIdToEmployee(),
                            StorageManager.getIdToStorage(), CustomerManager.getIdToCustomer());
                }
                //перемещение товара на пункт продаж
                if (choice == 6){
                    deserializerJson.deserialize(JsonNames.employees, JsonNames.customers, JsonNames.warehouse, JsonNames.pos);
                    System.out.println("Выберите склад, товар и пункт продаж");
                    List<Warehouse> warehouseList = Counters.warehouseCounter();
                    if (warehouseList.isEmpty()){
                        System.out.println("нет складов");
                        return;
                    }
                    int choiceToClose = ChoiceFrom.choiceFromList(warehouseList);
                    Warehouse warehouse = (Warehouse) StorageManager.getStorage(warehouseList.get(choiceToClose-1).getId());
                    List<Product> products = new ArrayList<>();
                    int counter = 0;
                    for(Product product : warehouse.getIdToProduct().values()){
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

                        if (choiceProd < 1 || choiceProd > counter){
                            continue;
                        }
                        break;
                    }
                    int choicePos;
                    List<PointOfSale> poses = Counters.posCounter();
                    if (poses.isEmpty()){
                        System.out.println("нет пвз");
                        return;
                    }
                    while(true){
                        choiceable = scanner.nextLine();
                        choicePos = Integer.parseInt(choiceable.trim());

                        if (choicePos < 1 || choicePos>poses.size()){
                            continue;
                        }
                        break;
                    }
                    Warehouse neededWarehouse = (Warehouse) StorageManager.getIdToStorage().get(warehouse.getId());
                    neededWarehouse.moveProductToPointSale(products.get(choiceProd-1).getId(), poses.get(choicePos-1).getId());
                    SerializerJson.serialize(EmployeeManager.getIdToEmployee(),
                            StorageManager.getIdToStorage(), CustomerManager.getIdToCustomer());
                }
                //выйти
                if(choice == 7){
                    return;
                }
                //узнать доходность
                if(choice==8){
                    deserializerJson.deserialize(JsonNames.employees, JsonNames.customers, JsonNames.warehouse, JsonNames.pos);
                    List<Warehouse> warehouseList = Counters.warehouseCounter();
                    if (warehouseList.isEmpty()){
                        System.out.println("нет складов");
                        return;
                    }
                    Warehouse warehouse = warehouseList.get(ChoiceFrom.choiceFromList(warehouseList)-1);
                    System.out.println(warehouse.getIncome());
                    SerializerJson.serialize(EmployeeManager.getIdToEmployee(),
                            StorageManager.getIdToStorage(), CustomerManager.getIdToCustomer());
                }
                //закупка продукта
                if(choice == 9){
                    deserializerJson.deserialize(JsonNames.employees, JsonNames.customers, JsonNames.warehouse, JsonNames.pos);
                    System.out.println("Введите имя продукта");
                    String productName = scanner.nextLine().trim();
                    System.out.println("Введите цену продукта");
                    int productPrice;
                    if (scanner.hasNextInt()){
                        choiceable = scanner.nextLine();
                        productPrice = Integer.parseInt(choiceable.trim());
                    }
                    else{
                        FailIn.failIn();
                        break;
                    }
                    System.out.println("Введите кол-во продукта");
                    int productAmount;
                    if (scanner.hasNextInt()){
                        choiceable = scanner.nextLine();
                        productAmount = Integer.parseInt(choiceable.trim());
                    }
                    else{
                        FailIn.failIn();
                        break;
                    }



                    List<Warehouse> warehouseList = Counters.warehouseCounter();
                    if (warehouseList.isEmpty()){
                        System.out.println("нет складов");
                        return;
                    }
                    System.out.println("В какой склад?");
                    Warehouse warehouse = warehouseList.get(ChoiceFrom.choiceFromList(warehouseList)-1);
                    if (warehouse.getIdToWarehouseCell().isEmpty()){
                        System.out.println("нет ячеек склада");
                        break;
                    }
                    Product newProduct = Product.newProduct(productName, productPrice, productAmount);
                    List<Warehouse.WarehouseCell> warehouseCells = new ArrayList<>();
                    int counterCell = 0;

                    for (Warehouse.WarehouseCell Cell : warehouse.getIdToWarehouseCell().values()) {
                        System.out.println(++counterCell + " - " + Cell.getId());
                        warehouseCells.add(Cell);
                    }
                    if (warehouseCells.isEmpty()){
                        System.out.println("нет ячеек");
                        return;
                    }
                    System.out.println("Выберите ячейку");
                    int choiceCell;
                    while (true){
                        choiceable = scanner.nextLine();
                        choiceCell = Integer.parseInt(choiceable.trim());

                        if (choiceCell < 1 || choiceCell > warehouseCells.size()){
                            System.out.println("выберите из данного");
                            continue;
                        }
                        break;
                    }
                    warehouse.purchaseProduct(newProduct, warehouseCells.get(choiceCell-1).getId());
                    SerializerJson.serialize(EmployeeManager.getIdToEmployee(),
                            StorageManager.getIdToStorage(), CustomerManager.getIdToCustomer());
                }
                //создать ячейку склада

                if (choice == 10){
                    deserializerJson.deserialize(JsonNames.employees, JsonNames.customers, JsonNames.warehouse, JsonNames.pos);
                    System.out.println("в каком складе?");
                    List<Warehouse> warehouseList = Counters.warehouseCounter();
                    if (warehouseList.isEmpty()){
                        System.out.println("нет складов");
                        return;
                    }
                    Warehouse warehouse = (Warehouse) StorageManager.getStorage(warehouseList.get(ChoiceFrom.choiceFromList(warehouseList)-1).getId());
                    System.out.println("Введите наименование ячейки");
                    String name = scanner.nextLine().trim();
                    warehouse.addWarehouseCell(name);
                    SerializerJson.serialize(EmployeeManager.getIdToEmployee(),
                            StorageManager.getIdToStorage(), CustomerManager.getIdToCustomer());
                }
            } catch (RuntimeException e) {
                System.out.println("неверная команда");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
