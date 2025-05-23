package menu;

import Managers.CustomerManager;
import Managers.EmployeeManager;
import Managers.StorageManager;
import PositionEnums.PointOfSalePositions;
import PositionEnums.WareHousePositions;
import Serialization.DeserializerJson;
import Serialization.JsonNames;
import Serialization.SerializerJson;
import Storages.PointOfSale;
import Storages.Storage;
import Storages.Warehouse;
import Tools.ChoiceFrom;
import Tools.Counters;
import Employee.Employee;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
public class MenuChoice4 {
    private final Scanner scanner = new Scanner(System.in);
    private final DeserializerJson deserializerJson = new DeserializerJson();


    //выбор меню 4
     public void menuChoice4() throws IOException {
        while (true) {
            System.out.println("1 - найм сотрудника");
            System.out.println("2 - уволить сотрудника");
            System.out.println("3 - смена ответственного лица");
            System.out.println("4 - назад");
            int choice;
            String choiceable;
            while (true) {
                try {
                    choiceable = scanner.nextLine();
                    choice = Integer.parseInt(choiceable.trim());

                    if (choice < 1 || choice > 7) {
                        System.out.println("неверная команда");
                        continue;
                    }
                    break;
                } catch (RuntimeException e) {
                    System.out.println("неверная команда");
                }
            }
            //найм сотрудника
            if (choice == 1) {
                deserializerJson.deserialize(JsonNames.employees, JsonNames.customers, JsonNames.warehouse, JsonNames.pos);
                System.out.println("введите имя");
                String name = scanner.nextLine().trim();
                System.out.println("введите фамилию");
                String lastname = scanner.nextLine().trim();
                System.out.println("склад или пункт продаж? 0 : 1");
                int choiceStore;
                while (true) {
                    choiceable = scanner.nextLine();
                    choiceStore = Integer.parseInt(choiceable.trim());

                    if (choiceStore != 0 && choiceStore != 1) {
                        continue;
                    }
                    break;
                }
                Storage storage = null;
                if (choiceStore == 0) {
                    List<Warehouse> warehouseList = Counters.warehouseCounter();
                    if (warehouseList.isEmpty()){
                        System.out.println("нет складов");
                        return;
                    }

                    storage = warehouseList.get(ChoiceFrom.choiceFromList(warehouseList)-1);

                }
                if (choiceStore == 1) {
                    List<PointOfSale> poses = Counters.posCounter();
                    if (poses.isEmpty()){
                        System.out.println("нет пвз");
                        return;
                    }

                    storage = poses.get(ChoiceFrom.choiceFromList(poses) - 1);
                }
                String position = "";
                if (storage instanceof PointOfSale) {
                    List<PointOfSalePositions> pointOfSalePositions = Counters.posPositionCounter();
                    position = pointOfSalePositions.get(ChoiceFrom.choiceFromList(pointOfSalePositions)-1).getPosition();


                }
                if (storage instanceof Warehouse) {
                    List<WareHousePositions> WarehousePos = Counters.WareHousePositionCounter();
                    position = WarehousePos.get(ChoiceFrom.choiceFromList(WarehousePos)-1).getPosition();
                }
                EmployeeManager.addEmployee(name, lastname, position, storage);
                System.out.println("добавлен сотрудник");
                SerializerJson.serialize(EmployeeManager.getIdToEmployee(),
                        StorageManager.getIdToStorage(), CustomerManager.getIdToCustomer());
            }
            //увольнение сотрудника
            if (choice == 2){
                deserializerJson.deserialize(JsonNames.employees, JsonNames.customers, JsonNames.warehouse, JsonNames.pos);
                List<Employee> employees = Counters.empCounter();
                if(employees.isEmpty()){
                    System.out.println("нет работников");
                    return;
                }
                Employee employee = employees.get(ChoiceFrom.choiceFromList(employees)-1);
                Storage fromStorage = StorageManager.getStorage(employee.getLocation());
                EmployeeManager.deleteEmployeePos(employee, fromStorage);
                SerializerJson.serialize(EmployeeManager.getIdToEmployee(),
                        StorageManager.getIdToStorage(), CustomerManager.getIdToCustomer());
            }
            //смена ответственного лица
            if(choice == 3){
                deserializerJson.deserialize(JsonNames.employees, JsonNames.customers, JsonNames.warehouse, JsonNames.pos);
                List<Employee> employees = Counters.empCounter();
                if(employees.isEmpty()){
                    System.out.println("нет работников");
                    return;
                }
                Employee employee = employees.get(ChoiceFrom.choiceFromList(employees)-1);
                Storage fromStorage = StorageManager.getStorage(employee.getLocation());
                System.out.println("склад или пункт продаж? 0 : 1");
                int choiceStore;
                while (true) {
                    choiceable = scanner.nextLine();
                    choiceStore = Integer.parseInt(choiceable.trim());

                    if (choiceStore != 0 && choiceStore != 1) {
                        continue;
                    }
                    break;
                }
                Storage storage = null;
                if (choiceStore == 0) {
                    List<Warehouse> warehouseList = Counters.warehouseCounter();
                    if(warehouseList.isEmpty()){
                        System.out.println("нет складов");
                        return;
                    }
                    storage = warehouseList.get(ChoiceFrom.choiceFromList(warehouseList)-1);

                }
                if (choiceStore == 1) {
                    List<PointOfSale> poses = Counters.posCounter();
                    if(poses.isEmpty()){
                        System.out.println("нет пвз");
                        return;
                    }
                    storage = poses.get(ChoiceFrom.choiceFromList(poses) - 1);
                }
                String position = "";
                if (storage instanceof PointOfSale) {
                    List<PointOfSalePositions> pointOfSalePositions = Counters.posPositionCounter();
                    position = pointOfSalePositions.get(ChoiceFrom.choiceFromList(pointOfSalePositions)-1).getPosition();

                }
                if (storage instanceof Warehouse) {
                    List<WareHousePositions> WarehousePos = Counters.WareHousePositionCounter();
                    position = WarehousePos.get(ChoiceFrom.choiceFromList(WarehousePos)-1).getPosition();
                }
                EmployeeManager.changePosition(employee, fromStorage, storage, position);
                SerializerJson.serialize(EmployeeManager.getIdToEmployee(),
                        StorageManager.getIdToStorage(), CustomerManager.getIdToCustomer());
            }
            //назад
            if(choice == 4){
                return;
            }


        }
    }
}
