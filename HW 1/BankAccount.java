public class BankAccount {
    private String accountNumber;
    private String accountName;
    private String password;
    private double balance;

    public BankAccount(String accountNumber, String accountName, String password, double balance) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.password = password;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
