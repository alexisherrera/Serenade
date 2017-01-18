package serenade;

import java.awt.Color;

public class Key {
    private BufferingQueue buffer; 
    static int SAMP_RATE = 44100; 
    static double ENERGY_DECAY_FACTOR = -0.997; 
    private int numSamples; 
    private int counter = 0;
    public Key(double frequency) {
        this.numSamples = (int) Math.ceil(SAMP_RATE / frequency);
        this.buffer = new BufferingQueue(numSamples);
        for (int i = 0; i < numSamples; i++) { this.buffer.enqueue(0); }
    }
    
    //pluck the harp string by making it go wild, bending at random
    public void pluck() {
        for (int j = 0; j < numSamples; j++) {
            buffer.dequeue();
            buffer.enqueue(Math.random() - 0.5);
        }
        float r = (float) Math.random();
        float g = (float) Math.random();
        float b = (float) Math.random();
        Color randomColor = new Color(r,g,b);
        StdDraw.setPenColor(randomColor);
    }
    
    //advance the simulation one time step using Karplus-strong calculation
    public void tic() {
        // pause for 20 ms
    	
        double firstSamp = buffer.dequeue();
        double secondSamp = buffer.peek();
        double addSamp = firstSamp + secondSamp;
        double avgSamp = addSamp / 2.0;
        double avgKarpStrong = avgSamp * ENERGY_DECAY_FACTOR;
        buffer.enqueue(avgKarpStrong);
        if (StdDraw.chaosMode) {
        	if (buffer.peek() > 0.01) {
            	if (counter == 200) {
                	StdDraw.line(Math.random(), Math.random(), Math.random(), Math.random());
                	counter = 0;
                }
            	 counter++;
            }
        }
        if (StdDraw.wavesMode) {
        	if (counter == 200) {
        		
        	}
        }
        
    }
    
    // return the current sample
    public double sample() {
        return buffer.peek(); 
    }
}