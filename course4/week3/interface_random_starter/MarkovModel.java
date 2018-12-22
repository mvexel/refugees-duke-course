
/**
 * Write a description of MarkovFour here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Random;
import java.util.ArrayList;

public class MarkovModel extends AbstractMarkovModel {
    private int keyLength;
    
    public MarkovModel(int keyLength) {
        myRandom = new Random();
        this.keyLength = keyLength;
    }
            
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        // First char, random
        int index = myRandom.nextInt(myText.length() - keyLength);
        String key = myText.substring(index, index + keyLength);
        sb.append(key);
        
        for(int k=0; k < numChars - 1; k++){
            //System.out.println(key);
            //System.out.println("key at start:  " + key);
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) break;
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            //System.out.println("next: " + next);
            key = key.substring(1, keyLength) + next;
            //System.out.println("key at end:  " + key);
        }
        
        return sb.toString();
    }

    public String toString() {
        return "This is a Markov model of order " + keyLength;
    }
}
