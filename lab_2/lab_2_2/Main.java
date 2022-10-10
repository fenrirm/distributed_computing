public class Main {

    public static void main(String[] args){
        int [] array = {1, 3, 6, 8, 9};
        Ivanov ivanov = new Ivanov(array);
        Lorry lorry = new Lorry(array);
        Petrov petrov = new Petrov(ivanov, lorry);
        Nechyporuk nechyporuk = new Nechyporuk(lorry);

    }

}
