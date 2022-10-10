public class Main {
    public static void main(String [] args){
        Semaphore semaphore = new Semaphore();
        Pot pot = new Pot(1000);
        Thread bee = new Thread(new Bee(pot, semaphore), "bee1");
        Thread bee2 = new Thread(new Bee(pot, semaphore), "bee2");
        Thread bee3 = new Thread(new Bee(pot, semaphore), "bee3");
        Thread bee4 = new Thread(new Bee(pot, semaphore), "bee4");
        Thread bear = new Thread(new Thread(new Bear(semaphore, pot)));
        bee.start();
        bee2.start();
        bee3.start();
        bee4.start();
        bear.start();
    }
}
