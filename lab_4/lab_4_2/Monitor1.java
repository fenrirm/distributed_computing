import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Monitor1 extends Thread{
    private String filepath;
    private int  [][] array;
    private ReadWriteLock readWriteLock;
    private Lock writeLock;



    Monitor1(String filepath, Garden garden){
        this.filepath = filepath;
        this.array = garden.getArray();
        this.readWriteLock =  new ReentrantReadWriteLock();
        this.writeLock = this.readWriteLock.writeLock();
    }

    public void write() throws IOException {
        File file = new File(filepath);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
        for(int i=0; i<array.length; i++){
            for(int j=0; j<array[i].length; j++){
                if(array[i][j]==0){
                    writer.write("plant" + "["+i+"]["+j+"]"+" is dead"+System.lineSeparator());
                }
                else if(array[i][j]==1){
                    writer.write("plant" + "["+i+"]["+j+"]"+" is weak"+System.lineSeparator());

                }
                else if(array[i][j]==2){
                    writer.write("plant" + "["+i+"]["+j+"]"+" is good"+System.lineSeparator());
                }
                else if(array[i][j]==3){
                    writer.write("plant" + "["+i+"]["+j+"]"+" is excellent"+System.lineSeparator());
                }
            }
        }
        writer.close();
    }

    @Override
    public void run() {
        while (true) {
            writeLock.lock();
            try {
                write();

            } catch (IOException e) {
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
}
