package main.java.ua.mykola.lab6.main;

public class MyMatrix {
    private static final int MAX_VALUE = 11;
    private int n;
    private int m;
    private int[][] matrix;
    public MyMatrix(){
        this(5);
    }
    public MyMatrix(int n){
        this(n, n);
    }
    public MyMatrix(int n, int m){
        this.n=n;
        this.m=m;
        matrix=new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j]=0;
            }
        }
    }

    public void setRandomValuesInMatrix(int maxValue){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j]=(int)(Math.random()*maxValue);
            }
        }
    }

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }

    public void setValue(int i, int j, int value) {
        matrix[i][j]=value;
    }
    public int getValue(int i, int j) {
        return matrix[i][j];
    }

    public void addValue(int i, int j, int value) {
        matrix[i][j]+=value;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                res.append(matrix[i][j]+"\t ");
            }
            res.append("\n");
        }
        return res.toString();
    }

}
