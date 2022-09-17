import java.util.LinkedList;
import java.util.Queue;

public class MyQueue {

    Queue<Integer> listOfSection;
    MyQueue(int numberOfRows) {
        listOfSection=new LinkedList<>();
        for (int i = 0; i < numberOfRows; i++) {
            listOfSection.add(i);
        }
    }

    public int getUncheckedRow(Bee bee) {
        if (bee != null){
            listOfSection.add(bee.getRowToSearch());
        }
        return listOfSection.poll();
    }

}
