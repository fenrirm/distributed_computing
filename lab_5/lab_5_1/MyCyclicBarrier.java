import java.util.concurrent.BrokenBarrierException;

public class MyCyclicBarrier {
    private int amountOfThreads;
    private int leftThreads;
    private Runnable barrierEvent;
    private boolean isBroken;
    private int resets;

    MyCyclicBarrier(int amountOfThreads, Runnable barrierEvent){
        this.amountOfThreads = amountOfThreads;
        this.leftThreads = amountOfThreads;
        this.barrierEvent = barrierEvent;
        this.isBroken = false;
    }

    MyCyclicBarrier(int amountOfThreads){
        this.amountOfThreads = amountOfThreads;
        this.leftThreads = amountOfThreads;
        this.barrierEvent = null;
    }

    public synchronized void await() throws BrokenBarrierException, InterruptedException {
        if(this.isBroken){
            throw new BrokenBarrierException();
        }
        --this.amountOfThreads;
        int currentResets = resets;

        while (this.amountOfThreads > 0) {
            try{
                this.wait();
            }catch (InterruptedException e){
                this.isBroken = true;
                throw e;
            }
        }

        if(resets != currentResets) return;

        this.leftThreads = this.amountOfThreads;
        notifyAll();
        if(this.barrierEvent != null){
            this.barrierEvent.run();
        }
    }

    public void reset(){
        --this.resets;
        this.leftThreads = this.amountOfThreads;
        this.isBroken = false;
    }


}
