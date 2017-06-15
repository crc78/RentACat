import java.util.*;

public class RentACat {

    private static List<Cat> cats = new ArrayList<Cat>();
    private static List<Customer> customers = new ArrayList<Customer>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){

        createCats();
        createCustomers();

        int answer = -1;
        String temp;
        while(answer != 4) {
            answer = ask();

            if(answer == 1) {
                list();
            }
            else if(answer == 2) {
                rentCat();
            }
            else if(answer == 3) {
                //return
            }
            else if(answer == 4) {
                //exit
            }
        }


    }

    public static int ask(){
        System.out.print("Options: [1, 2, 3, 4]  >  ");

        int input = -1;
        String temp = scanner.next();
        try{
            input = Integer.parseInt(temp);
            if(input<1 || input>4) {
                System.out.println("Error: input 1, 2, 3, or 4");
                return -1;
            }
            else{
                return input;
            }
        }
        catch(Exception ex) {
            System.out.println("Error: input 1, 2, 3, or 4");
            return -1;
        }
    }

    public static void list(){
        System.out.println("Cats for rent:");

        for(int i=0; i<3; i++) {
            Cat tempCat = cats.get(i);
            if(!tempCat.isRented()) {
                System.out.println(tempCat.toString());
            }
        }
    }

    public static void rentCat(){

        int id = -1;
        while(id == -1) {
            try{
                System.out.print("\nCustomer id:  ");
                String tempId = scanner.next();
                id = Integer.parseInt(tempId);
                if(id<1 || id> 3) {
                    System.out.println("Error: invalid customer id");
                    id = -1;
                }
                else{
                    //do stuff

                    int catId = -1;
                    while (catId == -1) {
                        try{
                            System.out.print("\nEnter cat you'd like to rent:   ");
                            String tempCatId = scanner.next();
                            catId = Integer.parseInt(tempCatId);
                            if(catId<1 || catId>3) {
                                catId = -1;
                                System.out.println("Error: invalid cat id");
                            }
                            else{
                                Cat tempCat = cats.get(catId - 1);
                                if(tempCat.isRented()) {
                                    System.out.println("Sorry, "+ tempCat.getName() + " is rented by " + tempCat.getRenter());
                                }
                                else{
                                    Customer tempCustomer = customers.get(id-1);
                                    tempCat.rentCat(tempCustomer.getName());
                                    System.out.println("You have rented " + tempCat.getName());
                                }
                            }
                        }
                        catch(Exception ex) {
                            System.out.println("Error: invalid cat id");
                            catId = -1;
                        }
                    }


                }
            }
            catch(Exception ex) {
                id = -1;
                System.out.println("Error: invalid customer id");
            }
        }
    }

    public void returnCat (Cat cat){

    }

    public static void createCats(){
        cats.add(new Cat(1, "Muffins", 10, false));
        cats.add(new Cat(2, "Greg", 15, false));
        cats.add(new Cat(3, "Bean", 20, false));
    }

    public static void createCustomers(){
        customers.add(new Customer(1, "Bill Gates"));
        customers.add(new Customer(2, "Will Lates"));
        customers.add(new Customer(3, "Gill Yates"));
    }

}
