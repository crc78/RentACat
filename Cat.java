public class Cat {

    private int id;
    private String name;
    private int price;
    private boolean rented;
    private String renter;

    public Cat(int i, String n, int p, boolean r){
        this.id = i;
        this.name = n;
        this.price = p;
        this.rented = r;
    }

    public boolean getRented(){
        return this.rented;
    }

    public String getName(){
        return this.name;
    }

    public void rentCat(String r){
        this.rented = true;
        this.renter = r;
    }

    public String getRenter(){
        return this.renter;
    }

    public int getPrice(){
        return this.price;
    }

    public void returnCat(){
        this.rented = false;
        this.renter = "";
    }

    public boolean isRented(){
        return this.rented;
    }

    public String tooString(){
        return this.id + ". " + this.name + " $" + this.price;
    }

    public String returnedToString(){
        return this.renter + " returned " + this.name + " and paid $" + this.price;
    }

}
