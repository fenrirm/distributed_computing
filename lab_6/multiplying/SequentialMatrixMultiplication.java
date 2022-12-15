package main.java.ua.mykola.lab6.multiplying;

import main.java.ua.mykola.lab6.main.Method;
import main.java.ua.mykola.lab6.main.MyMatrix;


public class SequentialMatrixMultiplication extends Method {
    @Override
    public MyMatrix multiply(MyMatrix a, MyMatrix b, int numberOfThreads) {
        MyMatrix result = new MyMatrix(a.getN(), b.getM());
        for (int i = 0; i < a.getN(); i++) {
            for (int j = 0; j < b.getM(); j++) {
                for (int k = 0; k < b.getN(); k++) {
                    result.addValue(i, j, a.getValue(i, k) * b.getValue(k, j));
                }
            }
        }
        return result;
    }
}
