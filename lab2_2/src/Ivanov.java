public class Ivanov {
    private int [] elements;

    Ivanov(int [] array){
        this.elements=array;
    }

    public int get(int i){
        return elements[i];
    }

    public int getAmountOfElements(){
        return elements.length;
    }
}
