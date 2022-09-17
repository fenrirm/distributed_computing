public class Nechyporuk implements Runnable {
    private Lorry lorry;
    private int totalValue;

    Nechyporuk(Lorry lorry){
       this.lorry = lorry;
       this.totalValue = 0;
       Thread t = new Thread(this, "Nechyporuk");
       t.start();
    }

    @Override
    public void run() {
        for(int i=0; i<lorry.getLorryLength(); i++){
            totalValue = totalValue + lorry.get(i);
            try {
                Thread.sleep(1000);} catch (Exception e){System.out.println("Exception caught");
            }
        }
        System.out.println("Nechyporuk counted that total value of all staff, " +
                "that they had stolen is - "+ totalValue);
    }
}
