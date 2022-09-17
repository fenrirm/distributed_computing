public class Wood {
    private boolean[][] array;

    Wood(int rows, int columns){
       array = new boolean[rows][columns];
    }


    public int getNumberOfRows(){
        return array.length;
    }

    public int getNumberOfColumns(){
        return array[0].length;
    }

    public boolean checkIfCellIsFree(int x, int y){
        return array[x][y];
    }

    public void setOccupied(int x, int y){
        array[x][y] = true;
    }

    public void setFree(int x, int y){
        array[x][y] = false;
    }

}
