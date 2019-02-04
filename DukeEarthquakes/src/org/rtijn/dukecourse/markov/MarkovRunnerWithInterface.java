package org.rtijn.dukecourse.markov;
/**
 * Write a description of class MarkovRunner here.
 *
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.FileResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MarkovRunnerWithInterface {
    public static void main(String[] args) throws IOException {
        MarkovRunnerWithInterface runner = new MarkovRunnerWithInterface();
        runner.runMarkov(621);
//		runner.testHashMap();
//		runner.compareMethods();
    }

    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for (int k = 0; k < 3; k++) {
            String st = markov.getRandomText(size);
            printOut(st);
        }
    }

    public void runMarkov(int seed) {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;

        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, seed);

        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, seed);

        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, seed);

        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, seed);

        EfficientMarkovModel mEff = new EfficientMarkovModel(3);
        runModel(mEff, st, size, seed);

    }

    private void testHashMap() throws IOException {
        String st = MarkovRunner.readFile("data/romeo.txt", StandardCharsets.UTF_8);
        st = st.replace('\n', ' ');
        EfficientMarkovModel model = new EfficientMarkovModel(5);
        model.setTraining(st);
        model.setRandom(615);
        model.printHashMapInfo();
        String result = model.getRandomText(50);
    }

    private void compareMethods() throws IOException {
        String st = MarkovRunner.readFile("data/hawthorne.txt", StandardCharsets.UTF_8);
        st = st.replace('\n', ' ');
        long t1 = System.nanoTime();
        MarkovModel m1 = new MarkovModel(2);
        m1.setRandom(42);
        m1.setTraining(st);
        m1.getRandomText(10000);
        long t2 = System.nanoTime();
        System.out.println("MarkovModel took " + (t2 - t1));

        t1 = System.nanoTime();
        EfficientMarkovModel m2 = new EfficientMarkovModel(2);
        m2.setRandom(42);
        m2.setTraining(st);
        m2.getRandomText(10000);
        t2 = System.nanoTime();
        System.out.println("MarkovModel took " + (t2 - t1));
    }

    private void printOut(String s) {
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for (int k = 0; k < words.length; k++) {
            System.out.print(words[k] + " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }

}
