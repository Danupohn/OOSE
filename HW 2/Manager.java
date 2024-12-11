public class Manager extends Person {
    private String loginID; // Login เข้าระบบ
    private String password; // รหัสผ่าน

    public Manager(String citizenID, String fullName, String gender, String loginID, String password) {
        super(citizenID, fullName, gender);
        this.loginID = loginID;
        this.password = password;
    }

    public String getLoginID() {
        return loginID;
    }

    public String getPassword() {
        return password;
    }
}
