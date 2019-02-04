package org.rtijn.dukecourse.markov;

import java.util.ArrayList;
import java.util.Random;

/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */

public abstract class AbstractMarkovModel implements IMarkovModel {
    String myText;
    Random myRandom;

    AbstractMarkovModel() {
        myRandom = new Random();
    }

    public void setTraining(String s) {
        myText = s.trim();
    }

    protected ArrayList<String> getFollows(String key) {
        ArrayList<String> result = new ArrayList<>();
        int idx = 0;
        while (idx < myText.length()) {
            // get index of next follower
            idx = myText.indexOf(key, idx);
            if (idx == -1 || idx == myText.length() - key.length()) {
                break;
            }
            String next = myText.substring(idx + key.length(), idx + key.length() + 1);
            result.add(next);
            idx = idx + key.length();
        }
        return result;
    }

    abstract public String getRandomText(int numChars);

}
