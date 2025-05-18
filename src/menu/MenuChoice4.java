package menu;

import Managers.EmployeeManager;
import Managers.StorageManager;
import PositionEnums.PointOfSalePositions;
import PositionEnums.WareHousePositions;
import Storages.PointOfSale;
import Storages.Storage;
import Storages.Warehouse;
import Tools.ChoiceFrom;
import Tools.Counters;
import Employee.Employee;

import java.util.List;
import java.util.Scanner;
public class MenuChoice4 {
    private final Scanner scanner = new Scanner(System.in);



    //выбор меню 4
     public void menuChoice4() {
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
                    position = pointOfSalePositions.get(ChoiceFrom.choiceFromList(pointOfSalePositions)).getPosition();

                }
                if (storage instanceof Warehouse) {
                    List<WareHousePositions> WarehousePos = Counters.WareHousePositionCounter();
                    position = WarehousePos.get(ChoiceFrom.choiceFromList(WarehousePos)).getPosition();
                }
                EmployeeManager.addEmployee(name, lastname, position, storage);
                System.out.println("добавлен сотрудник");
            }
            //увольнение сотрудника
            if (choice == 2){
                List<Employee> employees = Counters.empCounter();
                if(employees.isEmpty()){
                    System.out.println("нет работников");
                    return;
                }
                Employee employee = employees.get(ChoiceFrom.choiceFromList(employees)-1);
                Storage fromStorage = StorageManager.getStorage(employee.getLocation());
                EmployeeManager.deleteEmployeePos(employee, fromStorage);
            }
            //смена ответственного лица
            if(choice == 3){
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
                    storage = warehouseList.get(ChoiceFrom.choiceFromList(warehouseList));

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
                    position = pointOfSalePositions.get(ChoiceFrom.choiceFromList(pointOfSalePositions)).getPosition();

                }
                if (storage instanceof Warehouse) {
                    List<WareHousePositions> WarehousePos = Counters.WareHousePositionCounter();
                    position = WarehousePos.get(ChoiceFrom.choiceFromList(WarehousePos)).getPosition();
                }
                EmployeeManager.changePosition(employee, fromStorage, storage, position);
            }
            //назад
            if(choice == 4){
                return;
            }


        }
    }
}
