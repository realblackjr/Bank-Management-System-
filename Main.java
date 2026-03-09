import java.util.NoSuchElementException;

public class Main {

    private static final BankService bankService = new BankService();

    public static void main(String[] args) {

        printWelcome();
        seedDemoData();

        boolean running = true;

        while (running) {

            printMainMenu();
            int choice = MenuUtil.readInt("Enter choice: ");

            switch (choice) {

                case 1  -> createAccount();
                case 2  -> depositMenu();
                case 3  -> withdrawMenu();
                case 4  -> transferMenu();
                case 5  -> viewAccountMenu();
                case 6  -> viewHistoryMenu();
                case 7  -> bankService.displayAllAccounts();
                case 8  -> bankService.displayGlobalLog();

                case 0  -> {
                    running = false;
                    System.out.println("\n  Goodbye! Thank you for banking with MKU Bank.\n");
                }

                default -> System.out.println("  [ERROR] Invalid option. Please try again.");
            }

            if (running) MenuUtil.pause();
        }
    }

    // ─── Menu Screens ─────────────────────────────────────────────

    private static void printMainMenu() {

        MenuUtil.printHeader("MKU BANK MANAGEMENT SYSTEM");

        System.out.println("  [1] Create Account");
        System.out.println("  [2] Deposit Money");
        System.out.println("  [3] Withdraw Money");
        System.out.println("  [4] Transfer Funds");
        System.out.println("  [5] View Account Details");
        System.out.println("  [6] View Transaction History");
        System.out.println("  [7] List All Accounts");
        System.out.println("  [8] Global Transaction Log");
        System.out.println("  [0] Exit");

        MenuUtil.printDivider();
    }

    private static void createAccount() {

        MenuUtil.printHeader("CREATE ACCOUNT");

        System.out.println("  [1] Savings Account");
        System.out.println("  [2] Current Account");

        int type = MenuUtil.readInt("Account type: ");

        String name = MenuUtil.readString("Account holder name: ");
        double deposit = MenuUtil.readDouble("Initial deposit (RWF): ");

        if (type == 1) {

            double rate = MenuUtil.readDouble("Annual interest rate (e.g. 0.05 for 5%): ");
            bankService.createSavingsAccount(name, deposit, rate);

        } else if (type == 2) {

            double overdraft = MenuUtil.readDouble("Overdraft limit (RWF): ");
            bankService.createCurrentAccount(name, deposit, overdraft);

        } else {

            System.out.println("  [ERROR] Invalid account type.");
        }
    }

    private static void depositMenu() {

        MenuUtil.printHeader("DEPOSIT MONEY");

        String accNo = MenuUtil.readString("Account number: ");
        double amount = MenuUtil.readDouble("Amount to deposit (RWF): ");

        try {
            bankService.deposit(accNo, amount);
        } catch (NoSuchElementException e) {
            System.out.println("  [ERROR] " + e.getMessage());
        }
    }

    private static void withdrawMenu() {

        MenuUtil.printHeader("WITHDRAW MONEY");

        String accNo = MenuUtil.readString("Account number: ");
        double amount = MenuUtil.readDouble("Amount to withdraw (RWF): ");

        try {

            bankService.withdraw(accNo, amount);

        } catch (InsufficientFundsException e) {

            System.out.println("  [INSUFFICIENT FUNDS] " + e.getMessage());

        } catch (NoSuchElementException e) {

            System.out.println("  [ERROR] " + e.getMessage());
        }
    }

    private static void transferMenu() {

        MenuUtil.printHeader("TRANSFER FUNDS");

        String from = MenuUtil.readString("Source account number     : ");
        String to = MenuUtil.readString("Destination account number: ");
        double amount = MenuUtil.readDouble("Transfer amount (RWF)     : ");

        try {

            bankService.transfer(from, to, amount);

        } catch (InsufficientFundsException e) {

            System.out.println("  [INSUFFICIENT FUNDS] " + e.getMessage());

        } catch (NoSuchElementException e) {

            System.out.println("  [ERROR] " + e.getMessage());
        }
    }

    private static void viewAccountMenu() {

        MenuUtil.printHeader("VIEW ACCOUNT DETAILS");

        String accNo = MenuUtil.readString("Account number: ");

        try {

            Account acc = bankService.getAccount(accNo);

            System.out.println("\n" + acc);

            if (acc instanceof SavingsAccount sa) {
                System.out.printf("  Interest Rate: %.1f%%%n", sa.getInterestRate() * 100);
            }

            if (acc instanceof CurrentAccount ca) {
                System.out.printf("  Overdraft Limit: %.2f RWF%n", ca.getOverdraftLimit());
            }

        } catch (NoSuchElementException e) {

            System.out.println("  [ERROR] " + e.getMessage());
        }
    }

    private static void viewHistoryMenu() {

        MenuUtil.printHeader("TRANSACTION HISTORY");

        String accNo = MenuUtil.readString("Account number: ");

        try {

            bankService.displayTransactionHistory(accNo);

        } catch (NoSuchElementException e) {

            System.out.println("  [ERROR] " + e.getMessage());
        }
    }

    // ─── Demo Data ─────────────────────────────────────────────

    private static void seedDemoData() {

        System.out.println("\n  [SYSTEM] Loading demo accounts...");

        SavingsAccount acc1 = bankService.createSavingsAccount("Uwimana Jean Pierre", 50000, 0.05);
        SavingsAccount acc2 = bankService.createSavingsAccount("Mukamana Claudine", 75000, 0.04);
        SavingsAccount acc3 = bankService.createSavingsAccount("Ingabire Solange", 30000, 0.05);

        CurrentAccount acc4 = bankService.createCurrentAccount("Habimana Eric", 100000, 50000);
        CurrentAccount acc5 = bankService.createCurrentAccount("Niyonzima Patrick", 80000, 30000);

        try {

            acc1.deposit(20000);
            acc4.withdraw(15000);
            bankService.transfer(acc4.getAccountNumber(), acc2.getAccountNumber(), 10000);
            acc2.applyInterest();

        } catch (InsufficientFundsException e) {

            System.out.println("  [SEED ERROR] " + e.getMessage());
        }

        System.out.println("  [SYSTEM] Demo data loaded successfully.\n");
    }

    private static void printWelcome() {

        System.out.println();
        System.out.println("  ╔══════════════════════════════════════════╗");
        System.out.println("  ║     MOUNT KIGALI UNIVERSITY              ║");
        System.out.println("  ║     School of Computing & IT             ║");
        System.out.println("  ║     Java OOP – CAT 2                     ║");
        System.out.println("  ║     GROUP 2: Bank Management System      ║");
        System.out.println("  ╠══════════════════════════════════════════╣");
        System.out.println("  ║  Members:                                ║");
        System.out.println("  ║   1. Uwimana Jean Pierre                 ║");
        System.out.println("  ║   2. Mukamana Claudine                   ║");
        System.out.println("  ║   3. Habimana Eric                       ║");
        System.out.println("  ║   4. Ingabire Solange                    ║");
        System.out.println("  ║   5. Niyonzima Patrick                   ║");
        System.out.println("  ║   6. Uwase Diane                         ║");
        System.out.println("  ╚══════════════════════════════════════════╝");
        System.out.println();
    }
}