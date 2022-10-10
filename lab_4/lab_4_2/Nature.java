import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Nature extends Thread {
    private int [][] array;
    private ReadWriteLock readWriteLock;
    private Lock writeLock;


    Nature(Garden garden){
        this.array = garden.getArray();
        this.readWriteLock =  new ReentrantReadWriteLock();
        this.writeLock = this.readWriteLock.writeLock();
    }

    @Override
    public void run() {
        while (true) {
            writeLock.lock();
            try {
                worse();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                better();
            } catch (Exception e) {
                e.printStackTrace();
            }
            writeLock.unlock();
        }

    }

    public void worse(){
        for(int i=0; i<array.length; i++) {
            for (int j=0; j<array[i].length; j++){
                if(array[i][j]==0){
                    array[i][j]=0;
                }
                else {
                    array[i][j] -= 1;
                }
            }
        }
    }

    public void better(){
        for(int i=0; i<array.length; i++) {
            for (int j=0; j<array[i].length; j++){
                if(array[i][j]==3){
                    array[i][j]=3;
                }else {
                    array[i][j] += 1;
                }
            }
        }
    }
}
