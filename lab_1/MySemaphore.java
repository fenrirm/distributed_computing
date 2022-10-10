
public class MySemaphore {
    private int state;

    MySemaphore(){
        state = 0;
    }

    public void release(){
        state = 0;
    }

    public boolean isOccupied(){
        if(state==0){
            return false;
        }
        else{
            return true;
        }
    }

    public void setOccupied() {
        state = 1;

    }


}
