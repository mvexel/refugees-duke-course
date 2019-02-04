package org.rtijn.dukecourse.markov;

import java.util.ArrayList;
import java.util.Random;

/**
 * Write a description of class MarkovZero here.
 *
 * @author Duke Software
 * @version 1.0
 */


public class MarkovModel extends AbstractMarkovModel implements IMarkovModel {
    private int order;

    public MarkovModel(int order) {
        myRandom = new Random();
        this.order = order;
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
        int index = myRandom.nextInt(myText.length() - order);
        String key = myText.substring(index, index + order);
        sb.append(key);
        for (int k = 0; k < numChars - order; k++) {
            // get followers for key
            ArrayList<String> followers = this.getFollows(key);
            if (followers.size() == 0) {
                // break if none found
                break;
            }
            // select random item from follower list
            index = myRandom.nextInt(followers.size());
            String selectedFollower = followers.get(index);
            // and append it to the stringbuilder
            sb.append(selectedFollower);
            key = key.substring(1) + selectedFollower;
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Markov Model of order " + order;
    }


}
