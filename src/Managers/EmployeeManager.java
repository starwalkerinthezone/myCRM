package Managers;

import Storages.Storage;
import Employee.Employee;
import java.util.*;

public class EmployeeManager {
    private static Map<String, Employee> idToEmployee = new HashMap<>();

    //добавить работника
    public static void addEmployee(String name, String lastName, String position, Storage storage){
        String employeeID = UUID.randomUUID().toString();
        String location = storage.getId();
        Employee newEmployee = new Employee(employeeID, name, lastName, position, location);
        if (storage.getPositionToEmployee().get(position)==null){
            List<String> employees = new ArrayList<>();
            employees.add(employeeID);
            storage.getPositionToEmployee().put(position, employees);
        }
        else{
            storage.getPositionToEmployee().get(position).add(employeeID);
        }
        idToEmployee.put(employeeID, newEmployee);

    }
    //поменять должность работнику
    public static void changePosition(Employee employee, Storage fromStorage, Storage toStorage, String position){
        fromStorage.getPositionToEmployee().get(employee.getPosition()).remove(employee.getId());
        employee.setPosition(position);
        toStorage.getPositionToEmployee().get(employee.getPosition()).add(employee.getId());

    }
    //уволить сотрудника
    public static void deleteEmployeePos(Employee employee, Storage storage){
        idToEmployee.remove(employee.getPosition());
        storage.getPositionToEmployee().get(employee.getPosition()).remove(employee.getId());
    }

    //взять список работников
    public static Map<String, Employee> getIdToEmployee() {
        return idToEmployee;

    }
    //определить список работников
    public static void setIdToEmployee(Map<String, Employee> idToEmployee) {
        EmployeeManager.idToEmployee = idToEmployee;
    }
}

