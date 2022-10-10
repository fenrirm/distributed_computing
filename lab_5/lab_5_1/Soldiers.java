import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.atomic.AtomicBoolean;

public class Soldiers implements Runnable {
    private static final ArrayList <Boolean> partFinished = new ArrayList<>();
    private static final AtomicBoolean finished = new AtomicBoolean(false);
    private final Turn[] soldier;
    private final int groupNumber;
    private final int indexOfLeft;
    private final int indexOfRight;
    private final MyCyclicBarrier myCyclicBarrier;

    static {
        for(int i = 0; i< Main.partsNumber; i++){
            partFinished.add(false);
        }
    }

    public Soldiers(Turn[] soldier, int groupNumber, int indexOfLeft, int indexOfRight, MyCyclicBarrier myCyclicBarrier) {
        this.soldier = soldier;
        this.groupNumber = groupNumber;
        this.indexOfLeft = indexOfLeft;
        this.indexOfRight = indexOfRight;
        this.myCyclicBarrier = myCyclicBarrier;

    }

    @Override
    public void run() {
        while(!finished.get()){
            boolean currentFinished = partFinished.get(groupNumber);
            if(!currentFinished){
                System.out.println(Arrays.toString(soldier));
                boolean turnedCorrectly = true;
                for(int i=indexOfLeft; i<indexOfRight-1; i++){
                    if(soldier[i] != soldier[i+1]){
                        soldier[i] = Turn.values()[(soldier[i].ordinal()+1)%2];
                        turnedCorrectly = false;
                    }
                }
                if (turnedCorrectly){
                    checkIsFinished();
                }
            }
            try {
                myCyclicBarrier.await();

            } catch (BrokenBarrierException | InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void checkIsFinished() {
        partFinished.set(groupNumber, true);
        for (boolean currentPartFinished : partFinished) {
            if (!currentPartFinished) {
                return;
            }
        }
        finished.set(true);
    }
}
