public class Lorry {
    private int [] lorry;
    private int amount;
    private boolean valueSet;

    Lorry(int [] array){
        this.lorry = new int[array.length];
        this.amount = 0;
        this.valueSet=false;
    }
    public synchronized void put(int element){
        while (valueSet){
            try {wait();} catch (Exception e){System.out.println("Exception caught");}
        }
        System.out.println("Petrov put an element to the lorry -"+element);
        lorry[amount] = element;
        amount++;
        valueSet=true;
        notify();
    }
    public synchronized int get(int index){
        while (!valueSet){
            try {wait();} catch (Exception e){System.out.println("Exception caught");}
        }
        System.out.println("Nechyporuk got an element -" + lorry[index]);
        valueSet=false;
        notify();
        return lorry[index];
    }
    public int getLorryLength(){
        return lorry.length;
    }
}
