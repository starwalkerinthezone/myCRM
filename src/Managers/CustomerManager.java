package Managers;

import java.util.HashMap;
import java.util.Map;
import Customers.Customer;

public class CustomerManager {
    private static Map<String, Customer> idToCustomer = new HashMap<>();
    //добавить покупателя
    public static void addCustomer(Customer customer){
        idToCustomer.put(customer.getId(), customer);
    }
    //взять покупателя
    public static Customer getCustomer(String id) {
        return idToCustomer.get(id);
    }

    //взять список покупателей
    public static Map<String, Customer> getIdToCustomer() {
        return idToCustomer;
    }
    //поставить значение списку покупателей
    public static void setIdToCustomer(Map<String, Customer> idToCustomer) {
        CustomerManager.idToCustomer = idToCustomer;
    }
}
