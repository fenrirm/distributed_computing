public class Pot {
    private int capacity;
    private int amountOfHoney;
    private boolean full;


    Pot(int capacity){
        this.capacity = capacity;
        this.amountOfHoney = 0;
        this.full = false;

    }

    public synchronized void addHoney(){
        if(amountOfHoney==capacity){
            full = true;
            return;
        }
        amountOfHoney++;
        System.out.println("honey - "+amountOfHoney+" "+Thread.currentThread().getName());

    }

    public void free(){
        amountOfHoney = 0;
        System.out.println("amount of honey "+ amountOfHoney);
    }

    public synchronized boolean isFull(){
        return full;
    }

}
