
public class Main {
    public static void main(String... args) {

        Lock lock = new Lock();
        Thread writer = new Thread(new Writer(lock, "Database.txt", Instruction.DELETE,"marta", "111" ));
        //writer.start();
        Thread reader = new Thread(new Reader(lock,"Database.txt",Instruction.FIND_NUMBER_BY_NAME, "jo"));
        reader.start();



    }
}
