package PositionEnums;

public enum WareHousePositions {
    LOADER ("Грузчик"),
    WAREHOUSEGUARD ("Охранник склада"),
    LOGISTICIAN ("Логист"),
    WAREHOUSEKEEPER ("Смотритель склада"),
    TRUCKER ("Перевозчик");

    private final String position;

    WareHousePositions(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }




}

