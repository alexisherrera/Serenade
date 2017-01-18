package serenade;

public class BufferingQueue {
    private double[] bufferArray; 
    private int first;            
    private int last;             
    private int currentSize;      
    
    //create an empty buffer, with given max capacity
    public BufferingQueue(int capacity) {
        this.bufferArray = new double[capacity];
        this.last  = 0;
        this.first = 0;
    }
    
    //return number of items currently in the buffer
    public int currentSize() {
        return currentSize; 
    }
    
    //is the buffer empty
    public boolean isEmpty() {
        return this.currentSize == 0;
    }
    
    //is the buffer full
    public boolean isFull() {
        return currentSize() == bufferArray.length;
    }
    
    // add item x to the end
    public void enqueue(double x) {
        if (isFull()) {
            throw new RuntimeException("ERROR: Attempting to enqueue to a full buffer.");
        }
        bufferArray[last] = x;
        this.currentSize = currentSize + 1;
        if (last == bufferArray.length - 1) { this.last = 0; }
        else { this.last = 1 + last; }
    }
    
    public double dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("ERROR: Attempting to dequeue from an empty buffer.");
        }
        this.currentSize = currentSize - 1;
        if (first != bufferArray.length - 1) {
            this.first = 1 + first;
            return bufferArray[first - 1];
        }
        else {
            this.first = 0;
            return bufferArray[bufferArray.length - 1];
        }
    }
   
    public double peek() {
        if (isEmpty()) {
            throw new RuntimeException("ERROR: Attempting to peek at an empty buffer.");
        }
        return bufferArray[first]; 
    }
}