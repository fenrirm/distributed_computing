public class Customer extends Thread{
    Barber barber;

    Customer(Barber barber, String name){
        super(name);
        this.barber = barber;
    }

    public void wakeUpBarber(){
        System.out.println(getName()+" wakes barber up");
        synchronized (barber){
            barber.notify();
        }
    }

    @Override
    public void run() {
        System.out.println(this.getName()+" came to barbershop");
        barber.stayInQueue(this);
        if(barber.isBusy){
            System.out.println(this.getName()+" stays in queue and start sleeping");
            try {
                wait();
            }catch (InterruptedException e){e.printStackTrace();}
        }
        else {
            wakeUpBarber();
        }

    }
}
