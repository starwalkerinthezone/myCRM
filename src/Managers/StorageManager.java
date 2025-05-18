package Managers;

import Storages.Storage;

import java.util.HashMap;
import java.util.Map;

public class StorageManager {
    private static Map<String, Storage> idToStorage = new HashMap<>();

    public static void addStorage(Storage storage){
        idToStorage.put(storage.getId(), storage);
    }

    public static Storage getStorage(String id) {
        return idToStorage.get(id);
    }

    public static void closeStorage(String id){
        idToStorage.remove(id);
    }

    public static void getStorageIncome(String id){
        idToStorage.get(id).getIncome();
    }

    public static Map<String, Storage> getIdToStorage() {
        return idToStorage;
    }

    public static void setIdToStorage(Map<String, Storage> idToStorage) {
        StorageManager.idToStorage = idToStorage;
    }
}
