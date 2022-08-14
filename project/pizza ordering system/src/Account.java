import java.util.Scanner;

public class Account {
    private String username;
    private String password;
    private AccountType type;
    private static Account[] allAccounts = new Account[100];
    private static int accountCounter = 0;

    public Account() {
    }

    public Account(String username, String password, AccountType type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public static Account[] getAllAccounts() {
        return allAccounts;
    }

    public static void showAllAdmins() {

        int accounts = getAccountCounter();
        int counter = 0;
        Account[] tempAllAccounts = getAllAccounts();
        for (int i = 0; i < accounts; i++) {
            if (tempAllAccounts[i].getType() == AccountType.Admin) {
                counter++;
                System.out.println(counter + ".\t" + tempAllAccounts[i].username);
            }
        }
        if (counter == 0) {
            System.out.println("No admin found!");
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Press anything to continue.");
        String s = sc.nextLine();
    }

    public static void showAllCostumers() {
        int counter = 0;
        int accounts = getAccountCounter();
        Account[] tempAllAccounts = getAllAccounts();
        for (int i = 0; i < accounts; i++) {
            if (tempAllAccounts[i].getType() == AccountType.Costumer) {
                counter++;
                System.out.println(counter + ".\t" + tempAllAccounts[i].username);
            }
        }
        if (counter == 0) {
            System.out.println("No costumer found!");
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Press anything to continue.");
        String s = sc.nextLine();
    }

    public static Account getAccountByUsername(String user) {
        for (int i = 0; i < accountCounter; i++) {
            if (allAccounts[i].username.equals(user)) {
                return allAccounts[i];
            }
        }
        return null;
    }

    public static int getAccountCounter() {
        return accountCounter;
    }

    public static boolean addAccountToDatabase(Account add) {
        if (accountCounter >= 99) {
            return false;  //not enough space for new account.
        } else {
            allAccounts[accountCounter] = add;
            accountCounter++;
            return true;
        }
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public boolean changeAccountPassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter your current password:\n>>");
        String currentPass = scanner.nextLine();
        System.out.print("Please enter a new password:\n>>");
        String newPass = scanner.nextLine();
        if (this.password.equals(currentPass)) {
            this.setPassword(newPass);
            return true;
        } else {
            System.out.println("password is not correct");
            return false;
        }
    }


    public Account login(String username, String password) {
        Account temp = getAccountByUsername(username);
        if (temp == null) {
            return null; //this user does not exist.
        } else if (!temp.getPassword().equals(password)) {
            System.out.println("wrong password");
            return null; //wrong password.
        } else {
            return Account.getAccountByUsername(username); //login is done successfully.
        }
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }


    public Account signUp(String username, String password, AccountType type) {
        if (getAccountByUsername(username) != null) {
            System.out.println("This user already exists.");
            return null; //this user already exists.
        } else {
            this.username = username;
            this.password = password;
            this.type = type;
            Account temp = new Account(username, password, type);
            if (addAccountToDatabase(temp)) {
                return Account.getAccountByUsername(username);
            }
            return null;
        }
    }

    @Override
    public String toString() {
        return "Welcome! Dear " + username + ", your Account type is: " + type + "\n";
    }

}
