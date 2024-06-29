public class Customer {
    private String name;
    private int customerID;
    private String phone;

    public Customer(String name, int customerID, String phone) {
        this.name = name;
        this.customerID = customerID;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getPhone() {
        return phone;
    }
}
