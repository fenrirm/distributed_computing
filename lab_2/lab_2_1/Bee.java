public class Bee extends Thread {

    private int rowToSearch;
    private Wood wood;
    private Bear bear;
    private MyQueue queue;
    private int x;

    Bee(int row, Wood wood, Bear bear, MyQueue queue) {
        this.rowToSearch = queue.getUncheckedRow(null);
        this.wood = wood;
        this.bear = bear;
        this.queue = queue;
    }

    public int getRowToSearch(){
        return rowToSearch;
    }


    @Override
    public void run() {
        while (bear.getBearStatus()) {
            for (int j = 0; j < wood.getNumberOfColumns(); j++) {
                x=j;
                if (!bear.getBearStatus() /*|| wood.checkIfCellIsFree(rowToSearch, j)*/) {
                    bear.setNotAlive();
                    return;
                }
                wood.setOccupied(rowToSearch, j);
                System.out.println("bee " + rowToSearch + " " + j);
                System.out.println(this.getName() + " Searching for the bear!");
                try {
                    Thread.sleep(300);
                } catch (Exception e) {
                    System.out.println("Exception caught");
                }
                wood.setFree(rowToSearch, j);
            }
            rowToSearch=queue.getUncheckedRow(this);
        }
    }

}
