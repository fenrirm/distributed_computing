import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Monitor2 extends Thread{

    private int  [][] array;
    private ReadWriteLock readWriteLock;
    private Lock readLock;


    Monitor2(Garden garden){
        this.array = garden.getArray();
        this.readWriteLock =  new ReentrantReadWriteLock();
        this.readLock = this.readWriteLock.readLock();
    }

    public void show() {
        for(int i=0; i<array.length; i++){
            for(int j=0; j<array[i].length; j++){
                if(array[i][j]==0){
                    System.out.println("plant" + "["+i+"]["+j+"]"+" is dead");
                }
                else if(array[i][j]==1){
                    System.out.println("plant" + "["+i+"]["+j+"]"+" is weak");
                }
                else if(array[i][j]==2){
                    System.out.println("plant" + "["+i+"]["+j+"]"+" is good");
                }
                else if(array[i][j]==3){
                    System.out.println("plant" + "["+i+"]["+j+"]"+" is excellent");
                }
            }
        }

    }

    @Override
    public void run() {
        while (true) {
            readLock.lock();
            try {
                show();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            readLock.unlock();
        }
    }
}
