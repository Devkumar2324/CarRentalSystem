public class Car {
    private int carID;
    private String carModel;
    private String carBrand;
    private double carPrice;
    private boolean available;

    public Car(int carID, String carModel, String carBrand, double carPrice) {
        this.carID = carID;
        this.carModel = carModel;
        this.carBrand = carBrand;
        this.carPrice = carPrice;
        this.available = true;
    }

    public int getCarID() {
        return carID;
    }

    public String getCarModel() {
        return carModel;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public double getCarPrice() {
        return carPrice;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
