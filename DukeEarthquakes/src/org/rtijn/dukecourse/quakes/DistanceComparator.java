package org.rtijn.dukecourse.quakes;

import java.util.Comparator;

/**
 * Write a description of class org.rtijn.dukecourse.quakes.DistanceComparator here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class DistanceComparator implements Comparator<QuakeEntry> {
    Location fromWhere;

    public DistanceComparator(Location where) {
        fromWhere = where;
    }

    public int compare(QuakeEntry q1, QuakeEntry q2) {
        double dist1 = q1.getLocation().distanceTo(fromWhere);
        double dist2 = q2.getLocation().distanceTo(fromWhere);
        return Double.compare(dist1, dist2);
    }

}
 