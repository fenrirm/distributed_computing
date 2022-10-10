import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Gardener extends Thread {
    private int [][] array;
    private ReadWriteLock readWriteLock;
    private Lock writeLock;

    Gardener(Garden garden){
        this.array = garden.getArray();
        this.readWriteLock =  new ReentrantReadWriteLock();
        this.writeLock = this.readWriteLock.writeLock();
    }

    @Override
    public void run() {
        while (true) {
            writeLock.lock();
            try {
                water();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            writeLock.unlock();
        }
    }

    public void water(){
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
