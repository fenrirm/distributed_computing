public class Petrov implements Runnable{
    private Ivanov ivanov;
    private Lorry lorry;

    Petrov(Ivanov ivanov, Lorry lorry){
        this.ivanov = ivanov;
        this.lorry = lorry;
        Thread t = new Thread(this, "Petrov");
        t.start();
    }
    @Override
    public void run() {
        for (int i=0; i<ivanov.getAmountOfElements(); i++){
            lorry.put(ivanov.get(i));
            try {Thread.sleep(1000);} catch (Exception e){
                System.out.println("Exception caught");
            }
        }
    }
}
