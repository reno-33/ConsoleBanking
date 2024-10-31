package banking;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BankMain {
    private static final int QUIT_PROGRAM = 0;
    private static final int CHECK_BALANCE = 1;
    private static final int WITHDRAW_MONEY = 2;
    private static final int DEPOSIT_MONEY = 3;
    private static final int TRANSFER_MONEY = 4;
    private static final int LOGOUT_PROGRAM = 5;
    private static Map<String, Account> accounts = new HashMap<>();
    private static Map<String, Account> ibanAccounts = new HashMap<>(); //needed for transferMoney() method to lookup the receiver's IBAN


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int menuChoice;
        BankService bankService = new BankService();

        //create some test accounts and print to console
        initializeTestAccounts();


        // Outer loop to restart the login process upon logout
        while(true) {
            Account loggedInAccount = null;
            int attempts = 3;
            while (attempts > 0 && loggedInAccount == null) {
                System.out.println("Enter username:");
                String username = scanner.nextLine();

                System.out.println("Enter password: ");
                String password = scanner.nextLine();

                loggedInAccount = login(username, password);
                if (loggedInAccount == null) {
                    attempts--;
                    System.out.println("Invalid credentials. Number of attempts left: " + attempts);
                }
            }

            if(loggedInAccount != null) {
                System.out.println("Login successful. Accessing account for " + loggedInAccount.getName());
            } else {
                System.out.println("Too many failed attempts. Exiting program");
                return;
            }

            //main menu loop
            boolean isRunning = true;
            while (isRunning) {
                //show the user menu
                showMenu();

                //ask user input for menu
                menuChoice = getMenuChoice(scanner);

                switch (menuChoice) {
                    case QUIT_PROGRAM:
                        scanner.close();
                        bankService.quitProgram();
                    case CHECK_BALANCE:
                        bankService.checkBalance(loggedInAccount);
                        break;
                    case WITHDRAW_MONEY:
                        try {
                            System.out.println("Enter the amount you want to withdraw: ");
                            double withdraw = scanner.nextDouble();
                            scanner.nextLine();
                            bankService.withdrawMoney(loggedInAccount,withdraw);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: insufficient funds" + e.getMessage());
                        }

                        break;
                    case DEPOSIT_MONEY:
                        try {
                            System.out.println("How much money do you want to deposit? ");
                            double deposit = scanner.nextDouble();
                            scanner.nextLine();
                            bankService.depositMoney(loggedInAccount, deposit);
                        } catch (IllegalArgumentException e){
                            System.out.println("Error, deposit must be at least 1000 HUF");
                        }

                        break;
                    case TRANSFER_MONEY:
                        System.out.println("Enter the amount you want to send: ");
                        double amount = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.println("Enter the reciver's IBAN: ");
                        String receiverIBAN = scanner.nextLine();
                        Account toAccount = ibanAccounts.get(receiverIBAN);
                        if (toAccount != null) {
                            //account found, sending money
                            bankService.transferMoney(loggedInAccount, toAccount, amount);
                        } else {
                            //no account found
                            System.out.println("There's no account with that IBAN");
                        }
                        break;
                    case LOGOUT_PROGRAM:
                        System.out.println("logging out...");
                        loggedInAccount = null;
                        isRunning = false;
                        break;
                    default:
                        System.out.println("That's not an option, choose between 0-4");
                }
            }
        }
    }


    private static void initializeTestAccounts() {
        Account account1 = new Account("HU11773425-02904405", "Kiss János", 500000, "user1", "pwd123");
        Account account2 = new Account("HU11773675-12345678", "Nagy Anett", 100000, "user2", "password0");
        accounts.put(account1.getUsername(), account1);
        ibanAccounts.put(account1.getIBAN(), account1);
        accounts.put(account2.getUsername(), account2);
        ibanAccounts.put(account2.getIBAN(), account2);

        //print the test accounts
        System.out.println("Details for the accounts: ");
        for(Map.Entry<String, Account> entry : accounts.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    private static void showMenu() {
        System.out.println("\n===============================");
        System.out.println("         MAIN MENU             ");
        System.out.println("===============================");
        System.out.println("1. Check balance");
        System.out.println("2. Withdraw money");
        System.out.println("3. Deposit money");
        System.out.println("4. Transfer money");
        System.out.println("5. Logout");
        System.out.println("0. Quit program");

        System.out.println("Enter your choice: ");
    }

    private static int getMenuChoice(Scanner scanner) {
        while (true) {
            //Check for valid input to avoid InputMismatchException
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear invalid input
                continue;
            }
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline
            if (choice >= 0 && choice <= 5) {
                return choice;
            } else {
                System.out.println("Please choose a number between 0 and 5.");
            }
        }
    }

    //just a very simple login method
    private static Account login(String username, String password) {
        if (accounts.containsKey(username) && accounts.get(username).getPassword().equals(password))
            return accounts.get(username);
        return null;
    }

}
