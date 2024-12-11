public class Account implements ATMAction {
    private String accountNumber;
    private String accountName;
    private String gender;
    private String loginID;
    private String password;
    private double balance;

    public Account(String accountNumber, String accountName, String gender, String loginID, String password, double balance) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.gender = gender;
        this.loginID = loginID;
        this.password = password;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getLoginID() {
        return loginID;
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

    // ตรวจสอบยอดเงิน
    @Override
    public void checkBalance() {
        System.out.println("Current Balance: " + balance);
    }

    // ถอนเงิน
    @Override
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful! New balance: " + balance);
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    // ฝากเงิน
    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposit successful! New balance: " + balance);
    }

    // โอนเงิน
    @Override
    public void transfer(Account targetAccount, double amount) {
        if (amount <= balance) {
            balance -= amount;
            targetAccount.setBalance(targetAccount.getBalance() + amount);
            System.out.println("Transfer successful! New balance: " + balance);
        } else {
            System.out.println("Insufficient funds.");
        }
    }
}
