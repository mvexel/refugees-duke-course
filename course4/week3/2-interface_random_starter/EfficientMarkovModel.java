
/**
 * Write a description of MarkovFour here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;

public class EfficientMarkovModel extends AbstractMarkovModel {
    private int keyLength;
    private HashMap<String, ArrayList<String>> followers = new HashMap<>();

    public EfficientMarkovModel(int keyLength) {
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

    
    
    public void buildMap(String key) {
        if (followers.containsKey(key)) { 
            return;
        } else {    
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
            followers.put(key, follows);    
        }
        printHashMapInfo();
    }
    
    @Override
    public ArrayList getFollows(String key) {
        buildMap(key);
        return followers.get(key);
    }
    
    public String toString() {
        return "This is an Efficient Markov model of order " + keyLength;
    }
    
    public void printHashMapInfo(){
        if (followers.size() < 10) System.out.println(followers);
        System.out.println("followers has " + followers.size() + " entries");
        int maxsize = 0;
        for (ArrayList<String> f : followers.values()) {
            maxsize = Math.max(maxsize, f.size());
        }
        System.out.println("Largest list of followers is " + maxsize);
    }
}
