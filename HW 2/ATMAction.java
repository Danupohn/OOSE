public interface ATMAction {
    // ตรวจสอบยอดเงิน
    void checkBalance();

    // ถอนเงิน
    void withdraw(double amount);

    // ฝากเงิน
    void deposit(double amount);

    // โอนเงิน
    void transfer(Account targetAccount, double amount);
}
