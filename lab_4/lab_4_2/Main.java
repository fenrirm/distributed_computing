
public class Main {
    public static void main(String... args) {

        int [][] array = new int[2][2];
        for(int i=0; i<2; i++){
            for(int j=0; j<2; j++){
                array[i][j]=3;
            }
        }
        Garden garden  = new Garden(array);
        Nature nature = new Nature(garden);
        Gardener gardener = new Gardener(garden);
        Monitor1 monitor1 = new Monitor1("GardenStates.txt", garden);
        Monitor2 monitor2 = new Monitor2(garden);
        nature.start();
        gardener.start();
        monitor1.start();
        monitor2.start();



    }
}
