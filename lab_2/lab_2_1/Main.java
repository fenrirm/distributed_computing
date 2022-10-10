
public class Main {

    public static void main(String [] args){
        Wood wood = new Wood(10, 10);
        Bear bear = new Bear(wood);
        MyQueue queue = new MyQueue(10);
        Bee bee1 = new Bee(0,wood, bear,queue);
        bee1.setName("bee1");
        Bee bee2 = new Bee(1, wood, bear, queue);
        bee2.setName("bee2");
        Bee bee3 = new Bee(2, wood, bear, queue);
        bee3.setName("bee3");
        bee1.start();
        bee2.start();
        bee3.start();
        bear.start();

        System.out.println(bear.getBearStatus());
    }
}