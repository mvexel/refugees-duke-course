
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.ArrayList;
import edu.duke.FileResource;

public class Tester {

    public void testFollows() {
        MarkovOne m1 = new MarkovOne();
        String testString = "this is a test yes this is a test.";
        m1.setTraining(testString);
        String[] testCases = {"t", "e", "s", "es", "t", "t."};
        for (String c : testCases) {
            System.out.println("\n\nTest Case " + c + " =====");
            ArrayList<String> follows = m1.getFollows(c);
            System.out.println(follows + "\n" + follows.size());
        }
    }

    public void testGetFollowsWithFile() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne m1 = new MarkovOne();
        m1.setTraining(st);
        ArrayList<String> follows = m1.getFollows("t");
        System.out.println("Followers of \"t\": " + follows.size());
    }
    
    public void testToString() {
        MarkovZero mz = new MarkovZero();
        System.out.println(mz);
    }
}
