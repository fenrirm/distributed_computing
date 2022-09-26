import java.util.LinkedList;
import java.util.Queue;

public class Barber extends Thread{
    public boolean isBusy;
    private Queue<Customer> customers;

    Barber(){
        this.isBusy = false;
        customers = new LinkedList<>();
    }

    public synchronized void stayInQueue(Customer customer){
        customers.add(customer);
    }


    public synchronized void toSleep(){
        try {
            wait();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void doHaircut(Customer customer){
        try {
            System.out.println("start doing haircut for "+customer.getName());
            sleep(1000);
            System.out.println("finish haircut for "+ customer.getName());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        while (true){
            if(customers.isEmpty()){
                isBusy = false;
                System.out.println("barber goes to sleep");
                toSleep();
            }
            System.out.println("barber wakes up");
            isBusy = true;
            doHaircut(customers.poll());
        }

    }



}
