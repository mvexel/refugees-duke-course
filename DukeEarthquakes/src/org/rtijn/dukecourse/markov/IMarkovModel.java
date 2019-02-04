package org.rtijn.dukecourse.markov;

/**
 * Write a description of interface IMarkovModel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public interface IMarkovModel {
    void setTraining(String text);

    void setRandom(int seed);

    String getRandomText(int numChars);

}
