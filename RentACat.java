import java.util.*;
import java.io.*;

public class RentACat {

    public static void main(String[] args){

        List<Cat> cats = new ArrayList<Cat>();
        List<Customer> customers = new ArrayList<Customer>();
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));

        createCats(cats);
        createCustomers(customers);

        int answer = -1;
        String temp;
        while(answer != 4) {
            answer = ask(scanner);

            if(answer == 1) {
                list(cats);
            }
            else if(answer == 2) {
                rentCat(scanner, cats, customers);
            }
            else if(answer == 3) {
                returnCat(scanner, cats);
            }
            else if(answer == 4) {
                exit();
            }
        }
    }

    public static int ask(BufferedReader scanner){
        System.out.print("Options: [1, 2, 3, 4]  >  ");

        int input = -1;

        try{
            String temp = scanner.readLine();
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

    public static void list(List<Cat> cats){

        System.out.println("Cats for rent:");

        for(int i=0; i<3; i++) {
            Cat tempCat = cats.get(i);
            if(!tempCat.isRented()) {
                System.out.println(tempCat.tooString());
            }
        }
    }

    public static boolean rentCat(BufferedReader scanner, List<Cat> cats, List<Customer> customers){

        int id = -1;
        while(id == -1) {
            try{
                System.out.print("\nCustomer id:  ");
                String tempId = scanner.readLine();
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
                            String tempCatId = scanner.readLine();
                            catId = Integer.parseInt(tempCatId);
                            if(catId<1 || catId>3) {
                                catId = -1;
                                System.out.println("Error: invalid cat id");
                            }
                            else{
                                Cat tempCat = cats.get(catId - 1);
                                if(tempCat.isRented()) {
                                    System.out.println("Sorry, "+ tempCat.getName() + " is rented by " + tempCat.getRenter());
                                    return false;
                                }
                                else{
                                    Customer tempCustomer = customers.get(id-1);
                                    tempCat.rentCat(tempCustomer.getName());
                                    System.out.println("You have rented " + tempCat.getName());
                                    return true;
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
        //something weird happened???
        return false;
    }

    public static boolean returnCat (BufferedReader scanner, List<Cat> cats){
        int catId = -1;
        while(catId == -1) {
            System.out.print("\nEnter cat that you want to return:  ");

            try{
                String tempCat = scanner.readLine();
                catId = Integer.parseInt(tempCat);

                if(catId<0 || catId>3) {
                    System.out.println("Error: invalid cat id");
                    catId = -1;
                }
                else{
                    //return the cat
                    Cat cat = cats.get(catId-1);
                    if (cat.isRented()) {
                        System.out.println(cat.returnedToString());
                        cat.returnCat();
                        return true;
                    }
                    else{
                        System.out.println("That cat is not loaned out currently");
                        return false;
                    }
                }
            }
            catch(Exception ex) {
                System.out.println("Error: invalid cat id");
                catId = -1;
            }
        }
        //something weird happened???
        return false;

    }

    public static List<Cat> createCats(List<Cat> cats){
        cats.add(new Cat(1, "Muffins", 10, false));
        cats.add(new Cat(2, "Greg", 15, false));
        cats.add(new Cat(3, "Bean", 20, false));

        return cats;
    }

    public static List<Customer> createCustomers(List<Customer> customers){
        customers.add(new Customer(1, "Bill Gates"));
        customers.add(new Customer(2, "Will Lates"));
        customers.add(new Customer(3, "Gill Yates"));

        return customers;
    }

    public static void exit(){
        System.out.println("Rent A Cat is now closed!");
    }

}
