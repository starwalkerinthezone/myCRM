package Serialization;

import Customers.Customer;
import Managers.CustomerManager;
import Managers.EmployeeManager;
import Managers.StorageManager;
import Storages.PointOfSale;
import Storages.Storage;
import Storages.Warehouse;
import com.fasterxml.jackson.databind.ObjectMapper;
import Employee.Employee;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SerializerJson {

    public static void serialize(Map<String, Employee> idToEmployee, Map<String, Storage> idToStorage, Map<String, Customer> idToCustomer) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File Employees = new File(JsonNames.employees);
        mapper.writerWithDefaultPrettyPrinter().writeValue(Employees, idToEmployee);
        File Customers = new File(JsonNames.customers);
        mapper.writerWithDefaultPrettyPrinter().writeValue(Customers, idToCustomer);
        Map<String, Warehouse> idToWareHouse = new HashMap<>();
        Map<String, PointOfSale> idToPoints = new HashMap<>();
        File Warehouses = new File(JsonNames.warehouse);
        File PointOfSales = new File(JsonNames.pos);
        for(String key : idToStorage.keySet()){
            Storage storage = idToStorage.get(key);
            if (storage instanceof Warehouse){
                idToWareHouse.put(key, (Warehouse) storage);
            }
            else if (storage instanceof PointOfSale){
                idToPoints.put(key, (PointOfSale) storage);
            }
        }
        mapper.writerWithDefaultPrettyPrinter().writeValue(Warehouses, idToWareHouse);
        mapper.writerWithDefaultPrettyPrinter().writeValue(PointOfSales, idToPoints);

        EmployeeManager.getIdToEmployee().clear();
        CustomerManager.getIdToCustomer().clear();
        StorageManager.getIdToStorage().clear();
    }
}
