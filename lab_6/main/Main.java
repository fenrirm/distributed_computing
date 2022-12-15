package main.java.ua.mykola.lab6.main;

import main.java.ua.mykola.lab6.multiplying.CannonsMethodMatrixMultiplying;
import main.java.ua.mykola.lab6.multiplying.FoxesMethodForMatrixMultiplying;
import main.java.ua.mykola.lab6.multiplying.SequentialMatrixMultiplication;
import main.java.ua.mykola.lab6.multiplying.TapeMatrixMultiplying;

public class Main {
    public static void main(String[] args) {
        MyMatrix a = new MyMatrix(200);
        MyMatrix b = new MyMatrix(200);
        a.setRandomValuesInMatrix(10);
        b.setRandomValuesInMatrix(10);
        Method[] multiplyings = new Method[]{
                new SequentialMatrixMultiplication(),
                new TapeMatrixMultiplying(),
                new FoxesMethodForMatrixMultiplying(),
                new CannonsMethodMatrixMultiplying()
        };
        //System.out.println(a + "\n");
        //System.out.println(b + "\n");
        System.out.println("\n1 THREAD");
        for (Method multiplying : multiplyings) {
            long time = System.currentTimeMillis();
            multiplying.multiply(a,b,1);
            System.out.println( System.currentTimeMillis()-time +" millis");
        }
        System.out.println("\n2 THREAD");
        for (Method multiplying : multiplyings) {
            long time = System.currentTimeMillis();
            multiplying.multiply(a,b,2);
            System.out.println( System.currentTimeMillis()-time +" millis");
        }
        System.out.println("\n4 THREAD");
        for (Method multiplying : multiplyings) {
            long time = System.currentTimeMillis();
            multiplying.multiply(a,b,4);
            System.out.println( System.currentTimeMillis()-time +" millis");
        }
    }

}
