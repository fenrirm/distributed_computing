package main.java.ua.mykola.lab6.multiplying;

import main.java.ua.mykola.lab6.main.Method;
import main.java.ua.mykola.lab6.main.MyMatrix;


public class CannonsMethodMatrixMultiplying extends Method {
    private MyMatrix a;
    private MyMatrix b;
    private MyMatrix result;
    private  int numberOfThreads;
    public MyMatrix multiply(MyMatrix a, MyMatrix b, int numberOfThreads){
        this.a=a;
        this.b=b;
        result = new MyMatrix(a.getN(), b.getM());
        this.numberOfThreads = numberOfThreads;
        Thread[] tapes = new Thread[numberOfThreads];
        for(int i = 0; i < tapes.length; i++){
            tapes[i] = new Thread(new CannonsPart(i));
        }
        for(int i = 0; i < tapes.length; i++){
            tapes[i].start();
        }
        for(int i = 0; i < tapes.length; i++){
            try {
                tapes[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    private  class CannonsPart implements Runnable{

        private final int part_index;
        public CannonsPart(int part_index){
            this.part_index = part_index;
        }

        @Override
        public void run() {
            int pivot = (int) Math.ceil(a.getM() *1.0/  numberOfThreads);
            for (int row = part_index * pivot; row < (part_index + 1) * pivot && row < a.getN(); row++) {
                int counter = 0;
                int a_j = row;
                int b_i = row;
                while (counter < a.getN()) {
                    for (int i = 0; i < a.getN(); i++) {
                        result.addValue(row, i, a.getValue(row, a_j)*b.getValue(b_i,i));
                    }

                    a_j = (a.getN() + a_j - 1) % a.getN();
                    b_i = (a.getN() + b_i - 1) % a.getN();
                    counter++;
                }
            }
        }
    }

}
