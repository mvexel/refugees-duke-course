package org.rtijn.dukecourse.markov;

import java.util.ArrayList;
import java.util.Random;

/**
 * Write a description of class MarkovZero here.
 *
 * @author Duke Software
 * @version 1.0
 */

public class MarkovOne extends AbstractMarkovModel implements IMarkovModel {
    public MarkovOne() {
        myRandom = new Random();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String s) {
        myText = s.trim();
    }

    public String getRandomText(int numChars) {
        if (myText == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - 1);
        String key = Character.toString(myText.charAt(index));
        sb.append(key);
        for (int k = 0; k < numChars - 1; k++) {
            ArrayList<String> followers = this.getFollows(key);
            if (followers.size() == 0) {
                break;
            }
            index = myRandom.nextInt(followers.size());
            String selectedFollower = followers.get(index);
            sb.append(selectedFollower);
            key = selectedFollower;
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Markov Model of order 1";
    }

}
