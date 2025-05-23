package Products;

import java.util.UUID;

public class Product {
    private  String id;

    private  int price;

    private  String name;

    private static int counter;

    private int amount;

    public Product(String name, int price, int amount){
        id = UUID.randomUUID().toString();
        this.name = name;
        this.price = price;
        this.amount = amount;
    }
    public Product(){}
    public static Product newProduct(String nameOfProduct, int priceOfProduct, int amountOfProduct){
        return new Product(nameOfProduct, priceOfProduct, amountOfProduct);
    }

    public String getId() {
        return id;
    }

    public int getPrice(){
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void setCounter(int counter) {
        Product.counter = counter;
    }
}
