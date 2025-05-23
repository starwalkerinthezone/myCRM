package Serialization;

import Customers.Customer;
import Managers.CustomerManager;
import Managers.EmployeeManager;
import Managers.StorageManager;
import Storages.PointOfSale;
import Storages.Storage;
import Storages.Warehouse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import Employee.Employee;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class DeserializerJson {
    //десериализация
    private  Map<String, Employee> employeeMap = new HashMap<String, Employee>();
    private Map<String, Customer> customerMap = new HashMap<String, Customer>();
    private Map<String, Warehouse> warehouseMap = new HashMap<String, Warehouse>();
    private Map<String, PointOfSale> posMap = new HashMap<String, PointOfSale>();
    public void deserialize(String employeePath, String customerPath, String warehousePath, String posPath) throws IOException {
        File emp = new File(employeePath);
        File cus = new File(customerPath);
        File wh = new File(warehousePath);
        File pos = new File(posPath);
        ObjectMapper mapper = new ObjectMapper();
        if (emp.length() > 3) {  // Больше, чем "{ }"
            employeeMap = mapper.readValue(new File(employeePath), new TypeReference<HashMap<String, Employee>>(){});
            EmployeeManager.setIdToEmployee(employeeMap);
        }
        if (cus.length() > 3) {
            customerMap = mapper.readValue(new File(customerPath), new TypeReference<HashMap<String, Customer>>() {
            });
            CustomerManager.setIdToCustomer(customerMap);
        }
        if (wh.length() > 3) {
            warehouseMap = mapper.readValue(new File(warehousePath), new TypeReference<HashMap<String, Warehouse>>(){});
        }

        if (pos.length() > 3){
            posMap = mapper.readValue(new File(posPath), new TypeReference<HashMap<String, PointOfSale>>() {});
        }
        for(String key : warehouseMap.keySet()){
            StorageManager.getIdToStorage().put(key, warehouseMap.get(key));
        }
        for(String key : posMap.keySet()){
            StorageManager.getIdToStorage().put(key, posMap.get(key));
        }




    }
}
