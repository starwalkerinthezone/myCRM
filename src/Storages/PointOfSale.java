package Storages;

import Managers.StorageManager;
import Products.Product;

import java.util.*;

public class PointOfSale implements Storage {
    private Map<String, Product> idToProduct;
    private  Map<String, String> productIdToWarehouseId;
    private  Map<String, String> productToIdWarehouseCellId;
    private  String id;
    private String address;
    private int income;
    private final Scanner scanner = new Scanner(System.in);
    private  Map<String, List<String>> positionToEmployee;
 //   private final String type = "pos";



    public PointOfSale(String address){
        this.id = UUID.randomUUID().toString();
        this.idToProduct = new HashMap<>();
        this.productIdToWarehouseId = new HashMap<>();
        this.productToIdWarehouseCellId = new HashMap<>();
        this.positionToEmployee = new HashMap<>();
        this.address = address;
    }

    public PointOfSale(){

    }

//    public String getType() {
//        return type;
//    }

    public Map<String, Product> getIdToProduct() {
        return idToProduct;
    }

    //добавить товар
    public void addProduct(Product product, String WarehouseId, String WarehouseCellId){
        idToProduct.put(product.getId(), product);
        productIdToWarehouseId.put(product.getId(), WarehouseId);
        productToIdWarehouseCellId.put(product.getId(), WarehouseCellId);
    }
    //продать товар
    public int sellProduct(String id){
        Product fromProduct = idToProduct.get(id);
        System.out.println("введите количество(доступно " + fromProduct.getAmount() + ")") ;
        int amount;
        while(true) {
            amount = scanner.nextInt();
            if (amount > fromProduct.getAmount()) {
                System.out.println("ввели больше чем можно");
                continue;
            }
            break;
        }
        fromProduct.setAmount(fromProduct.getAmount()-amount);
        this.setIncome(income+(amount*fromProduct.getPrice()));
        Warehouse warehouse = (Warehouse) StorageManager.getStorage(productIdToWarehouseId.get(id));
        warehouse.setIncome(warehouse.getIncome()+(amount*fromProduct.getPrice()));
        return amount;


    }
    //вернуть товар
    public void returnProduct(String id, int amount){
        Product neededProduct = idToProduct.get(id);
        neededProduct.setAmount(neededProduct.getAmount()+amount);
    }
    //взять id
    public String getId(){
        return id;
    }
    //взять
    public int getIncome() {
        return income;
    }

    //поставить адрес
    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setIncome(int income) {
        this.income = income;
    }

    public Map<String, List<String>> getPositionToEmployee() {
        return positionToEmployee;
    }

    public void about(){
        System.out.println("адрес: " + address);
        int counter = 0;
        for (Product product : idToProduct.values()){
            System.out.println(++counter +  " " + product.getId());
        }

    }

    public void aboutProducts(){
        for (Product product : idToProduct.values()){
            System.out.println("id: " + product.getId() + "; name: " + product.getName() + "; amount: " + product.getAmount() + "; price: " + product.getPrice());
        }
    }

    public void setIdToProduct(Map<String, Product> idToProduct) {
        this.idToProduct = idToProduct;
    }

    public void setProductToWarehouse(Map<String, String> productToWarehouse) {
        this.productIdToWarehouseId = productToWarehouse;
    }

    public void setProductToWarehouseCell(Map<String, String> productToWarehouseCell) {
        this.productToIdWarehouseCellId = productToWarehouseCell;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPositionToEmployee(Map<String, List<String>> positionToEmployee) {
        this.positionToEmployee = positionToEmployee;
    }

    public Map<String, String> getProductIdToWarehouseId() {
        return productIdToWarehouseId;
    }

    public Map<String, String> getProductToIdWarehouseCellId() {
        return productToIdWarehouseCellId;
    }

    public void setProductIdToWarehouseId(Map<String, String> productIdToWarehouseId) {
        this.productIdToWarehouseId = productIdToWarehouseId;
    }

    public void setProductToIdWarehouseCellId(Map<String, String> productToIdWarehouseCellId) {
        this.productToIdWarehouseCellId = productToIdWarehouseCellId;
    }
}
