import java.time.LocalTime;
import java.util.Scanner;

import static java.time.LocalTime.now;

public class Order {
    private Account orderer;
    private LocalTime timeOfOrder;
    private Food orderingFood;
    private int amount = 1;
    //generates unique ID for orders automatically. Also is an index.
    private static int orders = 0;
    private OrderState state = OrderState.Pending;
    private static Order[] listOfOrders = new Order[1000];
    private int orderID = 0;

    public Food getOrderingFood() {
        return orderingFood;
    }

    public Account getOrderer() {
        return orderer;
    }

    public void setOrderer(Account orderer) {
        this.orderer = orderer;
    }

    public Order(){}

    public Order(Food orderingFood, Account orderer) {
        this.orderingFood = orderingFood;
        this.timeOfOrder = now();
        this.orderer = orderer;
    }

    public static void addToDatabase(Order order){
        //check for space
        if(orders >= 99){
            System.out.println("Order can not be added: database is full.");
        }
        else{
            listOfOrders[orders] = order;
        }
        order.orderID = orders;
        orders ++;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderer=" + orderer.getUsername() +
                ", timeOfOrder=" + timeOfOrder +
                ", orderingFood=" + orderingFood +
                " *" + amount +
                ", state=" + state +
                ", orderID=" + orderID +
                '}';
    }

    public static Order[] getListOfOrders() {
        return listOfOrders;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public static int getOrders() {
        return orders;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public static boolean makeOrder(Account orderer){
        //Food.showFoodMenu();
        int in;
        System.out.println("please select the food ID.");
        Scanner scanner = new Scanner(System.in);
        in = scanner.nextInt();
        if(in == -1){
            //return null;
            return false;
        }
        Food food = Food.getFoodByID(in);
        System.out.println("Please enter amount: ");
        in = scanner.nextInt();
        if(food == null){
            System.out.println("food not found!");
            //return null;
            return true;
        }
        else{
            Order order = new Order(food, orderer);
            order.setAmount(in);
            Order.addToDatabase(order);
            //return order;
            return true;
        }
    }

    public static Order getOrderByID(int ID){

        if(ID < 0 || ID > getOrders()){
            return null;
        }
        return listOfOrders[ID];
    }

    public void acceptOrder(Order input){
        input.state = OrderState.Accepted;
    }

    public void rejectOrder(Order input){
        input.state = OrderState.Rejected;
    }

    public static void showOrdersOfState(OrderState state){
        boolean exists = false;
        for(int i = 0; i < orders; i++){
            if(listOfOrders[i].state == state){
                exists = true;
//                System.out.print((i+1) + ".\t");
                System.out.println(listOfOrders[i]);
            }
        }
        if(!exists){
            System.out.println("No " + state + " order to show");
        }
    }

    public static void showAllOrders(){
        int i = 0;
        for(i = 0; i < orders; i++){
            System.out.println(getListOfOrders()[i]);
        }
        if(i == 0){
            System.out.println("No order to show.");

        }
        System.out.println("Press Enter to continue");
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
    }

    public static int countOrdersOfType(OrderState state){
        int sum = 0;
        for(int i = 0; i < orders; i++){
            if(getListOfOrders()[i].state == state){
                sum ++;
            }
        }
        return sum;
    }

    public static void accept_reject(){
        Order.showOrdersOfState(OrderState.Pending);
        if(Order.countOrdersOfType(OrderState.Pending) == 0){
            System.out.println("no orders to accept/reject");
            return;
        }
        System.out.println("\nSelect ID of order:");
        Scanner sc = new Scanner(System.in);
        int ID = sc.nextInt();
        Order order = Order.getOrderByID(ID);
        if(order == null){
            System.out.println("order not found");
            return;
        }
        System.out.println("\naccept or reject? [a/r]:");
        Scanner scanner1 = new Scanner(System.in);
        String s = scanner1.nextLine();
        if(s.equals("r")){
            order.setState(OrderState.Rejected);
        }
        else if(order.getOrderingFood().getAmount() < order.getAmount()){
            System.out.println("No enough food");
        }
        else{
            order.getOrderingFood().decreaseAmount(order.getAmount());
            order.setState(OrderState.Accepted);
            System.out.print("order: ");
            System.out.print(order);
            System.out.println(" accepted");
        }
    }

}
