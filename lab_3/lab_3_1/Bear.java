public class Bear extends Thread{
    private Semaphore semaphore;
    private Pot pot;

    Bear(Semaphore semaphore,Pot pot){
        this.semaphore = semaphore;
        this.pot = pot;
    }

    @Override
    public void run() {
       while (true){
           if(semaphore.state()){
               System.out.println("bear woke up");
               pot.free();
               semaphore.toFalse();
               break;
           }
           try {
              sleep(50);
           }catch (InterruptedException e){e.printStackTrace();}
           System.out.println("");
       }
    }
}
