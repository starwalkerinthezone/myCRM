package Tools;

import Products.Product;
import Storages.Warehouse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ReadyToBuyJson {
    private final Map<String, Product> readyToBuy;
    public ReadyToBuyJson(String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        this.readyToBuy = mapper.readValue(new File(path), new TypeReference<>() {});
    }

    public void buying(Warehouse warehouse, String cellId, Product product){
        warehouse.purchaseProduct(product, cellId);
    }

    public void aboutProducts() {
        System.out.println("Доступны к закупке:");
        for(Product product : readyToBuy.values()){
            System.out.println("id: " + product.getId() + "; name: " + product.getName() + "; amount: " + product.getAmount() + "; price: " + product.getPrice());
        }
    }

    public Map<String, Product> getReadyToBuy() {
        return readyToBuy;
    }
}
