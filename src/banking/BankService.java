package banking;

public class BankService {

    //Query the balance of a bank account
    public void checkBalance(Account account){
        System.out.println("\n===============================");
        System.out.println("Checking balance...");
        System.out.println("===============================");
        System.out.println("Name of the account: \t\t" + account.getName());
        System.out.println("The IBAN of the account: \t" + account.getIBAN());
        System.out.println("Your current balance: \t\t" + account.getAmount() + " HUF");
    }

    //Withdraw money from a bank account
    public void withdrawMoney(Account account, double amount){
        System.out.println("Withdraw money...");
        if(amount > account.getAmount()) {
            System.out.println("Error: you don't have enough money in your account.");
        } else {
            account.withdraw(amount);
            System.out.println(amount + " HUF has been withdrawn successfully");
        }

    }

    //Deposit money to a bank account
    public void depositMoney(Account account, double amount) {
        System.out.println("Deposit money...");
        account.deposit(amount);
        System.out.println("Deposit successful!");

    }

    //Send money to a different account
    public void transferMoney(Account fromAccount, Account toAccount, double amount) {
        System.out.println("Transfer money...");

        fromAccount.withdraw(amount);
            toAccount.deposit(amount);
            System.out.println("Transfer was successful! " +amount + " HUF has been sent to " + toAccount.getIBAN());
    }

    //Exit the program
    public void quitProgram(){
        System.out.println("Quitting program, bye!");
        System.exit(1);
    }
}
