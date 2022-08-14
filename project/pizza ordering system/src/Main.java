import java.util.Scanner;

public class Main {

    public static void showAllOfConsumersOrders(Account account) {
        boolean orderIsNotEmpty = false;
        int ordersAmount = Order.getOrders();
        Order[] list = Order.getListOfOrders();
        for(int i = 0; i < ordersAmount; i++){
            String user = list[i].getOrderer().getUsername();
            if(account.getUsername().equals(user)){
                //the account which ordered this is our account that we are looking for
                System.out.println(list[i]);
                orderIsNotEmpty = true;
            }
        }
        if(!orderIsNotEmpty){
            System.out.println("No orders found. Press Enter to continue.");
            Scanner scanner = new Scanner(System.in);
            String m = scanner.nextLine();
        }
    }

    public static void addNewOrder(Account orderer) {
        Food.showFoodMenu();
        if(Food.getNumberOfFoods() == 0){
            //there is no food and database is empty
            System.out.println("No food to order");
            return;
        }
        System.out.println("Enter foodID. If you are done Enter -1");
        while (true) {
            //if the output of this boolean was false, process id canceled
            if(!Order.makeOrder(orderer)){
                System.out.println("Orders: close");
                return;
            }

        }

    }

    public static void showWelcomeMenu(){
        System.out.println("+-----------------------------------------+");
        System.out.println("| Welcome to fast food management system. |");
        System.out.println("|                                         |");
        System.out.println("| 1. continue as guest and see menu       |");
        System.out.println("| 2. login                                |");
        System.out.println("| 3. sign up                              |");
        System.out.println("| 4. EXIT                                 |");
        System.out.println("+-----------------------------------------+");
        System.out.print("\n >> ");
    }

    public static void showAdminsMenu(){

        System.out.println("+-----------------------------------------+");
        System.out.println("|             Admins Menu                 |");
        System.out.println("|                                         |");
        System.out.println("| 1. Charge food                          |");
        System.out.println("| 2. Show list of costumers               |");
        System.out.println("| 3. Show list of admins                  |");
        System.out.println("| 4. Add a new food to the menu           |");
        System.out.println("| 5. Change password                      |");
        System.out.println("| 6. Accept/Reject orders                 |");
        System.out.println("| 7. Show Orders of selecting state       |");
        System.out.println("| 8. Show all Orders                      |");
        System.out.println("| 9. EXIT                                 |");
        System.out.println("+-----------------------------------------+");
        System.out.print("\n >> ");
    }

    public static void showCostumersMenu(){
        System.out.println("+-----------------------------------------+");
        System.out.println("|             Costumers Menu              |");
        System.out.println("|                                         |");
        System.out.println("| 1. Order food                           |");
        System.out.println("| 2. Show all of my orders                |");
        System.out.println("| 3. Show food menu                       |");
        System.out.println("| 4. Change password                      |");
        System.out.println("| 5. EXIT                                 |");
        System.out.println("+-----------------------------------------+");
        System.out.print("\n >> ");
    }

    public static Food designNewFood(){
        java.util.Scanner scanner = new Scanner(System.in);
        System.out.println("Press -1 to cancel the progress.");
        String name;
        Veg type;
        double price;
        int ID;
        //get foods name
        System.out.println("Please Enter foods name.");
        name = scanner.nextLine();
        if (name.equals("-1")) {
            return null;
        }

        //select unique id
        System.out.println("Please select an ID.");
        ID = scanner.nextInt();
        //breaking and cancel
        if (ID == -1) {
            return null;
        }

        int input;
        System.out.println("Is this food a vegetarian food?  [0 for no/ 1 for yes]");
        input = scanner.nextInt();
        if (input == -1) {
            return null;
        }
        if(input == 1){
            //vegetarian is selected
            type = Veg.Vegetarian;
        }
        else{
            //non vegetarian
            type = Veg.Non_Vegetarian;
        }

        System.out.println("Add a cooking time (in seconds)");
        int time = 0;
        time = scanner.nextInt();

        System.out.println("Please set price for each.");
        price = scanner.nextDouble();
        if (price == -1) {
            return null;
        }

        Food f = new Food( );

        return f.addNewFood(name,ID,price,type);
    }

    public static Account showLogin() {
        String username;
        String password;

        String input;
        while (true) {
            java.util.Scanner scanner = new Scanner(System.in);
            System.out.println("Press -1 to cancel the progress.");
            //get usename
            System.out.println("Please Enter your username.");
            input = scanner.nextLine();
            //if -1: do not change password
            if (input.equals("-1")) {
                return null;
            }
            username = input;
            //get password
            System.out.println("Please Enter your password.");
            input = scanner.nextLine();
            if (input.equals("-1")) {
                return null;
            }
            password = input;
            Account temp = new Account(username,password,null);
            //serach for it in database
            return temp.login(username, password);

        }
    }

    public static Account showSignUp(){
        String username;
        String password;

        String input;
        while (true) {
            java.util.Scanner scanner = new Scanner(System.in);
            System.out.println("Press -1 to cancel the progress.");
            //get username
            System.out.println("Please Enter your username.");
            input = scanner.nextLine();
            //if -1 is entered: finish progress
            if (input.equals("-1")) {
                return null;
            }
            username = input;

            //get password
            System.out.println("Please Enter your password.");
            input = scanner.nextLine();
            if (input.equals("-1")) {
                return null;
            }
            password = input;

            //get account type
            System.out.println("Do you want to be the admin or costumer? [a/c]");
            AccountType type;
            input = scanner.nextLine();
            if (input.equals("-1")) {
                return null;
            }
            if(input.equals("a")){
                //chosen option: admin
                type = AccountType.Admin;
            }
            else{
                //chosen option: customer
                type = AccountType.Costumer;
            }
            Account temp = new Account();
            //add temp to database:
            return temp.signUp(username,password,type);
        }
    }

    public static void main(String[] args) {

        //adding people to system
        Account p = new Account("pardis", "1234", AccountType.Admin);
        Account.addAccountToDatabase(p);

        Account p1 = new Account("ali", "4321", AccountType.Costumer);
        Account.addAccountToDatabase(p1);

        Account p3 = new Account("gholam", "9876", AccountType.Admin);
        Account.addAccountToDatabase(p3);

        Account p4 = new Account("mmd", "22", AccountType.Costumer);
        Account.addAccountToDatabase(p4);

        Account p5 = new Account("hasan", "333", AccountType.Admin);
        Account.addAccountToDatabase(p5);

        Account p6 = new Account("reza", "1", AccountType.Costumer);
        Account.addAccountToDatabase(p6);

        //adding foods to the system
        Food f1 = new Food("pizza", 33.4 , 434, Veg.Non_Vegetarian, 1234);
        Food.addToMenu(f1);

        Food f2 = new Food("burger", 113.9 , 989, Veg.Vegetarian, 989898);
        Food.addToMenu(f2);
        f2.setAmount(12);

        Food f3 = new Food("ghorme", 45500.7 , 23222, Veg.Vegetarian, 222);
        Food.addToMenu(f3);
        f3.setAmount(22);

        Food f4 = new Food("gheime", 11.87 , 99, Veg.Non_Vegetarian, 1111);
        Food.addToMenu(f4);
        f4.setAmount(8);

        //reza orders ghorme amount: 3
        Order o1 = new Order(f3,p6);
        o1.setAmount(3);
        Order.addToDatabase(o1);

        //mmd orders burger amount: 1
        Order o2 = new Order(f2,p4);
        o2.setAmount(1);
        Order.addToDatabase(o2);

        //mmd ordered gheime amount: 2
        Order o3 = new Order(f4,p4);
        o3.setAmount(2);
        Order.addToDatabase(o3);

        //mmd ordered pizza amount: 2
        Order o4 = new Order(f1,p4);
        o4.setAmount(2);
        Order.addToDatabase(o4);

        Scanner scanner = new Scanner(System.in);
        int input;

        AccountType currentAccount = AccountType.Guest;

        //main loop:
        while (true){
            Account temp = null;
            showWelcomeMenu();
            input = scanner.nextInt();
            if(input == 4){
                break;
            }
            else if(input == 1){
                Food.showFoodMenu();
            }
            else if(input == 2){
                temp = showLogin();
                if(temp != null){
                    System.out.println("log in action complete");
                    if(temp.getType() == AccountType.Admin){
                        currentAccount = AccountType.Admin;
                     }
                    else{
                        currentAccount = AccountType.Costumer;
                    }
                }
                else
                {
                    System.out.println("\n\nlogin failed.\n\n");
                }
            }
            else if(input == 3){
                temp = showSignUp();
                if(temp != null){
                    //sign up action
                    if(temp.getType() == AccountType.Admin){
                        currentAccount = AccountType.Admin;
                    }
                    else{
                        currentAccount = AccountType.Costumer;
                    }
                }
                else
                {
                    System.out.println("\n\nsign up failed.\n\n");
                }
            }
            else{
                break;
            }
            if((input == 3 || input == 2) && temp != null){
                System.out.println(temp);
                if(temp.getType() == AccountType.Admin){
                    while (true){
                        showAdminsMenu();
                        int input2 = scanner.nextInt();
                        if(input2 == 1){
                            //Charge food
                            Food.chargeFood();
                        }
                        else if(input2 == 2){
                            //show list of costumers
                            Account.showAllCostumers();
                        }
                        else if(input2 == 3){
                            //show list of admins
                            Account.showAllAdmins();
                        }
                        else if(input2 == 4){
                            //add a new food to the menu
                            Food f = designNewFood();
                            if(f != null){
                                System.out.println("New food added!");
                                System.out.println(f);
                            }
                            else{
                                System.out.println("Adding new food canceled.");
                            }
                        }
                        else if(input2 == 5){
                            //change password
                            temp.changeAccountPassword();
                        }
                        else if(input2 == 6){
                            //Accept / Reject orders
                           Order.accept_reject();
                        }
                        else if(input2 == 7){
                            //Order.showOrdersOfState(OrderState.Accepted);
                            System.out.println("+-----------------------------------------+");
                            System.out.println("|         Select orders of type:          |");
                            System.out.println("|                                         |");
                            System.out.println("| a. accepted                             |");
                            System.out.println("| b. rejected                             |");
                            System.out.println("| c. pending                              |");
                            System.out.println("+-----------------------------------------+");
                            System.out.print("\n >> ");
                            Scanner scanner1 = new Scanner(System.in);
                            String in = scanner1.nextLine();
                            if(in.equals("a")){
                                Order.showOrdersOfState(OrderState.Accepted);
                            }
                            else if(in.equals("b")){
                                Order.showOrdersOfState(OrderState.Rejected);

                            }
                            else if(in.equals("c")){
                                Order.showOrdersOfState(OrderState.Pending);
                            }

                        }
                        else if(input2 == 8){
                            Order.showAllOrders();
                        }
                        else{
                            currentAccount = AccountType.Guest;
                            temp = null;
                            break;
                        }
                    }
                }
                else{
                    while (true){
                        showCostumersMenu();
                        int input2 = scanner.nextInt();
                        if(input2 == 1){
                            //order food
                            addNewOrder(temp);
                        }
                        else if(input2 == 2){
                            //show all of my orders
                            showAllOfConsumersOrders(temp);
                        }
                        else if(input2 == 3){
                            //show foods
                            Food.showFoodMenu();
                        }
                        else if(input2 ==4){
                            //change my password
                            temp.changeAccountPassword();
                        }
                        else {
                            currentAccount = AccountType.Guest;
                            temp = null;
                            break;
                        }
                    }

                }
            }
        }

    }
}
