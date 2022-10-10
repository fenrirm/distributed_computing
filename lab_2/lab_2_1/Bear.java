public class Bear extends Thread{
    private int x, x2;
    private int y, y2;
    private boolean isAlive;
    Wood wood;

    Bear(Wood wood) {
        this.isAlive = true;
        this.wood = wood;
        x2=y2=1;
        x=y=5;
    }


    public void setNotAlive(){
        isAlive=false;
    }

    public boolean getBearStatus(){
        return isAlive;
    }


    @Override
    public void run() {
        while (getBearStatus()){
            if(wood.checkIfCellIsFree(x, y)){
                System.out.println("bear "+ x + " " + y);
                setNotAlive();
                System.out.println("bear has been found");
                return;
            }
            wood.setOccupied(x,y);
            try {
                Thread.sleep(300);
            }catch (Exception e){
                System.out.println("Exception caught!");
            }
            System.out.println("bear "+ x + " " + y);
            wood.setFree(x,y);
            if (x+x2<0 || x+x2>=wood.getNumberOfColumns())
                x2*=-1;
            if (y+y2<0 || y+y2>=wood.getNumberOfRows()  )
                y2*=-1;
            if (!wood.checkIfCellIsFree(x,y)){
                x+=x2;
                y+=y2;
            }
        }
    }
}
