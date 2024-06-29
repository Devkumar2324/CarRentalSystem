import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CarRentalSystem extends  JFrame {
    private List<Customer> customers = new ArrayList<>();
    private List<Car> carDetails = new ArrayList<>();
    private List<Reservation> reservations = new ArrayList<>();
    private List<Admin> admins = new ArrayList<>();
    private int reservationCounter = 1;

    private DefaultTableModel carTableModel;
    private DefaultTableModel reservationTableModel;
    private DefaultTableModel customerTableModel;
    private DefaultTableModel adminTableModel;

    public CarRentalSystem() {
        setTitle("Car Rental System");
        setSize(800, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Add all panels to tabbedPane
        tabbedPane.addTab("Add Car", createAddCarPanel());
        tabbedPane.addTab("Add Customer", createAddCustomerPanel());
        tabbedPane.addTab("Add Admin", createAddAdminPanel());
        tabbedPane.addTab("Available Cars", createDisplayCarsPanel());
        tabbedPane.addTab("Rent Car", createRentCarPanel());
        tabbedPane.addTab("Return Car", createReturnCarPanel());
        tabbedPane.addTab("Reservations", createDisplayReservationsPanel());
        tabbedPane.addTab("Customers", createDisplayCustomersPanel());
        tabbedPane.addTab("Admins", createDisplayAdminsPanel());

        add(tabbedPane);
    }

    private JPanel createAddCarPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField carIDField = new JTextField();
        JTextField carModelField = new JTextField();
        JTextField carBrandField = new JTextField();
        JTextField carPriceField = new JTextField();
        JButton addCarButton = new JButton("Add Car");

        panel.add(new JLabel("Car ID:"));
        panel.add(carIDField);
        panel.add(new JLabel("Car Model:"));
        panel.add(carModelField);
        panel.add(new JLabel("Car Brand:"));
        panel.add(carBrandField);
        panel.add(new JLabel("Car Price:"));
        panel.add(carPriceField);
        panel.add(new JLabel());
        panel.add(addCarButton);

        addCarButton.addActionListener(e -> {
            try {
                int carID = Integer.parseInt(carIDField.getText());
                String carModel = carModelField.getText();
                String carBrand = carBrandField.getText();
                double carPrice = Double.parseDouble(carPriceField.getText());
                addCar(new Car(carID, carModel, carBrand, carPrice));
                updateCarTable();
                showMessage("Car added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFields(carIDField, carModelField, carBrandField, carPriceField);
            } catch (NumberFormatException ex) {
                showMessage("Please enter valid car details.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    private JPanel createAddCustomerPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField customerIDField = new JTextField();
        JTextField customerNameField = new JTextField();
        JTextField customerPhoneField = new JTextField();
        JButton addCustomerButton = new JButton("Add Customer");

        panel.add(new JLabel("Customer ID:"));
        panel.add(customerIDField);
        panel.add(new JLabel("Customer Name:"));
        panel.add(customerNameField);
        panel.add(new JLabel("Customer Phone:"));
        panel.add(customerPhoneField);
        panel.add(new JLabel());
        panel.add(addCustomerButton);

        addCustomerButton.addActionListener(e -> {
            try {
                int customerID = Integer.parseInt(customerIDField.getText());
                String customerName = customerNameField.getText();
                String customerPhone = customerPhoneField.getText();
                addCustomer(new Customer(customerName, customerID, customerPhone));
                updateCustomerTable();
                showMessage("Customer added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFields(customerIDField, customerNameField, customerPhoneField);
            } catch (NumberFormatException ex) {
                showMessage("Please enter valid customer details.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    private JPanel createAddAdminPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField adminIDField = new JTextField();
        JTextField adminNameField = new JTextField();
        JButton addAdminButton = new JButton("Add Admin");

        panel.add(new JLabel("Admin ID:"));
        panel.add(adminIDField);
        panel.add(new JLabel("Admin Name:"));
        panel.add(adminNameField);
        panel.add(new JLabel());
        panel.add(addAdminButton);

        addAdminButton.addActionListener(e -> {
            try {
                int adminID = Integer.parseInt(adminIDField.getText());
                String adminName = adminNameField.getText();
                addAdmin(new Admin(adminName, adminID));
                updateAdminTable();
                showMessage("Admin added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFields(adminIDField, adminNameField);
            } catch (NumberFormatException ex) {
                showMessage("Please enter valid admin details.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    private JPanel createDisplayCarsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        carTableModel = new DefaultTableModel(new String[]{"Car ID", "Car Model", "Brand", "Price", "Available"}, 0);
        JTable carTable = new JTable(carTableModel);
        panel.add(new JScrollPane(carTable), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createRentCarPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField rentCarIDField = new JTextField();
        JTextField rentCustomerIDField = new JTextField();
        JTextField rentDaysField = new JTextField();
        JButton rentCarButton = new JButton("Rent Car");

        panel.add(new JLabel("Car ID:"));
        panel.add(rentCarIDField);
        panel.add(new JLabel("Customer ID:"));
        panel.add(rentCustomerIDField);
        panel.add(new JLabel("Number of Days:"));
        panel.add(rentDaysField);
        panel.add(new JLabel());
        panel.add(rentCarButton);

        rentCarButton.addActionListener(e -> {
            try {
                int carID = Integer.parseInt(rentCarIDField.getText());
                int customerID = Integer.parseInt(rentCustomerIDField.getText());
                int days = Integer.parseInt(rentDaysField.getText());
                if (rentCar(carID, customerID, days)) {
                    updateCarTable();
                    updateReservationTable();
                    showMessage("Car rented successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    clearFields(rentCarIDField, rentCustomerIDField, rentDaysField);
                } else {
                    showMessage("Car not available or invalid details.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                showMessage("Please enter valid rental details.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    private JPanel createReturnCarPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField returnReservationIDField = new JTextField();
        JButton returnCarButton = new JButton("Return Car");

        panel.add(new JLabel("Reservation ID:"));
        panel.add(returnReservationIDField);
        panel.add(new JLabel());
        panel.add(returnCarButton);

        returnCarButton.addActionListener(e -> {
            try {
                int reservationID = Integer.parseInt(returnReservationIDField.getText());
                if (returnCar(reservationID)) {
                    updateCarTable();
                    updateReservationTable();
                    showMessage("Car returned successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    clearFields(returnReservationIDField);
                } else {
                    showMessage("Invalid reservation ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                showMessage("Please enter a valid reservation ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    private JPanel createDisplayReservationsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        reservationTableModel = new DefaultTableModel(new String[]{"Reservation ID", "Car ID", "Customer ID", "Days", "Total Price"}, 0);
        JTable reservationTable = new JTable(reservationTableModel);
        panel.add(new JScrollPane(reservationTable), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createDisplayCustomersPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        customerTableModel = new DefaultTableModel(new String[]{"Customer ID", "Name", "Phone"}, 0);
        JTable customerTable = new JTable(customerTableModel);
        panel.add(new JScrollPane(customerTable), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createDisplayAdminsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        adminTableModel = new DefaultTableModel(new String[]{"Admin ID", "Name"}, 0);
        JTable adminTable = new JTable(adminTableModel);
        panel.add(new JScrollPane(adminTable), BorderLayout.CENTER);
        return panel;
    }

    private void addCar(Car car) {
        carDetails.add(car);
    }

    private void addCustomer(Customer customer) {
        customers.add(customer);
    }

    private void addAdmin(Admin admin) {
        admins.add(admin);
    }

    private boolean rentCar(int carID, int customerID, int days) {
        for (Car car : carDetails) {
            if (car.getCarID() == carID && car.isAvailable()) {
                car.setAvailable(false);
                double totalPrice = car.getCarPrice() * days;
                reservations.add(new Reservation(reservationCounter++, carID, customerID, days, totalPrice));
                return true;
            }
        }
        return false;
    }

    private boolean returnCar(int reservationID) {
        for (Reservation reservation : reservations) {
            if (reservation.getReservationID() == reservationID) {
                for (Car car : carDetails) {
                    if (car.getCarID() == reservation.getCarID()) {
                        car.setAvailable(true);
                        reservations.remove(reservation);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void updateCarTable() {
        carTableModel.setRowCount(0);
        for (Car car : carDetails) {
            carTableModel.addRow(new Object[]{car.getCarID(), car.getCarModel(), car.getCarBrand(), car.getCarPrice(), car.isAvailable()});
        }
    }

    private void updateCustomerTable() {
        customerTableModel.setRowCount(0);
        for (Customer customer : customers) {
            customerTableModel.addRow(new Object[]{customer.getCustomerID(), customer.getName(), customer.getPhone()});
        }
    }

    private void updateReservationTable() {
        reservationTableModel.setRowCount(0);
        for (Reservation reservation : reservations) {
            reservationTableModel.addRow(new Object[]{reservation.getReservationID(), reservation.getCarID(), reservation.getCustomerID(), reservation.getDays(), reservation.getTotalPrice()});
        }
    }

    private void updateAdminTable() {
        adminTableModel.setRowCount(0);
        for (Admin admin : admins) {
            adminTableModel.addRow(new Object[]{admin.getAdminID(), admin.getName()});
        }
    }

    private void showMessage(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }

    private void clearFields(JTextField... fields) {
        for (JTextField field : fields) {
            field.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CarRentalSystem app = new CarRentalSystem();
            app.setVisible(true);
        });
    }
}
