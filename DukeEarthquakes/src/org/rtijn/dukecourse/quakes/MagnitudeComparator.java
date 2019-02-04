package org.rtijn.dukecourse.quakes;

import java.util.Comparator;

/**
 * Write a description of class org.rtijn.dukecourse.quakes.MagnitudeComparator here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class MagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        return Double.compare(qe1.getMagnitude(), qe2.getMagnitude());
    }

}
