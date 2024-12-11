import java.util.Scanner;

public class ATM {
    private Manager admin = null; // ผู้ดูแลระบบ
    private Account[] accounts = new Account[5]; // เก็บบัญชีลูกค้า
    private int accountCount = 0; // นับจำนวนบัญชีที่ถูกสร้าง

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATM atm = new ATM();

        // 1. ตรวจสอบว่ามีผู้ดูแลระบบหรือยัง
        atm.setupAdmin(scanner);

        // 2. ผู้ดูแลระบบล็อกอิน
        if (!atm.adminLogin(scanner)) {
            System.out.println("Login failed! The program will exit.");
            return;
        }

        // 3. เพิ่มบัญชีลูกค้า
        atm.addBankAccounts(scanner);

        // 4. หลังจากเพิ่มบัญชีเสร็จ, ผู้ใช้สามารถทำรายการ ATM
        atm.displayATMMenu(scanner);
    }

    // ตั้งค่าผู้ดูแลระบบ
    private void setupAdmin(Scanner scanner) {
        if (admin == null) {
            System.out.println("No admin is set. Please set up the admin:");

            System.out.print("Citizen ID (13 digits): ");
            String citizenID = scanner.nextLine();

            System.out.print("Full Name: ");
            String fullName = scanner.nextLine();

            System.out.print("Gender: ");
            String gender = scanner.nextLine();

            System.out.print("Login ID: ");
            String loginID = scanner.nextLine();

            System.out.print("Password: ");
            String password = scanner.nextLine();

            admin = new Manager(citizenID, fullName, gender, loginID, password);
            System.out.println("Admin setup is complete!");
        }
    }

    // ผู้ดูแลระบบล็อกอิน
    private boolean adminLogin(Scanner scanner) {
        System.out.println("\nAdmin Login:");
        System.out.print("Login ID: ");
        String loginID = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (admin.getLoginID().equals(loginID) && admin.getPassword().equals(password)) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Incorrect Login ID or Password!");
            return false;
        }
    }

    // เพิ่มบัญชีลูกค้า
    private void addBankAccounts(Scanner scanner) {
        System.out.println("\nATM ComputerThanyaburi Bank");
        System.out.print("Enter the number of accounts to create: ");
        int numAccounts = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (numAccounts > accounts.length) {
            System.out.println("You cannot create more than " + accounts.length + " accounts.");
            return;
        }

        for (int i = 0; i < numAccounts; i++) {
            System.out.println("\nEnter details for account " + (i + 1));

            System.out.print("Account Number (14 characters): ");
            String accountNumber = scanner.nextLine();

            System.out.print("Account Name (max 13 characters): ");
            String accountName = scanner.nextLine();

            System.out.print("Password (4 characters): ");
            String password = scanner.nextLine();

            System.out.print("Initial Balance: ");
            double balance = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            if (accountNumber.length() == 14 && accountName.length() <= 13 && password.length() == 4 && balance <= 1000000) {
                addAccount(new Account(accountNumber, accountName, "Male", accountNumber, password, balance));
                System.out.println("Account created successfully!");
            } else {
                System.out.println("Invalid input. The account was not created.");
            }
        }
    }

    public void addAccount(Account account) {
        if (accountCount < accounts.length) {
            accounts[accountCount++] = account;
        } else {
            System.out.println("Cannot add more accounts. The maximum limit has been reached!");
        }
    }

    // แสดงเมนู ATM
    private void displayATMMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Deposit Money");
            System.out.println("4. Transfer Money");
            System.out.println("5. Exit");
    
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
    
            // รับข้อมูลบัญชีและรหัสผ่านจากผู้ใช้
            System.out.print("Enter account number: ");
            String accountNumber = scanner.nextLine();
    
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
    
            // ตรวจสอบบัญชี
            Account account = authenticate(accountNumber, password);
            if (account != null) {
                switch (choice) {
                    case 1: // ตรวจสอบยอดเงิน
                        account.checkBalance();
                        break;
                    case 2: // ถอนเงิน
                        System.out.print("Enter withdrawal amount: ");
                        double withdrawAmount = scanner.nextDouble();
                        account.withdraw(withdrawAmount);
                        break;
                    case 3: // ฝากเงิน
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = scanner.nextDouble();
                        account.deposit(depositAmount);
                        break;
                    case 4: // โอนเงิน
                        System.out.print("Enter target account number for transfer: ");
                        String targetAccountNumber = scanner.nextLine();
                        System.out.print("Enter transfer amount: ");
                        double transferAmount = scanner.nextDouble();
                        Account targetAccount = authenticate(targetAccountNumber, password);
                        if (targetAccount != null) {
                            account.transfer(targetAccount, transferAmount);
                        } else {
                            System.out.println("Target account not found.");
                        }
                        break;
                    case 5: // ออกจากระบบ
                        System.out.println("Exiting ATM. Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } else {
                System.out.println("Invalid account number or password.");
            }
        }
    }

    public Account authenticate(String accountNumber, String password) {
        for (Account account : accounts) {
            if (account != null && account.getAccountNumber().equals(accountNumber) && account.getPassword().equals(password)) {
                return account;
            }
        }
        return null; // Authentication failed
    }
}
