public class Reservation {
    private int reservationID;
    private int carID;
    private int customerID;
    private int days;
    private double totalPrice;

    public Reservation(int reservationID, int carID, int customerID, int days, double totalPrice) {
        this.reservationID = reservationID;
        this.carID = carID;
        this.customerID = customerID;
        this.days = days;
        this.totalPrice = totalPrice;
    }

    public int getReservationID() {
        return reservationID;
    }

    public int getCarID() {
        return carID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getDays() {
        return days;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
