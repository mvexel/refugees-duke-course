package org.rtijn.dukecourse.markov;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Tester {

    public static void main(String[] args) throws IOException {
        Tester tester = new Tester();
        tester.testGetFollowsWithFile();
    }

    public void testGetFollows() {
        MarkovOne mone = new MarkovOne();
        mone.setTraining("this is a test yes this is a test.");
        String[] tests = {"t", "e", "es", ".", "t."};
        for (String test : tests) {
            ArrayList<String> result = new ArrayList<>();
            result = mone.getFollows(test);
            System.out.println(result);
        }
    }

    public void testGetFollowsWithFile() throws IOException {
        String myText = MarkovRunner.readFile("data/melville.txt", StandardCharsets.UTF_8);
        MarkovOne mone = new MarkovOne();
        mone.setTraining(myText);
        ArrayList<String> result = mone.getFollows("th");
        System.out.println(result.size());
    }
}
