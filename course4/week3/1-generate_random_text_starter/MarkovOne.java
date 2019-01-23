
/**
 * Write a description of MarkovOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Random;
import java.util.ArrayList;

public class MarkovOne {
    private String myText;
    private Random myRandom;
    
    public MarkovOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
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
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        // First char, random
        int index = myRandom.nextInt(myText.length() - 1);
        String key = myText.substring(index, index + 1  );
        sb.append(key);
        
        for(int k=0; k < numChars - 1; k++){
//            System.out.println(key);
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) break;
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = next;
        }
        
        return sb.toString();
    }

}
