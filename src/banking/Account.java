package banking;

public class Account {

    private String username;
    private String password;
    //private final String account_ID = UUID.randomUUID().toString();
    private String name;
    private String IBAN;
    private double amount;

    public Account(String IBAN, String name, double amount, String username, String password) {
        this.IBAN = IBAN;
        this.name = name;
        this.amount = amount;
        this.username = username;
        this.password = password;
    }

/*    public String getAccountID() {
        return account_ID;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" +
                ", name='" + name + '\'' +
                ", IBAN='" + IBAN + '\'' +
                ", amount=" + amount +
                '}';
    }

    public void deposit(double amount) {
        if(amount < 1000) {
            throw new IllegalArgumentException("Deposit must be at least 1000 HUF");
        }
        this.amount += amount;
    }

    public void withdraw(double amount) {
        if(amount > this.amount)
            throw new IllegalArgumentException(("Insufficient funds"));
        this.amount -= amount;
    }
}
