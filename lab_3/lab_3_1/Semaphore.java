public class Semaphore {
    private boolean state;

    Semaphore(){
        this.state = false;
    }

    public void toTrue(){
        state = true;
    }

    public boolean state(){
        return state;
    }

    public void toFalse(){
        state = false;
    }
}
