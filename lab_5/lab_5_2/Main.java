import java.util.concurrent.CyclicBarrier;

public class Main {

    public static void main(String [] args){

        CyclicBarrier barrier = new CyclicBarrier(4);
        Checker checker = new Checker(4);

        String str1 = "ABCDCDAABCD";
        String str2 = "AAACAACBBAC";
        String str3 = "ACDCADCACDC";
        String str4 = "CDABBABCDAB";

        Changer thread1  = new Changer(str1, checker, barrier, 1);
        Changer thread2  = new Changer(str2, checker, barrier, 2);
        Changer thread3  = new Changer(str3, checker, barrier, 3);
        Changer thread4  = new Changer(str4, checker, barrier, 4);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();


    }
}
