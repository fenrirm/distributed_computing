public class Lock {

    private boolean readLock;
    private boolean writeLock;

    Lock(){
        this.readLock = false;
        this.writeLock = false;
    }

    public boolean isReadLock(){
        return readLock;
    }

    public boolean isWriteLock(){
        return writeLock;
    }

    public void readLock(){
        readLock=true;
    }

    public void readUnlock(){
        readLock=false;
    }

    public void writeLock(){
        writeLock=true;
    }

    public void writeUnlock(){
        writeLock=false;
    }
}
