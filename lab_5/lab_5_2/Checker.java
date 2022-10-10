import java.util.Arrays;

public class Checker {
    private boolean running;
    private int currentNumOfThreads;
    private final int totalNumOfThreads;
    private final int[] threadsInfo;
    private boolean arrived;

    Checker(int numberOfThreads){
        this.running = true;
        this.arrived = false;
        this.currentNumOfThreads = 0;
        this.totalNumOfThreads = numberOfThreads;
        this.threadsInfo = new int[numberOfThreads];
    }

    public void equalityCheck(){
        boolean isEqual = false;
        Arrays.sort(threadsInfo);
        for(int i=1; i<threadsInfo.length-2; i++){
            if(threadsInfo[i]!=threadsInfo[i+1]){
                isEqual = true;
                break;
            }
        }
        if(isEqual){
            if(threadsInfo[0]==threadsInfo[1] || threadsInfo[1]==threadsInfo[threadsInfo.length-1]){
                running = false;
                System.out.println("equal");
            }
        }
    }

    public boolean isRunning(){
        return running;
    }


    public synchronized void getInfo(int data){
        threadsInfo[currentNumOfThreads] = data;
        currentNumOfThreads++;
        if(currentNumOfThreads==totalNumOfThreads){
            notifyAll();
            arrived = true;
        }
        while(!arrived){
            try {
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        currentNumOfThreads--;
        if(currentNumOfThreads==0){
            equalityCheck();
            arrived = false;
        }


    }

}
