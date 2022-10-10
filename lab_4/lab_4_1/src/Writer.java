import java.io.*;

public class Writer implements Runnable {
    private String name;
    private String phoneNumber;
    private Lock lock;
    private Instruction instruction;
    private String filepath;

    Writer(Lock lock,String filepath,Instruction instruction, String name, String phoneNumber) {
        this.lock = lock;
        this.filepath = filepath;
        this.instruction = instruction;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public void addToFile(String name, String phoneNumber) throws IOException {
        File file = new File(filepath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(name + ":" + phoneNumber + System.lineSeparator());
        }

    }

    public void delete(String name, String phoneNumber) throws IOException {

        File tempFile = new File("tempFile.txt");
        File file = new File(filepath);
        String toDelete = name + ":" + phoneNumber;

        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        String currentString;

        while ((currentString = reader.readLine()) != null){
            if(!toDelete.equals(currentString.trim())){
                writer.write(currentString + System.lineSeparator());
            }
        }
        writer.close();
        reader.close();
        file.delete();
        File newFile = new File(filepath);
        tempFile.renameTo(newFile);

    }

    @Override
    public void run() {
        try {
            lock.writeLock();
            switch (instruction){
                case ADD -> {
                    addToFile(name, phoneNumber);
                    break;
                }
                case DELETE -> {
                    delete(name, phoneNumber);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            lock.writeUnlock();
        }
    }


}

