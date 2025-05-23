package Storages;

import Managers.StorageManager;
import Products.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Warehouse implements Storage {
    private  String id;
    private  Map<String, Product> cellIdToProduct;
    private  Map<String, WarehouseCell> idToWarehouseCell;
    private  Map<String, Product> idToProduct;
    private  Map<String, WarehouseCell> productIdToCell; //доделать чтобы сохранялся в json
    private  Map<String, List<String>> positionToEmployee;
    private String address;
    private int income;
    private final String type = "wh";
    public Warehouse(String address){
        cellIdToProduct = new HashMap<>();
        idToWarehouseCell = new HashMap<>();
        idToProduct = new HashMap<>();
        productIdToCell = new HashMap<>();
        id = UUID.randomUUID().toString();
        positionToEmployee = new HashMap<>();
        this.address = address;
    }
    public Warehouse(){

    }
    //сделать добавление ячеек склада
    public void addWarehouseCell(String id){
        if(!idToWarehouseCell.containsKey(id)){
            idToWarehouseCell.put(id, new WarehouseCell(id));
            return;
        }
        System.out.println("Данная ячейка уже есть на складе");

    }

    public String getId(){
        return id;
    }

    public Map<String, List<String>> getPositionToEmployee() {
        return positionToEmployee;
    }

    @Override
    public int getIncome() {
        return income;
    }

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

    public Map<String, Product> getIdToProduct() {
        return idToProduct;
    }

    public Map<String, WarehouseCell> getIdToWarehouseCell() {
        return idToWarehouseCell;
    }

    public void purchaseProduct(Product product, String id){
        if (product==null){
            System.out.println("нет продукта");
            return;
        }
        if(idToWarehouseCell.containsKey(id)) {
            if (cellIdToProduct.containsKey(id)){
                if (cellIdToProduct.get(id) != null) {
                    System.out.println("Данная ячейка занята другим товаром");
                    return;
                }
            }
            
            idToProduct.put(product.getId(), product);
            cellIdToProduct.put(id, product);
            productIdToCell.put(product.getId(), idToWarehouseCell.get(id));
            return;
        }
        System.out.println("Данной ячейки не существует");
    }

    public void moveProductInWarehouse(String product_id, String cell_id) {
        if(!idToProduct.containsKey(product_id)) {
            System.out.println("Нет такого товара");
            return;
        }

        if(!idToWarehouseCell.containsKey(cell_id)){
            System.out.println("Нет ячейки перемещения");
            return;
        }
        Product product = idToProduct.get(product_id);
        WarehouseCell fromCell = productIdToCell.get(product.getId());
        if(fromCell == null){
            System.out.println("товара нет ни в одной ячейке");
            return;
        }
        WarehouseCell toCell = idToWarehouseCell.get(cell_id);
        if(fromCell.equals(toCell)){
            System.out.println("Товар уже в этой ячейке");
            return;
        }
        if(cellIdToProduct.get(toCell.getId()) != null){
            System.out.println("Другой товар в данной ячейке");
            return;
        }
        cellIdToProduct.put(fromCell.getId(), null);
        cellIdToProduct.put(toCell.getId(), product);
        productIdToCell.put(product.getId(), toCell);
    }

    public void moveProductToPointSale(String productId, String pointId) {
        // Валидация
        if(productId == null || pointId == null) {
            System.out.println("ID не может быть null");
            return;
        }

        // Проверка товара
        Product product = idToProduct.get(productId);
        if(product == null) {
            System.out.println("Товар не найден");
            return;
        }

        // Проверка ячейки
        WarehouseCell cell = productIdToCell.get(product.getId());
        if(cell == null) {
            System.out.println("Товар не размещен на складе");
            return;
        }

        // Проверка точки продаж
        PointOfSale point = (PointOfSale) StorageManager.getIdToStorage().get(pointId);
        if(point == null) {
            System.out.println("Товар не размещен на складе");
            return;
        }

        // Перемещение
        cellIdToProduct.put(cell.getId(), null);
        productIdToCell.remove(product.getId());
        point.addProduct(product, this.id, cell.getId());
    }

    @Override
    public void about() {
        System.out.println("адрес: " + address);
        int counter = 0;
        for (Product product : idToProduct.values()){
            System.out.println(++counter + " " + product.getId() + " " + productIdToCell.get(product.getId()).id);
        }
    }

    @Override
    public void aboutProducts() {
        for (Product product : idToProduct.values()){
            System.out.println(productIdToCell.get(product.getId()).id + " id: " + product.getId() + "; name: " + product.getName() + "; amount: " + product.getAmount() + "; price: " + product.getPrice());
        }
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCellToProduct(Map<String, Product> cellToProduct) {
        this.cellIdToProduct = cellToProduct;
    }

    public void setIdToWarehouseCell(Map<String, WarehouseCell> idToWarehouseCell) {
        this.idToWarehouseCell = idToWarehouseCell;
    }

    public void setIdToProduct(Map<String, Product> idToProduct) {
        this.idToProduct = idToProduct;
    }

    public void setProductIdToCell(Map<String, WarehouseCell> productToCell) {
        this.productIdToCell = productToCell;
    }

    public void setPositionToEmployee(Map<String, List<String>> positionToEmployee) {
        this.positionToEmployee = positionToEmployee;
    }

    public Map<String, Product> getCellIdToProduct() {
        return cellIdToProduct;
    }

    public Map<String, WarehouseCell> getProductIdToCell() {
        return productIdToCell;
    }

    public String getType() {
        return type;
    }

    public void setCellIdToProduct(Map<String, Product> cellIdToProduct) {
        this.cellIdToProduct = cellIdToProduct;
    }

    public static class WarehouseCell{
        private  String id;

        public WarehouseCell(String id){
            this.id = id;
        }

        public WarehouseCell(){

        }
        public String getId(){
            return this.id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            WarehouseCell that = (WarehouseCell) o;
            return id.equals(that.id);
        }

        @Override
        public int hashCode() {
            return id.hashCode();
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
