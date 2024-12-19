## Banking Console Application

This is a simple **console-based banking application** implemented in Java. The application provides basic banking functionalities such as checking account balances, withdrawing and depositing money, and transferring funds between accounts.
It is designed for practice purposes and showcases fundamental Java programming skills, including object-oriented principles and console I/O operations.

## Features

- **Check Account Balance:** Displays the account holder's name, IBAN, and current balance.
- **Withdraw Money:** Allows the user to withdraw a specified amount from their account. If the account balance is insufficient, an error message is displayed.
- **Deposit Money:** Enables the user to deposit a specified amount into their account.
- **Transfer Money:** Facilitates transferring a specified amount from one account to another. Both accounts are updated accordingly.

## How It Works

1. The user is presented with a **main menu** displaying the available options:
    
    - Check balance
    - Withdraw money
    - Deposit money
    - Transfer money
    - Logout
    - Quit program
    
2. The user selects an option by entering the corresponding number.
3. Based on the selection, the application performs the requested operation and displays relevant messages or errors.
4. The program continues running until the user chooses to quit.


## Limitations

- No persistent data storage; all operations are performed in memory during runtime.
- Simplified error handling and validation (e.g., no checks for invalid input formats).
- Not designed for production use.

## How to Run

1. Clone or download this repository.
2. Compile and run the Java application in your preferred IDE or via the command line:   
    ```
    javac Main.java
    java Main
    ```
    
2. Follow the prompts in the console to interact with the application.
