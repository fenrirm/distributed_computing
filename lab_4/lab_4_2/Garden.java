import java.util.Arrays;

public class Garden {
    private int [][] array;

    Garden(int [][] array){
        this.array = array;
    }

    public String get(){
        return Arrays.deepToString(this.array);
    }

    public int [][] getArray(){
        return this.array;
    }




}
