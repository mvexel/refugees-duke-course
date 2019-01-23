
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }

    public void setRandom (int seed) {
        myRandom.setSeed(seed);
    }
    
    public ArrayList<String> getFollows(String key) {
        int pos = 0;
        ArrayList<String> follows = new ArrayList();
        while (true) {
            int index = myText.indexOf(key, pos);
            if (index > -1 && index < myText.length() - key.length()) {
                follows.add(myText.substring(index + key.length(), index + key.length() + 1));
                pos = index + 1;
            } else {
                break;
            }
        }
        return follows;
    }
    
    
    abstract public String getRandomText(int numChars);

}
