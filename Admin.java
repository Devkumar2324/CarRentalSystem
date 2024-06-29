public class Admin {
    private String name;
    private int adminID;

    public Admin(String name, int adminID) {
        this.name = name;
        this.adminID = adminID;
    }

    public String getName() {
        return name;
    }

    public int getAdminID() {
        return adminID;
    }
}
