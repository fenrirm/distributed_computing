import java.io.*;

public class Reader implements Runnable{
    String key;
    private Lock lock;
    private Instruction instruction;
    private String result;
    private String filepath;

    Reader(Lock lock, String filepath, Instruction instruction, String key){
        this.result = null;
        this.lock = lock;
        this.filepath = filepath;
        this.instruction = instruction;
        this.key = key;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            lock.readLock();
            String stringToFind;
            while ((stringToFind=reader.readLine()) != null){
                int separator  = stringToFind.indexOf(':');
                //System.out.println(separator);
                String name  = stringToFind.substring(0,separator);
                String phoneNumber = stringToFind.substring(separator+1);
                if(isThereRecord(name, phoneNumber)){
                    lock.readUnlock();
                    //System.out.println("there is a record");
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            lock.readUnlock();
        }


    }

    private boolean isThereRecord(String name, String number) {
        switch (instruction){
            case FIND_NAME_BY_NUMBER: {
                if(number.equals(key)) {
                    result = name;
                    System.out.println(name);
                    return true;
                }
                break;
            }
            case FIND_NUMBER_BY_NAME: {
                if(name.equals(key)) {
                    result = number;
                    System.out.println(number);
                    return true;
                }
                break;
            }
        }
        return false;
    }
}

