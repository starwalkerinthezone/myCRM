package Tools;

import Customers.Customer;
import Employee.Employee;
import Managers.CustomerManager;
import Managers.EmployeeManager;
import Managers.StorageManager;
import PositionEnums.PointOfSalePositions;
import PositionEnums.WareHousePositions;
import Storages.PointOfSale;
import Storages.Storage;
import Storages.Warehouse;

import java.util.ArrayList;
import java.util.List;

public class Counters {
    //счетчик складов
    public static List<Warehouse> warehouseCounter(){
        int counter = 0;
        List<Warehouse> warehouseList = new ArrayList<>();
        for (Storage storage : StorageManager.getIdToStorage().values()){
            if (storage instanceof Warehouse){
                System.out.println(++counter + " - " + storage.getAddress());
                warehouseList.add((Warehouse) storage);
            }
        }
        return warehouseList;
    }
    //счетчик пвз
   public static List<PointOfSale> posCounter(){
        int counter = 0;
        List<PointOfSale> pointOfSaleList = new ArrayList<>();
        for (Storage storage : StorageManager.getIdToStorage().values()){
            if(storage instanceof PointOfSale){
                System.out.println(++counter + " - " + storage.getAddress());
                pointOfSaleList.add((PointOfSale) storage);

            }
        }
        return pointOfSaleList;
    }
    //счетчик покупателей
    public static List<Customer> cusCounter(){
        int counter = 0;
        List<Customer> CustomerList = new ArrayList<>();
        for (Customer customer : CustomerManager.getIdToCustomer().values()){
            System.out.println(++counter + " - " + customer.getName());
            CustomerList.add(customer);
        }

        return CustomerList;
    }
    //счетчик работников
    public static List<Employee> empCounter(){
        int counter = 0;
        List<Employee> EmployeeList = new ArrayList<>();
        for (Employee employee : EmployeeManager.getIdToEmployee().values()){
            System.out.println(++counter + " - " + employee.getName());
            EmployeeList.add(employee);
        }

        return EmployeeList;
    }
    //счетчик должностей в пвз
    public static List<PointOfSalePositions> posPositionCounter(){
        int counter = 0;
        List<PointOfSalePositions> pointOfSalePositions = new ArrayList<>();
        for (PointOfSalePositions positions : PointOfSalePositions.values()){
            System.out.println(++counter + " - " + positions.getPosition());
            pointOfSalePositions.add(positions);
        }

        return pointOfSalePositions;
    }
    //счетчик должностей на складе
    public static List<WareHousePositions> WareHousePositionCounter(){
        int counter = 0;
        List<WareHousePositions> wareHousePositions = new ArrayList<>();
        for (WareHousePositions positions : WareHousePositions.values()){
            System.out.println(++counter + " - " + positions.getPosition());
            wareHousePositions.add(positions);
        }

        return wareHousePositions;
    }
}
