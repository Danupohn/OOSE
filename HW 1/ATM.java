import java.util.Scanner;

public class ATM {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATM atm = new ATM();

        // Step 1: ระบุจำนวนบัญชีที่ต้องการสร้าง
        System.out.println("ATM ComputerThanyaburi Bank");
        System.out.print("Enter amount of all account =  ");
        int numAccounts = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (numAccounts > 5) {
            System.out.println("Cannot create more than 5 accounts.");
            return;
        }

        // Step 2: ใส่ข้อมูลบัญชีธนาคาร
        for (int i = 0; i < numAccounts; i++) {
            System.out.println("\nEnter details for account " + (i + 1) + " : ");

            // รับข้อมูลเลขบัญชี
            String accountNumber = "";
            while (true) {
                System.out.print("Enter Detail of each account. ");
                accountNumber = scanner.nextLine();

                // ตรวจสอบว่าเลขบัญชีมีความยาว 14 ตัวและเป็นตัวเลขหรือตัวอักษรหรือลายเซ็น "-"
                if (accountNumber.length() == 14 && accountNumber.matches("[a-zA-Z0-9-]*")) {
                    break; // ถ้าผ่านเงื่อนไขให้หยุดการตรวจสอบ
                } else {
                    System.out.println("Please fill in the account name with 14 characters.");
                }
            }

            // รับข้อมูลชื่อบัญชี
            String accountName = "";
            while (true) {
                System.out.print("Account Name (max 13 characters): ");
                accountName = scanner.nextLine();

                // ตรวจสอบว่า ชื่อบัญชีไม่เกิน 13 ตัวอักษร
                if (accountName.length() <= 13) {
                    break; // ถ้าเงื่อนไขถูกต้องให้หยุดการตรวจสอบ
                } else {
                    System.out.println("Account name must be 13 characters or less. Please try again.");
                }
            }

            // รับข้อมูลรหัสผ่าน
            System.out.print("Password (4 digits) =  ");
            String password = scanner.nextLine();

            // รับข้อมูลยอดเงินเริ่มต้น
            System.out.print("Initial Balance = ");
            double balance = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            // ตรวจสอบข้อมูลก่อนเพิ่มบัญชี
            if (accountNumber.length() == 14 && accountName.length() <= 13 && password.length() == 4
                    && balance <= 1000000) {
                atm.addAccount(new BankAccount(accountNumber, accountName, password, balance));
            } else {
                System.out.println("Invalid input. Account not added.");
            }
        }

        // Step 3: แสดงหน้าจอตู้ ATM รอรับข้อมูลรหัสบัญชีและรหัสผ่าน
        atm.displayATMMenu(scanner);
    }

    private BankAccount[] accounts = new BankAccount[5];
    private int accountCount = 0;

    public void addAccount(BankAccount account) {
        if (accountCount < accounts.length) {
            accounts[accountCount++] = account;
        } else {
            System.out.println("Cannot add more accounts. Maximum limit reached.");
        }
    }

    public BankAccount authenticate(String accountNumber, String password) {
        for (BankAccount account : accounts) {
            if (account != null && account.getAccountNumber().equals(accountNumber)
                    && account.getPassword().equals(password)) {
                return account;
            }
        }
        return null; // Authentication failed
    }

    public void displayATMMenu(Scanner scanner) {

        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter account number: ");
                    String accountNumber = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();

                    BankAccount account = authenticate(accountNumber, password);
                    if (account != null) {
                        System.out.println("Current Balance: " + account.getBalance());
                    } else {
                        System.out.println("Invalid account number or password.");
                    }
                    break;

                case 2:
                    System.out.print("Enter account number: ");
                    accountNumber = scanner.nextLine();
                    System.out.print("Enter password: ");
                    password = scanner.nextLine();

                    account = authenticate(accountNumber, password);
                    if (account != null) {
                        System.out.print("Enter withdrawal amount: ");
                        double amount = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline

                        if (amount <= account.getBalance()) {
                            account.setBalance(account.getBalance() - amount);
                            System.out.println("Withdrawal successful! New balance: " + account.getBalance());
                        } else {
                            System.out.println("Insufficient funds.");
                        }
                    } else {
                        System.out.println("Invalid account number or password.");
                    }
                    break;

                case 3:
                    System.out.println("Exiting ATM. Goodbye!");
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
