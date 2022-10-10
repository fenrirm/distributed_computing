import java.util.Scanner;

public class Main {
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        Barber barber = new Barber();
        barber.start();
        while (true){
            String name = scanner.nextLine();
            new Customer(barber, name).start();
        }
    }

}
