package org.rtijn.dukecourse.markov;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Write a description of class MarkovZero here.
 *
 * @author Duke Software
 * @version 1.0
 */


public class EfficientMarkovModel extends AbstractMarkovModel implements IMarkovModel {
    private int order;
    private HashMap<String, ArrayList<String>> followersMap;

    public EfficientMarkovModel(int order) {
        myRandom = new Random();
        this.order = order;
    }


    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }


    public void setTraining(String s) {
        myText = s.trim();
        buildMap();
    }

    private void buildMap() {
        followersMap = new HashMap<>();
        for (int i = 0; i <= this.myText.length() - order; i++) {
            String key = myText.substring(i, i + order);
            if (followersMap.containsKey(key)) {
                continue;
            }
            ArrayList<String> result = new ArrayList<>();
            int idx = 0;
            while (idx < myText.length()) {
                idx = myText.indexOf(key, idx);
                if (idx == -1 || idx == myText.length() - key.length()) {
                    break;
                }
                String next = myText.substring(idx + key.length(), idx + key.length() + 1);
                result.add(next);
                idx = idx + key.length();
            }
            followersMap.put(key, result);
        }
    }

    @Override
    protected ArrayList<String> getFollows(String key) {
        return followersMap.get(key);
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
            if (followers == null || followers.size() == 0) {
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

    public void printHashMapInfo() {
        int largest = 0;
        int size = followersMap.size();
        ArrayList<String> largestKeys = new ArrayList<>();
        for (String key : followersMap.keySet()) {
            largest = Math.max(largest, followersMap.get(key).size());
            if (size < 10) {
                System.out.println(key + followersMap.get(key));
            }
        }

        for (String key : followersMap.keySet()) {
            if (followersMap.get(key).size() == largest) {
                largestKeys.add(key);
            }
        }

        System.out.println("Number of keys: " + followersMap.size());
        System.out.println("Largest follower size: " + largest);
        System.out.println("Keys that have this size: " + largestKeys);
    }

    @Override
    public String toString() {
        return "Markov Model of order " + order;
    }


}
