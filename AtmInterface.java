import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

class Transaction {
    private double amount;
    private Date timestamp;

    public Transaction(double amount) {
        this.amount = amount;
        this.timestamp = new Date();
    }

    public double getAmount() {
        return amount;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Transaction: $" + amount + "\nTimestamp: " + timestamp;
    }
}

class BankAccount {
    private double balance;
    private List<Transaction> transactionHistory;

    public BankAccount() {
        balance = 0.0;
        transactionHistory = new ArrayList<>();
    }

    public void deposit(double amount) {
        balance += amount;
        Transaction transaction = new Transaction(amount);
        transactionHistory.add(transaction);
        System.out.println("Deposit successful.\nCurrent balance: $" + balance);
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            Transaction transaction = new Transaction(-amount);
            transactionHistory.add(transaction);
            System.out.println("Withdrawal successful.\nCurrent balance: $" + balance);
        } else {
            System.out.println("Insufficient funds.\nCurrent balance: $" + balance);
        }
    }

    public void transfer(double amount, BankAccount recipientAccount) {
        if (amount <= balance) {
            balance -= amount;
            Transaction transaction = new Transaction(-amount);
            transactionHistory.add(transaction);
            recipientAccount.deposit(amount);
            System.out.println("Transfer successful.\nCurrent balance: $" + balance);
        } else {
            System.out.println("Insufficient funds.\nCurrent balance: $" + balance);
        }
    }

    public void showTransactionHistory() {
        System.out.println("Transaction History:");
        for (Transaction transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }

    public double getBalance() {
        return balance;
    }
}

public class AtmInterface {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nATM Interface\n");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer");
            System.out.println("4. Transaction History");
            System.out.println("5. Quit\n");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    bankAccount.deposit(depositAmount);
                    break;
                case 2:
                    System.out.print("Enter the amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    bankAccount.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter the amount to transfer: ");
                    double transferAmount = scanner.nextDouble();
                    System.out.print("Enter the recipient's account balance: ");
                    double recipientBalance = scanner.nextDouble();
                    BankAccount recipientAccount = new BankAccount();
                    recipientAccount.deposit(recipientBalance);
                    bankAccount.transfer(transferAmount, recipientAccount);
                    break;
                case 4:
                    bankAccount.showTransactionHistory();
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }
}
