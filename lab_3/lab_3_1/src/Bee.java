public class Bee extends Thread {
    Pot pot;
    Semaphore semaphore;

    Bee(Pot pot, Semaphore semaphore){
        this.pot = pot;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        while (true){
            if(pot.isFull()){
                semaphore.toTrue();
                break;
            }
            pot.addHoney();

        }
    }
}
