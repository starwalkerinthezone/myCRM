package PositionEnums;

public enum PointOfSalePositions {
    CASHIER ("Кассир");

    private final String position;

    PointOfSalePositions(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }
}
