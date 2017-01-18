package serenade;

public class Serenade {
    
    public static void main(String[] args) {
    	StdDraw.setXscale(-1.0, 1.0);
        StdDraw.setYscale(-1.0, 1.0);
        StdDraw.setPenColor(StdDraw.BLACK); 
    	String notePossibilities = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        Key[] harpS = new Key[notePossibilities.length()];
        for (int i = 0; i < notePossibilities.length(); i++) {
            harpS[i] = new Key(440.0 * Math.pow(2, (i - 24.0) / 12.0));
        }
        while (true) {
        	if (StdDraw.hasNextKeyTyped()) {
        		char key = StdDraw.nextKeyTyped();
        		int keyPlucked = notePossibilities.indexOf(key); 
                if (keyPlucked > -1) { 
                	harpS[keyPlucked].pluck(); 
            	}
            }
        	double sample = 0;
            for (int i = 0; i < notePossibilities.length(); i++) {
                sample += harpS[i].sample();
                if (harpS[i].sample() != 0) { harpS[i].tic(); }
            }
            StdAudio.play(sample);
        }
    }
}

