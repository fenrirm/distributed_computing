import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Changer extends Thread{
    private String currentString;
    private boolean running;
    private final Random random = new Random();
    private int ABCounter;
    private final Checker checker;
    private final CyclicBarrier barrier;
    private final int threadIndex;


    Changer(String string, Checker checker, CyclicBarrier barrier, int threadIndex){
        this.currentString = string;
        this.running = true;
        this.ABCounter = ABCounter(string);
        this.checker = checker;
        this.barrier = barrier;
        this.threadIndex = threadIndex;
    }

    @Override
    public void run() {
        while (running)
        {
            change();
        }
    }

    public void change(){
        int index = random.nextInt(currentString.length());
        switch (currentString.charAt(index)){
            case 'A': {
                currentString = currentString.substring(0, index) + 'C' + currentString.substring(index+1);
                ABCounter--;
            }
            case 'B': {
                currentString = currentString.substring(0, index) + 'D' + currentString.substring(index + 1);
                ABCounter--;
            }
            case 'C': {
                currentString = currentString.substring(0, index) + 'A' + currentString.substring(index + 1);
                ABCounter++;
            }
            case 'D': {
                currentString = currentString.substring(0, index) + 'B' + currentString.substring(index + 1);
                ABCounter++;
            }
        }
        checker.getInfo(ABCounter);
        try {
            barrier.await();
        } catch (BrokenBarrierException | InterruptedException e) {
            e.printStackTrace();
        }

        running = checker.isRunning();
    }

    public int ABCounter(String string){
        int count=0;
        for(int i=0; i<string.length(); i++){
            if(string.charAt(i) == 'A' || string.charAt(i) == 'B'){
                count++;
            }
        }
        return count;

    }

}
