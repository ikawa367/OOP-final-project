import java.util.Scanner;

public class Food {
    private String name;
    private int uniqueID;
    private double price = 0;
    private int cookingTime; //in seconds
    private Veg vegType = null;
    private static Food[] menu = new Food[100]; //restaurant can have up to 100 pizzas
    private static int numberOfFoods = 0;
    private int amount = 0;

    public Food(){}

    public Food(String name, double price, Veg vegType, int ID) {
        this.name = name;
        this.price = price;
        this.vegType = vegType;
        this.uniqueID = ID;
    }

    public Food(String name, double price, int cookingTime, Veg vegType, int ID) {
        this.name = name;
        this.price = price;
        this.cookingTime = cookingTime;
        this.vegType = vegType;
        this.uniqueID = ID;
    }

    public static boolean addToMenu(Food food){
        if (numberOfFoods >= 99) {
            return false;  //not enough space for new account.
        } else {
            menu[numberOfFoods] = food;
            numberOfFoods++;
            return true;
        }
    }

    public Food addNewFood(String name, int uniqueID, double price, Veg type){
        //Food.getFoodByID(uniqueID). there should be no foods with this ID
        if(Food.getFoodByID(uniqueID) != null){
            System.out.println("this ID already exists");
            return null;
        }
        else{
            this.name = name;
            this.uniqueID = uniqueID;
            this.price = price;
            this.vegType = type;
            Food food = new Food(name,price,type, uniqueID);
            Food.addToMenu(food);
            return Food.getFoodByID(uniqueID);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public static int getNumberOfFoods() {
        return numberOfFoods;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public Veg getVegType() {
        return vegType;
    }

    public void setVegType(Veg vegType) {
        this.vegType = vegType;
    }

    public int getUniqueID() {
        return uniqueID;
    }

    public static Food getFoodByID(int ID) {
        for (int i = 0; i < numberOfFoods; i++) {
            if (menu[i].getUniqueID() == ID) {
                //found
                return menu[i];
            }
        }
        return null;
    }

    public static void chargeFood() {
        Food.showFoodMenu();
        if(numberOfFoods == 0){
            System.out.println("no food to charge");
            return;
        }
        System.out.println("choose food: enter ID");
        Scanner scanner = new Scanner(System.in);
        int ID = scanner.nextInt();
        Food temp = Food.getFoodByID(ID);
        if(temp == null){
            System.out.println("Food not found");
            return;
        }
        System.out.println("Enter amount");
        Scanner sc = new Scanner(System.in);
        int amount = sc.nextInt();
        temp.amount += amount;
        System.out.println("Food charged successfully");
    }

    @Override
    public String toString() {
        return "[name: " + name +
                ", price: " + price +
                ", vegType=" + vegType +
                ", amount:" + amount+
                ", ID: " + uniqueID +"]";
    }

    public int getAmount() {
        return amount;
    }

    public void decreaseAmount(int amount){
        this.amount -=amount;
    }

    public static void showFoodMenu(){
        System.out.println("<--------Menu-------->");
        for(int i = 0; i < numberOfFoods; i++){
            System.out.println((i+1) + ".\t " + menu[i]);
        }
        if(numberOfFoods == 0){
            System.out.println("There is no food to show.");
        }
        System.out.println("<-------------------->");
        System.out.println("press Enter to continue");
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
    }

}
