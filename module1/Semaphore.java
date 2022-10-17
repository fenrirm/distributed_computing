public class Semaphore {
    private boolean available;

    Semaphore(){
        this.available = true;
    }

    public void toFalse(){
        available = false;
    }

    public void toTrue(){
        available = true;
    }

    public boolean getStatus(){
        return available;
    }
}
